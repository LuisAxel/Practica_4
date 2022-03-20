package Practica4;

/**
 *
 * @author axel
 */
public class Practica4 {

    public static void main(String[] args) {
        SistemaOperativo SO = SistemaOperativo.getSistemaOperativo();
        
        System.out.println("############## 0 Procesos ##############");
        //Operaciones al tener 0 procesos
        System.out.println("############## ESTADO ##############");
        SO.verEstado();
        System.out.println("############## COLA ##############");
        SO.verProcesos();
        System.out.println("############## ACTUAL ##############");
        SO.verProcesoActual();
        System.out.println("############## EJECUTA ##############");
        SO.ejecutaProceso();
        System.out.println("############## ELIMINA ##############");
        SO.eliminaProceso();
        System.out.println("############## SIGUIENTE ##############");
        SO.siguienteProceso();
        System.out.println("############## SALIR ##############");
        SO.salirSistema();
        
        
        System.out.println("############################");
        System.out.println("############## 1 Proceso ##############");
        //Operaciones al tener 1 proceso
        System.out.println("############## CREA ##############");
        SO.creaProceso("Word.exe", 512);
        
        System.out.println("############## ESTADO ##############");
        SO.verEstado();
        System.out.println("############## COLA ##############");
        SO.verProcesos();
        System.out.println("############## ACTUAL ##############");
        SO.verProcesoActual();
        System.out.println("############## EJECUTA ##############");
        SO.ejecutaProceso();
        System.out.println("############## SIGUIENTE ##############");
        SO.siguienteProceso();
        System.out.println("############## SALIR ##############");
        SO.salirSistema();
        
        System.out.println("############## ESTADO ##############");
        SO.verEstado();
        System.out.println("############## ACTUAL ##############");
        SO.verProcesoActual();
        System.out.println("############## ELIMINA ##############");
        SO.eliminaProceso();
        System.out.println("############## ESTADO ##############");
        SO.verEstado();
        System.out.println("############## ACTUAL ##############");
        SO.verProcesoActual();
        
        System.out.println("############################");
        System.out.println("############## Muchos Procesos ##############");
        //Operaciones al tener max procesos
        System.out.println("############## CREA ##############");
        SO.creaProceso("Word2.exe", 512);
        System.out.println("############## CREA ##############");
        SO.creaProceso("Word3.exe", 512);
        System.out.println("############## CREA ##############");
        SO.creaProceso("Word4.exe", 512);
        System.out.println("############## CREA ##############");
        SO.creaProceso("Word5.exe", 512);
        System.out.println("############## CREA ##############");
        SO.creaProceso("Word6.exe", 512);
        
        System.out.println("############## ESTADO ##############");
        SO.verEstado();
        System.out.println("############## COLA ##############");
        SO.verProcesos();
        System.out.println("############## ACTUAL ##############");
        SO.verProcesoActual();
        System.out.println("############## EJECUTA ##############");
        SO.ejecutaProceso();
        System.out.println("############## ESTADO ##############");
        SO.verEstado();
        System.out.println("############## SIGUIENTE ##############");
        SO.siguienteProceso();
        System.out.println("############## SALIR ##############");
        SO.salirSistema();
        
        System.out.println("############## ESTADO ##############");
        SO.verEstado();
        System.out.println("############## ACTUAL ##############");
        SO.verProcesoActual();
        System.out.println("############## ELIMINA ##############");
        SO.eliminaProceso();
        System.out.println("############## ESTADO ##############");
        SO.verEstado();
        System.out.println("############## ACTUAL ##############");
        SO.verProcesoActual();
        
        System.out.println("############## EJECUTA ##############");
        
        for(int i = 0; i < 20; i ++){
            SO.ejecutaProceso();
        }
        
        System.out.println("############## ESTADO ##############");
        SO.verEstado();
        System.out.println("############## ACTUAL ##############");
        SO.verProcesoActual();
    }
}
