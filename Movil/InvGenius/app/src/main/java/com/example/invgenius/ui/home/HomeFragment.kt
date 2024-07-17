package com.example.invgenius.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.invgenius.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }/*
        binding.btnNovedadesEmpleados.setOnClickListener {
            if (binding.btnOculto1.isVisible){
                binding.btnOculto1.visibility=View.GONE
            }else{
                binding.btnOculto1.visibility= View.VISIBLE
            }
            binding.btnOculto2.visibility=View.GONE
            binding.btnOculto3.visibility=View.GONE
        }
        binding.btnProductosCaducados.setOnClickListener {
            if (binding.btnOculto2.isVisible){
                binding.btnOculto2.visibility=View.GONE
            }else{
                binding.btnOculto2.visibility= View.VISIBLE
            }
            binding.btnOculto1.visibility= View.GONE
            binding.btnOculto3.visibility=View.GONE
        }
        binding.btnProductoaCaducar.setOnClickListener {
            if (binding.btnOculto3.isVisible){
                binding.btnOculto3.visibility=View.GONE
            }else{
                binding.btnOculto3.visibility= View.VISIBLE
            }
            binding.btnOculto1.visibility= View.GONE
            binding.btnOculto2.visibility=View.GONE
        }
        */
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