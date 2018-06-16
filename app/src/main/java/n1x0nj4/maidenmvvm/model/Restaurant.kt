package n1x0nj4.maidenmvvm.model

import com.google.gson.annotations.SerializedName

data class Restaurant(
        @SerializedName("Name")
        val name: String?,
        @SerializedName("Address")
        val address: String?,
        @SerializedName("Longitude")
        val longitude: Double?,
        @SerializedName("Latitude")
        val latitude: Double?)