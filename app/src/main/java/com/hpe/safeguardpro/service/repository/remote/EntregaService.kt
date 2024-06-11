package com.hpe.safeguardpro.service.repository.remote

import com.hpe.safeguardpro.service.model.Entrega
import com.hpe.safeguardpro.service.model.Epi
import com.hpe.safeguardpro.service.model.Funcionario
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface EntregaService {
    @Multipart
    @POST("add_entrega")
    suspend fun createEntrega(
        @Part("tempo") tempo: RequestBody,
        @Part("dataEntrega") dataEntrega: RequestBody,
        @Part("funcionario_id") funcionarioId: RequestBody,
        @Part("epi_id") epiId: RequestBody,
    ): Response<Entrega>


    @GET("get_entregas")
    suspend fun getEntregas(): List<Entrega>

    @GET("get_entrega/{entrega_id}")
    suspend fun getEntregaById(@Path("entrega_id") id: Int): Response<List<Entrega>>

    @PUT("update_entrega/{entrega_id}")
    suspend fun updateEntrega(
        @Path("id") id: Int,
        @Part("tempo") tempo: RequestBody,
        @Part("dataEntrega") dataEntrega: RequestBody,
        @Part("funcionario_id") funcionarioId: RequestBody,
        @Part("epi_id") epiId: RequestBody,
    ): Response<Entrega>

    @DELETE("delete_entrega{entrega_id}")
    suspend fun deleteEntregaById(@Path("entrega_id") id: Int): Response<Entrega>
}