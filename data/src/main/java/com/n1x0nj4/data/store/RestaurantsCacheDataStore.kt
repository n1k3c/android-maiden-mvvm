package com.n1x0nj4.data.store

import com.n1x0nj4.data.remote.model.RestaurantResponse
import com.n1x0nj4.data.repository.RestaurantsCache
import com.n1x0nj4.data.repository.RestaurantsDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class RestaurantsCacheDataStore @Inject constructor(
        private val restaurantsCache: RestaurantsCache)
    : RestaurantsDataStore {

    override fun getRestaurants(): Flowable<List<RestaurantResponse>> {
        return restaurantsCache.getRestaurants()
    }

    override fun saveRestaurants(restaurants: List<RestaurantResponse>): Completable {
        return restaurantsCache.saveRestaurants(restaurants)
                .andThen(restaurantsCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearRestaurants(): Completable {
        return restaurantsCache.clearRestaurants()
    }
}