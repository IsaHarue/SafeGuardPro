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
import com.hpe.safeguardpro.databinding.FragmentTelaRelatorioFuncionarioBinding
import com.hpe.safeguardpro.view.adapter.ItemFuncionarioAdapter
import com.hpe.safeguardpro.viewmodel.FuncionarioViewModel

class TelaRelatorioFuncionario : Fragment() {

    //chamar a viewmodel
    private val viewModel: FuncionarioViewModel by viewModels()

    //chamar o adapter
    private lateinit var adapter: ItemFuncionarioAdapter

    //criar o  binding
    private var _binding: FragmentTelaRelatorioFuncionarioBinding? = null
    private val binding: FragmentTelaRelatorioFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelaRelatorioFuncionarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //quando clicar em algum item da lista
        adapter = ItemFuncionarioAdapter(viewModel.funcionarioList.value){funcionario ->
            val funcionarioBundle = Bundle()
            funcionarioBundle.putInt("funcionarioId", funcionario.id)
            arguments = funcionarioBundle
            findNavController().navigate(R.id.telaDetalhesFuncionario, arguments)
        }

        // Configura a recycler
        val recycler = binding.rvFuncionarios
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        // Observa para adicionar um item na lista quando for adicionado
        viewModel.funcionarioList.observe(viewLifecycleOwner) {
            adapter.updateFuncionarios(it)
        }

        //navegar para a tela de cadastro de pessoa
        binding.btnAddFuncionario.setOnClickListener {
            findNavController().navigate(R.id.telaCadastroFuncionario)
        }

        //Carrega as pessoas e popula a lista
        viewModel.load()
    }
}