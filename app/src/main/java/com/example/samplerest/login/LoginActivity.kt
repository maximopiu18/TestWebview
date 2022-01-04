package com.example.samplerest.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.samplerest.R
import com.example.samplerest.login.activity.WebViewActivity
import com.example.samplerest.login.api.retrofit.RetrofitClient
import com.example.samplerest.login.factory.LoginViewModelFactory
import com.example.samplerest.login.repository.LoginRepository
import com.example.samplerest.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(), LoginViewModel.callBackLogin {


    lateinit var viewModel: LoginViewModel
    var  retrofitClient = RetrofitClient.getRetrofitLoginService()
    lateinit var edUser: EditText
    lateinit var edPass: EditText
    lateinit var btnLogin: Button
    lateinit var user : String
    lateinit var pass : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edUser = findViewById(R.id.edUser)
        edPass = findViewById(R.id.edPass)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
           //initViewModel()
            openWebView()
        }

    }

    override fun onSuccess() {

        openWebView()

    }
    override fun onError(menssage: String) {
        Log.e("Error", "Error: $menssage" )
    }

    fun openWebView(){

        user = edUser.text.toString()
        pass = edPass.text.toString()

        if(user.isNullOrEmpty() || pass.isNullOrEmpty()){
            Toast.makeText(this, "ingresa los campos", Toast.LENGTH_SHORT).show()
        }


        Log.e("onSuccess" , "el login se hizo correctamente o si existe el usuario")
        var intent  =Intent(this, WebViewActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("pass", pass)
        startActivity(intent)
        finish()
    }

     fun initViewModel(){
         user = edUser.text.toString()
         pass = edPass.text.toString()

         if(user.isNullOrEmpty() || pass.isNullOrEmpty()){
             Toast.makeText(this, "ingresa los campos", Toast.LENGTH_SHORT).show()
         }
         else{
             viewModel = ViewModelProvider(this, LoginViewModelFactory(LoginRepository(retrofitClient), this))
                 .get(LoginViewModel::class.java)
             viewModel.initLogin(user)
         }

     }
}