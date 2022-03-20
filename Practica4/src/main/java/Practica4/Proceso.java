
package Practica4;

import java.util.Random;

/**
 * Clase Proceso, modela al objeto proceso. 
 * 
 * Cuenta con los atributos: 
 * 
 * |> nombre: Nombre del proceso.
 * |> id: Identificador único del proceso.
 * |> instrucciones: Arreglo de tamaño 2, almacena las instrucciones totales y ejecutadas del proceso.
 * |> direcciones_memoria: Arreglo de tamaño 2, almacena la dirección base y límite del proceso.
 * |> estado: Almacena el estado del proceso (Nuevo, En ejecución, Preparado o Terminado).
 * |> num_procesos: Contador de total de procesos, es utilizada para asignar ids únicos.
 * 
 * @author Núñez Quintana, Luis Axel
 * @author Zárate García, Zuriel
 */
public class Proceso {
    private String nombre;
    private int id;
    private int[] instrucciones; // 2 [totales, ejecutadas]
    private int[] direcciones_memoria;// 2 [base, limite]
    private String estado;
    private static int num_procesos;
    
    /**
     * Método constructor, inicializa los atributos nombre, id, direcciones_memoria, instrucciones y estado. Solo realiza asignación directa a id y num_procesos, llama a los métodos setters.
     * @param nombre Nombre del proceso.
     * @param dir_base Dirección base del proceso.
     * @param dir_limite Dirección límite del proceso.
     */
    public Proceso(String nombre, int dir_base, int dir_limite){
        id = ++num_procesos;
        setNombre(nombre);
        direcciones_memoria = new int[2];
        setDir(dir_base, dir_limite);
        instrucciones = new int[2];
        setInstrucciones();
        setEstado(0);
    }
    
    /**
     * Método que inicializa el nombre.
     * @param nombre Cadena que es el nombre del proceso.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;  
    }
    
    /**
     * Método que inicializa y altera el arreglo direcciones_memoria.
     * @param dir_base Entero que indica la dirección base del proceso.
     * @param dir_limite Entero que indica la dirección límite del proceso.
     */
    public void setDir(int dir_base, int dir_limite) {
        this.direcciones_memoria[0] = dir_base;
        this.direcciones_memoria[1] = dir_limite;
    }
    
    /**
     * Método que inicializa el arreglo instrucciones. Las instrucciones totales es un valor aleatorio entero entre 10 y 30.
     */
    public void setInstrucciones() {
        Random rand = new Random();
        this.instrucciones[0] = rand.nextInt(21) + 10;
        this.instrucciones[1] = 0;
    }
    
    /**
     * Método que inicializa y altera el estado del proceso.
     * @param entero que indica el nuevo estado del proceso (0 : Nuevo, 1 : En ejecución, 2 : Preparado, 3 : Terminado).
     */
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
    
    /**
     * Método que devuelve al atributo nombre.
     * @return nombre Cadena que es el nombre del proceso.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Método que devuelve al atributo id.
     * @return id Entero que es el identificador único del proceso.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Método que devuelve al atributo estado.
     * @return estado Cadena que es el estado del proceso (Nuevo, En ejecución, Preparado o Terminado).
     */
    public String getEstado() {
        return estado;
    }
    
    /**
     * Método que devuelve al atributo instrucciones.
     * @return instrucciones Arreglo entero de tamaño 2 que indica: [0] : Instrucciones totales del proceso y [1] : Instrucciones ejecutadas del proceso.
     */
    public int[] getInstrucciones() {
        return instrucciones;
    }
    
    /**
     * Método que devuelve al atributo direcciones_memoria.
     * @return direcciones_memoria Arreglo entero de tamaño 2 que indica: [0] : Dirección base del proceso y [1] : Dirección límite del proceso.
     */
    public int[] getDirecciones() {
        return direcciones_memoria;
    }
    
    /**
     * Método que simula la realización de instrucciones del proceso.
     * Aumenta en 5 las instrucciones ejecutadas, sin sobrepasar las instrucciones totales. Muestra un mensaje si termina la ejecución del proceso, cambia su estado y notifica al usuario.
     * @return valorBooleano Booleano que indica si el proceso a concluido su ejecucuión (No hay más instrucciones por realizar).
     */
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
    
    /**
     * Método que simula la eliminación del proceso.
     * Cambia el estado del proceso a Terminado y asigna como dirección base y límite el valor 0.
     */
    public void eliminaProceso() {
        this.setEstado(3);
        this.setDir(0,0);
    }
    
    /**
     * Método que imprime los atributos del proceso.
     */
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

