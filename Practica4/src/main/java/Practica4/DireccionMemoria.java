
package Practica4;

/**
 * Clase DireccionMemoria, modela al objeto dirección de memoria. 
 * 
 * Cuenta con los atributos: 
 * 
 * |> localidad: Entero que indica la localidad a la que corresponde una dirección de memoria.
 * |> proceso: Cadena que indica el nombre del proceso que utiliza la localidad. Su valor es "-" si no se encuentra en uso.
 * |> estado: Booleano que indica si la localidad se encuentra en uso.
 * 
 * @author Núñez Quintana, Luis Axel
 * @author Zárate García, Zuriel
 */
public class DireccionMemoria {
    private int localidad;
    private String proceso;
    private boolean estado;
    
    /**
     * Método constructor, inicializa los atributos localidad, proceso y estado. Solo realiza asignación directa a localidad, llama a los métodos setters. Una nueva dirección de memoria no tiene un proceso asignado.
     * @param l Localidad correspondiente a la dirección de memoria.
     */
    public DireccionMemoria(int l){
        localidad = l;
        liberaMemoria();
    }
    
    /**
     * Método que devuelve al atributo localidad.
     * @return localidad Entero que es la localidad correspondiente a la dirección de memoria.
     */
    public int getLocalidad(){
        return localidad;
    }
    
    /**
     * Método que devuelve al atributo proceso.
     * @return proceso Cadena que es el proceso correspondiente a la dirección de memoria, "-" si no se encuentra en uso.
     */
    public String getProceso(){
        return proceso;
    }
    
    /**
     * Método que devuelve al atributo estado.
     * @return estado Booleano que indica si dirección de memoria se encuentra en uso.
     */
    public boolean getEstado(){
        return estado;
    }
    
    /**
     * Método que inicia una dirección de memoria indicando el nombre del proceso que hace uso de ella y cambiando su valor estado a verdadero.
     * @param p Cadena que indica el nombre del proceso que utilizará la dirección de memoria.
     */
    public void iniciaMemoria(String p){
        this.proceso = p;
        this.estado = true;
    }
    
    /**
     * Método que libera una dirección de memoria cambiando su valor estado a falso e indicando el nombre de proceso a "-".
     */
    public void liberaMemoria(){
        this.proceso = "-";
        this.estado = false;
    }
    
    /**
     * Método que imprime los atributos de la dirección de memoria, si no se encuentra en uso no muestra el atributo proceso.
     */
    public void imprimeMemoria(){
        System.out.print("\tLocalidad: " + getLocalidad());
        if(getEstado()){
            System.out.print("\tEstado: En uso");
            System.out.println("\tProceso: " + getProceso());
        }else{
            System.out.println("\tEstado: Libre");
        }
    }
}
