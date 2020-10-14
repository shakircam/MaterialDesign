package com.example.materialdesign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class CustomAdapter(private val userList: ArrayList<User>, private val listener : ItemListener): Adapter<CustomAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view= LayoutInflater.from(parent.context).inflate(R.layout.user_data,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position],listener)
    }



    override fun getItemCount(): Int {
        return userList.size
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: User, listener: ItemListener) {
            val nameTextView = itemView.findViewById(R.id.nameTx) as TextView
            val cityTextView = itemView.findViewById(R.id.cityTx) as TextView
            val phoneTextView = itemView.findViewById(R.id.phoneTx) as TextView

            nameTextView.text = user.name
            cityTextView.text = user.city
            phoneTextView.text = user.phone
            nameTextView.setOnClickListener {
                listener.onClicked(user.name)
            }


        }

    }
}