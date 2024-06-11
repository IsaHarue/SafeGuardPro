package com.hpe.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hpe.safeguardpro.R
import com.hpe.safeguardpro.databinding.FragmentTelaInicioGestorBinding
import com.hpe.safeguardpro.databinding.FragmentTelaLoginBinding

class TelaInicioGestor : Fragment() {
    private var _binding: FragmentTelaInicioGestorBinding? = null
    private val binding: FragmentTelaInicioGestorBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelaInicioGestorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardEpi.setOnClickListener {
            findNavController().navigate(R.id.telaRelatorioEpi)
        }

        binding.cardFuncionario.setOnClickListener {
            findNavController().navigate(R.id.telaRelatorioFuncionario)
        }

        binding.cardEntrega.setOnClickListener {
            findNavController().navigate(R.id.telaEntrega)
        }

    }
}