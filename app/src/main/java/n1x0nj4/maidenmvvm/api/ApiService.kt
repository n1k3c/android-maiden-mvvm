package n1x0nj4.maidenmvvm.api

import io.reactivex.Single
import n1x0nj4.maidenmvvm.model.Restaurant
import retrofit2.http.GET

interface ApiService {

    @GET("/v2/54ef80f5a11ac4d607752717")
    abstract fun getRestaurants(): Single<List<Restaurant>>

}