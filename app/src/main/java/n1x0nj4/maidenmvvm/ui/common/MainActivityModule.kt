package n1x0nj4.maidenmvvm.ui.common

import android.arch.lifecycle.ViewModelProvider
import com.jurajkusnier.androidapptemplate.di.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import n1x0nj4.maidenmvvm.ui.greeting.GreetingFragmentModule

@Module
internal abstract class MainActivityModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [GreetingFragmentModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}