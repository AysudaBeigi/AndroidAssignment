package ir.miare.androidcodechallenge.di

import ir.miare.androidcodechallenge.domain.IFootballRepository
import ir.miare.androidcodechallenge.domain.usecase.GetAccessTokenUseCase
import ir.miare.androidcodechallenge.domain.usecase.GetRepositoryListUseCase
import ir.miare.androidcodechallenge.domain.usecase.ReadTokenUseCase
import ir.miare.androidcodechallenge.domain.usecase.SaveIsFollowedUseCase
import ir.miare.androidcodechallenge.presentation.viewmodel.AuthorizeViewModel
import ir.miare.androidcodechallenge.presentation.viewmodel.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FootballModule = module {

    single<IFootballRepository> { FootballRepositoryImpl(get(),get()) }

    single { TokenRepositoryImpl(get(), coroutineDispatcherProvider()) }

    viewModel {
        RepositoryViewModel(get(), get(), coroutineDispatcherProvider())
    }
    viewModel {
        AuthorizeViewModel(get(), get(), coroutineDispatcherProvider())
    }

    factory { ReadTokenUseCase(get()) }

    factory { SaveIsFollowedUseCase(get()) }

    factory { GetAccessTokenUseCase(get()) }

    factory { GetRepositoryListUseCase(get()) }

}