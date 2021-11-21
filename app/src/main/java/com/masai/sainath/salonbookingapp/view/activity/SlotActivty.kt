package com.masai.sainath.salonbookingapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.salonbookingapp.R
import com.masai.sainath.salonbookingapp.view.adapter.SlotAdapter
import com.masai.sainath.salonbookingapp.model.SlotModel
import com.masai.sainath.salonbookingapp.model.AdminModel
import com.masai.sainath.salonbookingapp.view.interfaces.OnItemClickListener
import kotlinx.android.synthetic.main.activity_slot_activty.*

class SlotActivty : AppCompatActivity(), OnItemClickListener {


    lateinit var database: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slot_activty)

        database = FirebaseFirestore.getInstance()

        val imgurl = intent.getStringExtra("imgurl")
        val salonname = intent.getStringExtra("salonname")
        val barbername = intent.getStringExtra("barbername")
        val location = intent.getStringExtra("location")


        database.collection("slots").addSnapshotListener { value, error ->
            val listBestofTheMoth = arrayListOf<SlotModel>()

            val data = value?.toObjects(SlotModel::class.java)
            listBestofTheMoth.addAll(data!!)

            revSlot.layoutManager = GridLayoutManager(this, 3)
            revSlot.adapter = SlotAdapter(this, listBestofTheMoth, this)

        }


    }

    override fun onItemClicked(mallItem: AdminModel) {
        val intent = Intent(this, PaymentDetails::class.java)
        intent.putExtra("imgurl", mallItem.imgurl)
        startActivity(intent)

    }

    override fun onDirectionsClicked() {
    }

}