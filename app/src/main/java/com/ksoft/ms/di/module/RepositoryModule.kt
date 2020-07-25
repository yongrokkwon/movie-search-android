package com.ksoft.ms.di.module

import com.ksoft.ms.ui.movie.MoviesRepository
import com.ksoft.ms.ui.movie.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(repositoryImpl: MoviesRepositoryImpl): MoviesRepository =
        repositoryImpl
}