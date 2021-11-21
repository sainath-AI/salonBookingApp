package com.masai.sainath.salonbookingapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.masai.sainath.salonbookingapp.R
import kotlinx.android.synthetic.main.activity_payment_details.*

class PaymentDetails : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)

        val imgurl = intent.getStringExtra("imgurl")
        btnPayment.setOnClickListener {

        }

    }
}