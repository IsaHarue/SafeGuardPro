package com.hpe.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hpe.safeguardpro.R
import com.hpe.safeguardpro.databinding.FragmentTelaInicialBinding
import com.hpe.safeguardpro.databinding.FragmentTelaLoginBinding

class TelaInicial : Fragment() {
    private var _binding: FragmentTelaInicialBinding? = null
    private val binding: FragmentTelaInicialBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTelaInicialBinding.inflate(inflater, container, false)
        return binding.root
    }


}