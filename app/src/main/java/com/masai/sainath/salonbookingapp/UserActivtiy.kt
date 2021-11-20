package com.masai.sainath.salonbookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.salonbookingapp.databinding.ActivityUserActivtiyBinding

class UserActivtiy : AppCompatActivity() {

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
            binding.revUser.adapter = UserAdapter(this, listOfCategories)

        }


    }
}