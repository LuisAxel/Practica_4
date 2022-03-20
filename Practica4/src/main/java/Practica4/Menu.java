package Practica4;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase menú, no modela ningún tipo de dato abstracto en específico. Contiene un método para generar un menú.
 * @author Nuñez Quintana, Luis Axel
 * @author Zárate García, Zuriel
 */
public class Menu{

    /**
     * 
     * @param opciones: Un string que indica las opciones para acceder a las funciones del programa. (incluyendo "salir").
     * @return opcion: Un entero que almacena la opción elegida por el usuario.
     */
    public static int menu (String[] opciones) throws IOException, InputMismatchException {
            
            Scanner entrada = new Scanner(System.in);
            int opcion = 0;
            
            while(opcion == 0){
                System.out.println("\nElige una opción: ");
                for (int i = 0; i < opciones.length; i++) {
                    System.out.println("\t" + (i+1) + ") "+ opciones[i]);
                }
                
                System.out.print("\nOpción seleccionada: ");
                opcion = entrada.nextInt();
                entrada.nextLine();
                
                if(opcion < 1 || opcion > opciones.length){
                    System.out.println("Has elegido el camino de la muerte >:v");
                    System.out.println("\nPresione una letra para continuar...");
                    System.in.read();
                    opcion = 0;
                }               
            }
            
            return opcion;         
        }
}
