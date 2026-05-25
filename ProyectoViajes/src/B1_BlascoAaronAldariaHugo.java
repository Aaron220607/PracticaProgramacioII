import java.io.*;
import java.util.*;
public class B1_BlascoAaronAldariaHugo{

    // Trabaja hecho por: Aaron Blasco y Hugo Aldaria
    final static Scanner SC = new Scanner(System.in);
    public static void main(String[] args){
        // CREACCION DE LA AGENCIA Y CLASESE INDEPENDIENTES
        Agencia agencia = new Agencia();
        Empresa empresa = new Empresa("Servicios Turísticos Globales S.L.",15,25,5);
        Plataforma plataforma = new Plataforma("Viajes Globales.com",0.03);
        // LECTURA DE FICHEROS
        try {
        leerArchivoClientes("Clientes",agencia);
        leerViajes("Viajes",agencia);
        // GESTION AGENCIA
        gestionarAgencia(agencia,empresa,plataforma);
        } catch(FileNotFoundException fnfe){
            System.out.println("Error no existen los ficheros");
        }
    }
                    //      METODOS CLASE PRINCIPAL
    // METODO GESTION AGENCIA
    public static void gestionarAgencia(Agencia agencia,Empresa empresa,Plataforma plataforma){
        int opcion;
        do {
            opcion = menu();
            switch (opcion) {
                case 1: consulta1(agencia); ;
                break;
                case 2: consulta2(agencia);
                break;
                case 3: consulta3(agencia, plataforma);
                break;
                case 4:consulta4(agencia, empresa);
                break;
                case 5: consulta5(agencia); ;
                break;
                case 6: consulta6(agencia);
                break;
                case 7: consulta7(agencia);
                break;
                case 8: consulta8(agencia);
                break;
                case IDatos.OPCION_SALIR_MENU: System.out.println("Saliendo del sistema...");
                break;
            }
        } while (opcion != IDatos.OPCION_SALIR_MENU);
    }
   // MENU
       // Muestra las opciones y lee la eleccion del usuario
       // Excepcion 2: si el usuario introduce un dato no numerico
       // Excepcion 3: si la opcion no esta en el rango valido, vuelve a pedir
       public static int menu(){
           int opcion = 0;
           boolean opcionValida = false;
           do {
               System.out.println("\nIndique el numero de la consulta a realizar:\n");
               System.out.println("1. Mostrar informacion de todos los viajes");
               System.out.println("2. Mostrar viajes culturales baratos");
               System.out.println("3. Ganancia plataforma web");
               System.out.println("4. Cobro empresa colaboradora");
               System.out.println("5. Precio viaje para un cliente");
               System.out.println("6. Hacer reserva");
               System.out.println("7. Listar reservas de un cliente");
               System.out.println("8. Listar reservas con guia de cliente frecuente");
               System.out.println("9. Salir");
                   try{
                   opcion = numeroCorrecto();
                  // Lanzamos la posible excepcion
                    if (opcion < 1 || opcion > IDatos.OPCION_SALIR_MENU) {
                    throw new RangoException(" Rango no válido");
                   } else {
                       opcionValida = true;
                   }
               } catch (InputMismatchException e) {
                   // Excepcion 2: dato no numerico
                   System.out.println("Error: debe introducir un numero entero.");
                   SC.nextLine(); // Limpiar el buffer del scanner
               } catch (RangoException e) {
                       // Excepcion 3: opcion fuera de rango
                       System.out.println("Error " + e.getMessage());
                   }
           } while (!opcionValida);
           return opcion;
       }
     // METODOS DE CONSULTA
    public static void consulta1(Agencia agencia){
       // Consulta 1: Muestra la informacion de todos los viajes de la agencia
        System.out.println(agencia.mostrarInfoVIajes());
    }
    public static void consulta2(Agencia agencia){
        // Consulta 2: Muestra los viajes culturales de menos de 4 noches y precio inferior a 300 euros
        System.out.println(agencia.mostrarViajesCulturales());
    }
    public static void consulta3(Agencia agencia, Plataforma plataforma){
        // Consulta 3: Muestra la ganancia de la plataforma web sobre las excursiones
        System.out.println("El coste de la plataforma es: "+
                agencia.calcularGananciaPlataforma(plataforma)+"€");
    }
    public static void consulta4(Agencia agencia, Empresa empresa){
        // Consulta 4: Muestra el cobro total de la empresa colaboradora
        System.out.println("El coste de la empresa es: "+
                agencia.cobroEmpresa(empresa)+"€");
    }
    public static void consulta5(Agencia agencia){
       // Consulta 5: Muestra el precio de un viaje para un cliente concreto con su descuento aplicado.
        System.out.println("Introduzca su dni");
        String dni = SC.nextLine();
        System.out.println("Introduzca el destino");
        String destino = SC.nextLine();
        System.out.println(agencia.precioViaje(dni,destino));
    }
    public static void consulta6(Agencia agencia) {
       // Consulta 6: Realiza una reserva para un cliente en un viaje dado.
        try {
            System.out.println("Introduzca el DNI:");
            String dniCliente = SC.nextLine();
            int idCliente = agencia.sacarDNI(dniCliente);
            if (idCliente == -1) {
                if (agencia.getnClientes() >= IDatos.MAX_CLIENTES) {
                    throw new MaximoNumeroClientesException("No se pueden registrar más clientes: Cupo máximo alcanzado.");
                }
                registroCliente(agencia,dniCliente);
                // Actualizamos el idCliente para que la reserva funcione
                idCliente = agencia.sacarDNI(dniCliente);
            }
            String destino = destinoValido(agencia);
            System.out.println(agencia.hacerReserva(dniCliente, destino));
            //Excepciones
        } catch (MaximoNumeroClientesException e1) {
            // Excepcion: Maximo numero clientes (array lleno)
            System.out.println("ERROR: " + e1.getMessage());
        }
        catch (NumeroReservasException e2) {
            // Excepcion 4: maximo de reservas alcanzado
            System.out.println("ERROR RESERVA: " + e2.getMessage());
        }
    }
    public static void consulta7(Agencia agencia){
        // Consulta 7: Muestra el listado de todas las reservas de un cliente
        System.out.println("Introduzca su dni");
        String dni = SC.nextLine();
        System.out.println(agencia.listaReservasCliente(dni));
    }
    public static void consulta8(Agencia agencia){
        // Consulta 8: Muestra el listado de reservas con guia de un cliente frecuente.
        System.out.println("Introduzca su dni");
        String dni = SC.nextLine();
        System.out.println(agencia.listaReservasFrecuentes(dni));
    }
    // METODOS PARA LEER FICHEROS
        // METODO PARA LEER CLIENTES
    public static void leerArchivoClientes(String nombreArchivo,Agencia agencia) {
        try{
            File archivo = new File(nombreArchivo);
            Scanner lector = new Scanner(archivo);
            while(lector.hasNext()){
                int nFrecuente = 0;
                String nombre = lector.next();
                String nif = lector.next();
                String  nTarjeta = lector.next();
                boolean esFrecuente = lector.nextBoolean();
                if(esFrecuente){
                    nFrecuente = lector.nextInt();
                    agencia.addCliente( nif,nombre, nTarjeta, true, nFrecuente);
                }    else {
                    agencia.addCliente( nif,nombre, nTarjeta, false, 0);
                }
            }
            lector.close();
        } catch(FileNotFoundException fnfe){
            // Excepcion 1: Fichero no encontrado
            System.out.println("Archivo no encontrado");
        }
    }
        // METODO LEER VIAJES
    public static void leerViajes(String nombreArchivo,Agencia agencia){
        try{
            File archivo = new File(nombreArchivo);
            Scanner lector = new Scanner(archivo);
            while(lector.hasNext()){
                char tipoViaje = lector.next().charAt(0);
                String origen = lector.next();
                String fecha = lector.next();
                int precio = lector.nextInt();
                int numeroPlazas = lector.nextInt();
                int tipoDestino = lector.nextInt();
                if (tipoViaje == 'e'){
                    boolean comida = lector.nextBoolean();
                    boolean atraccion = lector.nextBoolean();
                    boolean guia = lector.nextBoolean();
                    agencia.addViaje("e", origen, fecha, precio, numeroPlazas, tipoDestino, 0, comida, atraccion, guia);
                }
                else {
                    int diasAlojado = lector.nextInt();
                    boolean hotel = lector.nextBoolean();
                    boolean paqueteActividades = lector.nextBoolean();
                    agencia.addViaje("v", origen, fecha, precio, numeroPlazas, tipoDestino, diasAlojado, hotel, paqueteActividades, true);
                }
            }
            lector.close();
        } catch(FileNotFoundException fnfe){
            // Excepcion 1: Fichero no encontrado
            System.out.println("Archivo no encontrado");
        }
    }
    // METODOS AUXILIARES
            // Metodo para registrar a un nuevo cliente con los datos correctos
 public static void registroCliente(Agencia agencia,String dniCliente) {
             System.out.println("Cliente no registrado. Introduzca datos:");
             System.out.print("Nombre: ");
             String nombre = SC.nextLine();
             System.out.print("Tarjeta: ");
             String tarjeta = SC.nextLine();
             System.out.print("¿Frecuente? (true/false): ");
             boolean esFrecuente =booleanCorrecto();
             int nFrecuente = 0;
             if (esFrecuente) {
                 System.out.println("Introduzca el numero de tarjeta");
                 nFrecuente = numeroCorrecto();
             }
             // Añadimos al array
             agencia.addCliente(dniCliente, nombre, tarjeta, esFrecuente, nFrecuente);
             System.out.println("Cliente registrado correctamente.");
 }
            // Metodo para obtener un boolean,evitando que sea un tipo incorrecto
 public static boolean booleanCorrecto(){
     boolean frecuente = false;
     boolean fallo= false;
     do {
         try {
             System.out.print("¿Frecuente? (true/false): ");
             frecuente = SC.nextBoolean();
             SC.nextLine();
             fallo = false;
         } catch (InputMismatchException e) {
             // Capturamos la excepcion para continuar en el programa
             fallo = true; // Ponemos que ha habido un fallo y continuamos en el bucle
             System.out.println("Error. Introduzca un boolean");
             SC.nextLine();
         }
     }while(fallo);
     return frecuente;

 }
            // Metodo para obtener un destino existente
 public static String destinoValido(Agencia agencia){
        int id=-1;
        String destino = "";
        do {
            System.out.println("Introduzca un destino valido");
            destino = SC.nextLine();
            // Buscamos si existe en el array de viajes
            id = agencia.sacarDestino(destino);
            if(id<0){
                // Nos devuelve un negativo y continuamos en el bucle
                System.out.println("Por favor un destino que exista");
            }
        } while(id<0);
        return destino;
 }
            // Metodo para obtener un numero, evitando que sea introducido otro tipo
 public static int numeroCorrecto(){
        int numero = 0;
        boolean fallo= false;
        do {
            try {
                numero = SC.nextInt();
                fallo = false;
                SC.nextLine();
            } catch (InputMismatchException e) {
                // Capturamos la excepcion para continuar en el programa
                fallo = true; // Indicamos que ha habido un fallo y continuamos en el bucle
                System.out.println("Error, introduzca un numero");
                SC.nextLine();
            }
        }while(fallo);
        return numero;
 }
}