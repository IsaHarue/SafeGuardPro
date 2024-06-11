package com.hpe.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.hpe.safeguardpro.R
import com.hpe.safeguardpro.databinding.FragmentTelaDetalhesFuncionarioBinding
import com.hpe.safeguardpro.viewmodel.FuncionarioViewModel
import java.time.LocalDateTime

class TelaDetalhesFuncionario : Fragment() {
    // Chamar a viewModel para pegar dos dados
    private val viewModel: FuncionarioViewModel by viewModels()

    //Criar o binding para pegar os elementos da tela
    private var _binding: FragmentTelaDetalhesFuncionarioBinding? = null
    private val binding: FragmentTelaDetalhesFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //configurar o binding (sempre segue o xml)
        _binding = FragmentTelaDetalhesFuncionarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    //chamar a função OnViewCreated onde vamos implementar o código
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //pegar o id da pessoa que foi enviado pela AllPessoaFragment
        // Carregar a pessoa caso tenha selecionado
        //setara a pessoa para ser carregada na tela
        arguments?.let {
            viewModel.getFuncionario( it.getInt("pessoaId"))

        }
        //carregar inormacoes
        viewModel.createdfuncionario.observe(viewLifecycleOwner) { funcionario ->
            binding.tvNome.setText(funcionario.nome)
            binding.tvEmail.setText(funcionario.email)
            binding.tvCpf.setText(funcionario.cpf)
            binding.tvData.setText(funcionario.dataNasc)

        }
    }
}