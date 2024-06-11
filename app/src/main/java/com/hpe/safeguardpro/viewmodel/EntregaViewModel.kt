package com.hpe.safeguardpro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hpe.safeguardpro.service.model.Entrega
import com.hpe.safeguardpro.service.repository.EntregaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntregaViewModel (application: Application) : AndroidViewModel(application) {
    private val repository = EntregaRepository(application)

    private val mEntregaList = MutableLiveData<List<Entrega>>()
    val entregaList: LiveData<List<Entrega>> = mEntregaList

    private val mCreatedEntrega = MutableLiveData<Entrega>()
    val createdentrega: LiveData<Entrega> = mCreatedEntrega

    private val mErro = MutableLiveData<String>()
    val erro: LiveData<String> = mErro

    private val mDeletedEntrega = MutableLiveData<Boolean>()
    val deletedEntrega: LiveData<Boolean> = mDeletedEntrega

    fun load() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                mEntregaList.postValue(repository.getEntregas())
            } catch (e: Exception){
                mErro.postValue(e.message)
            }
        }
    }

    fun insert(entrega: Entrega) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdEntrega = repository.insertEntrega(entrega)
                mCreatedEntrega.postValue(createdEntrega)
            } catch (e: Exception){
                mErro.postValue(e.message)
            }
        }
    }

    fun getEntrega(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mCreatedEntrega.postValue(repository.getEntrega(id))
            }catch (e: Exception){
                mErro.postValue(e.message)
            }
        }
    }

    fun update(entrega: Entrega) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEntrega(entrega)
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mDeletedEntrega.postValue(repository.deleteEntrega(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }
}
