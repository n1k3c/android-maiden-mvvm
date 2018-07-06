package com.n1x0nj4.data.remote

import com.n1x0nj4.data.remote.model.RestaurantResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("/v2/54ef80f5a11ac4d607752717")
    abstract fun getRestaurants(): Flowable<List<RestaurantResponse>>

}