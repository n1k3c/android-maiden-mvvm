package com.n1x0nj4.data.interactor

import com.n1x0nj4.data.remote.ApiService
import com.n1x0nj4.data.remote.model.RestaurantResponse
import com.n1x0nj4.data.repository.RestaurantsRemote
import io.reactivex.Flowable
import javax.inject.Inject

class RestaurantsRemoteImpl @Inject constructor(private val apiService: ApiService) : RestaurantsRemote {

    override fun getRestaurants(): Flowable<List<RestaurantResponse>> {
        return apiService.getRestaurants()
    }
}