package com.n1x0nj4.data.api

import io.reactivex.Single
import com.n1x0nj4.data.model.Restaurant
import retrofit2.http.GET

interface ApiService {

    @GET("/v2/54ef80f5a11ac4d607752717")
    abstract fun getRestaurants(): Single<List<Restaurant>>

}