package n1x0nj4.maidenmvvm.factory

import com.n1x0nj4.domain.model.Restaurant
import n1x0nj4.maidenmvvm.model.RestaurantView

object RestaurantFactory {

    fun makeRestaurantView(): RestaurantView {
        return RestaurantView(DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomDouble(), DataFactory.randomDouble())
    }

    fun makeRestaurant(): Restaurant {
        return Restaurant(DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomDouble(), DataFactory.randomDouble())
    }

    fun makeRestaurantViewList(count: Int): List<RestaurantView> {
        val projects = mutableListOf<RestaurantView>()
        repeat(count) {
            projects.add(makeRestaurantView())
        }
        return projects
    }

    fun makeRestaurantList(count: Int): List<Restaurant> {
        val projects = mutableListOf<Restaurant>()
        repeat(count) {
            projects.add(makeRestaurant())
        }
        return projects
    }
}