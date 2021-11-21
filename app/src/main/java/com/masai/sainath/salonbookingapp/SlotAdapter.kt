package com.masai.sainath.salonbookingapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.salonbookingapp.databinding.ItemCategoryBinding
import kotlinx.android.synthetic.main.item_category.view.*

class SlotAdapter(val requireContext: Context, val listBestofTheMoth: ArrayList<SlotModel>) :
    RecyclerView.Adapter<SlotAdapter.BomViewHolder>() {

    val db = FirebaseFirestore.getInstance()


    inner class BomViewHolder(val binding:ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        val time = itemView.findViewById<TextView>(R.id.slots)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BomViewHolder {
        return BomViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: BomViewHolder, position: Int) {
          holder.time.text=listBestofTheMoth[position].time
        holder.itemView.setOnClickListener {
            holder.binding.colorChange.setBackgroundResource(R.drawable.gradient3)
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                db.collection("slots").document(listBestofTheMoth[position].id).delete()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                requireContext,
                                "Slots Confirmed!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                requireContext,
                                "" + it.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
           val intent=Intent(requireContext,PaymentDetails::class.java)
               requireContext.startActivity(intent)
            },2000)

        }
    }

    override fun getItemCount(): Int {
        return listBestofTheMoth.size
    }

}