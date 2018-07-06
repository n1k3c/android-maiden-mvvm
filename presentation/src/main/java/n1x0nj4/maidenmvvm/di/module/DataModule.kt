package n1x0nj4.maidenmvvm.di.module

import com.n1x0nj4.data.RestaurantsDataRepository
import com.n1x0nj4.domain.repository.RestaurantsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: RestaurantsDataRepository): RestaurantsRepository
}