package Practica4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase SistemaOperativo, modela al objeto sistema operativo.
 * <p>
 * Cuenta con los atributos:
 * <p>
 * <ul>
 * <li> sistemaOperativo: Instancia única de sistema operativo.</li>
 * <li> nombre: Cadena que indica el nombre del sistema operativo, su valor es "AZ
 * SO".</li>
 * <li> cola_procesos: Cola que almacena los procesos "preparados" y el proceso en
 * "ejecución".</li>
 * <li> procesos_finalizados: Lista ligada que almacena los procesos que han
 * concluido todas sus instrucciones.</li>
 * <li> procesos_eliminados: Lista ligada que almacena los procesos que han
 * finalizado sin realizar todas sus instrucciones.</li>
 * <li> memoria: Arreglo de tamaño 2048 direcciones de memoria con las que cuenta
 * el sistema operativo.</li>
 * </ul>
 * 
 * @author Núñez Quintana, Luis Axel
 * @author Zárate García, Zuriel
 */
public class SistemaOperativo {

    private static SistemaOperativo sistemaOperativo;
    private String nombre;
    private Queue<Proceso> cola_procesos;
    private LinkedList<Proceso> procesos_finalizados;
    private LinkedList<Proceso> procesos_eliminados;
    private DireccionMemoria[] memoria;

    /**
     * Método constructor vacío privado, únicamente se puede tener una instancia
     * de sistema operativo.
     */
    private SistemaOperativo() {
    }

    /**
     * Método constructor privado que es utilizado para crear la única instancia
     * de sistema operativo, inicializa el arreglo de direcciones de memoria, la
     * cola de procesos y las listas de procesos finalizados y eliminados.
     *
     * @param n Nombre del sistema operativo, su valor es "AZ SO".
     */
    private SistemaOperativo(String n) {
        nombre = n;
        memoria = new DireccionMemoria[2048];
        cola_procesos = new LinkedList<>();
        procesos_finalizados = new LinkedList<>();
        procesos_eliminados = new LinkedList<>();
        inicializaMemoria();
    }

    /**
     * Método que devuelve o crea y devuelve a la única instancia del sistema
     * operativo
     *
     * @return sistemaOperativo Instancia del sistema operativo "AZ SO", cuenta
     * con 2048 localidades de memoria.
     */
    public static SistemaOperativo getSistemaOperativo() {
        if (sistemaOperativo == null) {
            sistemaOperativo = new SistemaOperativo("AZ SO");
        }
        return sistemaOperativo;
    }

    /**
     * Método que inicializa todas las localidades de memoria del sistema
     * operativo.
     */
    private void inicializaMemoria() {
        for (int i = 0; i < 2048; i++) {
            memoria[i] = new DireccionMemoria(i);
        }
    }

    /**
     * Método que imprime el estado de todas las localidades de memoria del
     * sistema operativo.
     */
    private void verMemoria() {
        for (int i = 0; i < 2048; i++) {
            memoria[i].imprimeMemoria();
        }
    }

    /**
     * Método que imprime los nombres de los procesos finalizados. Si no han
     * finalizado procesos, notifica al usuario.
     */
    private void verProcesosFinalizados() {
        if (procesos_finalizados.isEmpty()) {
            System.out.println("\tNo hay procesos finalizados");
        } else {
            System.out.println("\tProcesos finalizados");
            for (Proceso i : procesos_finalizados) {
                System.out.println("\t" + i.getNombre());
            }
        }
    }

    /**
     * Método que imprime los nombres de los procesos eliminados. Si no se han
     * eliminado procesos, notifica al usuario.
     */
    private void verProcesosEliminados() {
        if (procesos_eliminados.isEmpty()) {
            System.out.println("\tNo hay procesos eliminados");
        } else {
            System.out.println("\tProcesos eliminados");
            for (Proceso i : procesos_eliminados) {
                System.out.println("\t" + i.getNombre());
            }
        }
    }

    /**
     * Método que imprime los atributos de los procesos "preparados" y el
     * proceso "en ejecución". Si no hay procesos, notifica al usuario,
     */
    public void verProcesos() {
        if (cola_procesos.isEmpty()) {
            System.out.println("\tNo hay procesos preparados o en ejecucion");
        } else {
            System.out.println("\tProcesos listos");
            for (Proceso i : cola_procesos) {
                i.imprimeProceso();
            }
        }
    }

    /**
     * Método que imprime los atributos del proceso "en ejecución". Si no hay
     * procesos, notifica al usuario,
     */
    public void verProcesoActual() {
        if (cola_procesos.isEmpty()) {
            System.out.println("\tNo hay procesos preparados");
        } else {
            cola_procesos.peek().imprimeProceso();
        }
    }

