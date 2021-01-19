package com.gojek.assignment.repo

import com.gojek.assignment.model.RPMResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("/csrng/csrng.php?min=0&max=100")
    suspend fun getRpm(): Response<RPMResponse>
}