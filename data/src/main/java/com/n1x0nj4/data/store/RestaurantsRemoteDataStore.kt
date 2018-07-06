package com.n1x0nj4.data.store

import com.n1x0nj4.data.remote.model.RestaurantResponse
import com.n1x0nj4.data.repository.RestaurantsDataStore
import com.n1x0nj4.data.repository.RestaurantsRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class RestaurantsRemoteDataStore @Inject constructor(
        private val restaurantsRemote: RestaurantsRemote)
    : RestaurantsDataStore {

    override fun getRestaurants(): Flowable<List<RestaurantResponse>> {
        return restaurantsRemote.getRestaurants()
    }

    override fun saveRestaurants(restaurants: List<RestaurantResponse>): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }

    override fun clearRestaurants(): Completable {
        throw UnsupportedOperationException("Clearing projects isn't supported here...")
    }
}