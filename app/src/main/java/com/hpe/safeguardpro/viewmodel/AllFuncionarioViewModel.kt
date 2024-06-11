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

class AllFuncionarioViewModel (application: Application): AndroidViewModel(application) {

    private val repositoryF = FuncionarioRepository(application)

    private val mFuncionarioList = MutableLiveData<List<Funcionario>>()
    val funcionarioList: LiveData<List<Funcionario>> = mFuncionarioList

    private val mErro = MutableLiveData<String>()
    val erro: LiveData<String> = mErro

    fun load(api: Boolean) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                mFuncionarioList.postValue(repositoryF.getFuncionarios(api))
            } catch (e: Exception){
                mErro.postValue(e.message)
            }
        }
    }
}
