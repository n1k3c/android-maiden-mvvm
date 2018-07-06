package com.n1x0nj4.data.cache.mapper

import com.n1x0nj4.data.cache.model.CachedProject
import com.n1x0nj4.data.remote.model.RestaurantResponse
import javax.inject.Inject

class CachedProjectMapper @Inject constructor() : CacheMapper<CachedProject, RestaurantResponse> {

    override fun mapFromCached(type: CachedProject): RestaurantResponse {
        return RestaurantResponse(type.name, type.address, type.latitude, type.latitude)
    }

    override fun mapToCached(type: RestaurantResponse): CachedProject {
        return CachedProject(type.name, type.address, type.latitude, type.latitude)
    }
}