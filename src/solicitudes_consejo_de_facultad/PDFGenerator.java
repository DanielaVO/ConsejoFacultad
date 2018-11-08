/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solicitudes_consejo_de_facultad;

import java.io.FileOutputStream;
import java.util.Date;


import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author Usuario
 */
public class PDFGenerator {
    
    protected final String tipoDeArchivo = ".pdf";
    //Esta ruta toca cambiarla por la predeterminada del usuario;
    protected String FILE = "C:\\Users\\usuario\\Desktop\\dd";
    protected static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    protected static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    protected static Font regularFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);

    protected void openDoc(){}
    
    protected void addMetaData(Document document, String nombre, String tipoSolicitud){
        document.addTitle(tipoSolicitud+"_"+nombre);
        document.addKeywords(tipoSolicitud);
    }
    
    protected void addTitle(Document doc)throws DocumentException{}
    
    protected void addContent(Document doc)throws DocumentException{}
    
    protected void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph("  "));
        }
    }
}
