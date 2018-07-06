package n1x0nj4.maidenmvvm.ui.restaurants

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import n1x0nj4.maidenmvvm.R
import n1x0nj4.maidenmvvm.model.Restaurant

class RestaurantsAdapter(private val restaurants: List<Restaurant>) : RecyclerView.Adapter<RestaurantsAdapter.SingleItemRowHolder>() {

    private lateinit var onRestaurantClickListener: OnRestaurantClickListener

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_restaurant, null)
        return SingleItemRowHolder(v)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, i: Int) {

        val restaurant = restaurants[i]

        holder.name.text = restaurant.name

        holder.address.text = restaurant.address

        holder.row.setOnClickListener {
            onRestaurantClickListener.onRestaurantClicked(restaurant)
        }
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {
        var row: LinearLayout = view.findViewById<View>(R.id.row_parent) as LinearLayout

        var name: TextView = view.findViewById<View>(R.id.name) as TextView

        var address: TextView = view.findViewById<View>(R.id.address) as TextView
    }

    fun setOnRestaurantClickListener(onRestaurantClickListener: OnRestaurantClickListener) {
        this.onRestaurantClickListener = onRestaurantClickListener
    }

    interface OnRestaurantClickListener {
        fun onRestaurantClicked(restaurant: Restaurant)
    }
}