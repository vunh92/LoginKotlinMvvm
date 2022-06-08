package com.vunh.login_kotlin_mvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vunh.login_kotlin_mvvm.repository.login.LoginRepositoryImpl

class LoginViewModelFactory(
    private val loginRepositoryImp: LoginRepositoryImpl
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(loginRepositoryImp) as T
        }else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}