package com.n1x0nj4.data.repository

import com.n1x0nj4.data.remote.model.RestaurantResponse
import io.reactivex.Completable
import io.reactivex.Flowable

interface RestaurantsDataStore {

    fun getRestaurants(): Flowable<List<RestaurantResponse>>

    fun saveRestaurants(restaurants: List<RestaurantResponse>): Completable

    fun clearRestaurants(): Completable
}