package n1x0nj4.maidenmvvm.ui.browse

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.n1x0nj4.domain.browse.GetRestaurants
import com.n1x0nj4.domain.model.Restaurant
import io.reactivex.observers.DisposableObserver
import n1x0nj4.maidenmvvm.mapper.RestaurantViewMapper
import n1x0nj4.maidenmvvm.model.RestaurantView
import n1x0nj4.maidenmvvm.state.Resource
import n1x0nj4.maidenmvvm.state.ResourceState
import n1x0nj4.maidenmvvm.ui.common.BaseViewModel
import javax.inject.Inject

class RestaurantViewModel @Inject constructor(private val getRestaurants: GetRestaurants,
                                              private val mapper: RestaurantViewMapper)
    : BaseViewModel() {

    private val _restaurantResult: MutableLiveData<Resource<List<RestaurantView>>> = MutableLiveData()
    val restaurantResult: LiveData<Resource<List<RestaurantView>>>
        get() = _restaurantResult

    fun getRestaurants() {

        _restaurantResult.postValue(Resource(ResourceState.LOADING, null, null))
        getRestaurants.execute(RestaurantsSubscriber())

//        disposable = restaurantsRepository.getRestaurants()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { data ->
//                            _restaurantResult.postValue(Resource(ResourceState.SUCCESS, data, null))
//                        },
//                        { error ->
//                            d { error.toString() }
//                            _restaurantResult.postValue(Resource(ResourceState.ERROR, _restaurantResult.value?.data, error.localizedMessage))
//                        }
//                )
    }

    inner class RestaurantsSubscriber : DisposableObserver<List<Restaurant>>() {
        override fun onComplete() {

        }

        override fun onNext(data: List<Restaurant>) {
            _restaurantResult.postValue(Resource(ResourceState.SUCCESS,
                    data.map { mapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
        }

    }
}