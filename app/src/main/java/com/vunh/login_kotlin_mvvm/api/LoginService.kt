package com.vunh.login_kotlin_mvvm.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vunh.login_kotlin_mvvm.model.Account
import com.vunh.login_kotlin_mvvm.utils.AppUtils
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST("pda/account/login")
    fun callLoginAsync(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Deferred<Account>

    companion object {
        var loginService: LoginService? = null

        fun getInstance() : LoginService {
            if (loginService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(AppUtils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()

                loginService = retrofit.create(LoginService::class.java)
            }
            return loginService!!
        }
    }
}