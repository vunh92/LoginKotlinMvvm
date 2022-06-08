package com.vunh.login_kotlin_mvvm.repository.login

import com.vunh.login_kotlin_mvvm.model.Account
import com.vunh.login_kotlin_mvvm.usecase.UseCaseResult

interface LoginRepository {
    suspend fun getUser(username: String, password: String): UseCaseResult<Account>
}