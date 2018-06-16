package n1x0nj4.maidenmvvm.ui.restaurants

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.ajalt.timberkt.d
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import n1x0nj4.maidenmvvm.model.Restaurant
import n1x0nj4.maidenmvvm.repository.RestaurantsRepository
import n1x0nj4.maidenmvvm.ui.common.BaseViewModel
import n1x0nj4.maidenmvvm.util.Status
import javax.inject.Inject

class RestaurantViewModel @Inject constructor(private val restaurantsRepository: RestaurantsRepository) : BaseViewModel() {

    private val _restaurantResult: MutableLiveData<List<Restaurant>> = MutableLiveData()
    val restaurantResult: LiveData<List<Restaurant>>
        get() = _restaurantResult

    fun getRestaurants() {

        disposable?.dispose()

        _status.value = Status.LOADING

        disposable = restaurantsRepository.fetchRestaurantsFromAPI()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data ->
                            _restaurantResult.value = data
                            _status.value = Status.SUCCESS
                        },
                        { error ->
                            d { error.toString() }
                            _status.value = Status.ERROR
                        }
                )
    }
}