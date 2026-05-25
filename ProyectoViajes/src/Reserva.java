class Reserva{
    // Clase que representa una reserva de un cliente para un viaje concreto
    private String identificador;
    private double precioFinal;
    private Viaje viaje;
    public Reserva(String identificador, double precioFinal, Viaje viaje) {
        this.identificador = identificador;
        this.precioFinal = precioFinal;
        this.viaje = viaje;
    }
    public String getViajeReserva(){
        return  viaje.getDestino();
    }
    public String getIdentificador() {
        return identificador;
    }
    public double getPrecioFinal() {
        return precioFinal;
    }
    public boolean isGuia(){
        return viaje.tieneGuia();
    }
    public String toString(){
        return "Identificador " + identificador + " Precio Final " + precioFinal + " Viaje " +
                viaje;
    }
}
