package n1x0nj4.maidenmvvm.ui.restaurants

import android.arch.lifecycle.Observer
import io.reactivex.Single
import n1x0nj4.maidenmvvm.model.Restaurant
import n1x0nj4.maidenmvvm.repository.RestaurantsRepository
import n1x0nj4.maidenmvvm.util.Status
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever

class RestaurantViewModelTest : BaseViewModelTest() {

    private lateinit var restaurantViewModel: RestaurantViewModel

    @Mock
    private
    lateinit var restaurantRepository: RestaurantsRepository

    @Mock
    private
    lateinit var restaurantList: List<Restaurant>

    override fun setUp() {
        restaurantViewModel = RestaurantViewModel(restaurantRepository)
    }

    @Test
    fun shouldReturnListOfRestaurants() {
        whenever(restaurantRepository.fetchRestaurantsFromAPI()).thenReturn(Single.just(restaurantList))

        val result = mock(Observer::class.java)

        restaurantViewModel.restaurantResult.observeForever(result as Observer<List<Restaurant>>)

        restaurantViewModel.getRestaurants()

        assertEquals(restaurantViewModel._status.value, Status.SUCCESS)

        verify(restaurantRepository).fetchRestaurantsFromAPI()
    }

    @Test
    fun shouldReturnErrorWhileLoadingRestaurants() {
        whenever(restaurantRepository.fetchRestaurantsFromAPI()).thenReturn(Single.error(Throwable()))

        restaurantViewModel.getRestaurants()

        assertEquals(restaurantViewModel._status.value, Status.ERROR)

        verify(restaurantRepository).fetchRestaurantsFromAPI()
    }
}