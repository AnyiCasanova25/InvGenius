package com.InvGenius.InvGenius.Controller;


// import java.awt.Font;
// import java.awt.PageAttributes.MediaType;
// import java.io.ByteArrayOutputStream;
// import java.io.IOException;
// import java.net.MalformedURLException;
// import java.net.http.HttpHeaders;
// import java.text.SimpleDateFormat;
// import java.util.List;

// import javax.swing.text.Document;
// import javax.swing.text.html.ParagraphView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.interfaceService.IInformeService;
import com.InvGenius.InvGenius.models.informe;

@RestController
@RequestMapping("/api/v1/informe")
public class informeController {

    @Autowired
    private IInformeService informeService;
    
    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody informe informe){

        if (informe.getHoraInforme().equals("")) {
            return new ResponseEntity<>("El campo de hora es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getFechaInforme().equals("")) {
            return new ResponseEntity<>("El campo de fecha es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getMovimientos().equals("")) {
            return new ResponseEntity<>("El campo de movimientos es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getCategoria().equals("")){
            return new ResponseEntity<>("El campo de categoria es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getProducto().equals("")) {
            return new ResponseEntity<>("El campo de producto es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getMarca().equals("")) {
            return new ResponseEntity<>("El campo de marca es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getProveedor().equals("")) {
            return new ResponseEntity<>("El campo del proveedor es obligatorio",HttpStatus.BAD_REQUEST);
        }

        if (informe.getLote().equals("")) {
            return new ResponseEntity<>("El campo del lote es obligatorio",HttpStatus.BAD_REQUEST);
        }

        informeService.save(informe);
        return new ResponseEntity<>(informe,HttpStatus.OK);
    }

    //Filtro de informe
    @GetMapping("/busquedaFiltros/{filtro}")
    public ResponseEntity<Object>findFiltro(@PathVariable String filtro){
        var listaInforme = informeService.informeExist(filtro, filtro, null);
        return new ResponseEntity<>(listaInforme, HttpStatus.OK);
    }

    // @GetMapping("/pdf")
    // public ResponseEntity<byte[]> downloadPdf() throws
    // MalformedURLException, IOException {
    //     Document document = new Document();
    //     ByteArrayOutputStream out = new ByteArrayOutputStream();
    //     SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    //     try {
    //         PdfWriter.getInstance(document , out);
    //         document.open();

    //         //PARA AÑADIR TITULO AL PDF
    //         Paragraph title = new Paragraph("Informe de movimientos realizados",FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18,
    //         Font.BOLD, BaseColor.BLACK));
    //         title.setAlignment(Element.ALIGN_CENTER);
    //         Document.add(title);

    //         document.add(new Paragraph(""));

    //         //CREAR UNA TABLA CON LAS COLUMNAS ESPECIFICADAS
    //         PdfTable table = new PdfTable(8); //Numero de columnas
    //         table.setWidthPorcentage(100);

    //         //AÑADIR CONTENIDO A LA TABLA
    //         table.addCell("Hora");
    //         table.addCell("Fecha");
    //         table.addCell("movimientos");
    //         table.addCell("categoria");
    //         table.addCell("producto");
    //         table.addCell("marca");
    //         table.addCell("proveedor");
    //         table.addCell("lote");

    //         //AÑADIR CONTENIDO A LA TABLA
    //         List<informe> informes = informeService.findAll()
    //         for (informe informe : informes) {

    //             table.addCell(informe.getHoraInforme());
    //             table.addCell(informe.getFechaInforme());
    //             table.addCell(informe.getMovimientos());
    //             table.addCell(informe.getCategoria());
    //             table.addCell(informe.getProducto());
    //             table.addCell(informe.getMarca());
    //             table.addCell(informe.getProveedor());
    //             table.addCell(informe.getLote());
    //         }

    //         document.add(table);
    //         document.close();

    //     } catch (DocumentException e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_PDF);
    //     headers.setContextDispositionFormData("attachment","informe.pdf");

    //     return ResponseEntity.ok().headers(headers).body(out.toByteArray());
    // }


     @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        var listaInforme = informeService.findAll();
        return new ResponseEntity<>(listaInforme,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var informe = informeService.findOne(id);
        return new ResponseEntity<>(informe, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        informeService.delete(id);
        return new ResponseEntity<>("Resgistro Eliminado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody informe informeUpdate){
        var informe = informeService.findOne(id).get();

        if (informe != null){

            informe.setHoraInforme(informeUpdate.getHoraInforme());
            informe.setFechaInforme(informeUpdate.getFechaInforme());
            informe.setMovimientos(informeUpdate.getMovimientos());
            informe.setCategoria(informeUpdate.getCategoria());
            informe.setProducto(informeUpdate.getProducto());
            informe.setMarca(informeUpdate.getMarca());
            informe.setProveedor(informeUpdate.getProveedor());
            informe.setLote(informeUpdate.getLote());

            informeService.save(informe);

            return new ResponseEntity<>(informe,HttpStatus.OK);

        }else {
            return new ResponseEntity<>("Error informer no encontrado", HttpStatus.BAD_REQUEST);
        }
    }
}
