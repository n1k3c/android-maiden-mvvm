package com.n1x0nj4.data.remote.model

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
        @SerializedName("Name")
        val name: String,
        @SerializedName("Address")
        val address: String,
        @SerializedName("Longitude")
        val longitude: Double,
        @SerializedName("Latitude")
        val latitude: Double)