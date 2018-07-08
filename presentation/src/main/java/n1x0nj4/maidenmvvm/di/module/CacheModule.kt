package n1x0nj4.maidenmvvm.di.module

import android.app.Application
import com.n1x0nj4.data.interactor.RestaurantsCacheImpl
import com.n1x0nj4.data.cache.db.RestaurantsDatabase
import com.n1x0nj4.data.repository.RestaurantsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): RestaurantsDatabase {
            return RestaurantsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: RestaurantsCacheImpl): RestaurantsCache
}