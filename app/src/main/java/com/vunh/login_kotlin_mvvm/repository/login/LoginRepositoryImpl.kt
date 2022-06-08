package com.vunh.login_kotlin_mvvm.repository.login

import com.vunh.login_kotlin_mvvm.api.LoginService
import com.vunh.login_kotlin_mvvm.model.Account
import com.vunh.login_kotlin_mvvm.usecase.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepositoryImpl(private val loginService: LoginService) : LoginRepository {
    override suspend fun getUser(username: String, password: String): UseCaseResult<Account> {
        return try {
            val result =
                withContext(Dispatchers.IO) { loginService.callLoginAsync(username, password).await() }
            UseCaseResult.Success(result)
        } catch (ex: Throwable) {
            UseCaseResult.Error(ex.message ?: "")
        }
    }
}