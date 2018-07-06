package n1x0nj4.maidenmvvm.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import n1x0nj4.maidenmvvm.ui.restaurants.MainActivity
import n1x0nj4.maidenmvvm.ui.restaurants.RestaurantFragment

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity(): RestaurantFragment
}