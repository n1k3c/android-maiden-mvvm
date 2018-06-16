package n1x0nj4.maidenmvvm.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import n1x0nj4.maidenmvvm.api.ApiModule

@Module(includes = [ApiModule::class])
abstract class AppModule {

    @Binds
    abstract fun provideContext(application: Application): Context
}