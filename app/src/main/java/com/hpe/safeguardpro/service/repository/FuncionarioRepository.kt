package com.hpe.safeguardpro.service.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hpe.safeguardpro.service.model.Funcionario
import com.hpe.safeguardpro.service.repository.local.SafeGuardProDataBase
import com.hpe.safeguardpro.service.repository.remote.FuncionarioService
import com.hpe.safeguardpro.service.repository.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class FuncionarioRepository(context: Context) {
    private val safeGuardProDataBase = SafeGuardProDataBase.getDataBase(context).FuncionarioDAO()

    private val mRemoteFuncionario = RetrofitClient.createService(FuncionarioService::class.java)

    private val funcionarioEmpty = Funcionario(0,"","","",0)

    suspend fun insertFuncionario(api: Boolean, funcionario: Funcionario): Funcionario {
        return if(api){
            mRemoteFuncionario.createFuncionario(
                nome = funcionario.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
                email = funcionario.email.toRequestBody("text/plain".toMediaTypeOrNull()),
                cpf = funcionario.cpf.toRequestBody("text/plain".toMediaTypeOrNull()),
                dataNasc = funcionario.dataNasc.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            ).body() ?: funcionarioEmpty
        } else{
            safeGuardProDataBase.insert(funcionario)
            funcionario
        }
    }

    suspend fun getFuncionario(api: Boolean, id: Int): Funcionario{
        if (api){
            val response = mRemoteFuncionario.getFuncionarioById(id)
            if (response.isSuccessful){
                return response.body()?.first() ?: funcionarioEmpty
            }
        } else{
            return safeGuardProDataBase.getFuncionario(id)
        }
        return funcionarioEmpty
    }

    suspend fun getFuncionarios(api: Boolean): List<Funcionario> {
        return if (api) mRemoteFuncionario.getFuncionarios() else safeGuardProDataBase.getAll()
    }

    suspend fun updateFuncionario(api: Boolean, id: Int, funcionario: Funcionario): Funcionario {
        return if (api) {
            mRemoteFuncionario.updateFuncionario(
                nome = funcionario.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
                email = funcionario.email.toRequestBody("text/plain".toMediaTypeOrNull()),
                cpf = funcionario.cpf.toRequestBody("text/plain".toMediaTypeOrNull()),
                dataNasc = funcionario.dataNasc.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                funcionarioId = id
            ).body() ?: funcionarioEmpty
        } else{
            safeGuardProDataBase.insert(funcionario)
            funcionario
        }
    }

    suspend fun deleteFuncionario(api: Boolean, id: Int): Boolean {
        return if (api) {
            mRemoteFuncionario.deleteFuncionarioById(id).isSuccessful
        } else {
            safeGuardProDataBase.delete(id) == id
        }

    }


}