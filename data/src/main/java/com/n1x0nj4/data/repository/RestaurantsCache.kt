package com.n1x0nj4.data.repository

import com.n1x0nj4.data.remote.model.RestaurantResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface RestaurantsCache {

    fun clearRestaurants(): Completable

    fun saveRestaurants(restaurants: List<RestaurantResponse>): Completable

    fun getRestaurants(): Flowable<List<RestaurantResponse>>

    fun areRestaurantsCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isRestaurantCacheExpired(): Single<Boolean>
}