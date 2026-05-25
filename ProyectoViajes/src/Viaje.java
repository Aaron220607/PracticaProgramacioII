abstract class Viaje implements IDatos{
    // Clase que representa a un viaje generico. Es abstracta y padre de Excursion y ViajeLargo
    protected String destino;
    protected String fecha;
    protected int precio;
    protected int plazas;
    protected int tipoViaje;
    public Viaje(String destino, String fecha, int precio, int plazas, int tipoViaje) {
        this.destino = destino;
        this.fecha = fecha;
        this.precio = precio;
        this.plazas = plazas;
        this.tipoViaje = tipoViaje;
    }
    public String getDestino() {
        return destino;
    }
    public String getFecha() {
        return fecha;
    }
    public int getPrecio() {
        return precio;
    }
    public int getPlazas() {
        return plazas;
    }

    public int getTipoViaje() {
        return tipoViaje;
    }
    public String toString(){
        return "Destino: " + destino+" Fecha "+ fecha+ " Precio " + precio + " Euros"+
                " Plazas " + plazas + " Tipo Viaje " + tipoViaje;
    }
    // Metod abstracto, las clases hijas deben de implementarlo
    abstract boolean tieneGuia();
}
