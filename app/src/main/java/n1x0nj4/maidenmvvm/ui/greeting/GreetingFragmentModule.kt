package n1x0nj4.maidenmvvm.ui.greeting

import android.arch.lifecycle.ViewModel
import com.jurajkusnier.androidapptemplate.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class GreetingFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(GreetingViewModel::class)
    abstract fun bindGreetingViewModel(viewModel: GreetingViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeGreetingFragment(): GreetingFragment
}