package com.masai.sainath.salonbookingapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.salonbookingapp.R
import com.masai.sainath.salonbookingapp.model.AdminModel
import kotlinx.android.synthetic.main.activity_admin.*


class AdminActivity : AppCompatActivity() {
    lateinit var database: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        viewSalons.setOnClickListener {
            startActivity(Intent(this, ListOfSalons::class.java))
            finish()
        }


        btnAdd.setOnClickListener {
            if (etSalonName.text.toString().isEmpty()
                && etBarberName.text.toString().isEmpty() &&
                etImgUrl.text.toString().isEmpty() &&
                etLocation.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Paste link pls", Toast.LENGTH_SHORT).show()

            } else {
                addLinkToDatabase(
                    etSalonName.text.toString(),
                    etBarberName.text.toString(),
                    etImgUrl.text.toString(),
                    etLocation.text.toString()
                )

            }
        }


    }

    private fun addLinkToDatabase(salon: String, barber: String, imgurl: String, location: String) {
        val uid = database.collection("Admindata").document().id
        val finalData = AdminModel(
            id = uid,
            imgurl = imgurl,
            salonname = salon,
            barbarname = barber,
            location = location
        )
        database.collection("Admindata").document(uid).set(finalData).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show()
                etImgUrl.setText("")
                etLocation.setText("")
                etBarberName.setText("")
                etSalonName.setText("")
                etImgUrl.clearFocus()
                etLocation.clearFocus()
                etBarberName.clearFocus()
                etSalonName.clearFocus()

            } else {
                Toast.makeText(this, "" + it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                etImgUrl.setText("")
                etLocation.setText("")
                etBarberName.setText("")
                etSalonName.setText("")
                etImgUrl.clearFocus()
                etLocation.clearFocus()
                etBarberName.clearFocus()
                etSalonName.clearFocus()
            }
        }
    }
}