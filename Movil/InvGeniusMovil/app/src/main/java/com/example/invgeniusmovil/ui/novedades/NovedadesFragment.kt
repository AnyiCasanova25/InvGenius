package com.example.invgeniusmovil.ui.novedades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.invgeniusmovil.databinding.FragmentNovedadesBinding

class NovedadesFragment : Fragment() {

    private var _binding: FragmentNovedadesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val novedadesViewModel =
            ViewModelProvider(this).get(NovedadesViewModel::class.java)

        _binding = FragmentNovedadesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNovedades
        novedadesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


        binding.btnNovedadesEmpleados.setOnClickListener {mostrarFragment(binding.btnNovedadesEmpleados, binding.btnOculto1)}
        binding.btnProductosCaducados.setOnClickListener {mostrarFragment(binding.btnProductosCaducados, binding.btnOculto2)}
        binding.btnProductoaCaducar.setOnClickListener {mostrarFragment(binding.btnProductoaCaducar, binding.btnOculto3)}
        binding.btnBajoStock.setOnClickListener {mostrarFragment(binding.btnBajoStock, binding.btnOculto4)}
        return root
    }

    fun mostrarFragment(buttonMostrar: Button, btnOculto: Button){
        if(binding.btnNovedadesEmpleados!=buttonMostrar)
            binding.btnOculto1.visibility= View.GONE
        if(binding.btnProductosCaducados!=buttonMostrar)
            binding.btnOculto2.visibility=View.GONE
        if(binding.btnProductoaCaducar!=buttonMostrar)
            binding.btnOculto3.visibility=View.GONE
        if(binding.btnBajoStock!=buttonMostrar)
            binding.btnOculto4.visibility=View.GONE
        if (btnOculto.isVisible)
            btnOculto.visibility=View.GONE
        else
            btnOculto.visibility= View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}