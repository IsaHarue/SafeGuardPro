package com.hpe.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hpe.safeguardpro.databinding.FragmentListItemPessoaBinding
import com.hpe.safeguardpro.service.model.Funcionario

class ItemPessoaAdapter(funcionarios: List<Funcionario>?, private val clickListListener: (Funcionario) -> Unit) :
    RecyclerView.Adapter<ItemPessoaAdapter.PessoaViewHolder>() {

    private var itemFuncionarioList: List<Funcionario> = arrayListOf()

    class PessoaViewHolder(private val binding: FragmentListItemPessoaBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val listItemPessoaBinding =
            FragmentListItemPessoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PessoaViewHolder(listItemPessoaBinding)
    }

    override fun getItemCount(): Int {
        return itemFuncionarioList.count()
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.bind(itemFuncionarioList[position], clickListListener)
    }

    //carrega a lista de pessoa para serem exibidas
    fun updatePessoas(list: List<Funcionario>) {
        itemFuncionarioList = list
        notifyDataSetChanged()
    }
}