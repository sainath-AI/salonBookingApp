package com.masai.sainath.salonbookingapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masai.sainath.salonbookingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Admin.setOnClickListener {

            startActivity(Intent(this@MainActivity, AdminActivity::class.java))

        }
        binding.user.setOnClickListener {

            startActivity(Intent(this@MainActivity, UserActivtiy::class.java))

        }


    }
}