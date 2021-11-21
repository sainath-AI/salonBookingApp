package com.masai.sainath.salonbookingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class UserAdapter1(private val list: List<AdminModel>,private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<User1viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): User1viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_users1, parent, false)
        return User1viewholder(view,itemClickListener)
    }

    override fun onBindViewHolder(holder: User1viewholder, position: Int) {
        val mallItem = list[position]
        holder.setData(mallItem)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}