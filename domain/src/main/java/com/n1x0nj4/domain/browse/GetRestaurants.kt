package com.n1x0nj4.domain.browse

import com.n1x0nj4.domain.ObservableUseCase
import com.n1x0nj4.domain.executor.PostExecutionThread
import com.n1x0nj4.domain.model.Restaurant
import com.n1x0nj4.domain.repository.RestaurantsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRestaurants @Inject constructor(
        private val restaurantsRepository: RestaurantsRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Restaurant>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Restaurant>> {
        return restaurantsRepository.getRestaurants()
    }
}