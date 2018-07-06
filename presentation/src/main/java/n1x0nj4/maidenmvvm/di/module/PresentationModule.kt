package n1x0nj4.maidenmvvm.di.module

import com.n1x0nj4.domain.executor.PostExecutionThread
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import n1x0nj4.maidenmvvm.ui.browse.MainActivity
import n1x0nj4.maidenmvvm.ui.browse.RestaurantFragment
import n1x0nj4.maidenmvvm.util.UiThread

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity(): RestaurantFragment
}