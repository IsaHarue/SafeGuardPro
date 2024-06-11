package com.hpe.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hpe.safeguardpro.databinding.FragmentListItemEpiBinding
import com.hpe.safeguardpro.service.model.Epi

class ItemEpiAdapter(Epis: List<Epi>?, private val clickListListener: (Epi) -> Unit) :
    RecyclerView.Adapter<ItemEpiAdapter.EpiViewHolder>() {

    private var itemEpiList: List<Epi> = arrayListOf()

    class EpiViewHolder(private val binding: FragmentListItemEpiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //carrega as informacoes da pessoa na lista
        fun bind(epi: Epi, clickListListener: (Epi) -> Unit) {
            //onde meche

            //configura o click de algum item da lista
            binding.root.setOnClickListener{
                clickListListener(epi)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpiViewHolder {
        val listItemEpiBinding =
            FragmentListItemEpiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpiViewHolder(listItemEpiBinding)
    }

    override fun getItemCount(): Int {
        return itemEpiList.count()
    }

    override fun onBindViewHolder(holder: EpiViewHolder, position: Int) {
        holder.bind(itemEpiList[position], clickListListener)
    }

    //carrega a lista de pessoa para serem exibidas
    fun updateEpis(list: List<Epi>) {
        itemEpiList = list
        notifyDataSetChanged()
    }
}