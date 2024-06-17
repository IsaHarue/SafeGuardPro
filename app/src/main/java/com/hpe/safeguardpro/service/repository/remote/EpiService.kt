package com.hpe.safeguardpro.service.repository.remote

import com.hpe.safeguardpro.service.model.Epi
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface EpiService {
    @Multipart
    @POST("add_epi")
    suspend fun createEpi(
        @Part("nome") nome: RequestBody,
        @Part("dataFab") dataFabricacao: RequestBody,
        @Part("ca") ca: RequestBody,
        @Part("Validade") validade: RequestBody,
        @Part("Descricao") descricao: RequestBody,
    ): Response<Epi>


    @GET("get_epis")
    suspend fun getEpis(): List<Epi>

    @GET("get_epi/{epi_id}")
    suspend fun getEpiById(@Path("epi_id") id: Int): Response<List<Epi>>

    @GET("get_epi/{epi_ca}")
    suspend fun getEpiByCa(@Part("epi_ca") ca: Int): Response<List<Epi>>

    @PUT("update_epi/{epi_id}")
    suspend fun updateEpi(
        @Path("epi_id") id: Int,
        @Part("nome") nome: RequestBody,
        @Part("dataFab") dataFabricacao: RequestBody,
        @Part("ca") ca: RequestBody,
        @Part("Validade") validade: RequestBody,
        @Part("Descricao") descricao: RequestBody,
    ): Response<Epi>

    @DELETE("delete_epi/{epi_id}")
    suspend fun deleteEpiById(@Path("epi_id") id: Int): Response<Epi>
}