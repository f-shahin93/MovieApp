package com.shahin.movieapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SubComponentsModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        DatabaseModule::class
    ]
)
interface ApplicationGraph {

    fun mainComponent(): MainActivitySubComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationGraph
    }

}