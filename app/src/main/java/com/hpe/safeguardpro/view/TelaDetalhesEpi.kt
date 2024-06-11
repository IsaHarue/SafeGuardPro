package com.hpe.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.hpe.safeguardpro.R
import com.hpe.safeguardpro.databinding.FragmentTelaCadastroFuncionarioBinding
import com.hpe.safeguardpro.databinding.FragmentTelaDetalhesEpiBinding
import com.hpe.safeguardpro.viewmodel.EpiViewModel
import com.hpe.safeguardpro.viewmodel.FuncionarioViewModel

class TelaDetalhesEpi : Fragment() {
    // Chamar a viewModel para pegar dos dados
    private val viewModel: EpiViewModel by viewModels()

    private var _binding: FragmentTelaDetalhesEpiBinding? = null
    private val binding: FragmentTelaDetalhesEpiBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTelaDetalhesEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //pegar o id da pessoa que foi enviado pela AllPessoaFragment
        // Carregar a pessoa caso tenha selecionado
        //setara a pessoa para ser carregada na tela
        arguments?.let {
            viewModel.getEpi( it.getInt("pessoaId"))

        }
        //carregar inormacoes
        viewModel.createdepi.observe(viewLifecycleOwner) { epi ->
            binding.tvnome.setText(epi.nome)
            binding.tvDataF.setText(epi.dataF)
            binding.tvValidade.setText(epi.validade)
            binding.tvDescricao.setText(epi.descricao)

        }
    }

}