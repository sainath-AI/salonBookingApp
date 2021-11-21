package com.masai.sainath.salonbookingapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.masai.sainath.salonbookingapp.R
import com.masai.sainath.salonbookingapp.model.ServiceModel
import com.masai.sainath.salonbookingapp.view.adapter.ServiceAdapter
import com.masai.sainath.salonbookingapp.view.interfaces.ServiceSelector
import kotlinx.android.synthetic.main.activity_saloon_details.*

class SaloonDetailsActivity : AppCompatActivity(), ServiceSelector {

    private lateinit var serviceAdapter: ServiceAdapter
    private val services = mutableListOf<ServiceModel>()
    private var serviceName = ""
    private var price = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saloon_details)
        if (intent != null) {
            Glide.with(saloonImage).load(intent.getStringExtra("imgurl")).into(saloonImage)
            saloonName.text = intent.getStringExtra("salonname")
            saloonAddress.text = intent.getStringExtra("location")
        }

        btnBook.setOnClickListener {
            val gotToSlot = Intent(this, SlotActivty::class.java)
            gotToSlot.putExtra("imgurl", intent.getStringExtra("imgurl"))
            gotToSlot.putExtra("salonname", intent.getStringExtra("salonname"))
            gotToSlot.putExtra("location", intent.getStringExtra("location"))
            gotToSlot.putExtra("barbername", intent.getStringExtra("barbername"))
            gotToSlot.putExtra("price", serviceName)
            gotToSlot.putExtra("servicename", price)
            startActivity(gotToSlot)
        }
        buildList()
        setRecyclerView()
    }


    private fun buildList() {
        services.add(
            ServiceModel(
                "Men's Hair Style",
                "https://i.postimg.cc/4yJ5QY72/mens-hair-cutting.jpg",
                100
            )
        )
        services.add(
            ServiceModel(
                "Women's Hair Style",
                "https://i.postimg.cc/NfC4Rfgb/ladies-hair-cutting.jpg",
                150
            )
        )
        services.add(
            ServiceModel(
                "Beard Shaving",
                "https://i.postimg.cc/SsLd96sz/beard-shaving.jpg",
                40
            )
        )
        services.add(
            ServiceModel(
                "Beard Style",
                "https://i.postimg.cc/sg1TLGnt/beard-style.jpg",
                50
            )
        )
        services.add(
            ServiceModel(
                "Men's Hair Colour",
                "https://i.postimg.cc/63xzTj9X/mens-hair-colour.jpg",
                80
            )
        )
        services.add(
            ServiceModel(
                "Women's Hair Colour",
                "https://i.postimg.cc/QC0q4jc8/ladies-hair-colour.jpg",
                130
            )
        )
        services.add(
            ServiceModel(
                "Men's Head Massage",
                "https://i.postimg.cc/N0HpsqvD/mens-head-massage.jpg",
                250
            )
        )
        services.add(
            ServiceModel(
                "Women's Head Massage",
                "https://i.postimg.cc/6pSMx6gT/ledies-head-massage.jpg",
                200
            )
        )
        services.add(
            ServiceModel(
                "Men's Facial",
                "https://i.postimg.cc/h4rMP16k/mens-facial.jpg",
                300
            )
        )
        services.add(
            ServiceModel(
                "Women's Facial",
                "https://i.postimg.cc/VLkgSbcd/ledies-facial.jpg",
                800
            )
        )
    }

    private fun setRecyclerView() {
        serviceAdapter = ServiceAdapter(services, this)
        serviceRecyclerView.layoutManager = GridLayoutManager(this, 2)
        serviceRecyclerView.adapter = serviceAdapter
    }


    override fun onSelectService(model: ServiceModel, position: Int) {
        serviceName = model.serviceName
        price=model.servicePrice
    }
}