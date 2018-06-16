package n1x0nj4.maidenmvvm.repository

import io.reactivex.Single
import n1x0nj4.maidenmvvm.api.ApiService
import javax.inject.Inject

class SayHelloRepository @Inject constructor(private val apiService: ApiService) {

    fun sayHello(greeting: String): Single<String> {
        return Single.just("$greeting... and hello from repository")
    }
}