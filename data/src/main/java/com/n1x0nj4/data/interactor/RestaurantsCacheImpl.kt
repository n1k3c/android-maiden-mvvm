package com.n1x0nj4.data.interactor

import com.n1x0nj4.data.cache.db.RestaurantsDatabase
import com.n1x0nj4.data.cache.mapper.CachedRestaurantMapper
import com.n1x0nj4.data.cache.model.Config
import com.n1x0nj4.data.remote.model.RestaurantResponse
import com.n1x0nj4.data.repository.RestaurantsCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class RestaurantsCacheImpl @Inject constructor(
        private val projectsDatabase: RestaurantsDatabase,
        private val mapper: CachedRestaurantMapper)
    : RestaurantsCache {

    override fun clearRestaurants(): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().deleteRestaurants()
            Completable.complete()
        }
    }

    override fun saveRestaurants(restaurants: List<RestaurantResponse>): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().insertRestaurants(
                    restaurants.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    override fun getRestaurants(): Flowable<List<RestaurantResponse>> {
        return projectsDatabase.cachedProjectsDao().getRestaurants()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
    }

    override fun areRestaurantsCached(): Single<Boolean> {
        return projectsDatabase.cachedProjectsDao().getRestaurants().isEmpty
                .map {
                    !it
                }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            projectsDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isRestaurantCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return projectsDatabase.configDao().getConfig()
                .onErrorReturn { Config(lastCacheTime = 0) }
                .map {
                    currentTime - it.lastCacheTime > expirationTime
                }
    }
}