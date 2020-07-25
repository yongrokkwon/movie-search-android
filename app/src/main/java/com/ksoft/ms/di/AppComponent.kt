package com.ksoft.ms.di

import com.ksoft.ms.MSApplication
import com.ksoft.ms.di.module.AndroidViewModule
import com.ksoft.ms.di.module.NetworkModule
import com.ksoft.ms.di.module.RepositoryModule
import com.ksoft.ms.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidViewModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(msApplication: MSApplication)

    @Component.Builder
    interface Builder {
        fun netWorkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }
}
