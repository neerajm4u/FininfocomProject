package com.example.fininfocomproject

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import java.util.regex.Pattern

class LoginManager (context:Context){
    private var ctx = context
    fun loginAuthorization(id:String ,pass:String):Boolean{
                if(id=="Fininfocom" && pass == "Fin@123"){
                    startApp()
        //            loginIdText.setText("")
        //            loginPassText.setText("")
                    return true
                }

                //val sh = getSharedPreferences("Credentials", MODE_PRIVATE)
                val sh = PreferenceManager.getDefaultSharedPreferences(ctx)
                val s1 = sh.getString(id, "def")
                Log.i("Neeraj in login" , s1.toString())
                if(s1.equals("def") || !s1.equals(pass) ){
                    Log.i("Neeraj in login" , id.toString()+"- pass:"+s1.toString())
                    Toast.makeText(ctx,"No such ID exists!!", Toast.LENGTH_LONG).show()
        //            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        //            val myEdit = sharedPreferences.edit()
        //
        //            myEdit.remove(id )
        //            myEdit.apply()
        //            loginIdText.setText("")
        //            loginPassText.setText("")
                    return false
                }else if (s1.equals(pass)) {
                    startApp()
                }
        //        loginIdText.setText("")
        //        loginPassText.setText("")
                return true
    }

    fun  registerNewUser(id:String , pass:String):Boolean{
                if(id=="Fininfocom" && pass == "Fin@123"){
                    Toast.makeText(ctx,"Already Registered. Please Login!!", Toast.LENGTH_LONG).show()
        //            loginIdText.setText("")
        //            loginPassText.setText("")
                    return false
                }
                //val sharedPreferences = getSharedPreferences("Credentials", MODE_PRIVATE)
                val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx)

                val s1 = sharedPreferences.getString(id, "def")
                if (s1 != null) {
                    Log.i("Neeraj in regis" , id +"- pass:"+s1.toString() + " " + s1.length.toString())
                }
        //        if(!s1.equals("def")   || s1 != null){
        //            Toast.makeText(this,"Already Registered. Please Login!!",Toast.LENGTH_LONG).show()
        //            loginIdText.setText("")
        //            loginPassText.setText("")
        //            return false
        //        }
                val myEdit = sharedPreferences.edit()

                myEdit.putString(id, pass)
                myEdit.commit()
                Toast.makeText(ctx,"Registered Successfully!!", Toast.LENGTH_LONG).show()

                startApp()
        //        loginIdText.setText("")
        //        loginPassText.setText("")
                return true;

    }
    fun verifyValidInput  (id:String ,pass:String): Boolean  {
                if(id.length<10 ){
        //            loginIdText.setText("")
        //            loginPassText.setText("")
                    Toast.makeText(ctx, "Please Enter Minimum 10 character!!", Toast.LENGTH_LONG).show()
                    return false
                }

                val passwordREGEX = Pattern.compile("^" +
                        "(?=.*[0-9])" +         //at least 1 digit
                        "(?=.*[a-z])" +         //at least 1 lower case letter
                        "(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +      //any letter
                        "(?=.*[@#$%^&+=])" +    //at least 1 special character
                        //"(?=\\S+$)" +           //no white spaces
                        ".{6,}" +               //at least 7 characters
                        "$")
                if(!passwordREGEX.matcher(pass).matches() || pass.length!=7) {


                    Toast.makeText(ctx,"Password must be 7 Characters with" +
                            " 1UpperCase Alphabet and 1SpecialCharacter and Numeric!!", Toast.LENGTH_LONG).show()

        //            loginIdText.setText("")
        //            loginPassText.setText("")
                    return false

                }


                return true
    }

    fun startApp(){
               // clearCredentialInputCache()
        //        loginPassText.text=""
        //        loginPassText.text = ""
                val intent = Intent(ctx , AppView::class.java)
                ctx.startActivity(intent)
            }


}