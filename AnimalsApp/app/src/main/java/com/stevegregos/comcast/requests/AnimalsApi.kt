package com.stevegregos.comcast.requests

import com.stevegregos.comcast.models.Animal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AnimalApi {

    // SEARCH
    @Headers(
        "accept: application/json",
        "content-type: application/json",
        "X-Api-Key: hfzU/CRReGRxuNUXfqzuJQ==HSMctzE8woGjhpE4"
    )
    @GET("animals")
    suspend fun searchAnimal(
        @Query("name") query: String
    ): Response<List<Animal>>
}