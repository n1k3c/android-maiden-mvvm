package com.n1x0nj4.data.cache.db

object RestaurantConstants {

    const val TABLE_NAME = "restaurants"

    const val COLUMN_PROJECT_ID = "name"

    const val QUERY_PROJECTS = "SELECT * FROM $TABLE_NAME"

    const val DELETE_PROJECTS = "DELETE FROM $TABLE_NAME"
}