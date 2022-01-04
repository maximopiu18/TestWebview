package com.example.samplerest.login.api

import com.example.samplerest.login.model.LoginResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface LoginApiService {

    // meter  la itnerface sea GET O POST PUT
    @Headers("Content-Type: application/json")
    @GET("/api/users/{idClient}")
    fun getLogin(@Path("idClient") idClient: String): Single<LoginResponse?>
}