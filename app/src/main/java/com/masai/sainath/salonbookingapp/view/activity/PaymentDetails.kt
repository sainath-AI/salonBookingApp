package com.masai.sainath.salonbookingapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.bumptech.glide.Glide
import com.masai.sainath.salonbookingapp.R
import kotlinx.android.synthetic.main.activity_payment_details.*
import kotlinx.android.synthetic.main.activity_saloon_details.*

class PaymentDetails : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)


        Glide.with(saloonImage).load(intent.getStringExtra("imgurl")).into(saloonImage)
        saloonName.text = intent.getStringExtra("salonname")
        barberName.text = intent.getStringExtra("barbername")
        address.text = intent.getStringExtra("location")
        tvService.text = intent.getStringExtra("servicename")
        payPrice.text = "Rs ${intent.getStringExtra("price")}"
        btnPayment.setOnClickListener {
            paymentCard.visibility = View.INVISIBLE
            cartAnimation.visibility = View.VISIBLE
            cartAnimation.playAnimation()
            Handler().postDelayed({
                intent = Intent(this, UserActivtiy::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }


    }
}