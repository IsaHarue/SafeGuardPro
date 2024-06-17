package com.hpe.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hpe.safeguardpro.R
import com.hpe.safeguardpro.databinding.FragmentTelaEntregaBinding
import com.hpe.safeguardpro.view.adapter.ItemEntregaAdapter
import com.hpe.safeguardpro.viewmodel.EntregaViewModel

class TelaEntrega : Fragment() {

    private val viewModel: EntregaViewModel by viewModels()

    //chamar o adapter
    private lateinit var adapter: ItemEntregaAdapter

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

        adapter = ItemEntregaAdapter(viewModel.entregaList.value) { entrega ->
            val entregaBundle = Bundle()
            entregaBundle.putInt("entregaId", entrega.id)
            arguments = entregaBundle
            findNavController().navigate(R.id.telaDetalhesEntrega, arguments)

            // Configura a recycler
            val recycler = binding.rvEntregas
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter

            // Observa para adicionar um item na lista quando for adicionado
            viewModel.entregaList.observe(viewLifecycleOwner) {
                adapter.updateEntregas(it)
            }

            binding.btnAddEntrega.setOnClickListener {
                findNavController().navigate(R.id.telaCadastroEntrega)
            }


        }
    }
}