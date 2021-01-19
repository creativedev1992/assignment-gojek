package com.gojek.assignment.di


import com.gojek.assignment.repo.RPMRemoteDataSource
import com.gojek.assignment.repo.RPMRepositoryImpl
import com.gojek.assignment.ui.MainActivity
import com.gojek.assignment.viewmodel.RPMViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [APIModule::class])
interface APIComponent {
    fun inject(remoteDataSource: RPMRemoteDataSource)
    fun inject(retrofitRepositoryImpl: RPMRepositoryImpl)
    fun inject(rpmViewModelFactory: RPMViewModelFactory)

    //fun inject(notificationViewModel: NotificationViewModel)
    fun inject(activity: MainActivity)
    //fun inject(notificationManager: NotificationManager)

}