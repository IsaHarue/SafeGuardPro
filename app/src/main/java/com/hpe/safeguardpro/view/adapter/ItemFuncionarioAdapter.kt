package com.hpe.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hpe.safeguardpro.databinding.FragmentListItemFuncionarioBinding
import com.hpe.safeguardpro.service.model.Epi
import com.hpe.safeguardpro.service.model.Funcionario

class ItemFuncionarioAdapter(funcionarios: List<Funcionario>?, private val clickListListener: (Funcionario) -> Unit) :
    RecyclerView.Adapter<ItemFuncionarioAdapter.FuncionarioViewHolder>() {

    private var itemFuncionarioList: List<Funcionario> = arrayListOf()

    class FuncionarioViewHolder(private val binding: FragmentListItemFuncionarioBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //carrega as informacoes da pessoa na lista
        fun bind(funcionario: Funcionario, clickListListener: (Funcionario) -> Unit) {
            //onde meche

            //configura o click de algum item da lista
            binding.root.setOnClickListener{
                clickListListener(funcionario)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncionarioViewHolder {
        val listItemPessoaBinding =
            FragmentListItemFuncionarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FuncionarioViewHolder(listItemPessoaBinding)
    }

    override fun getItemCount(): Int {
        return itemFuncionarioList.count()
    }

    override fun onBindViewHolder(holder: FuncionarioViewHolder, position: Int) {
        holder.bind(itemFuncionarioList[position], clickListListener)
    }

    //carrega a lista de pessoa para serem exibidas
    fun updateFuncionarios(list: List<Funcionario>) {
        itemFuncionarioList = list
        notifyDataSetChanged()
    }
}