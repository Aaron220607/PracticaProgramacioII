class ViajeLargo extends Viaje{
    private int numeroNoches;
    private boolean hotel;
    private boolean  actividades;
    public ViajeLargo(String destino, String fecha, int precio, int plazas, int tipoViaje, int numeroNoches, boolean hotel,
                      boolean actividades) {
        super(destino, fecha, precio, plazas, tipoViaje);
        this.numeroNoches = numeroNoches;
        this.hotel = hotel;
        this.actividades = actividades;
    }
    public int getNumeroNoches() {
        return numeroNoches;
    }
    public boolean isHotel() {
        return hotel;
    }
    public boolean isActividades() {
        return actividades;
    }
    public String toString(){
        String srtHotel = BOOLEAN2STRING(hotel);
        String srtActividades = BOOLEAN2STRING(actividades);
        return super.toString()+ " numeroNoches " + numeroNoches + " hotel " + srtHotel + " actividades " + srtActividades;
    }
    // Siempre incluye guia local
    public boolean tieneGuia(){
        return true;
    }

}