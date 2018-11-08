/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solicitudes_consejo_de_facultad;

/**
 *
 * @author usuario
 */
public class MateriaUdem {
    String nombre;
    

    public MateriaUdem(String nombre) {
        this.nombre = nombre;
      
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

   
}
