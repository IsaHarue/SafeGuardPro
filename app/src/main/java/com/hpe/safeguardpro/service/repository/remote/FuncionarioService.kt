package com.hpe.safeguardpro.service.repository.remote

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

interface FuncionarioService {
    @Multipart
    @POST("add_funcionario")
    suspend fun createFuncionario(
        @Part("nome") nome: RequestBody,
        @Part("email") email: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("dataNasc") dataNasc: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("admin") admin: RequestBody,
    ): Response<Funcionario>


    @GET("get_funcionarios")
    suspend fun getFuncionarios(): List<Funcionario>

    @GET("get_funcionario/{funcionario_id}")
    suspend fun getFuncionarioById(@Path("funcionario_id") id: Int): Response<List<Funcionario>>

    @GET("get_funcionario/{funcionario_cpf}")
    suspend fun getFuncionarioByCpf(@Path("funcionario_cpf") cpf: Int): Response<List<Funcionario>>

    @PUT("update_funcionario/{funcionario_id}")
    suspend fun updateFuncionario(
        @Path("funcionario_id") funcionarioId: Int,
        @Part("nome") nome: RequestBody,
        @Part("email") cpf: RequestBody,
        @Part("cpf") email: RequestBody,
        @Part("dataNasc") dataNasc: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("admin") admin: RequestBody,
    ): Response<Funcionario>

    @DELETE("delete_funcionario{funcionario_id}")
    suspend fun deleteFuncionarioById(@Path("funcionario_id") id: Int): Response<Funcionario>
}