package com.hpe.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hpe.safeguardpro.databinding.FragmentTelaCadastroEpiBinding
import com.hpe.safeguardpro.service.model.Epi
import com.hpe.safeguardpro.viewmodel.EpiViewModel

class TelaCadastroEpi : Fragment() {

    private val viewModel: EpiViewModel by viewModels()

    private var _binding: FragmentTelaCadastroEpiBinding? = null
    private val binding: FragmentTelaCadastroEpiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTelaCadastroEpiBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            viewModel.getEpi(it.getInt("epiId"))
        }

        binding.btnCadastrarEpi.setOnClickListener {
            val nome = binding.edtnome.editableText.toString()
            val dataF = binding.edtDataF.editableText.toString()
            val validadade = binding.edtvalidade.editableText.toString()
            val descricao = binding.edtdescricao.editableText.toString()

            if (nome != "" && dataF != "" && validadade != "" && descricao != "") {
                val epi = Epi(
                    nome = nome,
                    dataF = dataF,
                    validade = validadade,
                    descricao = descricao
                )

                viewModel.createdepi.value?.let {
                    epi.id = it.id
                    viewModel.update(epi)
                    viewModel.insert(epi)
                }

                findNavController().navigateUp()

                binding.edtnome.editableText.clear()
                binding.edtDataF.editableText.clear()
                binding.edtvalidade.editableText.clear()
                binding.edtdescricao.editableText.clear()
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.createdepi.observe(viewLifecycleOwner) {
            if (it.id == 0) {
                Toast.makeText(
                    requireContext(),
                    "Epi não foi possivel ser criado",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Epi ${it.nome} criada com sucesso",
                    Toast.LENGTH_LONG
                ).show()

                binding.edtnome.editableText.clear()
                binding.edtDataF.editableText.clear()
                binding.edtvalidade.editableText.clear()
                binding.edtdescricao.editableText.clear()
                findNavController().navigateUp()
            }
        }
        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()

        }

    }
}