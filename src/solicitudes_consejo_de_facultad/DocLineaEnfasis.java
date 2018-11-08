/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solicitudes_consejo_de_facultad;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Usuario
 */
public class DocLineaEnfasis extends PDFGenerator{

    private final Linea_enfasis lineaDeEnfasis;
    private final String ced_enfasis;
    private final String esp_enfasis;
    private final String estado_enfasis;
    private final String fecha_enfasis;
    private final String materia_enfasis;
    private final String nom_enfasis;
    private final String prog_enfasis;
    private final String rad_enfasis;
    private final String asi_enfasis;

    public DocLineaEnfasis(Linea_enfasis lineaDeEnfasis, String ced_enfasis, String esp_enfasis, String estado_enfasis, String fecha_enfasis, String materia_enfasis, String nom_enfasis, String prog_enfasis, String rad_enfasis, String asi_enfasis) {
        this.lineaDeEnfasis = lineaDeEnfasis;
        this.FILE += "Linea de enfasis" + lineaDeEnfasis.ced_enfasis.getText()+this.tipoDeArchivo;
        this.ced_enfasis = ced_enfasis;
        this.esp_enfasis = esp_enfasis;
        this.estado_enfasis = estado_enfasis;
        this.fecha_enfasis = fecha_enfasis;
        this.materia_enfasis = materia_enfasis;
        this.nom_enfasis = nom_enfasis;
        this.prog_enfasis = prog_enfasis;
        this.rad_enfasis = rad_enfasis;
        this.asi_enfasis = asi_enfasis;
    }
    

    @Override
    protected void openDoc() {
        super.openDoc(); //To change body of generated methods, choose Tools | Templates.
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addContent(document);
            document.close();
         }catch(FileNotFoundException | DocumentException e){
             System.err.println(e.getMessage());
         }
    }
    
    @Override
    protected void addContent(Document doc) throws DocumentException {
        super.addContent(doc); //To change body of generated methods, choose Tools | Templates.
        addHeader(doc);
        addBody(doc);
        addBottom(doc);
    }

    private void addHeader(Document doc) throws DocumentException{
        
        Paragraph para = new Paragraph();
        para.setFont(regularFont);
        para.setAlignment(Element.ALIGN_LEFT);
        para.add("Medellín, "+fecha_enfasis);
        doc.add(para);
        para = new Paragraph();
        para.setFont(regularFont);
        para.setAlignment(Element.ALIGN_RIGHT);
        para.add("420-0-1");
        doc.add(para);
        para = new Paragraph();
        para.setFont(regularFont);
        addEmptyLine(para, 3);
        para.setLeading(20);
        para.add("Estudiante\n");
        para.add(nom_enfasis);
        para.add("\nPrograma "+prog_enfasis);
        para.add("\nUniversidad de Medellín");
        para.add("\nMedellín");
        addEmptyLine(para, 4);
        doc.add(para);
    }
    
    private void addBody(Document doc) throws DocumentException{
        
        Paragraph para = new Paragraph();
        para.setFont(regularFont);
        para.setLeading(20);
        para.setAlignment(Element.ALIGN_JUSTIFIED);
        para.add("Cordial saludo, ");
        addEmptyLine(para, 2);
        para.add("En respuesta a la solicitud con radicado "+rad_enfasis+ " "
                + "donde solicita se le apruebe cursar las asignaturas " + asi_enfasis + " "
                + "en especialización en " +esp_enfasis+", "
                + "para luego ser convalidada por la línea de "+ materia_enfasis+ " "
                + "del programa "+prog_enfasis+".");
        addEmptyLine(para, 1);
        para.add("El Consejo de Facultad en Sesión del "+fecha_enfasis + " "
                + "Acta No 22 " + estado_enfasis+ " "
                + "la solicitud a cursar la asignatura "+asi_enfasis + "en la especialización en "+esp_enfasis
                + " para luego ser reconocida como línea de énfasis de "+ materia_enfasis+ " "
                + " en su respectivo programa.");
        addEmptyLine(para, 3);
        doc.add(para);
        
    }
    
    /*
    private String getAsginaturas(){
        String[] asignaturas = lineaDeEnfasis.asi_enfasis.getText();
        String asig = "";
        for (String asignatura : asignaturas) {
            asig += asignatura + ", ";
        }
        
        return asig;        
    }
*/
    
    private void addBottom(Document doc) throws DocumentException{
        
        Paragraph para = new Paragraph();
        para.setLeading(20);
        para.setFont(regularFont);
        para.add("Atentamente, ");
        addEmptyLine(para, 3);        
        para.add("CARLOS EDUARDO LÓPEZ BERMEO\n");
        para.add("Presidente Consejo de Facultad");
        doc.add(para);
    }
}