package com.masai.sainath.salonbookingapp.view.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.salonbookingapp.model.AdminModel
import com.masai.sainath.salonbookingapp.R

class AdminAdapter(
    private val requireContext: Context,
    private val listBestofTheMoth: ArrayList<AdminModel>
) :
    RecyclerView.Adapter<AdminAdapter.BomViewHolder>() {

    private val db = FirebaseFirestore.getInstance()

    inner class BomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageview = itemView.findViewById<ImageView>(R.id.SalonImg)
        val salonname = itemView.findViewById<TextView>(R.id.SalonName)
        val btndelete = itemView.findViewById<ImageView>(R.id.btnDeletetct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BomViewHolder {
        return BomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_admindetails, parent, false)
        )

    }

    override fun onBindViewHolder(holder: BomViewHolder, position: Int) {
        holder.salonname.text = listBestofTheMoth[position].salonname


        Glide.with(requireContext).load(listBestofTheMoth[position].imgurl).into(holder.imageview)
        holder.btndelete.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext)
            dialog.setMessage("are you sure want to delete this item")
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, _ ->
                db.collection("Admindata").document(listBestofTheMoth[position].id).delete()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                requireContext,
                                "deleted Successfully",
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
                dialog.dismiss()

            })
            dialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
            dialog.show()
        }


    }

    override fun getItemCount(): Int {
        return listBestofTheMoth.size
    }

}