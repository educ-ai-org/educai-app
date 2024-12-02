package com.example.educai.data.di

import com.example.educai.data.network.RetrofitInstance
import com.example.educai.data.services.LeaderboardService
import com.example.educai.data.services.UserService
import com.example.educai.data.viewmodel.LeaderboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val productionModule = module {
    single<LeaderboardService> {
        RetrofitInstance.leaderboardService
    }

    single<UserService> {
        RetrofitInstance.userService
    }

    viewModel<LeaderboardViewModel> {
        LeaderboardViewModel(get(), get())
    }

}

fun provideTestModule(leaderboardService: LeaderboardService, userService: UserService) = module {
    single { leaderboardService }
    single { userService }
}

