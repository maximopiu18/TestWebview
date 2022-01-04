package com.example.samplerest.login.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.samplerest.login.repository.LoginRepository
import com.example.samplerest.login.viewmodel.LoginViewModel

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val repository: LoginRepository, private val callback : LoginViewModel.callBackLogin) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(this.repository, callback) as T
        } else throw IllegalArgumentException("No se encontró la clase")
    }
}
