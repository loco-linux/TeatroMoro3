
package teatromoro3;

import java.util.Scanner;

/**
 *
 * @author jbarr
 */
public class TeatroMoro3 {

    // Variables estáticas
    // defino colores para optimizar visualizacion de texto del programa   
    public static String red=     "\033[31m"; 
    public static String green=   "\033[32m"; 
    public static String blue=    "\033[34m"; 
    public static String cyan=    "\033[36m"; 
    public static String reset=   "\u001B[0m";   
    
    public static double totalIngresos = 0;
    public static boolean unAdulto = false;
    
    public static boolean llenoZonaA = false;
    public static boolean llenoZonaB = false;
    public static boolean llenoZonaC = false;
    
    
    // Abstraccion de representacion de datos
     // Declaro una matriz unidimensional para las zonas de entradas
     public static String[] tipoEntrada = {"A", "B", "C"};
     // Declaro matriz unidimensional para cuantificar entradas por sector
     public static int[] contadorEntrada = {0,0,0};
     
 
    public static String[] entradasCompradas = {"","","","","","","","","","","","","","",""};
    public static int indicadorEnt = 0; 
     // declaro matriz 3x5
     public static int[][] ubicacionAsiento = {
         {0,0,0,0,0}, // [0,0][0,1][0,2][0,3][0,4]
         {0,0,0,0,0}, // [1,0][1,1][1,2][1,3][1,4]
         {0,0,0,0,0}, // [2,0][2,1][2,2][2,3][2,4]
        
     };
    
    
    
    public static void main(String[] args) {
     
        bienvenida(); // metodo que imprime una bienvenida en pantalla
        
        // Variables de input de usuario desde teclado
        Scanner teclado = new Scanner(System.in);
        
        // Definicion de variables locales
     boolean encontrado = false; 
     int opcion;
             
     while(encontrado==false){
        System.out.println("Presiona 1 si quieres "+green+"[Comprar Entrada]"+reset);
        System.out.println("Presiona 2 si quieres "+green+"[Promociones]"+reset);
        System.out.println("Presiona 3 si quieres "+green+"[Total Entradas]"+reset);
        System.out.println("Presiona 4 si quieres "+green+"[Plano Teatro]"+reset);
        System.out.println("Presiona 5 si quieres "+green+"[Salir]"+reset);
        
        
        opcion = teclado.nextInt();
        
        switch(opcion){
            case 1 -> fxComprarEntrada();
            case 2 -> promociones();
            case 3 -> Entrada.entradasVendidas();
            case 4 -> planoTeatro();
            case 5 -> encontrado = true; // para salir del bucle while
            default -> System.out.println(red+"Ingrese una opcion valida!"+reset); // control de errores
        }
     }
     
     
     boleta();
        
    }
    
    
    
    
    public static void boleta(){
        
        System.out.println("");
        System.out.println(green+"[BOLETA]"+reset);
        System.out.println("[Ubicaciones compradas]: ");
        
        for(int i=0; i<15; i++){
        System.out.print(entradasCompradas[i]+ " ");
                }
        System.out.println("");
        System.out.println("[Cantidad de entradas]: " + Entrada.cantidadEntradasVendidas);
        if(Entrada.cantidadEntradasVendidas>=5){
            totalIngresos = totalIngresos - (totalIngresos * 0.1);
        }
        Entrada.entradasVendidas();
        System.out.println("Total a pagar: $" + totalIngresos);
        
        
    }
    
    
    
     public static void bienvenida(){
        
    // Despliegue menu principal
        System.out.println(red+"*******************************");
        System.out.println(red+"********* TEATRO MORO *********");              
        System.out.println(red+"*******************************"); 
        System.out.println(cyan+"------ TICKETERA VIRTUAL ------"+reset); 
        System.out.println("");
               
    }
    
    
     
