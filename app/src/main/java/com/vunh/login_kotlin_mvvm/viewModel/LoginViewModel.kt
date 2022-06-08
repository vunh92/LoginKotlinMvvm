package com.vunh.login_kotlin_mvvm.viewModel

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vunh.login_kotlin_mvvm.model.Account
import com.vunh.login_kotlin_mvvm.repository.login.LoginRepositoryImpl
import com.vunh.login_kotlin_mvvm.usecase.UseCaseResult
import com.vunh.login_kotlin_mvvm.utils.AppUtils.validateEmail
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
    private val loginRepositoryImp: LoginRepositoryImpl
    ) : ViewModel() , CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()
    val userResult= MutableLiveData<Account>()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    init {
//        this.user = User("nguyenvanc","123456")
    }

    fun getUser(username: String, password: String){
        showLoading.value = true
        launch {
            var result = withContext(Dispatchers.IO){
                loginRepositoryImp.getUser(username, password)
            }
            showLoading.value= false
            when (result) {
                is UseCaseResult.Success -> {
                    userResult.value = result.data
                }
                is UseCaseResult.Error -> {
                    showError.value = result.errorMessage

                }
            }
        }
    }

    fun checkValidate(email:String,password:String):Boolean{
        if(email.isEmpty()){
            showError.value = "Email is empty"
            return false
        }
        if(!email.validateEmail()){
            showError.value = "Email incorrect type"
            return false
        }
        if(password.isEmpty()){
            showError.value = "Password less 6 charater"
            return false
        }
        return true

    }
}