class Empresa{
    // Clase que representa la empresa colaboradora que gestiona guias y viajes largos
    private String nombre;
    private double costeGestionGuia;
    private double  tarifaBaseViajeLargo;
    private double plusNocheExtra;
    public Empresa(String nombre, double costeGestionGuia, double tarifaBaseViajeLargo, double plusNocheExtra) {
        this.nombre = nombre;
        this.costeGestionGuia = costeGestionGuia;
        this.tarifaBaseViajeLargo = tarifaBaseViajeLargo;
        this.plusNocheExtra = plusNocheExtra;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getCosteGestionGuia() {
        return costeGestionGuia;
    }
    public void setCosteGestionGuia(double costeGestionGuia) {
        this.costeGestionGuia = costeGestionGuia;
    }
    public double getTarifaBaseViajeLargo() {
        return tarifaBaseViajeLargo;
    }
    public void setTarifaBaseViajeLargo(double tarifaBaseViajeLargo) {
        this.tarifaBaseViajeLargo = tarifaBaseViajeLargo;
    }
    public double getPlusNocheExtra() {
        return plusNocheExtra;
    }
    public void setPlusNocheExtra(double plusNocheExtra) {
        this.plusNocheExtra = plusNocheExtra;
    }


}
