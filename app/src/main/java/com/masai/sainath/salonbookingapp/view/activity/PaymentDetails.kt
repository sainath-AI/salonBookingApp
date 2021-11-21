package com.masai.sainath.salonbookingapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.bumptech.glide.Glide
import com.masai.sainath.salonbookingapp.R
import kotlinx.android.synthetic.main.activity_payment_details.*

class PaymentDetails : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)
        btnPayment.setOnClickListener {
            paymentCard.visibility= View.INVISIBLE
            cartAnimation.visibility=View.VISIBLE
            cartAnimation.playAnimation()
            Handler().postDelayed({
                intent= Intent(this,UserActivtiy::class.java)
                startActivity(intent)
                finish()
            },3000)
        }


    }
}