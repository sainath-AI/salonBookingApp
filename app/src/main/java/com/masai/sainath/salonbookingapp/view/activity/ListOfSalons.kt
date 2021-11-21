package com.masai.sainath.salonbookingapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.salonbookingapp.R
import com.masai.sainath.salonbookingapp.view.adapter.AdminAdapter
import com.masai.sainath.salonbookingapp.model.AdminModel
import kotlinx.android.synthetic.main.activity_list_of_salons.*

class ListOfSalons : AppCompatActivity() {

    lateinit var database: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_salons)

        database = FirebaseFirestore.getInstance()

        database.collection("Admindata").addSnapshotListener { value, error ->
            val listOfCategories = arrayListOf<AdminModel>()
            val data = value?.toObjects(AdminModel::class.java)
            listOfCategories.addAll(data!!)
            revBom.layoutManager = LinearLayoutManager(this)
            revBom.adapter = AdminAdapter(this, listOfCategories)

        }
    }
}