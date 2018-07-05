package n1x0nj4.maidenmvvm.di.module

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import n1x0nj4.maidenmvvm.di.ViewModelFactory
import n1x0nj4.maidenmvvm.ui.restaurants.MainActivity

@Module
internal abstract class MainActivityModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [RestaurantFragmentModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}