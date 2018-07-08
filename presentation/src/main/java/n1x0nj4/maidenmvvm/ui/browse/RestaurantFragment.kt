package n1x0nj4.maidenmvvm.ui.browse

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.ajalt.timberkt.d
import kotlinx.android.synthetic.main.fragment_restaurants.*
import n1x0nj4.maidenmvvm.R
import n1x0nj4.maidenmvvm.di.ViewModelFactory
import n1x0nj4.maidenmvvm.model.RestaurantView
import n1x0nj4.maidenmvvm.state.Resource
import n1x0nj4.maidenmvvm.state.ResourceState
import n1x0nj4.maidenmvvm.ui.common.BaseFragment
import javax.inject.Inject

class RestaurantFragment : BaseFragment(), RestaurantsAdapter.OnRestaurantClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: RestaurantViewModel

    override val contentViewResource: Int = R.layout.fragment_restaurants

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RestaurantViewModel::class.java)

        viewModel.fetchRestaurants()
    }

    override fun onStart() {
        super.onStart()

        viewModel.getRestaurants().observe(this,
                Observer<Resource<List<RestaurantView>>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
    }

    private fun handleDataState(resource: Resource<List<RestaurantView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                d { "Success state" }
                setupScreenForSuccess(resource.data)
            }
            ResourceState.ERROR -> {
                d { "Error state" }
            }
            ResourceState.LOADING -> {
                d { "Loading state" }
                progress.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
        }
    }

    private fun setupScreenForSuccess(restaurants: List<RestaurantView>?) {
        progress.visibility = View.GONE
        restaurants?.let {
            recyclerView.hasFixedSize()
            val adapter = RestaurantsAdapter(it)
            adapter.setOnRestaurantClickListener(this)
            recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            recyclerView.adapter = adapter
            recyclerView.visibility = View.VISIBLE
        } ?: run {

        }
    }

    override fun onRestaurantClicked(restaurant: RestaurantView) {
        Snackbar.make(coordinator, restaurant.name.toString(), Snackbar.LENGTH_SHORT).show()
    }
}