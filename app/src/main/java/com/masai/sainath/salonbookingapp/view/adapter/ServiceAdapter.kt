package com.masai.sainath.salonbookingapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.sainath.salonbookingapp.R
import com.masai.sainath.salonbookingapp.model.ServiceModel
import com.masai.sainath.salonbookingapp.view.interfaces.ServiceSelector
import com.masai.sainath.salonbookingapp.view.viewholder.ServiceViewHolder

class ServiceAdapter(
    private val serviceList: MutableList<ServiceModel>,
    private val onClickService: ServiceSelector
) :
    RecyclerView.Adapter<ServiceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_layout, parent, false)
        return ServiceViewHolder(view, onClickService)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val model=serviceList[position]
        holder.setServiceData(model)
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }
}