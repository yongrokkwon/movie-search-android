package com.ksoft.ms.di.module

import com.ksoft.ms.ui.main.MainActivity
import com.ksoft.ms.ui.movie.MovieFragment
import com.ksoft.ms.ui.web.WebActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
abstract class AndroidViewModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeWebActivity(): WebActivity

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

}