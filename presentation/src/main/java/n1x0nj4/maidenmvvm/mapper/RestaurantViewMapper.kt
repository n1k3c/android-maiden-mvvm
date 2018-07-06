package n1x0nj4.maidenmvvm.mapper

import com.n1x0nj4.domain.model.Restaurant
import n1x0nj4.maidenmvvm.model.RestaurantView
import javax.inject.Inject

class RestaurantViewMapper @Inject constructor() : ViewMapper<Restaurant, RestaurantView> {

    override fun mapToView(domain: Restaurant): RestaurantView {
        return RestaurantView(domain.name, domain.address,
                domain.longitude, domain.latitude)
    }
}