package com.example.invgeniusmovil.ui.miPerfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.invgeniusmovil.databinding.FragmentMiPerfilBinding

class MiPerfilFragment : Fragment() {

    private var _binding: FragmentMiPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val miPerfilViewModel =
            ViewModelProvider(this).get(MiPerfilViewModel::class.java)

        _binding = FragmentMiPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Aquí puedes añadir otras vistas o lógica si es necesario

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
