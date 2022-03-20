/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica4;

/**
 *
 * @author axel
 */
public class DireccionMemoria {
    private int localidad;
    private String proceso;
    private boolean estado;
    
    public DireccionMemoria(int l){
        localidad = l;
        liberaMemoria();
    }
    
    public int getLocalidad(){
        return localidad;
    }
    
    public String getProceso(){
        return proceso;
    }
    
    public boolean getEstado(){
        return estado;
    }
    
    public void iniciaMemoria(String p){
        this.proceso = p;
        this.estado = true;
    }
    
    public void liberaMemoria(){
        this.proceso = "-";
        this.estado = false;
    }
    
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
