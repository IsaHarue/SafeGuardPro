package com.hpe.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hpe.safeguardpro.R
import com.hpe.safeguardpro.databinding.FragmentTelaLoginBinding
import com.hpe.safeguardpro.service.model.Login
import com.hpe.safeguardpro.viewmodel.FuncionarioViewModel

class TelaLogin : Fragment() {
    private val viewModelFuncionario: FuncionarioViewModel by viewModels()

    private var _binding: FragmentTelaLoginBinding? = null
    private val binding: FragmentTelaLoginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelaLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var senha = ""
        var cpf = ""

        binding.btnLogar.setOnClickListener {
            cpf = binding.edtcpf.editableText.toString()
            senha = binding.edtsenha.editableText.toString()

            if ((cpf.isBlank() || cpf.isEmpty()) || (senha.isBlank() || senha.isEmpty())) {
                Toast.makeText(requireContext(), "Preencha os campos", Toast.LENGTH_LONG).show()
            } else {
                viewModelFuncionario.getFuncionarioByCpf(cpf.toInt())
            }
        }

        viewModelFuncionario.funcionario.observe(viewLifecycleOwner) {
            if (it.senha == senha && it.cpf == cpf){
                Login.userConected(it.id, it.cpf, it.admin)

                if (it.admin) {
                    findNavController().navigate(R.id.telaInicioGestor)
                } else {
                    findNavController().navigate(R.id.telaInicialFuncionario)
                }
            } else {
                Toast.makeText(requireContext(), "Usuario ou senha inválidos", Toast.LENGTH_LONG).show()
            }
        }

        binding.tvcriarconta.setOnClickListener {
            findNavController().navigate(R.id.telaCadastro)
        }

    }
}
