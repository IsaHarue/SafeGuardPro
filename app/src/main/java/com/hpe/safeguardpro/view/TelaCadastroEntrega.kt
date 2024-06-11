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
import com.hpe.safeguardpro.databinding.FragmentTelaCadastroEntregaBinding
import com.hpe.safeguardpro.service.model.Entrega
import com.hpe.safeguardpro.viewmodel.EntregaViewModel

class TelaCadastroEntrega : Fragment() {
    private val viewModel: EntregaViewModel by viewModels()

    private var _binding: FragmentTelaCadastroEntregaBinding? = null
    private val binding: FragmentTelaCadastroEntregaBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTelaCadastroEntregaBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            viewModel.getEntrega( it.getInt("entregaId"))
        }

        binding.btnEmprestimo.setOnClickListener {
            val funcionario = binding.edtnome.editableText.toString()
            val epi = binding.edtepi.editableText.toString()
            val entrega = binding.edtEntrega.editableText.toString()
            val validade = binding.edtValidade.editableText.toString()

            val tempo = validade - entrega

            if (funcionario != "" && epi != "" && entrega != "" && validade != "") {
                val entrega = Entrega(
                    funcionarioId = funcionario,
                    epiId = epi,
                    dataEntrega = entrega,
                    tempo = tempo
                )

                viewModel.createdentrega.value?.let {
                    entrega.id = it.id
                    viewModel.update(entrega)
                    viewModel.insert(entrega)
                }

                findNavController().navigateUp()

                binding.edtnome.editableText.clear()
                binding.edtepi.editableText.clear()
                binding.edtEntrega.editableText.clear()
                binding.edtValidade.editableText.clear()
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }

            viewModel.createdentrega.observe(viewLifecycleOwner) {
                if (it.id == 0) {
                    Toast.makeText(
                        requireContext(),
                        "Entrega não foi possivel ser criado",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Entrega ${it.id} criada com sucesso",
                        Toast.LENGTH_LONG
                    ).show()

                    binding.edtnome.editableText.clear()
                    binding.edtepi.editableText.clear()
                    binding.edtEntrega.editableText.clear()
                    binding.edtValidade.editableText.clear()
                    findNavController().navigateUp()
                }
            }
            viewModel.erro.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()

            }
        }

    }

}