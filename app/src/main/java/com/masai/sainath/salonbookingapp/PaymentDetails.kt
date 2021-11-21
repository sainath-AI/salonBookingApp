package com.masai.sainath.salonbookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_payment_details.*

class PaymentDetails : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)

        val imgurl =intent.getStringExtra("imgurl")

        Glide.with(this).load(imgurl).into(image11)

    }
}