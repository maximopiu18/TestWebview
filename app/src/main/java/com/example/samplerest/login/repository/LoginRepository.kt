package com.example.samplerest.login.repository

import com.example.samplerest.login.api.LoginApiService
import com.example.samplerest.login.model.LoginResponse
import io.reactivex.Single

class LoginRepository(
    private val loginApiService: LoginApiService) {

    fun getLoginService( idClient :String): Single<LoginResponse?> =
        loginApiService.getLogin(idClient)
}