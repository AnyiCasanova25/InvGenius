package com.InvGenius.InvGenius.Controller;

import org.springframework.http.MediaType;

import com.InvGenius.InvGenius.interfaceService.IloteService;
import com.InvGenius.InvGenius.models.lote;
import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Document;

import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Element;

import com.itextpdf.text.Font;

import com.itextpdf.text.FontFactory;

import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfPTable;

import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.InvGenius.InvGenius.interfaceService.IInformeService;
// import com.InvGenius.InvGenius.models.informe;

@RestController
@RequestMapping("/api/v1/informe")
public class informeController {

    @Autowired
    private IloteService loteService;

    @GetMapping("/pdf/caducidad")
    public ResponseEntity<byte[]> downloadCaducidadPdf() throws MalformedURLException, IOException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // PARA AÑADIR TITULO AL PDF
            Paragraph title = new Paragraph("Informe Novedades",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18,
                            Font.BOLD, BaseColor.BLACK));
            title.setAlignment(Element.ALIGN_CENTER);

            // document.add(new Paragraph("Genius Inventory Company"));
            document.add(title);
            document.addCreationDate();

            //PRODUCTOS PROXIMOS A CADUCAR

            document.add(new Paragraph(""));
            document.add(new Paragraph(""));
            document.add(new Paragraph("Productos proximos a caducar"));
            document.add(new Paragraph("   "));

            PdfPTable tableProximo = new PdfPTable(5); // Numero de columnas
            tableProximo.setWidthPercentage(100);

            tableProximo.addCell("Lote");
            tableProximo.addCell("Producto");
            tableProximo.addCell("Fecha ingreso");
            tableProximo.addCell("Fecha Vencimiento");
            tableProximo.addCell("Cantidad");

            List<lote> proximo = loteService.findAll();
            for (lote lote : proximo) {

                tableProximo.addCell(lote.getNumeroLote());
                tableProximo.addCell(lote.getProducto().getNombreProducto());
                tableProximo.addCell(dateFormat.format(lote.getFechaIngreso()));
                tableProximo.addCell(dateFormat.format(lote.getFechaVencimiento()));
                tableProximo.addCell(lote.getCantidad());
            }
            document.add(tableProximo);


            document.add(new Paragraph(""));
            document.add(new Paragraph(""));
            document.add(new Paragraph("Productos caducados"));
            document.add(new Paragraph("   "));

            PdfPTable tableCaducados = new PdfPTable(5); // Numero de columnas
            tableCaducados.setWidthPercentage(100);

            tableCaducados.addCell("Lote");
            tableCaducados.addCell("Producto");
            tableCaducados.addCell("Fecha ingreso");
            tableCaducados.addCell("Fecha Vencimiento");
            tableCaducados.addCell("Cantidad");

            List<lote> caducados = loteService.findAll();
            for (lote lote : caducados) {

                tableCaducados.addCell(lote.getNumeroLote());
                tableCaducados.addCell(lote.getProducto().getNombreProducto());
                tableCaducados.addCell(dateFormat.format(lote.getFechaIngreso()));
                tableCaducados.addCell(dateFormat.format(lote.getFechaVencimiento()));
                tableCaducados.addCell(lote.getCantidad());
            }

            document.add(tableProximo);

            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "informe.pdf");

        return ResponseEntity.ok().headers(headers).body(out.toByteArray());
    }

    @GetMapping("/pdf/bajo")
    public ResponseEntity<byte[]> downloadBajoPdf() throws MalformedURLException, IOException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // PARA AÑADIR TITULO AL PDF
            Paragraph title = new Paragraph("Informe Novedades",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18,
                            Font.BOLD, BaseColor.BLACK));
            title.setAlignment(Element.ALIGN_CENTER);

            // document.add(new Paragraph("Genius Inventory Company"));
            document.add(title);
            document.addCreationDate();

            //  PRODUCTOS BAJOS EN STOCK

           
            document.add(new Paragraph(""));
            document.add(new Paragraph(""));
            document.add(new Paragraph("bajo en stock"));
            document.add(new Paragraph("   "));

            // CREAR UNA TABLA CON LAS COLUMNAS ESPECIFICADAS
            PdfPTable tableBajoStock = new PdfPTable(3); // Numero de columnas
            tableBajoStock.setWidthPercentage(100);


            // AÑADIR CONTENIDO A LA TABLA
            // tableBajoStock.addCell("Movimientos");
            // tableBajoStock.addCell("Categoria");
            tableBajoStock.addCell("Lote");
            tableBajoStock.addCell("Cantidad");
            tableBajoStock.addCell("Producto");
            // tableBajoStock.addCell("Proveedor");

            // AÑADIR CONTENIDO A LA TABLA
            List<lote> bajoStock = loteService.findAll();
            for (lote lote : bajoStock) {
                // tableBajoStock.addCell(informe.getHoraInforme().toString());
                tableBajoStock.addCell(lote.getNumeroLote());
                tableBajoStock.addCell(lote.getCantidad());
                tableBajoStock.addCell(lote.getProducto().getNombreProducto());
                // tableBajoStock.addCell(lote.getLote().getNumeroLote());
                // tableBajoStock.addCell(dateFormat.format(lote.getMovimientos().getFechaMovimiento()));
                // tableBajoStock.addCell(lote.getProveedor().getNombreProveedor());
            }

            document.add(tableBajoStock);

            
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "informe.pdf");

        return ResponseEntity.ok().headers(headers).body(out.toByteArray());
    }

    // @GetMapping("/pdf/movimientos")
    // public ResponseEntity<byte[]> downloadPdf() throws MalformedURLException, IOException {
    //     Document document = new Document();
    //     ByteArrayOutputStream out = new ByteArrayOutputStream();
    //     SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    //     try {
    //         PdfWriter.getInstance(document, out);
    //         document.open();

    //         // PARA AÑADIR TITULO AL PDF
    //         Paragraph title = new Paragraph("Informe Novedades",
    //                 FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18,
    //                         Font.BOLD, BaseColor.BLACK));
    //         title.setAlignment(Element.ALIGN_CENTER);

    //         // document.add(new Paragraph("Genius Inventory Company"));
    //         document.add(title);
    //         document.addCreationDate();

    //         //  PRODUCTOS BAJOS EN STOCK

           
    //         document.add(new Paragraph(""));
    //         document.add(new Paragraph(""));
    //         document.add(new Paragraph("Movimientos"));
    //         document.add(new Paragraph("   "));

    //         // CREAR UNA TABLA CON LAS COLUMNAS ESPECIFICADAS
    //         PdfPTable table = new PdfPTable(5); // Numero de columnas
    //         table.setWidthPercentage(100);


    //         // AÑADIR CONTENIDO A LA TABLA
    //         table.addCell("Movimientos");
    //         table.addCell("Categoria");
    //         table.addCell("Producto");
    //         table.addCell("Cantidad");
    //         table.addCell("Fecha");
    //         table.addCell("Proveedor");
    //         // table.addCell("Lote");

    //         // AÑADIR CONTENIDO A LA TABLA
    //         List<lote> pdfMovimientos = loteService.findAll();
    //         for (lote lote : pdfMovimientos) {
    //             // tableBajoStock.addCell(informe.getHoraInforme().toString());
    //             // table.addCell(lote.getNumeroLote());
    //             table.addCell(lote.);
    //             table.addCell(lote.getCantidad());
    //             table.addCell(lote.getProducto().getNombreProducto());
    //             // table.addCell(lote.getLote().getNumeroLote());
    //             // table.addCell(dateFormat.format(lote.getMovimientos().getFechaMovimiento()));
    //             // table.addCell(lote.getProveedor().getNombreProveedor());
    //         }

    //         document.add(table);

            
    //         document.close();

    //     } catch (DocumentException e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_PDF);
    //     headers.setContentDispositionFormData("attachment", "informe.pdf");

    //     return ResponseEntity.ok().headers(headers).body(out.toByteArray());
    // }
}
