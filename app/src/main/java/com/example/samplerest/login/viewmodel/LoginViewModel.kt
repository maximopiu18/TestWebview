package com.example.samplerest.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.samplerest.login.repository.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class
LoginViewModel(private val repository: LoginRepository?, private val  callback: callBackLogin?) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun initLogin(idCliente: String) {
        compositeDisposable.add(
            repository!!.getLoginService(idCliente)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.e("response", "response" + response!!.toString())
                    callback!!.onSuccess()
                }, { throwable ->
                    Log.e("error", "error: $throwable")
                    callback!!.onError(throwable.toString())
                })
        )
    }
    interface callBackLogin{
        fun onSuccess()
        fun onError(menssage : String)
    }
}
