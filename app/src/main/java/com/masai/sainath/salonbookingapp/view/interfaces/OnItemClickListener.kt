package com.masai.sainath.salonbookingapp.view.interfaces

import com.masai.sainath.salonbookingapp.model.AdminModel


interface OnItemClickListener {

    fun onItemClicked(mallItem: AdminModel)

    fun onDirectionsClicked()

}