    /**
     * Método que ejecuta 5 instrucciones del proceso "en ejecución", vuelve a
     * ingresarlo a la cola de procesos si tiene instrucciones por realizar,
     * cambia los estados del proceso actual a "preparado" y al siguiente a "en
     * ejecución". Si concluye sus instrucciones cambia su estado a "terminado"
     * y lo agrega a la lista de procesos finalizados. Si no hay procesos,
     * notifica al usuario.
     */
    public void ejecutaProceso() {
        if (cola_procesos.isEmpty()) {
            System.out.println("\tNo hay procesos preparados");
        } else {
            Proceso actual = cola_procesos.remove();

            if (actual.realizaInstrucciones()) {
                cola_procesos.add(actual);
            } else {
                liberaProceso(actual);
                actual.eliminaProceso();
                procesos_finalizados.add(actual);
            }
            if (cola_procesos.peek() != null) {
                cola_procesos.peek().setEstado(1);
            }
        }
    }

    /**
     * Método que cambia al siguiente proceso en la cola sin realizar
     * instrucciones del actual. Cambia los estados del proceso actual a
     * "preparado" y al siguiente a "en ejecución". Si no hay procesos, notifica
     * al usuario.
     */
    public void siguienteProceso() {
        if (cola_procesos.isEmpty()) {
            System.out.println("\tNo hay procesos preparados");
        } else {
            Proceso actual = cola_procesos.remove();
            actual.setEstado(2);
            cola_procesos.add(actual);
            cola_procesos.peek().setEstado(1);
        }
    }

    /**
     * Método que elimina al proceso actual y libera las direcciones de memoria
     * que utilizaba. Cambia los estados del proceso actual a "finalizado" y al
     * siguiente a "en ejecución". Si no hay procesos, notifica al usuario.
     */
    public void eliminaProceso() {
        if (cola_procesos.isEmpty()) {
            System.out.println("\tNo hay procesos preparados");
        } else {
            Proceso actual = cola_procesos.remove();
            liberaProceso(actual);
            actual.eliminaProceso();
            procesos_eliminados.add(actual);

            if (cola_procesos.peek() != null) {
                cola_procesos.peek().setEstado(1);
            }
        }
    }

    /**
     * Método que libera las direcciones de memoria que utilizaba el proceso a
     * eliminar.
     *
     * @param p Proceso a eliminar.
     */
    private void liberaProceso(Proceso p) {
        for (int i = p.getDirecciones()[0]; i < p.getDirecciones()[0] + p.getDirecciones()[1]; i++) {
            memoria[i].liberaMemoria();
        }
    }

    /**
     * Método que muestra el estado del sistema operativo. Muestra las
     * direcciones de memoria, el número de procesos "preparados" y "en
     * ejecución", el nombre de los procesos finalizados y eliminados.
     */
    public void verEstado() {
        System.out.println("\tNumero de procesos listos: " + cola_procesos.size());
        verProcesosFinalizados();
        verProcesosEliminados();
        verMemoria();
    }

    /**
     * Método que crea un proceso y lo agrega a la cola de procesos si hay
     * espacio disponible en la memoria.
     *
     * @param nombre Nombre del proceso a crear.
     * @param tamano Número de direcciones de memoria que utilizará el proceso.
     * @return valorBooleano Indica si fue posible la creación del proceso, su
     * valor es falso si no hay localidades de memoria contiguas disponibles
     * para el proceso.
     */
    public boolean creaProceso(String nombre, int tamano) {
        int counter = 0;

        for (int i = 0; i < 2048; i++) {
            if (!memoria[i].getEstado()) {
                counter++;
                if (counter == tamano) {
                    Proceso nuevo_proceso = new Proceso(nombre, i - tamano + 1, tamano);
                    int dir_base = i - tamano + 1;
                    for (int j = 0; j < tamano; j++) {
                        memoria[j + dir_base].iniciaMemoria(nuevo_proceso.getNombre());
                    }

                    if (cola_procesos.isEmpty()) {
                        nuevo_proceso.setEstado(1);
                    } else {
                        nuevo_proceso.setEstado(2);
                    }

                    cola_procesos.add(nuevo_proceso);

                    return true;
                }
            } else {
                counter = 0;
            }
        }

        return false;
    }

    /**
     * Método que termina la ejecución del sistema operativo. Muestra la cola de
     * procesos.
     */
    public void salirSistema() {
        verProcesos();
        for(Proceso p : cola_procesos){
            p = null;
        }
        for(Proceso p : procesos_eliminados){
            p = null;
        }
        for(Proceso p : procesos_finalizados){
            p = null;
        }
        for(DireccionMemoria m : memoria){
            m = null;
        }
        sistemaOperativo = null;
    }

}
