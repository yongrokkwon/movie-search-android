package com.ksoft.ms.di

import com.ksoft.ms.MSApplication
import com.ksoft.ms.di.module.ActivityModule
import com.ksoft.ms.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(msApplication: MSApplication)
}
