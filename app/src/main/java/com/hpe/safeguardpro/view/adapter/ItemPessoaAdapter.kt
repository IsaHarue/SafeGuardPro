package com.hpe.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hpe.safeguardpro.databinding.FragmentListItemPessoaBinding
import com.hpe.safeguardpro.service.model.Pessoa

class ItemPessoaAdapter(pessoas: List<Pessoa>?, private val clickListListener: (Pessoa) -> Unit) :
    RecyclerView.Adapter<ItemPessoaAdapter.PessoaViewHolder>() {

    private var itemPessoaList: List<Pessoa> = arrayListOf()

    class PessoaViewHolder(private val binding: FragmentListItemPessoaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //carrega as informacoes da pessoa na lista
        fun bind(pessoa: Pessoa, clickListListener: (Pessoa) -> Unit) {
            //onde meche

            //configura o click de algum item da lista
            binding.root.setOnClickListener{
                clickListListener(pessoa)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val listItemPessoaBinding =
            FragmentListItemPessoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PessoaViewHolder(listItemPessoaBinding)
    }

    override fun getItemCount(): Int {
        return itemPessoaList.count()
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.bind(itemPessoaList[position], clickListListener)
    }

    //carrega a lista de pessoa para serem exibidas
    fun updatePessoas(list: List<Pessoa>) {
        itemPessoaList = list
        notifyDataSetChanged()
    }
}