package com.hpe.safeguardpro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hpe.safeguardpro.service.model.Funcionario
import com.hpe.safeguardpro.service.repository.FuncionarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FuncionarioViewModel (application: Application) : AndroidViewModel(application) {
    private val Frepository = FuncionarioRepository(application)

    private val mCreatedFuncionario = MutableLiveData<Funcionario>()
    val createdfuncionario: LiveData<Funcionario> = mCreatedFuncionario

    private val mErro = MutableLiveData<String>()
    val erro: LiveData<String> = mErro

    private val mDeletedFuncionrio = MutableLiveData<Boolean>()
    val deletedFuncionario: LiveData<Boolean> = mDeletedFuncionrio

    fun insert(api: Boolean, funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdFuncionario = Frepository.insertFuncionario(api, funcionario)
                mCreatedFuncionario.postValue(createdFuncionario)
            } catch (e: Exception){
                mErro.postValue(e.message)
            }
        }
    }

    fun getFuncionario(api: Boolean, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mCreatedFuncionario.postValue(Frepository.getFuncionario(api, id))
            }catch (e: Exception){
                mErro.postValue(e.message)
            }
        }
    }

    fun update(api: Boolean, funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
            Frepository.updateFuncionario(api, funcionario.id, funcionario)
        }
    }

    fun delete(api: Boolean, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mDeletedFuncionrio.postValue(Frepository.deleteFuncionario(api, id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }
}
