package com.hpe.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hpe.safeguardpro.R
import com.hpe.safeguardpro.databinding.FragmentTelaLoginBinding

class TelaLogin : Fragment() {
    // TODO: Rename and change types of parameters
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

        binding.btnLogar.setOnClickListener {
            findNavController().navigate(R.id.telaInicialFuncionario)
        }

        binding.tvcriarconta.setOnClickListener {
            findNavController().navigate(R.id.telaCadastroFuncionario)
        }

    }
}
