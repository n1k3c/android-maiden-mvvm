package n1x0nj4.maidenmvvm.data

import io.reactivex.Observable
import n1x0nj4.maidenmvvm.api.ApiService
import n1x0nj4.maidenmvvm.model.Restaurant
import javax.inject.Inject

class RestaurantsRepository @Inject constructor() {

    @Inject
    lateinit var apiService: ApiService

    fun fetchRestaurantsFromAPI(): Observable<List<Restaurant>> {
        return apiService.getRestaurants()
    }
}