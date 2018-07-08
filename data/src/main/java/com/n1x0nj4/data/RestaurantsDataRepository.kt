package com.n1x0nj4.data

import com.n1x0nj4.data.interactor.RestaurantsCacheImpl
import com.n1x0nj4.data.interactor.RestaurantsRemoteImpl
import com.n1x0nj4.data.mapper.RestaurantMapper
import com.n1x0nj4.data.repository.RestaurantsCache
import com.n1x0nj4.domain.model.Restaurant
import com.n1x0nj4.domain.repository.RestaurantsRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class RestaurantsDataRepository @Inject constructor(
        private val mapper: RestaurantMapper,
        private val cache: RestaurantsCache,
        private val cacheRepository: RestaurantsCacheImpl,
        private val remoteRepository: RestaurantsRemoteImpl)
    : RestaurantsRepository {

    override fun getRestaurants(): Observable<List<Restaurant>> {
        return Observable.zip(cache.areRestaurantsCached().toObservable(),
                cache.isRestaurantCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    val restaurantsCached = it.first
                    val cacheExpired = it.second

                    if (restaurantsCached && !cacheExpired) {
                        cacheRepository.getRestaurants().toObservable().distinctUntilChanged()
                    } else {
                        remoteRepository.getRestaurants().toObservable().distinctUntilChanged()
                    }
                }
                .flatMap { restaurants ->
                    cacheRepository.setLastCacheTime(System.currentTimeMillis())
                            .andThen(cacheRepository.saveRestaurants(restaurants))
                            .andThen(Observable.just(restaurants))
                }
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }
}