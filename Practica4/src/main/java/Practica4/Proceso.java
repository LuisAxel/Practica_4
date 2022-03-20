
package Practica4;

import java.util.Random;

/**
 * Clase Proceso, modela al objeto proceso. 
 * 
 * Cuenta con los atributos: 
 * 
 * |> nombre: nombre del proceso
 *
 * @author Núñez Quintana, Luis Axel
 * @author Zárate García, Zuriel
 *
 */
public class Proceso {
    private String nombre;
    private int id;
    private int[] instrucciones; // 2 [totales, ejecutadas]
    private int[] direcciones_memoria;// 2 [base, limite]
    private String estado;
    private static int num_procesos;
    
    public Proceso(String nombre, int dir_base, int dir_limite){
        id = ++num_procesos;
        setNombre(nombre);
        direcciones_memoria = new int[2];
        setDir(dir_base, dir_limite);
        instrucciones = new int[2];
        setInstrucciones();
        setEstado(0);
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;  
    }
    
    public void setDir(int dir_base, int dir_limite) {
        this.direcciones_memoria[0] = dir_base;
        this.direcciones_memoria[1] = dir_limite;
    }
    
    public void setInstrucciones() {
        Random rand = new Random();
        this.instrucciones[0] = rand.nextInt(21) + 10;
        this.instrucciones[1] = 0;
    }
    
    public void setEstado(int estado) {
        switch(estado){
            case 0:
                this.estado = "Nuevo";
                break;
            
            case 1:
                this.estado = "En ejecucion";
                break;
            
            case 2:
                this.estado = "Preparado";
                break;
                
            case 3:
                this.estado = "Terminado";
                break;
        }
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getId() {
        return id;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public int[] getInstrucciones() {
        return instrucciones;
    }
    
    public int[] getDirecciones() {
        return direcciones_memoria;
    }
    
    public boolean realizaInstrucciones() {
        this.instrucciones[1] += 5;
        if(this.instrucciones[1] >= this.instrucciones[0]){
            System.out.println("\tEl proceso ha concluido su ejecucion");
            this.instrucciones[1] = this.instrucciones[0];
            this.setEstado(3);
            return false;
        }
        this.setEstado(2);
        return true;
    }
    
    public void eliminaProceso() {
        this.setEstado(3);
    }
    
    public void imprimeProceso(){
        System.out.println("\tNombre del Proceso: " + getNombre());
        System.out.println("\tId del Proceso: " + getId());
        System.out.println("\tEstado del Proceso: " + getEstado());
        System.out.println("\tInstrucciones totales del Proceso: " + getInstrucciones()[0]);
        System.out.println("\tInstrucciones ejecutadas del Proceso: " + getInstrucciones()[1]);
        System.out.println("\tDireccion base del Proceso: " + getDirecciones()[0]);
        System.out.println("\tDireccion limite del Proceso: " + getDirecciones()[1]);
    }
}
