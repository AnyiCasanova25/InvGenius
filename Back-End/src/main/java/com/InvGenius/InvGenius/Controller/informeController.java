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

   
        @GetMapping("/pdf")
    public ResponseEntity<byte[]> downloadPdf() throws MalformedURLException, IOException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // PARA AÑADIR TITULO AL PDF
            Paragraph title = new Paragraph("Informe de movimientos realizados",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18,
                            Font.BOLD, BaseColor.BLACK));
            title.setAlignment(Element.ALIGN_CENTER);
            
            // document.add(new Paragraph("Genius Inventory Company"));
            document.add(title);
            document.addCreationDate();

            document.add(new Paragraph(""));
            document.add(new Paragraph(""));
            document.add(new Paragraph("bajo en stock"));
            document.add(new Paragraph("   "));

            // CREAR UNA TABLA CON LAS COLUMNAS ESPECIFICADAS
            PdfPTable table = new PdfPTable(3); // Numero de columnas
            table.setWidthPercentage(100);

            // AÑADIR CONTENIDO A LA TABLA
            // table.addCell("Movimientos");
            // table.addCell("Categoria");
            table.addCell("Lote");
            table.addCell("Cantidad");
            table.addCell("Producto");
            // table.addCell("Proveedor");
            

            // AÑADIR CONTENIDO A LA TABLA
            List<lote> lotes = loteService.findAll();
            for (lote lote : lotes) {
                // table.addCell(informe.getHoraInforme().toString());
                table.addCell(lote.getNumeroLote());
                table.addCell(lote.getCantidad());
                table.addCell(lote.getProducto().getNombreProducto());
                // table.addCell(lote.getLote().getNumeroLote());
                // table.addCell(dateFormat.format(lote.getMovimientos().getFechaMovimiento()));
                // table.addCell(lote.getProveedor().getNombreProveedor());  
            }

            document.add(table);
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
}
