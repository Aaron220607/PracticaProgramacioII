import java.util.*; // Lo usamos para obtener la clase Random
class Cliente implements IDatos{
    // Clase que representa a un cliente normal de la agencia
    // Es la clase padre de ClienteFrecuente
    protected String nif;
    protected String nombre;
    protected String numeroTarjeta;
    protected Reserva[] reservas;
    protected int nReservas;
    public Cliente(String nif, String nombre, String numeroTarjeta) {
        this.nif = nif;
        this.nombre = nombre;
        this.numeroTarjeta = numeroTarjeta;
        reservas = new Reserva[MAX_RESERVAS];
        nReservas = 0;
    }
    public String getNif() {
        return nif;
    }
    public String getNombre() {
        return nombre;
    }
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
    public int sacarNumeroReservas(){
        return nReservas;
    }
    public boolean existeReserva(String destino){
        boolean existeRerseva = false;
        for(int i=0;i<nReservas;i++){
            if(destino.equals(reservas[i].getViajeReserva()))
                existeRerseva = true;
        }
        return existeRerseva;
    }
    public String consultarReserva(){
        String cadena = "";
        if(nReservas==0)
            cadena = "No hay reservas asignadas";
        else
            for(int i =0;i<nReservas;i++){
                cadena += reservas[i].toString()+"\n";
            }
        return cadena;
    }
    public String addReserva(Viaje p, double precio)throws NumeroReservasException{
        String seHaceReserva = "Usted ya posee esa reserva";
        if(nReservas == MAX_RESERVAS){
            // Excepcion a Capturarar, inahibilita que se haga una reserva
            // Cuando se ha alcanzado el maximo de ellas
            throw new NumeroReservasException("Se ha alcanzado el numero maximo de reservas");
        }
        else {
            if(!existeReserva(p.getDestino())) {
                // Creamos un identificador a traves de la clase Random
                Random Rnd = new Random();
                // Nuestro identificador es nuestro dni y un numero aleatorio
                String identificador = nif + Rnd.nextInt(10);
                reservas[nReservas] = new Reserva(identificador, precio, p);
                nReservas++;
                seHaceReserva = " La reseva se ha realizado";
            }

        }
        return seHaceReserva;
    }
    public double descuento(){
        double descuento = 0;
        if(nReservas>0) // vemos el numero de reservas, si hay reserva antes se le aplica un descuento
            descuento = DESCUENTO_CON_RESERVA;    // descuento del 10% al precio final
        else
            descuento = DESCUENTO_SIN_RESERVA; // el precio se mantine igual
        return descuento;
    }
}


