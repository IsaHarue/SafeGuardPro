package com.hpe.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hpe.safeguardpro.R
import com.hpe.safeguardpro.databinding.FragmentTelaEntregaBinding
import com.hpe.safeguardpro.databinding.FragmentTelaRelatorioFuncionarioBinding

class TelaEntrega : Fragment() {
    private var _binding: FragmentTelaEntregaBinding? = null
    private val binding: FragmentTelaEntregaBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelaEntregaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddEntrega.setOnClickListener {
            findNavController().navigate(R.id.telaCadastroEntrega)
        }


    }
}