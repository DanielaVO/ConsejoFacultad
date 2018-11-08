/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solicitudes_consejo_de_facultad;

import com.itextpdf.text.DocumentException;
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
import java.io.FileOutputStream;

/**
 *
 * @author Usuario
 */
public class DocHomologaciones extends PDFGenerator{
    
    private final Homologacion homologacion;
    private final String jefePrograma;
    private final String programa;
    private final String nom_homo;
    private final String valor_homo;
    private final String semestre_hmaterias;
    private final String fechaE_hmaterias;
    private final String rec_hmaterias;
    private final String numasi_hmaterias;
    private final String rad_hmaterias;
    private final String uproce_hmaterias;
    
    public DocHomologaciones(Homologacion homologacion, String jefePrograma,String nom_homo,String prog_hmaterias, String valor_homo, String semestre_hmaterias
        ,String fechaE_hmaterias,String rec_hmaterias,String numasi_hmaterias,String rad_hmaterias,String uproce_hmaterias) {
        this.homologacion = homologacion;
        this.programa = prog_hmaterias;
        this.nom_homo = nom_homo;
        this.valor_homo = valor_homo;
        this.semestre_hmaterias = semestre_hmaterias;
        this.fechaE_hmaterias = fechaE_hmaterias;
        this.rec_hmaterias = rec_hmaterias;
        this.numasi_hmaterias = numasi_hmaterias;
        this.FILE += "Homologacion_" + nom_homo+this.tipoDeArchivo;
        this.jefePrograma = jefePrograma;
        this.rad_hmaterias = rad_hmaterias;
        this.uproce_hmaterias = uproce_hmaterias;
    }

