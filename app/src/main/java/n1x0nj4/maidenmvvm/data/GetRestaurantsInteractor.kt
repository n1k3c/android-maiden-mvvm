package n1x0nj4.maidenmvvm.data

import io.reactivex.Observable
import n1x0nj4.maidenmvvm.model.Restaurant
import n1x0nj4.maidenmvvm.util.PostExecutionThread
import javax.inject.Inject

class GetRestaurantsInteractor @Inject constructor(
        private val restaurantsRepository: RestaurantsRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Restaurant>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Restaurant>> {
        return restaurantsRepository.fetchRestaurantsFromAPI()
    }
}