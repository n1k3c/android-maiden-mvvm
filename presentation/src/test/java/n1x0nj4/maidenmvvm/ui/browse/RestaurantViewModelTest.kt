package n1x0nj4.maidenmvvm.ui.browse

import com.n1x0nj4.domain.browse.GetRestaurants
import com.n1x0nj4.domain.model.Restaurant
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import n1x0nj4.maidenmvvm.factory.DataFactory
import n1x0nj4.maidenmvvm.factory.RestaurantFactory
import n1x0nj4.maidenmvvm.mapper.RestaurantViewMapper
import n1x0nj4.maidenmvvm.model.RestaurantView
import n1x0nj4.maidenmvvm.state.ResourceState
import org.junit.Test
import org.mockito.Captor
import kotlin.test.assertEquals
import org.mockito.Mockito.`when` as whenever

class RestaurantViewModelTest : BaseViewModelTest() {

    private lateinit var restaurantViewModel: RestaurantViewModel

    private var getRestaurants: GetRestaurants = mock<GetRestaurants>()

    private var restaurantMapper: RestaurantViewMapper = mock<RestaurantViewMapper>()

    @Captor
    private val captor = argumentCaptor<DisposableObserver<List<Restaurant>>>()

    override fun setUp() {
        restaurantViewModel = RestaurantViewModel(getRestaurants, restaurantMapper)
    }

    @Test
    fun getRestaurantsExecutesUseCase() {
        restaurantViewModel.fetchRestaurants()

        verify(getRestaurants, times(1)).execute(any(), eq(null))
    }

    @Test
    fun getRestaurantsReturnsSuccess() {
        val restaurants = RestaurantFactory.makeRestaurantList(2)
        val restaurantsView = RestaurantFactory.makeRestaurantViewList(2)

        stubRestaurantMapperToView(restaurantsView[0], restaurants[0])
        stubRestaurantMapperToView(restaurantsView[1], restaurants[1])

        restaurantViewModel.fetchRestaurants()

        verify(getRestaurants).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(restaurants)

        assertEquals(ResourceState.SUCCESS, restaurantViewModel.getRestaurants().value?.status)
    }

    @Test
    fun getRestaurantsReturnsError() {
        val restaurants = RestaurantFactory.makeRestaurantList(2)
        val restaurantsView = RestaurantFactory.makeRestaurantViewList(2)

        stubRestaurantMapperToView(restaurantsView[0], restaurants[0])
        stubRestaurantMapperToView(restaurantsView[1], restaurants[1])

        restaurantViewModel.fetchRestaurants()

        verify(getRestaurants).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR, restaurantViewModel.getRestaurants().value?.status)
    }

    @Test
    fun fetchRestaurantsReturnData() {
        val restaurants = RestaurantFactory.makeRestaurantList(2)
        val restaurantsView = RestaurantFactory.makeRestaurantViewList(2)

        stubRestaurantMapperToView(restaurantsView[0], restaurants[0])
        stubRestaurantMapperToView(restaurantsView[1], restaurants[1])

        restaurantViewModel.fetchRestaurants()

        verify(getRestaurants).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(restaurants)

        assertEquals(restaurantsView, restaurantViewModel.getRestaurants().value?.data)
    }

    @Test
    fun fetchRestaurantsReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()

        restaurantViewModel.fetchRestaurants()

        verify(getRestaurants).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage, restaurantViewModel.getRestaurants().value?.message)
    }

    private fun stubRestaurantMapperToView(restaurantView: RestaurantView, restaurant: Restaurant) {
        whenever(restaurantMapper.mapToView(restaurant)).thenReturn(restaurantView)
    }
}