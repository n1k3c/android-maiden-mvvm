package com.n1x0nj4.data.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.n1x0nj4.data.cache.dao.CachedRestaurantsDao
import com.n1x0nj4.data.cache.dao.ConfigDao
import com.n1x0nj4.data.cache.model.CachedRestaurant
import com.n1x0nj4.data.cache.model.Config
import javax.inject.Inject

@Database(entities = [(CachedRestaurant::class), (Config::class)], version = 1)
abstract class RestaurantsDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedProjectsDao(): CachedRestaurantsDao

    abstract fun configDao(): ConfigDao

    companion object {

        private var INSTANCE: RestaurantsDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): RestaurantsDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                RestaurantsDatabase::class.java, "projects.db")
                                .build()
                    }
                    return INSTANCE as RestaurantsDatabase
                }
            }
            return INSTANCE as RestaurantsDatabase
        }
    }
}