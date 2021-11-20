package com.masai.sainath.salonbookingapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.salonbookingapp.view.adapter.AdminAdapter
import com.masai.sainath.salonbookingapp.model.AdminModel
import com.masai.sainath.salonbookingapp.databinding.ActivityListOfSalonsBinding

class ListOfSalons : AppCompatActivity() {

    lateinit var database: FirebaseFirestore
    lateinit var binding: ActivityListOfSalonsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListOfSalonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseFirestore.getInstance()

        database.collection("Admindata").addSnapshotListener { value, error ->
            val listOfCategories = arrayListOf<AdminModel>()
            val data = value?.toObjects(AdminModel::class.java)
            listOfCategories.addAll(data!!)
            binding.revBom.layoutManager = LinearLayoutManager(this)
            binding.revBom.adapter = AdminAdapter(this, listOfCategories)

        }
    }
}