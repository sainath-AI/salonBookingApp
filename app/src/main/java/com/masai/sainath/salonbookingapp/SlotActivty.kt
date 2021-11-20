package com.masai.sainath.salonbookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.salonbookingapp.databinding.ActivitySlotActivtyBinding

class SlotActivty : AppCompatActivity() {

    lateinit var binding: ActivitySlotActivtyBinding
    lateinit var database: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySlotActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseFirestore.getInstance()

        database.collection("slots").addSnapshotListener { value, error ->
            val  listBestofTheMoth = arrayListOf<SlotModel>()

            val data=value?.toObjects(SlotModel::class.java)
            listBestofTheMoth.addAll(data!!)

            binding.revSlot.layoutManager=
                GridLayoutManager(this    ,3)
            binding.revSlot.adapter= SlotAdapter(this,listBestofTheMoth)

        }


    }
}