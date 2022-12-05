package com.example.newfinal.model

import com.google.gson.annotations.SerializedName


data class Books(

    @SerializedName("items") var items: ArrayList<Items> = arrayListOf()

)
