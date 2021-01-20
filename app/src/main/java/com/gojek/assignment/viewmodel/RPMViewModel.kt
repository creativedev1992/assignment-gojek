package com.gojek.assignment.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.gojek.assignment.repo.RPMRepository
import com.gojek.assignment.util.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

open class RPMViewModel(val repository: RPMRepository?) : ViewModel() {
    private var _rpmLiveData = MutableLiveData<Resource<Any>>()
    val rpmLiveData = _rpmLiveData
    suspend fun fetchRPM() {
        repository?.getRPM()?.collect {
            _rpmLiveData.postValue(it)
        }

    }
}