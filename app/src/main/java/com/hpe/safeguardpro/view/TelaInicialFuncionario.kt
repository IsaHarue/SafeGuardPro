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
import com.hpe.safeguardpro.databinding.FragmentTelaEntregaBinding
import com.hpe.safeguardpro.databinding.FragmentTelaInicialFuncionarioBinding
import com.hpe.safeguardpro.view.adapter.ItemFuncionarioAdapter
import com.hpe.safeguardpro.viewmodel.FuncionarioViewModel


class TelaInicialFuncionario : Fragment() {

    private val viewModel: FuncionarioViewModel by viewModels()

    //chamar o adapter
    private lateinit var adapter: ItemFuncionarioAdapter

    private var _binding: FragmentTelaInicialFuncionarioBinding? = null
    private val binding: FragmentTelaInicialFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTelaInicialFuncionarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ItemFuncionarioAdapter(viewModel.funcionarioList.value){funcionario ->
            val funcionarioBundle = Bundle()
            funcionarioBundle.putInt("funcionarioId", funcionario.id)
            arguments = funcionarioBundle
            findNavController().navigate(R.id.telaDetalhesFuncionario, arguments)

            // Configura a recycler
            val recycler = binding.rvFuncionarios
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter

            // Observa para adicionar um item na lista quando for adicionado
            viewModel.funcionarioList.observe(viewLifecycleOwner) {
                adapter.updateFuncionarios(it)
            }

            //Carrega as pessoas e popula a lista
            viewModel.load()
        }
    }
}