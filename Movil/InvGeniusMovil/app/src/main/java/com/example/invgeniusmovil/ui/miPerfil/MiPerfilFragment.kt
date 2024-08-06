package com.example.invgeniusmovil.ui.miPerfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.invgeniusmovil.databinding.FragmentMiPerfilBinding

class MiPerfilFragment : Fragment() {

    private var _binding: FragmentMiPerfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        val textView: TextView = binding.textMiPerfil
        miPerfilViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}