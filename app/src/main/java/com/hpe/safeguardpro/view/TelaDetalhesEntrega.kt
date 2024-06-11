package com.hpe.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.hpe.safeguardpro.R
import com.hpe.safeguardpro.databinding.FragmentTelaDetalhesEntregaBinding
import com.hpe.safeguardpro.databinding.FragmentTelaDetalhesFuncionarioBinding
import com.hpe.safeguardpro.viewmodel.EntregaViewModel
import com.hpe.safeguardpro.viewmodel.FuncionarioViewModel


class TelaDetalhesEntrega : Fragment() {
    private val viewModel: EntregaViewModel by viewModels()

    //Criar o binding para pegar os elementos da tela
    private var _binding: FragmentTelaDetalhesEntregaBinding? = null
    private val binding: FragmentTelaDetalhesEntregaBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTelaDetalhesEntregaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //pegar o id da pessoa que foi enviado pela AllPessoaFragment
        // Carregar a pessoa caso tenha selecionado
        //setara a pessoa para ser carregada na tela
        arguments?.let {
            viewModel.getEntrega( it.getInt("entregaId"))

        }
        //carregar inormacoes
        viewModel.createdentrega.observe(viewLifecycleOwner) { entrega ->
            binding.tvtempo.setText(entrega.tempo)
            binding.tvtextoDataEntrega.setText(entrega.dataEntrega)
            binding.tvtextoFuncionario.setText(entrega.funcionarioId)
            binding.tvtextoEpi.setText(entrega.epiId)

        }
    }
}