package n1x0nj4.maidenmvvm.util

import n1x0nj4.maidenmvvm.model.Restaurant

object RestaurantFactory {

    fun makeRestaurant(): Restaurant {
        return Restaurant(DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomDouble(), DataFactory.randomDouble())
    }

    fun makeRestaurantList(count: Int): List<Restaurant> {
        val projects = mutableListOf<Restaurant>()
        repeat(count) {
            projects.add(makeRestaurant())
        }
        return projects
    }
}