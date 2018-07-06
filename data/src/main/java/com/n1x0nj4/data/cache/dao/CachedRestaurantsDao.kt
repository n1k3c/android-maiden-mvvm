package com.n1x0nj4.data.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.n1x0nj4.data.cache.db.RestaurantConstants.DELETE_PROJECTS
import com.n1x0nj4.data.cache.db.RestaurantConstants.QUERY_PROJECTS
import com.n1x0nj4.data.cache.model.CachedRestaurant
import io.reactivex.Flowable

@Dao
abstract class CachedRestaurantsDao {

    @Query(QUERY_PROJECTS)
    @JvmSuppressWildcards
    abstract fun getRestaurants(): Flowable<List<CachedRestaurant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertRestaurants(restaurants: List<CachedRestaurant>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteRestaurants()
}