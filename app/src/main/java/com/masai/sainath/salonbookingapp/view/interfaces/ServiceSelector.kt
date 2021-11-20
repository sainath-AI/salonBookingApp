package com.masai.sainath.salonbookingapp.view.interfaces

import com.masai.sainath.salonbookingapp.model.ServiceModel

interface ServiceSelector {
    fun onSelectService(model:ServiceModel,position:Int)
}