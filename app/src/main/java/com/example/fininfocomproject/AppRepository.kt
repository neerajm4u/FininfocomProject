package com.example.fininfocomproject

import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AppRepository{

    lateinit var database: DatabaseReference


     var dbList:ArrayList<ItemModel> = ArrayList()
    init {
        database = Firebase.database.reference
        val cred = CredentialModel("Fininfocom","Fin@123")
        database.child("credentials").child("Fininfocom").setValue("Fin@123")





            database.child("users").get().addOnSuccessListener {

                 Log.i("Neeraj" , it.getValue<MutableList<ItemModel>>().toString() )
                var _dbList:ArrayList<ItemModel> =  it.getValue<ArrayList<ItemModel>>()!!
                Log.i("Neeraj" , _dbList.size.toString())
                _dbList.removeAt(0)
                dbList.addAll(_dbList)
                Log.i("Neeraj" , dbList.toString())


                    //Toast.makeText(applicationContext,""+it.value, Toast.LENGTH_SHORT).show()



        }

    }



}

