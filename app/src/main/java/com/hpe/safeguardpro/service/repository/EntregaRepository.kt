package com.hpe.safeguardpro.service.repository

import android.content.Context
import com.hpe.safeguardpro.service.model.Entrega
import com.hpe.safeguardpro.service.model.Epi
import com.hpe.safeguardpro.service.model.Funcionario
import com.hpe.safeguardpro.service.repository.local.SafeGuardProDataBase
import com.hpe.safeguardpro.service.repository.remote.EntregaService
import com.hpe.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class EntregaRepository(context: Context) {
    private val safeGuardProDataBase = SafeGuardProDataBase.getDataBase(context).EntregaDAO()

    private val mRemoteEntrega = RetrofitClient.createService(EntregaService::class.java)

    private val entregaEmpty = Entrega(0, "", "", 0, 0)

    suspend fun insertEntrega(entrega: Entrega): Entrega {
        return mRemoteEntrega.createEntrega(
            tempo = entrega.tempo.toRequestBody("text/plain".toMediaTypeOrNull()),
            dataEntrega = entrega.dataEntrega.toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionarioId = entrega.funcionarioId.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            epiId = entrega.epiId.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
        ).body() ?: entregaEmpty
    }

    suspend fun getEntrega(id: Int): Entrega {
        val response = mRemoteEntrega.getEntregaById(id)
        return if (response.isSuccessful) {
            response.body()?.first() ?: entregaEmpty
        } else {
            entregaEmpty
        }
    }

    suspend fun getEntregas(): List<Entrega> {
        return mRemoteEntrega.getEntregas()
    }

    suspend fun updateEntrega(entrega: Entrega): Entrega {
        return mRemoteEntrega.updateEntrega(
            tempo = entrega.tempo.toRequestBody("text/plain".toMediaTypeOrNull()),
            dataEntrega = entrega.dataEntrega.toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionarioId = entrega.funcionarioId.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            epiId = entrega.epiId.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            id = entrega.id
        ).body() ?: entregaEmpty
    }

    suspend fun deleteEntrega(id: Int): Boolean {
        return mRemoteEntrega.deleteEntregaById(id).isSuccessful
    }
}