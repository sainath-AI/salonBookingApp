package com.masai.sainath.salonbookingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(private val requireContext: Context, private val listBestofTheMoth : ArrayList<AdminModel>) :
    RecyclerView.Adapter<UserAdapter.BomViewHolder>() {



    inner class BomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //lateinit var foldingCell: FoldingCell
        val imageview  = itemView.findViewById<ImageView>(R.id.ImgSalon)
        val salonname=itemView.findViewById<TextView>(R.id.tvSalon)
        val barbername=itemView.findViewById<TextView>(R.id.tvName)
        val location=itemView.findViewById<TextView>(R.id.tvLocation)
        //val  foldingCell1=itemView.findViewById<FoldingCell>(R.id.folding_cell)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BomViewHolder {

        return BomViewHolder(LayoutInflater.from(requireContext).inflate(R.layout.item_users, parent, false))

    }

    override fun onBindViewHolder(holder: BomViewHolder, position: Int) {
        holder.salonname.text=listBestofTheMoth[position].salonname
        holder.barbername.text=listBestofTheMoth[position].barbarname
        holder.location.text=listBestofTheMoth[position].location
        Glide.with(requireContext).load(listBestofTheMoth[position].imgurl).into(holder.imageview)
//        holder.foldingCell1.setOnClickListener {
//            foldingcell.toggle(false)
//        }


    }

    override fun getItemCount(): Int {
        return listBestofTheMoth.size
    }

}