package com.n1x0nj4.domain.repository

import com.n1x0nj4.domain.model.Restaurant
import io.reactivex.Observable

interface RestaurantsRepository {

    fun getRestaurants(): Observable<List<Restaurant>>
}