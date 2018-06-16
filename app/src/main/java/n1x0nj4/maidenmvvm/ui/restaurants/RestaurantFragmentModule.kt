package n1x0nj4.maidenmvvm.ui.restaurants

import android.arch.lifecycle.ViewModel
import com.jurajkusnier.androidapptemplate.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class RestaurantFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantViewModel::class)
    abstract fun bindGreetingViewModel(viewModel: RestaurantViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeGreetingFragment(): RestaurantFragment
}