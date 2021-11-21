package com.masai.sainath.salonbookingapp.view.viewholder

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masai.sainath.salonbookingapp.R
import com.masai.sainath.salonbookingapp.model.ServiceModel
import com.masai.sainath.salonbookingapp.view.interfaces.ServiceSelector
import kotlinx.android.synthetic.main.service_layout.view.*

class ServiceViewHolder(val view: View, private val onClickService: ServiceSelector) :
    RecyclerView.ViewHolder(view) {
    private var isServiceSelected = false
    var totalCoast = 0
    var itemCount = 0

    fun setServiceData(model: ServiceModel) {
        view.apply {
            Glide.with(service_image).load(model.serviceImage).into(service_image)
            service_name.text = model.serviceName
            service_price.text = model.servicePrice.toString()

            // Handle Select Service
            service.setOnClickListener {
                onClickService.onSelectService(model, adapterPosition)
                if (isServiceSelected) {
                    selected.visibility = View.GONE
                    itemCount--
                    totalCoast = totalCoast - model.servicePrice
                    isSelected = false;

                } else {
                    selected.visibility = View.VISIBLE
                    itemCount++
                    totalCoast = totalCoast + model.servicePrice
                    isSelected = true

                }
//                Toast.makeText(context, "Item=$itemCount Price=$totalCoast", Toast.LENGTH_SHORT)
//                    .show()
            }
        }
    }
}