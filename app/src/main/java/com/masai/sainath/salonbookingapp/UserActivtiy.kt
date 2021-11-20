package com.masai.sainath.salonbookingapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.salonbookingapp.databinding.ActivityUserActivtiyBinding

class UserActivtiy : AppCompatActivity() ,OnItemClickListener{

    lateinit var binding: ActivityUserActivtiyBinding
    lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserActivtiyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database= FirebaseFirestore.getInstance()

        database.collection("Admindata").addSnapshotListener { value, error ->
            val listOfCategories = arrayListOf<AdminModel>()
            val data = value?.toObjects(AdminModel::class.java)
            listOfCategories.addAll(data!!)
            binding.revUser.layoutManager = LinearLayoutManager(this)
            binding.revUser.adapter = UserAdapter1(listOfCategories,this)

        }


    }

    override fun onItemClicked(mallItem: AdminModel) {
        TODO("Not yet implemented")
    }

    override fun onDirectionsClicked() {
        val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode("Whatsapp Salon,Bengalore"))

        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (this.let {
                mapIntent.resolveActivity(it.packageManager) } != null) {
            startActivity(mapIntent)
        }
    }
}