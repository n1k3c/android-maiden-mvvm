package n1x0nj4.maidenmvvm.ui.restaurants

import com.n1x0nj4.data.model.RestaurantResponse
import com.n1x0nj4.data.RestaurantsRemoteImpl
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever

class RestaurantResponseViewModelTest : BaseViewModelTest() {

    private lateinit var restaurantViewModel: RestaurantViewModel

    @Mock
    private lateinit var restaurantRepository: RestaurantsRemoteImpl

    @Mock
    private lateinit var restaurantList: List<RestaurantResponse>

    override fun setUp() {
        restaurantViewModel = RestaurantViewModel(restaurantRepository)
    }
}