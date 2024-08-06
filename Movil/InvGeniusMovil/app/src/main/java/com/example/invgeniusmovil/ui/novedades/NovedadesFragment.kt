package com.example.invgeniusmovil.ui.novedades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.invgeniusmovil.R
import com.example.invgeniusmovil.adapters.adapterNovedadesDeUsuario
import com.example.invgeniusmovil.databinding.FragmentNovedadesBinding
import com.example.invgeniusmovil.models.novedades_de_usuario

class NovedadesFragment : Fragment() {

    private var _binding: FragmentNovedadesBinding? = null
    private val binding get() = _binding!!

    private lateinit var novedadesViewModel: NovedadesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inicializa el ViewModel
        novedadesViewModel = ViewModelProvider(this).get(NovedadesViewModel::class.java)

        // Infla el layout y configura el binding
        _binding = FragmentNovedadesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observa el texto del ViewModel
        novedadesViewModel.text.observe(viewLifecycleOwner) { text ->
            binding.textNovedades.text = text
        }

        // Configura los botones para mostrar los LinearLayouts correspondientes
        binding.btnNovedadesEmpleados.setOnClickListener {
            mostrarFragment(binding.btnNovedadesEmpleados, binding.Linear1)
        }
        binding.btnProductosCaducados.setOnClickListener {
            mostrarFragment(binding.btnProductosCaducados, binding.Linear2)
        }
        binding.btnProductoaCaducar.setOnClickListener {
            mostrarFragment(binding.btnProductoaCaducar, binding.Linear3)
        }
        binding.btnBajoStock.setOnClickListener {
            mostrarFragment(binding.btnBajoStock, binding.Linear4)
        }

        // Configuración del RecyclerView
        val listNovedadesDeUsuario = mutableListOf(
            novedades_de_usuario("1", "10/08/2024"),
            novedades_de_usuario("2", "30/01/204")
        )

        val recycler: RecyclerView = binding.RVNovedadesUsuario
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapterNovedadesDeUsuario(listNovedadesDeUsuario)
    }

    fun mostrarFragment(buttonMostrar: Button, Linear: LinearLayout) {
        // Ocultar todas las vistas cuando el botón no coincide
        if (binding.btnNovedadesEmpleados != buttonMostrar) {
            binding.Linear1.visibility = View.GONE
        }
        if (binding.btnProductosCaducados != buttonMostrar) {
            binding.Linear2.visibility = View.GONE
        }
        if (binding.btnProductoaCaducar != buttonMostrar) {
            binding.Linear3.visibility = View.GONE
        }
        if (binding.btnBajoStock != buttonMostrar) {
            binding.Linear4.visibility = View.GONE
        }

        // Alternar la visibilidad de RVOculto y barrabusqueda
        if (Linear.isVisible) {
            Linear.visibility = View.GONE
        } else {
            Linear.visibility = View.VISIBLE
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




