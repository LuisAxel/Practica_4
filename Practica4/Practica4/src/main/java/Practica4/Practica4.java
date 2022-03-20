package Practica4;
import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author Nuñez Quintana, Luis Axel
 * @author Zárate García, Zuriel
 */
public class Practica4 {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SistemaOperativo SO = SistemaOperativo.getSistemaOperativo();
       
        System.out.println("Gracias por instalar...");
        System.out.println("    __________________________________________________________________");
        System.out.println("");
        System.out.println("            ´\"_/_/´\"´\" _/_/_/_/_/´\" ´\"´\" ´\"_/_/´\"´\" ´\"_/_/_/´\" ");
        System.out.println("            _/´\"´\"_/´\" ´\"´\"´\"_/´\"´\" ´\"´\" _/´\"´\"_/´\" _/´\"´\"´\"´\" ");
        System.out.println("            _/_/_/_/´\" ´\"´\"_/´\"´\"´\" ´\"´\" _/´\"´\"_/´\" ´\"_/_/´\"´\" ");
        System.out.println("            _/´\"´\"_/´\" ´\"_/´\"´\"´\"´\" ´\"´\" _/´\"´\"_/´\" ´\"´\"´\"_/´\" ");
        System.out.println("            _/´\"´\"_/´\" _/_/_/_/_/´\" ´\"´\" ´\"_/_/´\"´\" _/_/_/´\"´\" ");
        System.out.println("    __________________________________________________________________\n");
       
        String[] opciones = {
            "Crear proceso",
            "Ver proceso actual",
            "Ejecutar proceso actual",
            "Pasar al proceso siguiente",
            "Matar proceso actual",
            "Ver estado actual del sistema",
            "Imprimir cola de procesos",
            "Salir del programa"
        };
        
        int eleccion = 0;
        while(eleccion != 8){
            switch(eleccion){
                case 0:
                    try{
                        eleccion = Menu.menu(opciones);
                    }catch(IOException e){
                        System.out.println("Algo salio mal");
                    }
                    break;
                
                case 1:
                    System.out.print("Ingresa el nombre del proceso: ");
                    Scanner nombreP = new Scanner(System.in);
                    String nombre = nombreP.nextLine();
                    System.out.print("\n Elige el tama\u00f1o de tu proceso: ");
                    String[] tamanios = {"64","128","256","512","Cancelar"};
                    
                    int opcase1 = 0;
                    while(opcase1!=5){
                        
                        boolean disponible;
                        
                        switch(opcase1){
                            case 0:
                                try{
                                    opcase1 = Menu.menu(tamanios);
                                }catch(IOException e){
                                    System.out.println("Algo salio mal");
                                }
                                break;
                                
                            case 1:
                                disponible = SO.creaProceso(nombre, 64);
                                if(disponible == true){
                                    System.out.println("El proceso se ha creado con exito! Agregado a la cola :D");
                                }
                                else{
                                    System.out.println("No hay espacio suficiente para el proceso :C");
                                }
                                opcase1 = 5;
                                break;
                                
                            case 2:
                                disponible = SO.creaProceso(nombre, 128);
                                if(disponible == true){
                                    System.out.println("El proceso se ha creado con exito! Agregado a la cola :D");
                                }
                                else{
                                    System.out.println("No hay espacio suficiente para el proceso :C");
                                }
                                opcase1 = 5;
                                break;
                                
                            case 3:
                                disponible = SO.creaProceso(nombre, 256);
                                if(disponible == true){
                                    System.out.println("El proceso se ha creado con exito! Agregado a la cola :D");
                                }
                                else{
                                    System.out.println("No hay espacio suficiente para el proceso :C");
                                }
                                opcase1 = 5;
                                break;
                                
                            case 4:
                                disponible = SO.creaProceso(nombre, 512);
                                if(disponible == true){
                                    System.out.println("El proceso se ha creado con exito! Agregado a la cola :D");
                                }
                                else{
                                    System.out.println("No hay espacio suficiente para el proceso :C");
                                }
                                opcase1 = 5;
                                break;
                                
                            case 5:
                                opcase1 = 0;
                                break;
                        }
                    }
                    
                    eleccion=0;
                    break;
                    
                case 2:
                    SO.verProcesoActual();
                    eleccion=0;
                    break;
                    
                case 3:
                    System.out.println("Ejecutando proceso...");
                    SO.ejecutaProceso();
                    eleccion=0;
                    break;
                    
                case 4:
                    SO.siguienteProceso();
                    eleccion=0;
                    break;
                    
                case 5:
                    System.out.println("Eliminando proceso actual...");
                    SO.eliminaProceso();
                    eleccion=0;
                    break;
                    
                case 6:
                    System.out.println("Estado del sistema: ");
                    SO.verEstado();
                    eleccion=0;
                    break;
                    
                case 7:
                    SO.verProcesos();
                    eleccion=0;
                    break;
                    
                case 8:
                    System.out.println("Saliendo...");
                    SO.salirSistema();
                    eleccion=0;
                    break;
            }

        }
        
    }
}
