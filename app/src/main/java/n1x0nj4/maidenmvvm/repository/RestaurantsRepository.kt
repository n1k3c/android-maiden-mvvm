package n1x0nj4.maidenmvvm.repository

import io.reactivex.Flowable
import n1x0nj4.maidenmvvm.api.ApiService
import n1x0nj4.maidenmvvm.model.Restaurant
import javax.inject.Inject

class RestaurantsRepository @Inject constructor() {

    @Inject
    lateinit var apiService: ApiService

    fun fetchRestaurantsFromAPI(): Flowable<List<Restaurant>> {
        return apiService.getRestaurants()
    }
}