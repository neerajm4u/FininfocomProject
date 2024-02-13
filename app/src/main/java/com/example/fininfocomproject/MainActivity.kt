package com.example.fininfocomproject

import android.R.attr.name
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var registerButton:Button
    lateinit var loginIdText:EditText
    lateinit var loginPassText:EditText
    lateinit var loginClickListener:View.OnClickListener
    lateinit var registerClickListener:View.OnClickListener
    lateinit var loginManager:LoginManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val b = findViewById<Button>(R.id.button)
//        b.setOnClickListener{
//            val i = Intent(this, AppView::class.java)
//            startActivity(i)
//        }
        loginButton = findViewById<Button>(R.id.login)
        registerButton = findViewById(R.id.register)
        loginIdText = findViewById(R.id.userId)
        loginPassText = findViewById(R.id.pass)
        loginManager = LoginManager(this)

        var loginID = loginIdText.text
        var loginPass = loginPassText.text
        Log.i("Neeraj ID" , loginID.toString())
        Log.i("Neeraj pass" , loginPass.toString())

          loginClickListener = View.OnClickListener {
            Log.i("Neeraj ID" , loginIdText.getText().toString())
            Log.i("Neeraj pass" , loginPassText.getText().toString())
            if(loginManager.verifyValidInput(loginIdText.getText().toString().trim() , loginPassText.getText().toString().trim()))
                loginManager.loginAuthorization( loginIdText.getText().toString().trim() ,  loginPassText.getText().toString().trim())
            loginIdText.setText("")
            loginPassText.setText("")
        }

        registerClickListener = View.OnClickListener {
            if(loginManager.verifyValidInput(loginIdText.getText().toString().trim() , loginPassText.getText().toString().trim()))
                loginManager.registerNewUser( loginIdText.getText().toString().trim() ,  loginPassText.getText().toString().trim())
            loginIdText.setText("")
            loginPassText.setText("") }

        loginButton.setOnClickListener (loginClickListener)

        registerButton.setOnClickListener(registerClickListener)
    }

    override fun onResume() {
        loginButton.setOnClickListener (loginClickListener)
        registerButton.setOnClickListener(registerClickListener)
        super.onResume()
    }

    override fun onStop() {
        loginButton.setOnClickListener(null)
        registerButton.setOnClickListener(null)
        loginIdText.setText("")
        loginPassText.setText("")
        super.onStop()
    }



    fun clearCredentialInputCache(){
        Log.i("Neeraj" , "clearing credentials")


    }
}