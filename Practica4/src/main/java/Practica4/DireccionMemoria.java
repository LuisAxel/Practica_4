package Practica4;

/**
 * Clase DireccionMemoria, modela al objeto dirección de memoria.
 * <p>
 * Cuenta con los atributos:
 * <p>
 * <ul>
 * <li> localidad: Entero que indica la localidad a la que corresponde una
 * dirección de memoria.</li>
 * <li> proceso: Cadena que indica el nombre del proceso que utiliza la localidad.
 * Su valor es "-" si no se encuentra en uso.</li>
 * <li> estado: Booleano que indica si la localidad se encuentra en uso.</li>
 * <li> marco: Entero que indica el marco a la que corresponde una dirección de 
 * memoria.</li>
 * </ul>
 * @author Núñez Quintana, Luis Axel
 * @author Zárate García, Zuriel
 */
public class DireccionMemoria {

    private final int localidad;
    private String proceso;
    private boolean estado;
    private final int marco;

    /**
     * Método constructor, inicializa los atributos localidad, proceso, estado y marco. Solo realiza asignación directa a localidad y marco, llama a los métodos setters.
     * Una nueva dirección de memoria no tiene un proceso asignado.
     *
     * @param l Localidad correspondiente a la dirección de memoria.
     * @param m Marco correspondiente a la dirección de memoria.
     */
    public DireccionMemoria(int l, int m) {
        localidad = l;
        marco = m;
        liberaMemoria();
    }

    /**
     * Método que devuelve al atributo localidad.
     *
     * @return localidad Entero que es la localidad correspondiente a la
     * dirección de memoria.
     */
    public int getLocalidad() {
        return localidad;
    }

    /**
     * Método que devuelve al atributo proceso.
     *
     * @return proceso Cadena que es el proceso correspondiente a la dirección
     * de memoria, "-" si no se encuentra en uso.
     */
    public String getProceso() {
        return proceso;
    }

    /**
     * Método que devuelve al atributo estado.
     *
     * @return estado Booleano que indica si dirección de memoria se encuentra
     * en uso.
     */
    public boolean getEstado() {
        return estado;
    }

    /**
     * Método que inicia una dirección de memoria indicando el nombre del
     * proceso que hace uso de ella y cambiando su valor estado a verdadero.
     *
     * @param p Cadena que indica el nombre del proceso que utilizará la
     * dirección de memoria.
     */
    public void iniciaMemoria(String p) {
        this.proceso = p;
        this.estado = true;
    }

    /**
     * Método que libera una dirección de memoria cambiando su valor estado a
     * falso e indicando el nombre de proceso a "-".
     */
    public void liberaMemoria() {
        this.proceso = "-";
        this.estado = false;
    }
    
    /**
     * Método que devuelve al atributo marco.
     *
     * @return marco al que pertenece la localidad de memoria
     */
    public int getMarco() {
        return marco;
    }

    /**
     * Método que imprime los atributos de la dirección de memoria, si no se
     * encuentra en uso no muestra el atributo proceso.
     */
    public void imprimeMemoria() {
        System.out.print("\tLocalidad: " + getLocalidad());
        System.out.print("\tMarco: " + getMarco());
        if (getEstado()) {
            System.out.print("\tEstado: En uso");
            System.out.println("\tProceso: " + getProceso());
        } else {
            System.out.println("\tEstado: Libre");
        }
    }
}
