package com.example.fininfocomproject

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class My_Adapter(val itemList:ArrayList<ItemModel>):RecyclerView.Adapter<My_Adapter.MyViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): My_Adapter.MyViewHolder {
                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_layout,parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: My_Adapter.MyViewHolder, position: Int) {
                    holder.age.text=(itemList[position].age).toString()
                    holder.name.text =  (itemList[position].name)
                    holder.city.text = (itemList[position].city)
        //Log.i("neeraj" ,"dasd")
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

  inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView ){
        var name:TextView
        var age: TextView
        var city: TextView

        init {
            name = itemView.findViewById(R.id.name)
            age = itemView.findViewById(R.id.age)
            city = itemView.findViewById(R.id.city)
            Log.i("neeraj" ,"dasd")
        }
  }
}