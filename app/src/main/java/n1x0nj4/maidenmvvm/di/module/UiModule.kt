package n1x0nj4.maidenmvvm.di.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import n1x0nj4.maidenmvvm.ui.restaurants.MainActivity
import n1x0nj4.maidenmvvm.ui.restaurants.RestaurantFragment
import n1x0nj4.maidenmvvm.util.PostExecutionThread
import n1x0nj4.maidenmvvm.util.UiThread

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity(): RestaurantFragment
}