package com.hpe.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hpe.safeguardpro.databinding.FragmentListItemEntregaBinding
import com.hpe.safeguardpro.service.model.Entrega
import com.hpe.safeguardpro.service.model.Epi

class ItemEntregaAdapter(entregas: List<Entrega>?, private val clickListListener: (Entrega) -> Unit) :
    RecyclerView.Adapter<ItemEntregaAdapter.PessoaViewHolder>() {

    private var itemEntregaList: List<Entrega> = arrayListOf()

    class PessoaViewHolder(private val binding: FragmentListItemEntregaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //carrega as informacoes da pessoa na lista
        fun bind(entrega: Entrega, clickListListener: (Entrega) -> Unit) {
            //onde meche

            //configura o click de algum item da lista
            binding.root.setOnClickListener{
                clickListListener(entrega)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val listItemPessoaBinding =
            FragmentListItemEntregaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PessoaViewHolder(listItemPessoaBinding)
    }

    override fun getItemCount(): Int {
        return itemEntregaList.count()
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.bind(itemEntregaList[position], clickListListener)
    }

    //carrega a lista de pessoa para serem exibidas
    fun updateEntregas(list: List<Entrega>) {
        itemEntregaList = list
        notifyDataSetChanged()
    }
}