class Excursion extends Viaje{
    private boolean comida;
    private boolean atraccion;
    private boolean guia;
    public Excursion(String destino, String fecha, int precio, int plazas, int tipoViaje, boolean comida, boolean atraccion,
                     boolean guia) {
        super(destino, fecha, precio, plazas, tipoViaje);
        this.comida = comida;
        this.atraccion = atraccion;
        this.guia = guia;
    }

    public boolean isComida() {
        return comida;
    }
    public boolean isAtraccion() {
        return atraccion;
    }
    public boolean isGuia() {
        return guia;
    }
    public String toString(){
        // Aplicamos el metod de la interfaz para la visualizacion de la informacion
        String strComida = BOOLEAN2STRING(comida);
        String srtAtraccion = BOOLEAN2STRING(atraccion);
        String srtGuia = BOOLEAN2STRING(guia);
        return super.toString()+" Comida " + strComida + " Atraccion " + srtAtraccion + " Guia " + srtGuia;
    }
    public boolean tieneGuia(){
        return guia;
    }
}
