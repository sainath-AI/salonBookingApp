package com.masai.sainath.salonbookingapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.salonbookingapp.databinding.ItemCategoryBinding

class SlotAdapter(val requireContext: Context, val listBestofTheMoth: ArrayList<SlotModel>,private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<SlotAdapter.BomViewHolder>() {

    val db = FirebaseFirestore.getInstance()



    inner class BomViewHolder(val binding:ItemCategoryBinding,private val itemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {

        val time = itemView.findViewById<TextView>(R.id.slots)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BomViewHolder {
        return BomViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false),itemClickListener)

    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: BomViewHolder, position: Int) {
          holder.time.text=listBestofTheMoth[position].time
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClicked(mallItem = AdminModel(imgurl = "https://i.pinimg.com/736x/df/b9/dc/dfb9dceb23a8c4aa3ade189fe423beae.jpg", salonname = "Retrofitz", barbarname = "Jose Zuniga", location = "Indira Nagar"))
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
//           val intent=Intent(requireContext,PaymentDetails::class.java)
//               requireContext.startActivity(intent)
            },2000)

        }
    }

    override fun getItemCount(): Int {
        return listBestofTheMoth.size
    }

}