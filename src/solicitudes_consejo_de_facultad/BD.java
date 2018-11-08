/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solicitudes_consejo_de_facultad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class BD {
    static Connection con = null;
    static Statement sentencia;
    static ResultSet resultado;
    
    //puede que si funcione 

public static void ejecutar(String sql){
    try {
        con.createStatement();
        sentencia.executeUpdate(sql);
        System.out.println("Sentencia ejecutada.");
    } catch (Exception e) {
        System.out.println("Error: "+e.getMessage()+" al ejecutar");
    }
}
public static void agregarHomologacion(int cod, java.sql.Date fecha,int id,String Uproce,int rad,int numC,int asiRecono,String semestre,String programa_A,String fechaR){
    try {
        JOptionPane.showMessageDialog(null, fecha);
        String sql ="insert into HOMOLOGACION values ("+cod+",(TO_DATE('"+fecha+"','yyyy/MM/dd')),"+id+",'"+Uproce+"',"+rad+","+numC+","+asiRecono+",'"+semestre+"','"+programa_A+"','"+fechaR+"')";
    if (con == null)
        conectar();
    try {
        sentencia = con.createStatement();
        sentencia.executeQuery(sql);
        JOptionPane.showMessageDialog(null,"Solicitud Guardada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
} 
public static void agregar_C_Enfasis(int id,int rad,int asignatura,int especializacion,int matRecono,java.sql.Date fecha,String estado,int programa){
    try {
        String sql2 ="INSERT INTO LINEA_ENFASIS VALUES(null,"+id+","+rad+","+asignatura+","+especializacion+","+matRecono+",(TO_DATE('"+fecha+"','yyyy/MM/dd')),'"+estado+"',"+programa+")";
    if (con == null){
        conectar();
    }
    try {
        String sql = "Select IDENTIFICACION from ESTUDIANTE where IDENTIFICACION = '"+id+"'";
        sentencia = con.createStatement();
        resultado = sentencia.executeQuery(sql);
        if(resultado.next() == false){
            JOptionPane.showMessageDialog(null, "El Estudiante no existe");
            sentencia.close();
        }
        else{
            sentencia = con.createStatement();
            sentencia.executeUpdate(sql2);
            JOptionPane.showMessageDialog(null,"Solicitud Guardada");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
} 
public static ArrayList<String> llenarCombobox(){
    ArrayList<String> listaCombobox = new ArrayList<String>();
    String sql = "SELECT NOMBRE from MATERIA;";
    try {
        resultado=sentencia.executeQuery(sql);
    } catch (Exception e) {
        System.out.println("Error: "+e.getMessage()+" con el combobox");
    }
    try {
        while (resultado.next()) {            
            listaCombobox.add(resultado.getString("NOMBRE"));
        }
    } catch (Exception e) {
    }
    
    return listaCombobox;
}

public static Vector<MateriaUdem> leerDatosV(String sql){
    Vector<MateriaUdem> materia = new Vector<MateriaUdem>();
    MateriaUdem mat = null;
    if (con == null)
        conectar();
        try {
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery(sql);
            while (resultado.next()) { 
                mat = new MateriaUdem(resultado.getString(1));     
                materia.add(mat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return materia;
}
public static Vector<String> llenarPrograma(String sql){
    Vector<String> programas = new Vector<String>();
    String mat = null;
    if (con == null)
        conectar();
        try {
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery(sql);
            while (resultado.next()) { 
                mat = resultado.getString(1);     
                programas.add(mat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return programas;
}
public static DefaultTableModel reporteHomo() throws SQLException{
    if(con == null)
        conectar();
    DefaultTableModel modelo = new DefaultTableModel();
    String sql = "SELECT ID_ESTUDIANTE CEDULA,F_EXPEDICION FECHA_EXPEDICION"
            +",U_PROCEDENCIA UNIVERSIDAD, SEMESTRE,PROGRAMA_A FROM HOMOLOGACION";
    try {
        sentencia = con.createStatement();
        resultado = sentencia.executeQuery(sql);
        ResultSetMetaData metaDatos = resultado.getMetaData();
        // Se obtiene el número de columnas.
        int numeroColumnas = metaDatos.getColumnCount();

        // Se crea un array de etiquetas para rellenar
        Object[] etiquetas = new Object[numeroColumnas];

        // Se obtiene cada una de las etiquetas para cada columna
        for (int i = 0; i < numeroColumnas; i++)
        {
           // Nuevamente, para ResultSetMetaData la primera columna es la 1. 
           etiquetas[i] = metaDatos.getColumnLabel(i + 1); 
        }
        modelo.setColumnIdentifiers(etiquetas);
        // Bucle para cada resultado en la consulta
        while (resultado.next())
        {
           // Se crea un array que será una de las filas de la tabla. 
           Object [] fila = new Object[numeroColumnas]; // Hay tres columnas en la tabla

           // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
           for (int i=0;i<numeroColumnas;i++)
              fila[i] = resultado.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

           // Se añade al modelo la fila completa.
           modelo.addRow(fila); 
        }
        
    } catch (Exception e) {
        System.out.println("Error tabla homologacion: "+e.getMessage());
    }
    return modelo;
}
public static DefaultTableModel reporteLinea() throws SQLException{
    if(con == null)
        conectar();
    DefaultTableModel modelo = new DefaultTableModel();
    String sql = "SELECT ID_ESTUDIANTE Identificación,ASIGNATURA,ESPECIALIZACION,ESTADO  FROM LINEA_ENFASIS";
    try {
        sentencia = con.createStatement();
        resultado = sentencia.executeQuery(sql);
        ResultSetMetaData metaDatos = resultado.getMetaData();
        // Se obtiene el número de columnas.
        int numeroColumnas = metaDatos.getColumnCount();
        // Se crea un array de etiquetas para rellenar
        Object[] etiquetas = new Object[numeroColumnas];
        // Se obtiene cada una de las etiquetas para cada columna
        for (int i = 0; i < numeroColumnas; i++)
        {
           // Nuevamente, para ResultSetMetaData la primera columna es la 1. 
           etiquetas[i] = metaDatos.getColumnLabel(i + 1); 
        }
        modelo.setColumnIdentifiers(etiquetas);
        // Bucle para cada resultado en la consulta
        while (resultado.next())
        {
           // Se crea un array que será una de las filas de la tabla. 
           Object [] fila = new Object[numeroColumnas]; // Hay tres columnas en la tabla
           // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
           for (int i=0;i<numeroColumnas;i++)
              fila[i] = resultado.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
           // Se añade al modelo la fila completa.
           modelo.addRow(fila); 
        }
        
    } catch (Exception e) {
        System.out.println("Error tabla homologacion: "+e.getMessage());
    }
    return modelo;
}
public static DefaultTableModel reporteBusqueda(String id) throws SQLException{
    if(con == null)
        conectar();
    DefaultTableModel modelo = new DefaultTableModel();
    String sql = "SELECT E.NOMBRES,M.NOMBRE_M_V,H.F_EXPEDICION,H.U_PROCEDENCIA,H.RADICADO,H.NUM_CAJA,H.SEMESTRE,H.PROGRAMA_A,H.F_RESPUESTA FROM ESTUDIANTE E INNER JOIN HOMOLOGACION H ON E.IDENTIFICACION=H.ID_ESTUDIANTE INNER JOIN MATERIA_HOMO M ON  M.COD_HOMO = H.COD_HOMO where E.IDENTIFICACION='"+id+"'";
    try {
        sentencia = con.createStatement();
        resultado = sentencia.executeQuery(sql);
        ResultSetMetaData metaDatos = resultado.getMetaData();
        // Se obtiene el número de columnas.
        int numeroColumnas = metaDatos.getColumnCount();
        // Se crea un array de etiquetas para rellenar
        Object[] etiquetas = new Object[numeroColumnas];
        // Se obtiene cada una de las etiquetas para cada columna
        for (int i = 0; i < numeroColumnas; i++)
        {
           // Nuevamente, para ResultSetMetaData la primera columna es la 1. 
           etiquetas[i] = metaDatos.getColumnLabel(i + 1); 
        }
        modelo.setColumnIdentifiers(etiquetas);
        // Bucle para cada resultado en la consulta
        while (resultado.next())
        {
           // Se crea un array que será una de las filas de la tabla. 
           Object [] fila = new Object[numeroColumnas]; // Hay tres columnas en la tabla
           // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
           for (int i=0;i<numeroColumnas;i++)
              fila[i] = resultado.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
           // Se añade al modelo la fila completa.
           modelo.addRow(fila); 
        }
        
    } catch (Exception e) {
        System.out.println("Error tabla Buqueda: "+e.getMessage());
    }
    return modelo;
}
public static void agregarMhomologacion(int cod,String nomMvista, String nomMudem,Double nota,int codHomo){
    try {
        String sql ="insert into MATERIA_HOMO values (null,"+codHomo+",'"+nomMvista+"','"+nomMudem+"',"+nota+")";
    if (con == null)
        conectar();
    try {
        sentencia = con.createStatement();
        sentencia.executeUpdate(sql);
            //sentencia.close();
            //resultado.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
} 
public static boolean loggin(String id,String pass) throws SQLException{
    if (con == null) {
        conectar();
    }
    String sql = "Select ID_SECRETARIA,CONTRASEÑA from SECRETARIA where ID_SECRETARIA = '"+id+"' AND CONTRASEÑA = '"+pass+"'";
    sentencia = con.createStatement();
    resultado = sentencia.executeQuery(sql);
    if (resultado.next() == false){
        JOptionPane.showMessageDialog(null, "Datos Erroneos");
        return false;
    }else {
        return true;
    }
}
public static String nombreLineaEnfasis (String cod) throws SQLException{
    String nombreC="";
    if (con == null) {
        conectar();
    }
    String sql = "Select NOMBRES,APELLIDOS from ESTUDIANTE where IDENTIFICACION = '"+cod+"'";
    sentencia = con.createStatement();
    resultado = sentencia.executeQuery(sql);
    ResultSetMetaData metaDatos = resultado.getMetaData();
    int numeroColumnas = metaDatos.getColumnCount();
        while (resultado.next())
        {
            for (int i=0;i<numeroColumnas;i++)
                nombreC = nombreC + " " + resultado.getObject(i+1).toString();
        }
    return nombreC;
}
public static String nombreEspecializacion (String cod) throws SQLException{
    String nombreA="";
    if (con == null) {
        conectar();
    }
    String sql = "Select NOMBRE from ESPECIALIZACION where COD_ESPECIALIZACION = "+cod+"";
    sentencia = con.createStatement();
    resultado = sentencia.executeQuery(sql);
    ResultSetMetaData metaDatos = resultado.getMetaData();
    int numeroColumnas = metaDatos.getColumnCount();
        while (resultado.next())
        {
            for (int i=0;i<numeroColumnas;i++)
                nombreA = resultado.getObject(i+1).toString();
        }
    return nombreA;
}

public static String nombreMateria (String cod) throws SQLException{
    String nombreA="";
    if (con == null) {
        conectar();
    }
    String sql = "Select NOMBRE from MATERIA where COD_MATERIA = "+cod+"";
    sentencia = con.createStatement();
    resultado = sentencia.executeQuery(sql);
    ResultSetMetaData metaDatos = resultado.getMetaData();
    int numeroColumnas = metaDatos.getColumnCount();
        while (resultado.next())
        {
            for (int i=0;i<numeroColumnas;i++)
                nombreA = resultado.getObject(i+1).toString();
        }
    return nombreA;
}

public static String nombreEnfasis (String cod) throws SQLException{
    String nombreA="";
    if (con == null) {
        conectar();
    }
    String sql = "Select NOMBRE from MATERIA_ENFASIS where COD_MATE = "+cod+"";
    sentencia = con.createStatement();
    resultado = sentencia.executeQuery(sql);
    ResultSetMetaData metaDatos = resultado.getMetaData();
    int numeroColumnas = metaDatos.getColumnCount();
        while (resultado.next())
        {
            for (int i=0;i<numeroColumnas;i++)
                nombreA = resultado.getObject(i+1).toString();
        }
    return nombreA;
}
static void conectar() {
    String ruta="jdbc:oracle:thin:@localhost:1521:XE";
    String user="system";
    String pass="123";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con=DriverManager.getConnection(ruta,user,pass);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


