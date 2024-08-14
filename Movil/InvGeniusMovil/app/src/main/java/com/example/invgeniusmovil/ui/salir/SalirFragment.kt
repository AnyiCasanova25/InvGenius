package com.example.invgeniusmovil.ui.salir

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.invgeniusmovil.databinding.FragmentSalirBinding

class SalirFragment : Fragment() {

    private var _binding: FragmentSalirBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val salirViewModel =
            ViewModelProvider(this).get(SalirViewModel::class.java)

        _binding = FragmentSalirBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val textView: TextView = binding.textSalir
        salirViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}