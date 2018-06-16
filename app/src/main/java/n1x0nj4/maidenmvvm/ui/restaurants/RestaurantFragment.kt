package n1x0nj4.maidenmvvm.ui.restaurants

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.github.ajalt.timberkt.d
import com.jurajkusnier.androidapptemplate.di.ViewModelFactory
import kotlinx.android.synthetic.main.greeting_fragment.*
import n1x0nj4.maidenmvvm.R
import n1x0nj4.maidenmvvm.model.Restaurant
import n1x0nj4.maidenmvvm.ui.common.BaseFragment
import n1x0nj4.maidenmvvm.util.Status
import javax.inject.Inject

class RestaurantFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: RestaurantViewModel

    override val contentViewResource: Int = R.layout.greeting_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RestaurantViewModel::class.java)

        viewModel.restaurantResult.observe(this, Observer<List<Restaurant>> { t ->
            if (t?.isNotEmpty() == true) {
                t.forEach {
                    d { it.toString() }
                    tvGreetingText.text = "Restaurants loaded"
                }
            } else {
                tvGreetingText.text = "No restaurants"
            }
        })

        viewModel.status.observe(this, Observer<Status> {
            when (it) {
                Status.LOADING -> d { "Loading state" }
                Status.SUCCESS -> d { "Success state" }
                Status.ERROR -> d { "Error state" }
            }
        })

        viewModel.getRestaurants()
    }
}