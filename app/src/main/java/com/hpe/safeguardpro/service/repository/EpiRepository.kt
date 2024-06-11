package com.hpe.safeguardpro.service.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hpe.safeguardpro.service.model.Epi
import com.hpe.safeguardpro.service.repository.local.SafeGuardProDataBase
import com.hpe.safeguardpro.service.repository.remote.EpiService
import com.hpe.safeguardpro.service.repository.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class EpiRepository(context: Context) {
    private val safeGuardProDataBase = SafeGuardProDataBase.getDataBase(context).EpiDAO()

    private val mRemoteEpi = RetrofitClient.createService(EpiService::class.java)

    private val epiEmpty = Epi(0, "", "", "", "")

    suspend fun insertEpi(epi: Epi): Epi {
        return mRemoteEpi.createEpi(
            nome = epi.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            dataFabricacao = epi.dataF.toRequestBody("text/plain".toMediaTypeOrNull()),
            dataCA = epi.dataCA.toRequestBody(("text/plain".toMediaTypeOrNull())),
            validade = epi.validade.toRequestBody("text/plain".toMediaTypeOrNull()),
            descricao = epi.descricao.toRequestBody("text/plain".toMediaTypeOrNull())
        ).body() ?: epiEmpty
    }

    suspend fun getEpi(id: Int): Epi {
        val response = mRemoteEpi.getEpiById(id)
        return if (response.isSuccessful) {
            response.body()?.first() ?: epiEmpty
        } else {
            epiEmpty
        }
    }

    suspend fun getEpis(): List<Epi> {
        return mRemoteEpi.getEpis()
    }

    suspend fun updateEpi(epi: Epi): Epi {
        return mRemoteEpi.updateEpi(
            nome = epi.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            dataFabricacao = epi.dataF.toRequestBody("text/plain".toMediaTypeOrNull()),
            validade = epi.validade.toRequestBody("text/plain".toMediaTypeOrNull()),
            descricao = epi.descricao.toRequestBody("text/plain".toMediaTypeOrNull()),
            id = epi.id
        ).body() ?: epiEmpty
    }

    suspend fun deleteEpi(id: Int): Boolean {
        return mRemoteEpi.deleteEpiById(id).isSuccessful
    }
}