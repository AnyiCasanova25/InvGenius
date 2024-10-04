package com.example.invgeniusmovil.ui.novedades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.invgeniusmovil.adapters.Novedades.adapterBajoStock
import com.example.invgeniusmovil.adapters.Novedades.adapterProductosACaducar
import com.example.invgeniusmovil.adapters.Novedades.adapterProductosCaducados
import com.example.invgeniusmovil.adapters.Novedades.adapterUsuario
import com.example.invgeniusmovil.databinding.FragmentNovedadesBinding
import com.example.invgeniusmovil.models.Novedades.bajo_stock
import com.example.invgeniusmovil.models.Novedades.productos_a_caducar
import com.example.invgeniusmovil.models.Novedades.productos_caducados

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

        // Configura los botones para mostrar los LinearLayouts correspondiente
        binding.btnProductosCaducados.setOnClickListener {
            mostrarFragment(binding.btnProductosCaducados, binding.Linear2)
        }
        binding.btnProductoaCaducar.setOnClickListener {
            mostrarFragment(binding.btnProductoaCaducar, binding.Linear3)
        }
        binding.btnBajoStock.setOnClickListener {
            mostrarFragment(binding.btnBajoStock, binding.Linear4)
        }

        val listNovedadesDeProductosCaducados = mutableListOf(
            productos_caducados("1", "10/08/2024"),
            productos_caducados("2", "10/08/2024"),
            productos_caducados("3", "10/08/2024"),
            productos_caducados("4", "10/08/2024"),
            productos_caducados("5", "10/08/2024"),
            productos_caducados("6", "10/08/2024"),
            productos_caducados("7", "10/08/2024"),
            productos_caducados("8", "10/08/2024"),
            productos_caducados("9", "10/08/2024"),
            productos_caducados("10", "10/08/2024")
        )

        val recycler2: RecyclerView = binding.RVProductosCaducados
        recycler2.layoutManager = LinearLayoutManager(context)
        recycler2.adapter = adapterProductosCaducados(listNovedadesDeProductosCaducados)



        val listNovedadesDeProductosACaducar = mutableListOf(
            productos_a_caducar("1", "10/08/2024"),
            productos_a_caducar("2", "10/08/2024"),
            productos_a_caducar("3", "10/08/2024"),
            productos_a_caducar("4", "10/08/2024"),
            productos_a_caducar("5", "10/08/2024"),
            productos_a_caducar("6", "10/08/2024"),
            productos_a_caducar("7", "10/08/2024"),
            productos_a_caducar("8", "10/08/2024"),
            productos_a_caducar("9", "10/08/2024"),
            productos_a_caducar("10", "10/08/2024")
        )

        val recycler3: RecyclerView = binding.RVProductosACaducar
        recycler3.layoutManager = LinearLayoutManager(context)
        recycler3.adapter = adapterProductosACaducar(listNovedadesDeProductosACaducar)



        val listNovedadesBajoStock = mutableListOf(
            bajo_stock("1", "Leche"),
            bajo_stock("2", "Granos"),
            bajo_stock("3", "Carnes"),
            bajo_stock("4", "Carnes"),
            bajo_stock("5", "Leche"),
            bajo_stock("6", "Leche"),
            bajo_stock("7", "Carnes"),
            bajo_stock("8", "Leche"),
            bajo_stock("9", "Carnes"),
            bajo_stock("10", "Leche")
        )

        val recycler4: RecyclerView = binding.RVBajoStock
        recycler4.layoutManager = LinearLayoutManager(context)
        recycler4.adapter = adapterBajoStock(listNovedadesBajoStock)


    }

    fun mostrarFragment(buttonMostrar: Button, Linear: LinearLayout) {
        // Ocultar todas las vistas cuando el botón no coincide

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




