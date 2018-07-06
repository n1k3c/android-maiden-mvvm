package com.n1x0nj4.data.store

import com.n1x0nj4.data.repository.RestaurantsDataStore
import javax.inject.Inject

open class RestaurantsDataStoreFactory @Inject constructor(
        private val restaurantsCacheDataStore: RestaurantsCacheDataStore,
        private val restaurantsRemoteDataStore: RestaurantsRemoteDataStore) {

    open fun getDataStore(restaurantsCached: Boolean,
                          cacheExpired: Boolean): RestaurantsDataStore {
        return if (restaurantsCached && !cacheExpired) {
            restaurantsCacheDataStore
        } else {
            restaurantsRemoteDataStore
        }
    }

    open fun getCacheDataStore(): RestaurantsDataStore {
        return restaurantsCacheDataStore
    }

    fun getRemoteDataStore(): RestaurantsDataStore {
        return restaurantsRemoteDataStore
    }
}