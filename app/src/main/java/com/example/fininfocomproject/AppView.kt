package com.example.fininfocomproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AppView : AppCompatActivity() {


    lateinit var itemLists: ArrayList<ItemModel>
    lateinit var adapter:My_Adapter
    lateinit var repository:AppRepository
    lateinit var appViewModel : AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_view)
        repository = AppRepository()
        Toast.makeText(this , "Please connect to internet for Firebase!" , Toast.LENGTH_SHORT).show()
        Log.i("neeraj" , "in appview")

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)



        repository.database.child("users").get().addOnSuccessListener {

            Log.i("Neeraj" , it.getValue<MutableList<ItemModel>>().toString() )
            var _dbList:ArrayList<ItemModel> =  it.getValue<ArrayList<ItemModel>>()!!
            Log.i("Neeraj" , _dbList.size.toString())
            _dbList.removeAt(0)
            repository.dbList.addAll(_dbList)
            Log.i("Neeraj" , repository.dbList.toString())
            adapter = My_Adapter(repository.dbList)
            recyclerView.adapter = adapter

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.app_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.byName-> repository.dbList.sortBy{it.name}
            R.id.byAge->  repository.dbList.sortBy { it.age }
            R.id.byCity-> repository.dbList.sortBy { it.city }
        }
            // Log.i("neeraj" , itemLists.toString())
            adapter.notifyDataSetChanged()

        return super.onOptionsItemSelected(item)
    }


}