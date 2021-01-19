package com.gojek.assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gojek.assignment.di.APIModule
import com.gojek.assignment.di.DaggerAPIComponent
import com.gojek.assignment.repo.RPMRepositoryImpl
import javax.inject.Inject

class RPMViewModelFactory : ViewModelProvider.Factory {
    @set:Inject
    lateinit var retrofitRepository: RPMRepositoryImpl


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val component = DaggerAPIComponent
            .builder()
            .aPIModule(APIModule())
            .build()
        component.inject(this)
        if (modelClass.isAssignableFrom(RPMViewModel::class.java)) {
            return RPMViewModel(retrofitRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}