    public static double fxComprarEntrada(){
        // Variables de input de usuario desde teclado
     Scanner teclado2 = new Scanner(System.in);
     
     // Variables locales
    // String[] Opcion = {"1", "A"};
     String opcionZona;
     int opcionFila;
     int edad=0;
     boolean encontra2 = false;
     

     
     do{
        
        planoTeatro();
         
         
        System.out.print("Ingresa la zona deseada (A, B o C): ");
                       
        opcionZona = teclado2.nextLine().toUpperCase();
              
         switch (opcionZona) {
             case "A" -> {
                 if(llenoZonaA == false){
                 encontra2 = true; 
                 Entrada.entradaA++;
                    }else{
                     System.out.println(red+"Sin cupos, ingrese una ubicacion en otra Zona."+reset);
                    }
                }
             case "B" -> {   
                if(llenoZonaB == false){
                 encontra2 = true;
                 Entrada.entradaB++;
                    }else{
                     System.out.println(red+"Sin cupos, ingrese una ubicacion en otra Zona."+reset);
                    }
                }  
             case "C" -> {  
                 if(llenoZonaC == false){
                 encontra2 = true;
                 Entrada.entradaC++;
                    }else{
                     System.out.println(red+"Sin cupos, ingrese una ubicacion en otra Zona."+reset);
                    }
                }
             default -> System.out.println(red+"Entrada no valida. Reintente..."+reset);
             } // fin switch
         
         
         
     }while(encontra2 == false);
        
     
     do{
         encontra2=false;
         do{
         System.out.print("Ingrese la fila deseada (1-5): ");
         opcionFila = teclado2.nextInt();
         }while(opcionFila<1 || opcionFila>5);
         
          // Usa el asiento y lo registra en el array
         for(int i = 0; i<3; i++){
            for (int j=0; j<5; j++){
                if(
                        tipoEntrada[i].equals(opcionZona) && 
                        ubicacionAsiento[i][opcionFila-1]!=1
                    ){
                ubicacionAsiento[i][opcionFila-1] = 1;
                contadorEntrada[i]++;
                entradasCompradas[indicadorEnt]= opcionFila+opcionZona;
                indicadorEnt++;
                System.out.println("Su ubicacion escogida: " + opcionFila + opcionZona);
                if(contadorEntrada[i]==5){
                    switch(opcionZona){
                        case "A" -> llenoZonaA = true;
                        case "B" -> llenoZonaB = true;
                        case "C" -> llenoZonaC = true;
                    }
                    
                } 
                encontra2 = true;
                break;
                } // if
                else if(
                        tipoEntrada[i].equals(opcionZona) && 
                        ubicacionAsiento[i][opcionFila-1]==1
                    ){
                    System.out.println(red+"Ubicacion ocupada. Seleccione otra..."+reset);
                    break;
                }
                
                } // for2
                } // for1
            
             
     }while(encontra2 == false);
     
     
     
        do{
        System.out.print("Ingresa edad: ");
        edad = teclado2.nextInt();
        if(edad >= 18) {
            unAdulto = true;
        }
        }while(edad<0);
        
        double precioEntrada = calcularPrecioEntrada(opcionZona,edad);
        System.out.println(blue+"Valor a pagar: $"+precioEntrada+reset);
        Entrada.cantidadEntradasVendidas++;
        totalIngresos += precioEntrada;
        
        return precioEntrada;
    }
     
    
    
        // Metodo para calcular precio de entrada con descuentos si requiere
    private static double calcularPrecioEntrada(String ubicacion, int edad){
        // Variables locales
        double precioBase = 0;
        double descuento = 0;
        
        switch(ubicacion){
            case "A" -> precioBase = 35000.0;
            case "B" -> precioBase = 30000.0;
            case "C" -> precioBase = 25000.0;
            }
               
        // Aplica descuentos segun edad
        if(edad >= 6 && edad < 18){
            descuento = precioBase * 0.1;
            } else if (edad >= 65) {
                descuento = precioBase * 0.15;
            }
        
        if(edad<6 && unAdulto == true){
            descuento = precioBase;
        }
               
        return (precioBase - descuento);
    }

    
     
     public static void planoTeatro(){
         
         System.out.println("\n"+cyan+"**** PLANO DEL TEATRO ****"+reset);
        for(int i = 0; i<3; i++){
            System.out.print("Zona " + tipoEntrada[i] + " ");
            for (int j=0; j<5; j++){
                System.out.print("[");
                if(ubicacionAsiento[i][j]==1){
                    System.out.print(red+ubicacionAsiento[i][j]+reset);
                }else{
                    System.out.print(ubicacionAsiento[i][j]);
                }
                System.out.print("]");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("[0] Disponible");
        System.out.println(red+"[1] No disponible"+reset);
        System.out.println();
         
         
     }
     
    
        
    public static void promociones(){
        // metodo que informa sobre las promociones
        System.out.println(red+"-Por la compra de 5 entradas o mas, se le aplicara un 10% de descuento del total de su boleta"+reset);
        System.out.println(red+"-Menores de 6 anios acompaniados por un adulto, entran gratis"+reset);
    }
     
    
} // fin Clase TeatroMoro3




class Entrada {
    
    // Variables de instancia (global)
    static int entradaA = 0;
    static int entradaB = 0;
    static int entradaC = 0;
    static int cantidadEntradasVendidas = entradaA + entradaB + entradaC;
    
     
    public static void entradasVendidas(){
        boolean hayEntrada = false;
        
        if(entradaA != 0){
            System.out.println(TeatroMoro3.red+"[A]: " + entradaA+TeatroMoro3.reset);
            hayEntrada = true;
        } 
        
        if (entradaB != 0){
            System.out.println(TeatroMoro3.red+"[B]: " + entradaB+TeatroMoro3.reset);
            hayEntrada = true;
        } 
        
        if (entradaC != 0){
            System.out.println(TeatroMoro3.red+"[C]: " + entradaC+TeatroMoro3.reset);
            hayEntrada = true;
        }
        
        if(hayEntrada == false){
            System.out.println(TeatroMoro3.red+"Aun no compra entradas!"+TeatroMoro3.reset);
        }
        
        if(cantidadEntradasVendidas>1){
            System.out.println(TeatroMoro3.blue+"[TOTAL]: " + cantidadEntradasVendidas +TeatroMoro3.reset);
        }
        
        if(cantidadEntradasVendidas>=5){
            System.out.println(TeatroMoro3.cyan+"**Califica para promocion**" +TeatroMoro3.reset);
        }
        
    }
    
}