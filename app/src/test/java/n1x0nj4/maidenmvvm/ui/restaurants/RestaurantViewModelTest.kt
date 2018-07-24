package n1x0nj4.maidenmvvm.ui.restaurants

import com.nhaarman.mockitokotlin2.*
import io.reactivex.observers.DisposableObserver
import n1x0nj4.maidenmvvm.data.GetRestaurantsInteractor
import n1x0nj4.maidenmvvm.model.Restaurant
import n1x0nj4.maidenmvvm.state.ResourceState
import n1x0nj4.maidenmvvm.util.DataFactory
import n1x0nj4.maidenmvvm.util.RestaurantFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Captor
import org.mockito.Mockito.`when` as whenever

class RestaurantViewModelTest : BaseViewModelTest() {

    private lateinit var restaurantViewModel: RestaurantViewModel

    private var getRestaurants: GetRestaurantsInteractor = mock<GetRestaurantsInteractor>()

    @Captor
    private val captor = argumentCaptor<DisposableObserver<List<Restaurant>>>()

    override fun setUp() {
        restaurantViewModel = RestaurantViewModel(getRestaurants)
    }

    @Test
    fun getRestaurantsExecutesUseCase() {
        restaurantViewModel.fetchRestaurants()

        verify(getRestaurants, times(1)).execute(any(), eq(null))
    }

    @Test
    fun getRestaurantsReturnsSuccess() {
        val restaurants = RestaurantFactory.makeRestaurantList(2)

        restaurantViewModel.fetchRestaurants()

        verify(getRestaurants).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(restaurants)

        assertEquals(ResourceState.SUCCESS, restaurantViewModel.getRestaurants().value?.status)
    }

    @Test
    fun getRestaurantsReturnsError() {
        val restaurants = RestaurantFactory.makeRestaurantList(2)

        restaurantViewModel.fetchRestaurants()

        verify(getRestaurants).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR, restaurantViewModel.getRestaurants().value?.status)
    }

    @Test
    fun fetchRestaurantsReturnData() {
        val restaurants = RestaurantFactory.makeRestaurantList(2)

        restaurantViewModel.fetchRestaurants()

        verify(getRestaurants).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(restaurants)

        assertEquals(restaurants, restaurantViewModel.getRestaurants().value?.data)
    }

    @Test
    fun fetchRestaurantsReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()

        restaurantViewModel.fetchRestaurants()

        verify(getRestaurants).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage, restaurantViewModel.getRestaurants().value?.message)
    }
}