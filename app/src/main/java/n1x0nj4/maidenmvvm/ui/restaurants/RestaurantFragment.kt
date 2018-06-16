package n1x0nj4.maidenmvvm.ui.restaurants

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.ajalt.timberkt.d
import com.jurajkusnier.androidapptemplate.di.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_restaurants.*
import n1x0nj4.maidenmvvm.R
import n1x0nj4.maidenmvvm.model.Restaurant
import n1x0nj4.maidenmvvm.ui.common.BaseFragment
import n1x0nj4.maidenmvvm.util.Status
import javax.inject.Inject

class RestaurantFragment : BaseFragment(), RestaurantsAdapter.OnRestaurantClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: RestaurantViewModel

    override val contentViewResource: Int = R.layout.fragment_restaurants

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RestaurantViewModel::class.java)

        viewModel.restaurantResult.observe(this, Observer<List<Restaurant>> { restaurants ->
            if (restaurants?.isNotEmpty() == true) {
                recyclerView.hasFixedSize()
                val adapter = RestaurantsAdapter(restaurants)
                adapter.setOnRestaurantClickListener(this)
                recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
                recyclerView.adapter = adapter
            } else {

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

    override fun onRestaurantClicked(restaurant: Restaurant) {
        Snackbar.make(coordinator, restaurant.name.toString(), Snackbar.LENGTH_SHORT).show()
    }
}