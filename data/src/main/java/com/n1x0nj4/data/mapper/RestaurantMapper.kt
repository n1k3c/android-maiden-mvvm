package com.n1x0nj4.data.mapper

import com.n1x0nj4.data.remote.model.RestaurantResponse
import com.n1x0nj4.domain.model.Restaurant
import javax.inject.Inject

open class RestaurantMapper @Inject constructor() : EntityMapper<RestaurantResponse, Restaurant> {

    override fun mapFromEntity(entity: RestaurantResponse): Restaurant {
        return Restaurant(entity.name, entity.address, entity.longitude, entity.latitude)
    }

    override fun mapToEntity(domain: Restaurant): RestaurantResponse {
        return RestaurantResponse(domain.name, domain.address, domain.longitude, domain.latitude)
    }
}