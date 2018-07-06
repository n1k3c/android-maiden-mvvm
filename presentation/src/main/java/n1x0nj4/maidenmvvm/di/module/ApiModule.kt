package n1x0nj4.maidenmvvm.di.module

import android.content.Context
import com.n1x0nj4.data.RestaurantsRemoteImpl
import com.n1x0nj4.data.api.ApiService
import com.n1x0nj4.data.api.ApiServiceFactory
import com.n1x0nj4.data.repository.RestaurantsRemote
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ApiModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(context: Context): ApiService {
            return ApiServiceFactory.provideApiService(context)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(restaurantsRemote: RestaurantsRemoteImpl): RestaurantsRemote
}