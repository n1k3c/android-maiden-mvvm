package com.n1x0nj4.data.repository

import com.n1x0nj4.data.remote.model.RestaurantResponse
import io.reactivex.Flowable

interface RestaurantsRemote {

    fun getRestaurants(): Flowable<List<RestaurantResponse>>
}