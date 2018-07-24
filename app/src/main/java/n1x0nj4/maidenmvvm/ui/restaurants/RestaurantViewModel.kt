package n1x0nj4.maidenmvvm.ui.restaurants

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableObserver
import n1x0nj4.maidenmvvm.data.GetRestaurantsInteractor
import n1x0nj4.maidenmvvm.model.Restaurant
import n1x0nj4.maidenmvvm.state.Resource
import n1x0nj4.maidenmvvm.state.ResourceState
import n1x0nj4.maidenmvvm.ui.common.BaseViewModel
import javax.inject.Inject

class RestaurantViewModel @Inject constructor(private val getRestaurants: GetRestaurantsInteractor) : BaseViewModel() {

    private val restaurantResult: MutableLiveData<Resource<List<Restaurant>>> = MutableLiveData()

    fun getRestaurants(): LiveData<Resource<List<Restaurant>>> {
        return restaurantResult
    }

    fun fetchRestaurants() {

        restaurantResult.postValue(Resource(ResourceState.LOADING, null, null))

        getRestaurants.execute(RestaurantsSubscriber())
    }

    inner class RestaurantsSubscriber : DisposableObserver<List<Restaurant>>() {
        override fun onComplete() {

        }

        override fun onNext(data: List<Restaurant>) {
            restaurantResult.postValue(Resource(ResourceState.SUCCESS, data, null))
        }

        override fun onError(e: Throwable) {
            restaurantResult.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}