
package Practica4;

import java.util.LinkedList;
import java.util.Queue;

public class SistemaOperativo {
    static SistemaOperativo sistemaOperativo;
    private String nombre;
    private Queue<Proceso> cola_procesos;
    private LinkedList<Proceso> procesos_finalizados;
    private LinkedList<Proceso> procesos_eliminados;
    private DireccionMemoria[] memoria;
    
    
    private SistemaOperativo(){
    }
    
    private SistemaOperativo(String nombre){
        memoria = new DireccionMemoria[2048];
        cola_procesos = new LinkedList<>();
        procesos_finalizados = new LinkedList<>();
        procesos_eliminados = new LinkedList<>();
        inicializaMemoria();
    }
    
    public static SistemaOperativo getSistemaOperativo(){
        if(sistemaOperativo == null){
            sistemaOperativo = new SistemaOperativo("AZ SO");
        }
        return sistemaOperativo;
    }
    
    
    private void inicializaMemoria(){
        for(int i = 0; i < 2048; i ++){
            memoria[i] = new DireccionMemoria(i);
        }
    }
    
    private void verMemoria(){
        for(int i = 0; i < 2048; i ++){
            memoria[i].imprimeMemoria();
        }
    }
    
    private void verProcesosFinalizados(){
        System.out.println("\tProcesos finalizados");
        for(Proceso i : procesos_finalizados){
            System.out.println("\t" + i.getNombre());
        }
    }
    
    private void verProcesosEliminados(){
        System.out.println("\tProcesos eliminados");
        for(Proceso i : procesos_eliminados){
            System.out.println("\t" + i.getNombre());
        }
    }
    
    public void verProcesos(){
        System.out.println("\tProcesos listos");
        for(Proceso i : cola_procesos){
            i.imprimeProceso();
        }
    }
    
    public void verProcesoActual(){
        if(cola_procesos.isEmpty()){
            System.out.println("\tNo hay procesos preparados");
        }else{
            cola_procesos.peek().imprimeProceso();
        }
    }
    
    public void ejecutaProceso(){
        if(cola_procesos.isEmpty()){
            System.out.println("\tNo hay procesos preparados");
        }else{
            Proceso actual = cola_procesos.remove();
            
            if(actual.realizaInstrucciones()){
                cola_procesos.add(actual);
            }else{
                liberaProceso(actual);
                procesos_finalizados.add(actual);
            }
            if(cola_procesos.peek() != null){
                cola_procesos.peek().setEstado(1);
            }
        }
    }
    
    public void siguienteProceso(){
        if(cola_procesos.isEmpty()){
            System.out.println("\tNo hay procesos preparados");
        }else{
            Proceso actual = cola_procesos.remove();
            actual.setEstado(2);
            cola_procesos.add(actual); 
            cola_procesos.peek().setEstado(1);
        }
    }
    
    public void eliminaProceso(){
        if(cola_procesos.isEmpty()){
            System.out.println("\tNo hay procesos preparados");
        }else{
            Proceso actual = cola_procesos.remove();
            liberaProceso(actual);
            procesos_eliminados.add(actual);
            
            if(cola_procesos.peek() != null){
                cola_procesos.peek().setEstado(1);
            }
        }
    }
    
    private void liberaProceso(Proceso p){
        for(int i = p.getDirecciones()[0]; i < p.getDirecciones()[0] + p.getDirecciones()[1]; i ++){
            memoria[i].liberaMemoria();
        }
    }
    
    public void verEstado(){
        System.out.println("\tNumero de procesos listos: " + cola_procesos.size());
        verProcesosFinalizados();
        verProcesosEliminados();
        verMemoria();
    }
    
    public boolean creaProceso(String nombre, int tamano){
        int counter = 0;
        
        for(int i = 0; i < 2048; i++){
            if(!memoria[i].getEstado()){
                counter ++;
                if(counter == tamano){
                    Proceso nuevo_proceso = new Proceso(nombre, i - tamano + 1,tamano);
                    int dir_base = i - tamano + 1;
                    for(int j = 0; j < tamano; j ++){
                        memoria[j + dir_base].iniciaMemoria(nuevo_proceso.getNombre());
                    }
                    
                    if(cola_procesos.isEmpty()){
                        nuevo_proceso.setEstado(1);
                    }else{
                        nuevo_proceso.setEstado(2);
                    }
                                        
                    cola_procesos.add(nuevo_proceso);
                    
                    return true;
                }
            }else{
                counter = 0;
            }
        }
        
        return false;
    }
    
    public void salirSistema(){
        verProcesos();
    }
    
}
