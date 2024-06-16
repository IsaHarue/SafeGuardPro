package com.hpe.safeguardpro.service.repository

import android.content.Context
import com.hpe.safeguardpro.service.model.Funcionario
import com.hpe.safeguardpro.service.repository.remote.FuncionarioService
import com.hpe.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class FuncionarioRepository(context: Context) {

    private val mRemoteFuncionario = RetrofitClient.createService(FuncionarioService::class.java)

    private val funcionarioEmpty = Funcionario(0, "", "", "", "")

    suspend fun insertFuncionario(funcionario: Funcionario): Funcionario {
        return mRemoteFuncionario.createFuncionario(
            nome = funcionario.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            email = funcionario.email.toRequestBody("text/plain".toMediaTypeOrNull()),
            cpf = funcionario.cpf.toRequestBody("text/plain".toMediaTypeOrNull()),
            dataNasc = funcionario.dataNasc.toRequestBody("text/plain".toMediaTypeOrNull()),
            senha = funcionario.senha.toRequestBody("text/plain".toMediaTypeOrNull()),
            admin = funcionario.admin.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
        ).body() ?: funcionarioEmpty
    }


    suspend fun getFuncionario(id: Int): Funcionario {
        val response = mRemoteFuncionario.getFuncionarioById(id)
        return if (response.isSuccessful) {
            return response.body()?.first() ?: funcionarioEmpty
        } else {
            funcionarioEmpty
        }
    }

    suspend fun getFuncionarioByCpf(cpf: Int): Funcionario {
        val response = mRemoteFuncionario.getFuncionarioByCpf(cpf)
        return if (response.isSuccessful) {
            response.body()?.first() ?: funcionarioEmpty
        } else {
            funcionarioEmpty
        }
    }

    suspend fun getFuncionarios(): List<Funcionario> {
        return mRemoteFuncionario.getFuncionarios()
    }

    suspend fun updateFuncionario(funcionario: Funcionario): Funcionario {
        return mRemoteFuncionario.updateFuncionario(
            nome = funcionario.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            email = funcionario.email.toRequestBody("text/plain".toMediaTypeOrNull()),
            cpf = funcionario.cpf.toRequestBody("text/plain".toMediaTypeOrNull()),
            dataNasc = funcionario.dataNasc.toRequestBody("text/plain".toMediaTypeOrNull()),
            senha = funcionario.senha.toRequestBody("text/plain".toMediaTypeOrNull()),
            admin = funcionario.admin.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionarioId = funcionario.id
        ).body() ?: funcionarioEmpty
    }


    suspend fun deleteFuncionario(id: Int): Boolean {
        return mRemoteFuncionario.deleteFuncionarioById(id).isSuccessful
    }
}