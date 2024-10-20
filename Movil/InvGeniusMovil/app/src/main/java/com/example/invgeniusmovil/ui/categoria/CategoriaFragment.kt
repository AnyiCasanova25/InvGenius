package com.example.invgeniusmovil.ui.categoria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.invgeniusmovil.databinding.FragmentCategoriaBinding

class CategoriaFragment : Fragment() {

    private var _binding: FragmentCategoriaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val categoriaViewModel =
            ViewModelProvider(this).get(CategoriaViewModel::class.java)

        _binding = FragmentCategoriaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCategoria
        categoriaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}