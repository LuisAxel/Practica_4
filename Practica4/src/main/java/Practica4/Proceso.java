package Practica4;

import java.util.Random;

/**
 * Clase Proceso, modela al objeto proceso.
 * <p>
 * Cuenta con los atributos:
 * <p>
 * <ul>
 * <li> nombre: Nombre del proceso.</li>
 * <li> id: Identificador único del proceso.
 * <li> instrucciones: Arreglo de tamaño 2, almacena las instrucciones totales y
 * ejecutadas del proceso.</li>
 * <li> paginas: Número de páginas con las que cuenta el proceso. </li>
 * <li> marcos: Arreglo que relaciona la página de posición i con el marco de 
 * memoria (Tabla de páginas).</li>
 * <li> estado: Almacena el estado del proceso (Nuevo, En ejecución, Preparado o
 * Terminado).</li>
 * <li> num_procesos: Contador de total de procesos, es utilizada para asignar ids
 * únicos.</li>
 *</ul>
 * @author Núñez Quintana, Luis Axel
 * @author Zárate García, Zuriel
 */
public class Proceso {

    private String nombre;
    private int id;
    private int[] instrucciones; // 2 [totales, ejecutadas]
    private int paginas; 
    private int[] marcos; // posicion = pag, valor = marco 
    private String estado;
    private static int num_procesos;

    /**
     * Método constructor, inicializa los atributos nombre, id,
     *tabla de páginas, páginas, instrucciones y estado. Solo realiza asignación
     * directa a id y num_procesos, llama a los métodos setters.
     *
     * @param nombre Nombre del proceso.
     * @param m Lista de marcos inciales del proceso.
     * @param dir_limite Tamaño del proceso.
     */
    public Proceso(String nombre, int[] m, int dir_limite) {
        id = ++num_procesos;
        setNombre(nombre);
        setPaginas(dir_limite);
        instrucciones = new int[2];
        marcos = new int[paginas];
        setMarcos(m);
        setInstrucciones();
        setEstado(0);
    }

    /**
     * Método que inicializa el nombre.
     *
     * @param nombre Cadena que es el nombre del proceso.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Método que inicializa el número de páginas que tendrá un proceso.
     *
     * @param dir_limite Entero que indica la el tamaño del proceso.
     */
    public void setPaginas(int dir_limite) {
        this.paginas = dir_limite / 16;
    }

    /**
     * Método que inicializa la tabla de páginas de un proceso.
     *
     * @param m Lista de marcos del proceso
     */
    public void setMarcos(int[] m) {
        for(int i = 0; i < paginas; i ++){
            this.marcos[i] = m[i];
        }
    }
    
    /**
     * Método que altera una relación entre página y marco.
     *
     * @param marco_viejo marco a ser reemplazado.
     * @param marco_nuevo marco por el cual se reemplazará.
     */
    public void setMarco(int marco_viejo, int marco_nuevo) {
        for(int i = 0; i < paginas; i ++){
            if(this.marcos[i] == marco_viejo){
                this.marcos[i] = marco_nuevo;
                break;
            }
        }
    }

    /**
     * Método que inicializa el arreglo instrucciones. Las instrucciones totales
     * es un valor aleatorio entero entre 10 y 30.
     */
    public void setInstrucciones() {
        Random rand = new Random();
        this.instrucciones[0] = rand.nextInt(21) + 10;
        this.instrucciones[1] = 0;
    }

    /**
     * Método que inicializa y altera el estado del proceso.
     *
     * @param estado que indica el nuevo estado del proceso (0 : Nuevo, 1 : En
     * ejecución, 2 : Preparado, 3 : Terminado).
     */
    public void setEstado(int estado) {
        switch (estado) {
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
     *
     * @return nombre Cadena que es el nombre del proceso.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que devuelve al atributo id.
     *
     * @return id Entero que es el identificador único del proceso.
     */
    public int getId() {
        return id;
    }

    /**
     * Método que devuelve al atributo estado.
     *
     * @return estado Cadena que es el estado del proceso (Nuevo, En ejecución,
     * Preparado o Terminado).
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método que devuelve al atributo instrucciones.
     *
     * @return instrucciones Arreglo entero de tamaño 2 que indica: [0] :
     * Instrucciones totales del proceso y [1] : Instrucciones ejecutadas del
     * proceso.
     */
    public int[] getInstrucciones() {
        return instrucciones;
    }

    /**
     * Método que devuelve la tabla de páginas de un proceso
     *
     * @return marcos Arreglo entero de tamaño paginas que indica: posición :
     * página del proceso y valor : marco asociado.
     */
    public int[] getMarcos() {
        return marcos;
    }

    /**
     * Método que simula la realización de instrucciones del proceso.
     * Aumenta en 5 las instrucciones ejecutadas, sin sobrepasar las
     * instrucciones totales. Muestra un mensaje si termina la ejecución del
     * proceso, cambia su estado y notifica al usuario.
     *
     * @return valorBooleano Booleano que indica si el proceso a concluido su
     * ejecucuión (No hay más instrucciones por realizar).
     */
    public boolean realizaInstrucciones() {
        this.instrucciones[1] += 5;
        if (this.instrucciones[1] >= this.instrucciones[0]) {
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
     * Cambia el estado del proceso a Terminado y elimina el arreglo de marcos
     */
    public void eliminaProceso() {
        this.setEstado(3);
        this.marcos = null;
    }

    /**
     * Método que imprime los atributos y tabla de páginas del proceso.
     */
    public void imprimeProceso() {
        System.out.println("\tNombre del Proceso: " + getNombre());
        System.out.println("\tId del Proceso: " + getId());
        System.out.println("\tEstado del Proceso: " + getEstado());
        System.out.println("\tInstrucciones totales del Proceso: " + getInstrucciones()[0]);
        System.out.println("\tInstrucciones ejecutadas del Proceso: " + getInstrucciones()[1]);
        System.out.println("\tMarcos: ");
        for(int i = 0; i < paginas; i ++){
             System.out.println("\tPagina " + i +": Marco " + marcos[i]);
        }
    }
}
