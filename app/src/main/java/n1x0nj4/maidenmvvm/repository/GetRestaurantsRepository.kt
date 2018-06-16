package n1x0nj4.maidenmvvm.repository

import io.reactivex.Single
import n1x0nj4.maidenmvvm.api.ApiService
import n1x0nj4.maidenmvvm.model.Restaurant
import javax.inject.Inject

class GetRestaurantsRepository @Inject constructor() {

    @Inject
    lateinit var apiService: ApiService

    fun fetchRestaurantsFromAPI(): Single<List<Restaurant>> {
        return apiService.getRestaurants()
    }
}