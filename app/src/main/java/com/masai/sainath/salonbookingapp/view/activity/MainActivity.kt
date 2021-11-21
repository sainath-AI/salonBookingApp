package com.masai.sainath.salonbookingapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masai.sainath.salonbookingapp.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Admin.setOnClickListener {

            startActivity(Intent(this@MainActivity, AdminActivity::class.java))

        }
        user.setOnClickListener {

            startActivity(Intent(this@MainActivity, UserActivtiy::class.java))

        }


    }
}