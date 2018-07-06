package com.n1x0nj4.data.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.n1x0nj4.data.cache.db.RestaurantConstants

@Entity(tableName = RestaurantConstants.TABLE_NAME)
data class CachedRestaurant(
        @PrimaryKey
        @ColumnInfo(name = RestaurantConstants.COLUMN_PROJECT_ID)
        var name: String,
        var address: String,
        var longitude: Double,
        var latitude: Double
)