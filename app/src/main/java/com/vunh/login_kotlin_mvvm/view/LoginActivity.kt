package com.vunh.login_kotlin_mvvm.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.vunh.login_kotlin_mvvm.MainActivity
import com.vunh.login_kotlin_mvvm.api.LoginService
import com.vunh.login_kotlin_mvvm.databinding.ActivityLoginBinding
import com.vunh.login_kotlin_mvvm.repository.login.LoginRepositoryImpl
import com.vunh.login_kotlin_mvvm.utils.AppUtils.afterTextChanged
import com.vunh.login_kotlin_mvvm.utils.AppUtils.isEnable
import com.vunh.login_kotlin_mvvm.utils.AppUtils.validateEmail
import com.vunh.login_kotlin_mvvm.viewModel.LoginViewModel
import com.vunh.login_kotlin_mvvm.viewModel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {
//    private val viewModel by viewModels<LoginViewModel>()
    lateinit var viewModel: LoginViewModel
    lateinit var viewModelFactory: LoginViewModelFactory
    private val loginService = LoginService.getInstance()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginRepositoryImpl: LoginRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginRepositoryImpl = LoginRepositoryImpl(loginService)
        viewModelFactory = LoginViewModelFactory(loginRepositoryImpl)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        initializeView()
        initializeViewModel()
    }

    private fun initializeView(){
        binding.btnLogin.setOnClickListener {
            if(viewModel.checkValidate(binding.edtEmail.text.toString(),binding.edtPassword.text.toString())){
                viewModel.getUser(username = "nguyenvanc", password = "123456")
            }

        }
        binding.edtEmail.afterTextChanged{
            binding.btnLogin.isEnable(this,it.validateEmail())
        }
    }

    private fun initializeViewModel(){
        viewModel.userResult.observe(this, Observer {
            binding.lblEmailAnswer.text = it.username
            Snackbar.make(binding.root, "Success", Snackbar.LENGTH_LONG).show()
            startActivity(newTaskIntent(this@LoginActivity))
        })
        viewModel.showLoading.observe(this, Observer {
            if (it) binding.progressLoading.visibility =
                View.VISIBLE else binding.progressLoading.visibility = View.GONE
        })
        viewModel.showError.observe(this, Observer {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        })

    }



    companion object {
        fun newTaskIntent(context: Context): Intent {
            //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return Intent(context, MainActivity::class.java)
        }
    }

}