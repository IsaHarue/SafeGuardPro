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
    private val repository = FuncionarioRepository(application)

    private val mFuncionario = MutableLiveData<Funcionario>()
    val funcionario: LiveData<Funcionario> = mFuncionario

    private val mFuncionarioList = MutableLiveData<List<Funcionario>>()
    val funcionarioList: LiveData<List<Funcionario>> = mFuncionarioList

    private val mCreatedFuncionario = MutableLiveData<Funcionario>()
    val createdfuncionario: LiveData<Funcionario> = mCreatedFuncionario

    private val mErro = MutableLiveData<String>()
    val erro: LiveData<String> = mErro

    private val mDeletedFuncionrio = MutableLiveData<Boolean>()
    val deletedFuncionario: LiveData<Boolean> = mDeletedFuncionrio

    fun load() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                mFuncionarioList.postValue(repository.getFuncionarios())
            } catch (e: Exception){
                mErro.postValue(e.message)
            }
        }
    }

    fun insert(funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdFuncionario = repository.insertFuncionario( funcionario)
                mCreatedFuncionario.postValue(createdFuncionario)
            } catch (e: Exception){
                mErro.postValue(e.message)
            }
        }
    }

    fun getFuncionario(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mFuncionario.postValue(repository.getFuncionario(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun getFuncionarioByCpf(cpf: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mFuncionario.postValue(repository.getFuncionarioByCpf(cpf))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun update(funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdFuncionario = repository.updateFuncionario(funcionario)
                mCreatedFuncionario.postValue(createdFuncionario)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mDeletedFuncionrio.postValue(repository.deleteFuncionario(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }
}
