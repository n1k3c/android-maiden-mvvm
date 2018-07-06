package n1x0nj4.maidenmvvm.ui.restaurants

import com.n1x0nj4.data.model.Restaurant
import com.n1x0nj4.data.RestaurantsRemoteImpl
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever

class RestaurantViewModelTest : BaseViewModelTest() {

    private lateinit var restaurantViewModel: RestaurantViewModel

    @Mock
    private lateinit var restaurantRepository: RestaurantsRemoteImpl

    @Mock
    private lateinit var restaurantList: List<Restaurant>

    override fun setUp() {
        restaurantViewModel = RestaurantViewModel(restaurantRepository)
    }
}