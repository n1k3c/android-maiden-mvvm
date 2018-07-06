package com.n1x0nj4.data

import com.n1x0nj4.data.mapper.RestaurantMapper
import com.n1x0nj4.data.repository.RestaurantsCache
import com.n1x0nj4.data.store.RestaurantsDataStoreFactory
import com.n1x0nj4.domain.model.Restaurant
import com.n1x0nj4.domain.repository.RestaurantsRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class RestaurantsDataRepository @Inject constructor(
        private val mapper: RestaurantMapper,
        private val cache: RestaurantsCache,
        private val factory: RestaurantsDataStoreFactory)
    : RestaurantsRepository {

    override fun getRestaurants(): Observable<List<Restaurant>> {
        return Observable.zip(cache.areRestaurantsCached().toObservable(),
                cache.isRestaurantCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getRestaurants().toObservable()
                            .distinctUntilChanged()
                }
                .flatMap { restaurants ->
                    factory.getCacheDataStore()
                            .saveRestaurants(restaurants)
                            .andThen(Observable.just(restaurants))
                }
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }
}