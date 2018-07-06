package com.n1x0nj4.data.repository

import com.n1x0nj4.data.model.RestaurantResponse
import io.reactivex.Single

interface RestaurantsRemote {

    fun getRestaurants(): Single<List<RestaurantResponse>>
}