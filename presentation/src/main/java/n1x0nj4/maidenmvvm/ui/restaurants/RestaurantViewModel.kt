package n1x0nj4.maidenmvvm.ui.restaurants

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.ajalt.timberkt.d
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.n1x0nj4.data.model.RestaurantResponse
import com.n1x0nj4.data.RestaurantsRemoteImpl
import n1x0nj4.maidenmvvm.state.Resource
import n1x0nj4.maidenmvvm.state.ResourceState
import n1x0nj4.maidenmvvm.ui.common.BaseViewModel
import javax.inject.Inject

class RestaurantViewModel @Inject constructor(private val restaurantsRepository: RestaurantsRemoteImpl) : BaseViewModel() {

    private val _restaurantResult: MutableLiveData<Resource<List<RestaurantResponse>>> = MutableLiveData()
    val restaurantResult: LiveData<Resource<List<RestaurantResponse>>>
        get() = _restaurantResult

    fun getRestaurants() {

        _restaurantResult.postValue(Resource(ResourceState.LOADING, null, null))

        disposable = restaurantsRepository.getRestaurants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data ->
                            _restaurantResult.postValue(Resource(ResourceState.SUCCESS, data, null))
                        },
                        { error ->
                            d { error.toString() }
                            _restaurantResult.postValue(Resource(ResourceState.ERROR, _restaurantResult.value?.data, error.localizedMessage))
                        }
                )
    }
}