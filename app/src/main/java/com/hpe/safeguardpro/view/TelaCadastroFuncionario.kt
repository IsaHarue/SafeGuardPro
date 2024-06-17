package com.hpe.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hpe.safeguardpro.databinding.FragmentTelaCadastroFuncionarioBinding
import com.hpe.safeguardpro.service.model.Funcionario
import com.hpe.safeguardpro.service.model.Login
import com.hpe.safeguardpro.viewmodel.FuncionarioViewModel


class TelaCadastroFuncionario : Fragment() {

    private val viewModel: FuncionarioViewModel by viewModels()

    private var _binding: FragmentTelaCadastroFuncionarioBinding? = null
    private val binding: FragmentTelaCadastroFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTelaCadastroFuncionarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            viewModel.getFuncionario( it.getInt("funcionarioId"))
        }

        binding.btnCadastrarFuncionario.setOnClickListener {
            val nome = binding.edtnome.editableText.toString()
            val email = binding.edtemail.editableText.toString()
            val cpf = binding.edtCpf.editableText.toString()
            val dataNasc = binding.edtData.editableText.toString()
            val admin = binding.cbAdmin.isChecked


            if (nome != "" && email != "" && cpf != "" && dataNasc != "") {
                val funcionario = Funcionario(
                    nome = nome,
                    email = email,
                    cpf = cpf,
                    dataNasc = dataNasc,
                    admin = admin
                )

                viewModel.createdfuncionario.value?.let {
                    funcionario.id = it.id
                    viewModel.update(funcionario)
                    viewModel.insert(funcionario)
                }

                findNavController().navigateUp()

                binding.edtnome.editableText.clear()
                binding.edtemail.editableText.clear()
                binding.edtCpf.editableText.clear()
                binding.edtData.editableText.clear()
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.createdfuncionario.observe(viewLifecycleOwner) {
            if (it.id == 0) {
                Toast.makeText(
                    requireContext(),
                    "Funcionario não foi possivel ser criado",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Funcionario ${it.nome} criada com sucesso",
                    Toast.LENGTH_LONG
                ).show()

                binding.edtnome.editableText.clear()
                binding.edtemail.editableText.clear()
                binding.edtCpf.editableText.clear()
                binding.edtData.editableText.clear()
                findNavController().navigateUp()
            }
        }
        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()

        }
    }
}