    @Override
    protected void openDoc() {
        super.openDoc(); //To change body of generated methods, choose Tools | Templates.
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();  
            addTitle(document);
            
            addSubtitle(document);
            addContent(document);
            addPara(document);
            addTable(document);
            segundoArt(document);
            //asignaturasNoAprobadas(document);
            addEnd(document);
            addEnd2(document);
            document.close();
        } catch (Exception e) {
            System.err.println(""+e.getMessage());
        }
        
    }

    @Override
    protected void addTitle(Document doc) throws DocumentException {
        super.addTitle(doc); //To change body of generated methods, choose Tools | Templates.
        Paragraph preface = new Paragraph();
        preface.setAlignment(Element.ALIGN_CENTER);
        preface.setLeading(25);
        preface.add(new Paragraph("FACULTAD DE INGENIERIAS", catFont));
        preface.add(new Paragraph("PROGRAMA DE "+programa, catFont));
        preface.add(new Paragraph("CONSEJO DE FACULTAD 420-16-01", catFont));
        preface.add(new Paragraph("RESOLUCION NUMERO 010", catFont));
        preface.add(new Paragraph("DE 21 de Julio de 2017", catFont));
        addEmptyLine(preface, 1);
        doc.add(preface);
    }

    private void addSubtitle(Document doc) throws DocumentException{
        Paragraph preface = new Paragraph();
        preface.setAlignment(Element.ALIGN_JUSTIFIED);
                preface.add(new Paragraph("POR LA CUAL SE HACE EL RECONOCIMENTO DE "+numasi_hmaterias+" " +
                    "ASIGNATURAS, EL CONSEJO DE FACULTAD, DE LA FACULTAD DE " +
                    "INGENIERÍAS, en uso de sus atribuciones reglamentarias, y ", subFont));
        
        doc.add(preface);
    }
 
    @Override
    protected void addContent(Document doc) throws DocumentException {
        super.addContent(doc);
        Paragraph preface = new Paragraph();
        
        preface.setAlignment(Element.ALIGN_JUSTIFIED);
        
        preface.setAlignment(Element.ALIGN_CENTER);
        preface.add(new Paragraph("CONSIDERANDO: ", catFont));
        addEmptyLine(preface, 1);
        doc.add(preface);        
        preface = new Paragraph();
        preface.setLeading(20);
        
        preface.setAlignment(Element.ALIGN_JUSTIFIED);
        preface.add(new Paragraph("1. Que "+nom_homo+", "
                + "matriculado en este programa para el "+ semestre_hmaterias+ ", procedente de " +
                "la "+uproce_hmaterias+ " programa "+ programa+".", regularFont ));
        preface.add(new Paragraph("2. Que mediante comunicado del "+fechaE_hmaterias+ ", "
                + "con radicado "+rad_hmaterias+" y del recibo de caja "+rec_hmaterias+" "
                + "solicita se le reconozca unas asignaturas " +
                "cursadas y aprobadas en su programa origen. ",regularFont) );
        preface.add(new Paragraph("3. Que el consejo de Facultad podrá reconocer a los estudiantes admitidos en "
                + "un programa, las asignaturas cursadas y aprobadas en la misma Universidad "
                + "en otra Institución de Educación Superior, cuando los objetivos, contenidos "
                + "e intensidad horaria de las asignaturas presentadas para su reconocimiento "
                + "no sean significativamente diferentes de los que ella ofrece en su respectivo "
                + "plan de estudios.", regularFont));
        addEmptyLine(preface, 2);
        doc.add(preface);
        
    }
    
    private void addPara(Document doc) throws DocumentException{
        Paragraph preface = new Paragraph();

        preface.setAlignment(Element.ALIGN_CENTER);
        preface.add(new Paragraph("RESUELVE: ", catFont));
        preface.setLeading(20);
        addEmptyLine(preface, 1);
        doc.add(preface);
        
        Paragraph para = new Paragraph("Articulo primero: ", catFont);
        para.setLeading(20);
        para.setFont(regularFont);
        para.add("Reconózcase "+numasi_hmaterias+" asignadas a "+ nom_homo
                + " matriculado(a) en el programa "+programa+ " "
                + "para el semestre "+ semestre_hmaterias + "; ya que cumple con los contenidos "
                + "programáticos e intensidad horaria semanal en este programa.");
        
        addEmptyLine(para, 3);
        doc.add(para);
        
    }
    
    private void addTable(Document doc) throws DocumentException{
        PdfPTable table = new PdfPTable(1);
        
        PdfPCell c1 = new PdfPCell(new Phrase(homologacion.nom_homo.getText()));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        table.addCell(c1);        
        
        PdfPTable table2 = new PdfPTable(5);
        
        addContenido(table2, uproce_hmaterias);
        addContenido(table2, "Por");
        addContenido(table2, "Universidad de Medellín " + homologacion.prog_hmaterias.getText());
        addContenido(table2, homologacion.prog_hmaterias.getText());
        addContenido(table2, "cod");
        
       // String[] materias = homologacion.mateApro;
        
        //for (int i = 0; i < homologacion.getMateriasAprobadas().length; i++) {
            
            addContenido(table2, homologacion.mvistas_hmaterias.getText());
           /* addContenido(table2, "  ");
            addContenido(table2, homologacion.getMateriasAprobadas()[i].getNombre());
            addContenido(table2, materias[i].getNota());
            addContenido(table2, homologacion.getMateriasAprobadas()[i].getCodigo());

        }   */
        
        doc.add(table);
        doc.add(table2);
        
        Paragraph para = new Paragraph();
        para.setFont(regularFont);
        addEmptyLine(para, 2);
        para.add("Asignaturas reconocidas "+ numasi_hmaterias+ 
                " por un valor de "+valor_homo+ ".");
        addEmptyLine(para, 3);

        doc.add(para);
    }
    
    
    private PdfPTable addContenido(PdfPTable table, String titulo){
        
        Paragraph para = new Paragraph(titulo);
        para.setFont(regularFont);
        PdfPCell c1 = new PdfPCell(para);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
                
        return table;
    }
    
    private void segundoArt(Document doc) throws DocumentException{
        
        Paragraph para = new Paragraph("Artículo segundo: ", catFont);
        para.setLeading(20);
        para.setFont(regularFont);
        para.add("Aclárese que las asignaturas reconocidas, le serán asentadas " +
                "en el récord académico por la Sección de Admisiones y Registro de la Universidad, " +
                "una vez tenga aprobados los correspondientes prerrequisitos.");
        addEmptyLine(para, 3);
        doc.add(para);
    }
    /*
    private void asignaturasNoAprobadas(Document doc) throws DocumentException{
        
        String nombreAsig = "";
        
        for (MateriaExterna asignaturasNoAprobada : homologacion.getAsignaturasNoAprobadas()) {
            nombreAsig += asignaturasNoAprobada.getNombre() + ", ";
        }
        Paragraph para = new Paragraph();
        para.setLeading(20);
        para.setFont(regularFont);
        para.add("Las asignaturas de: "+ nombreAsig + " no es viable el reconocimiento, toda vez que los " +
                            "contenidos e intensidad horaria no es acorde con los del programa.");
       
        doc.add(para);
    }
*/
    
    private void addEnd(Document doc) throws DocumentException{
        Paragraph para = new Paragraph();
        para.setFont(regularFont);
        addEmptyLine(para, 5);
        para.setLeading(25);
        para.add("Comuníquese y cúmplase.\n");
        para.add("Dada en Medellín, 28/10/2017 ");//+homologacion.getFecha());
        
        doc.add(para);        
    }
    
    private void addEnd2(Document doc) throws DocumentException{
        Paragraph para = new Paragraph();
        para.setFont(regularFont);
        para.setLeading(25);
        para.add(this.jefePrograma);
        para.add("\nJefe de programa "+ programa);
        
        doc.add(para);        
    }
}