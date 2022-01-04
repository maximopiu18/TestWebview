package com.example.samplerest.login.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.samplerest.R
import android.webkit.WebSettings
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.samplerest.login.api.retrofit.RetrofitClient
import com.example.samplerest.login.factory.LoginViewModelFactory
import com.example.samplerest.login.repository.LoginRepository
import com.example.samplerest.login.viewmodel.LoginViewModel


class WebViewActivity : AppCompatActivity(), LoginViewModel.callBackLogin{

    lateinit var viewModel: LoginViewModel
    var  retrofitClient = RetrofitClient.getRetrofitLoginService()
    lateinit var webView : WebView
    lateinit var btnCheck : Button
    lateinit var user : String
    lateinit var pass : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        checkoutArguments()
        btnCheck = findViewById(R.id.btnConsultar)
        btnCheck.setOnClickListener {
            initViewModel()
        }
    }


    fun checkoutArguments(){
        if(intent.hasExtra("user") && intent.hasExtra("pass")){
            // iniciar pagina web
            user = intent.getStringExtra("user").toString()
            pass = intent.getStringExtra("pass").toString()
            initWebView()
        }
        else{
            Log.e("error", "No se encontraron los putExtra")
        }
    }
    fun initWebView(){
        webView = findViewById(R.id.myWebView)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.loadUrl("https://192.168.101.4:8080")
    }


    fun initViewModel(){


        if(user.isNullOrEmpty() || pass.isNullOrEmpty()){
            Toast.makeText(this, "ingresa los campos", Toast.LENGTH_SHORT).show()
        }
        else{
            viewModel = ViewModelProvider(this, LoginViewModelFactory(LoginRepository(retrofitClient), this))
                .get(LoginViewModel::class.java)
            viewModel.initLogin(user)
        }

    }

    override fun onSuccess() {

        Toast.makeText(this, "el usuario existe!", Toast.LENGTH_SHORT).show()
    }

    override fun onError(menssage: String) {
        Log.e("error", "error $menssage")
    }
}