package com.ksoft.ms.di.module

import com.ksoft.ms.ui.main.MainActivity
import com.ksoft.ms.ui.movie.MovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidViewModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

}