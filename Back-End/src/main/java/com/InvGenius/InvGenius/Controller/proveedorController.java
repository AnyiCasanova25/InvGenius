package com.InvGenius.InvGenius.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.models.proveedor;
import com.InvGenius.InvGenius.interfaceService.IproveedorService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping ("api/v1/proveedor")
public class proveedorController {

    @Autowired
    private IproveedorService proveedorService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("proveedor")proveedor proveedor) {
        
        //verifica que no se repita el documento del proveedor
         var listaProveedor = proveedorService.proveedorExist(proveedor.getEmpresaProveedor(), proveedor.getDocumentoProveedor());

         if (listaProveedor.size() != 0){
            return new ResponseEntity<>("El proveedor ya existe", HttpStatus.BAD_REQUEST);

         }

         // Verifique que lso campos sean obligatorios

         if (proveedor.getEmpresaProveedor().equals("")){
            return new ResponseEntity<>("Este campo es obligatorio", HttpStatus.BAD_REQUEST);
         }

         if (proveedor.getDocumentoProveedor().equals("")){
            return new ResponseEntity<>("Este campo es obligatorio", HttpStatus.BAD_REQUEST);
         }

         if (proveedor.getNombreProveedor().equals("")){
            return new ResponseEntity<>("Este campo es obligatorio", HttpStatus.BAD_REQUEST);
         }

         if (proveedor.getApellidoProveedor().equals("")){
            return new ResponseEntity<>("Este campo es obligatorio", HttpStatus.BAD_REQUEST);
            
         }

         if (proveedor.getEstadoProveedor().equals("")){
            return new ResponseEntity<>("Este campo es obligatorio", HttpStatus.BAD_REQUEST);
         }

         if (proveedor.getNumeroProveedor().equals("")){
            return new ResponseEntity<>("Este campo es obligatorio", HttpStatus.BAD_REQUEST);
         }

         proveedorService.save(proveedor);
         return new ResponseEntity<>(proveedor,HttpStatus.OK);

        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> findOne(@PathVariable String id) {
            var proveedor = proveedorService.findOne(id);
            return new ResponseEntity<>(proveedor, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Object> delete(@PathVariable String id){
            proveedorService.delete(id);
            return new ResponseEntity<>("Registro Eliminado", HttpStatus.OK);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute ("proveedor") proveedor proveedorUpdate) {
            var proveedor = proveedorService.findOne(id).get();
            
            if (proveedor != null) {
                
                proveedor.setNombreProveedor(proveedorUpdate.getNombreProveedor());
                proveedor.setApellidoProveedor(proveedorUpdate.getApellidoProveedor());
                proveedor.setDocumentoProveedor(proveedorUpdate.getDocumentoProveedor());
                proveedor.setEmpresaProveedor(proveedorUpdate.getEmpresaProveedor());
                proveedor.setEstadoProveedor(proveedorUpdate.getEstadoProveedor());
                proveedor.setNumeroProveedor(proveedorUpdate.getNumeroProveedor());

                proveedorService.save(proveedor);

                return new ResponseEntity<>(proveedor, HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Error proveedor no encontrado", HttpStatus.BAD_REQUEST);
            }
            
        }
        
    
    
}