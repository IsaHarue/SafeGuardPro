package com.hpe.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hpe.safeguardpro.R
import com.hpe.safeguardpro.databinding.FragmentTelaRelatorioEpiBinding
import com.hpe.safeguardpro.view.adapter.ItemEpiAdapter
import com.hpe.safeguardpro.viewmodel.EpiViewModel

class TelaRelatorioEpi : Fragment() {
    //chamar a viewmodel
    private val viewModel: EpiViewModel by viewModels()

    //chamar o adapter
    private lateinit var adapter: ItemEpiAdapter

    private var _binding: FragmentTelaRelatorioEpiBinding? = null
    private val binding: FragmentTelaRelatorioEpiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelaRelatorioEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //quando clicar em algum item da lista
        adapter = ItemEpiAdapter(viewModel.epiList.value){epi ->
            val epiBundle = Bundle()
            epiBundle.putInt("epiId", epi.id)
            arguments = epiBundle
//            findNavController().navigate(R.id.pessoaFragment, arguments)
            findNavController().navigate(R.id.telaDetalhesEpi, arguments)
        }

        // Configura a recycler
        val recycler = binding.rvepis
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        // Observa para adicionar um item na lista quando for adicionado
        viewModel.epiList.observe(viewLifecycleOwner) {
            adapter.updateEpis(it)
        }

        //navegar para a tela de cadastro de pessoa
        binding.btnAddEpi.setOnClickListener {
            findNavController().navigate(R.id.telaCadastroEpi)
        }

        //Carrega as pessoas e popula a lista
        viewModel.load()
    }
}