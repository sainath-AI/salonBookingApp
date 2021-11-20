package com.masai.sainath.salonbookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.salonbookingapp.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {

    lateinit var binding: ActivityAdminBinding
    lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseFirestore.getInstance()

        database.collection("Admindata").addSnapshotListener { value, error ->
            val listOfCategories = arrayListOf<AdminModel>()
            val data = value?.toObjects(AdminModel::class.java)
            listOfCategories.addAll(data!!)
            binding.revBom.layoutManager = LinearLayoutManager(this)
            binding.revBom.adapter = AdminAdapter(this, listOfCategories)

        }
        binding.btnAdd.setOnClickListener {
            if (binding.etSalonName.text.toString().isEmpty()
                && binding.etBarberName.text.toString().isEmpty()&&
                binding.etImgUrl.text.toString().isEmpty()&&
                binding.etLocation.text.toString().isEmpty()) {
                Toast.makeText(this, "Paste link pls", Toast.LENGTH_SHORT).show()

            } else {
                addLinkToDatabase(
                    binding.etSalonName.text.toString(),
                    binding.etBarberName.text.toString(),
                    binding.etImgUrl.text.toString(),
                    binding.etLocation.text.toString()
                )

            }
        }


    }

    private fun addLinkToDatabase(salon: String, barber: String, imgurl: String, location: String) {
        val uid= database.collection("Admindata").document().id
        val finalData = AdminModel(id = uid ,imgurl = imgurl, salonname = salon, barbarname = barber,location=location)
        database.collection("Admindata").document(uid).set(finalData).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show()
                binding.etImgUrl.setText("")
                binding.etLocation.setText("")
                binding.etBarberName.setText("")
                binding.etSalonName.setText("")
                binding.etImgUrl.clearFocus()
                binding.etLocation.clearFocus()
                binding.etBarberName.clearFocus()
                binding.etSalonName.clearFocus()

            } else {
                Toast.makeText(this, "" + it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                binding.etImgUrl.setText("")
                binding.etLocation.setText("")
                binding.etBarberName.setText("")
                binding.etSalonName.setText("")
                binding.etImgUrl.clearFocus()
                binding.etLocation.clearFocus()
                binding.etBarberName.clearFocus()
                binding.etSalonName.clearFocus()
            }
        }
    }
}