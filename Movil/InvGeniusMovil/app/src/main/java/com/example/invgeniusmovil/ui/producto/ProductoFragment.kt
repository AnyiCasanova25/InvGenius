package com.example.invgeniusmovil.ui.producto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.invgeniusmovil.adapters.Novedades.adapterBajoStock
import com.example.invgeniusmovil.adapters.Producto.adapterMovimientoProductos
import com.example.invgeniusmovil.databinding.FragmentProductoBinding
import com.example.invgeniusmovil.models.Novedades.bajo_stock
import com.example.invgeniusmovil.models.Producto.movimiento_productos

class ProductoFragment : Fragment() {

    private var _binding: FragmentProductoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val productoViewModel =
            ViewModelProvider(this).get(ProductoViewModel::class.java)

        _binding = FragmentProductoBinding.inflate(inflater, container, false)
        val root: View = binding.root



        val listMovimientoProductos = mutableListOf(
        movimiento_productos("arroz", "cereal"),
        movimiento_productos("huevos", "proteína"),
        movimiento_productos("pollo", "carne"),
        movimiento_productos("queso", "lácteo"),
        movimiento_productos("tomates", "vegetal"),
        movimiento_productos("aceite", "grasa"),
        movimiento_productos("azúcar", "endulzante"),
        movimiento_productos("pasta", "cereal"),
        movimiento_productos("cereal", "desayuno"),
        movimiento_productos("sal", "especie"),
        movimiento_productos("zanahorias", "vegetal"),
        movimiento_productos("yogur", "lácteo"),
        movimiento_productos("leche", "lácteo"),
        movimiento_productos("mantequilla", "grasa"),
        movimiento_productos("jugo", "bebida"),
        movimiento_productos("café", "bebida"),
        movimiento_productos("té", "bebida"),
        movimiento_productos("papas", "vegetal"),
        movimiento_productos("cebollas", "vegetal"),
        movimiento_productos("harina", "cereal"),
        movimiento_productos("miel", "endulzante"),
        movimiento_productos("pescado", "carne"),
        movimiento_productos("hamburguesas", "carne"),
        movimiento_productos("salchichas", "carne")
        )

        val recycler5: RecyclerView = binding.RVMovimientoProducto
        recycler5.layoutManager = LinearLayoutManager(context)
        recycler5.adapter = adapterMovimientoProductos(listMovimientoProductos)

        val textView: TextView = binding.textProducto
        productoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}