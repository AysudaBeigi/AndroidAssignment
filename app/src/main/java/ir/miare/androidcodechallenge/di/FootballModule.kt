package ir.miare.androidcodechallenge.di

import com.jabama.challenge.base.coroutineDispatcherProvider
import com.jabama.challenge.data.repository.GithubRepositoryImpl
import com.jabama.challenge.data.repository.TokenRepositoryImpl
import com.jabama.challenge.domain.IGithubRepository
import com.jabama.challenge.domain.ITokenRepository
import ir.miare.androidcodechallenge.domain.usecase.GetAccessTokenUseCase
import ir.miare.androidcodechallenge.domain.usecase.GetRepositoryListUseCase
import ir.miare.androidcodechallenge.domain.usecase.ReadTokenUseCase
import ir.miare.androidcodechallenge.domain.usecase.SaveTokenUseCase
import ir.miare.androidcodechallenge.presentation.viewmodel.AuthorizeViewModel
import ir.miare.androidcodechallenge.presentation.viewmodel.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FootballModule = module {

    single<IGithubRepository> { GithubRepositoryImpl(get(),get()) }
    single<ITokenRepository> { TokenRepositoryImpl(get(), coroutineDispatcherProvider()) }

    single { TokenRepositoryImpl(get(), coroutineDispatcherProvider()) }

    viewModel {
        RepositoryViewModel(get(), get(), coroutineDispatcherProvider())
    }
    viewModel {
        AuthorizeViewModel(get(), get(), coroutineDispatcherProvider())
    }

    factory { ReadTokenUseCase(get()) }

    factory { SaveTokenUseCase(get()) }

    factory { GetAccessTokenUseCase(get()) }

    factory { GetRepositoryListUseCase(get()) }

}