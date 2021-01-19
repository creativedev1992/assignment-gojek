package com.gojek.assignment.repo

import android.util.Log
import com.gojek.assignment.di.APIModule
import com.gojek.assignment.di.DaggerAPIComponent
import com.gojek.assignment.model.RPMResponse
import com.gojek.assignment.util.ErrorUtils
import com.gojek.assignment.util.Resource
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RPMRemoteDataSource {
    @Inject
    lateinit var retrofit: Retrofit

    init {
        val component = DaggerAPIComponent
            .builder()
            .aPIModule(APIModule())
            .build()
        component.inject(this)
    }

    suspend fun getRPM(): Resource<RPMResponse> {
        val apiService: APIService =
            retrofit.create(APIService::class.java)
        return getResponse(
            request = { apiService.getRpm() },
            defaultErrorMessage = "Error fetching Notification list"
        )
    }

    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): Resource<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return Resource.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Resource.error(errorResponse?.message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            Resource.error("Unknown Error", null)
        }
    }

}