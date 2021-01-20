package com.gojek.assignment.repo

import com.gojek.assignment.di.APIModule
import com.gojek.assignment.di.DaggerAPIComponent
import com.gojek.assignment.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

open class RPMRepositoryImpl : RPMRepository {
    @Inject
    lateinit var rpmRemoteDataSource: RPMRemoteDataSource

    init {
        val component = DaggerAPIComponent
            .builder()
            .aPIModule(APIModule())
            .build()
        component.inject(this)
    }

    override fun getRPM(): Flow<Resource<Any>> {
        return flow {
            emit(Resource.loading("loading"))
            emit(rpmRemoteDataSource.getRPM())
        }.flowOn(Dispatchers.IO)
    }
}