package com.n1x0nj4.data.cache.mapper

import com.n1x0nj4.data.cache.model.CachedRestaurant
import com.n1x0nj4.data.remote.model.RestaurantResponse
import javax.inject.Inject

class CachedRestaurantMapper @Inject constructor() : CacheMapper<CachedRestaurant, RestaurantResponse> {

    override fun mapFromCached(type: CachedRestaurant): RestaurantResponse {
        return RestaurantResponse(type.name, type.address, type.latitude, type.latitude)
    }

    override fun mapToCached(type: RestaurantResponse): CachedRestaurant {
        return CachedRestaurant(type.name, type.address, type.latitude, type.latitude)
    }
}