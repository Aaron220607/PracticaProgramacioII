class Plataforma{
    // Clase que representa la plataforma web que vende excursiones cobrando un porcentaje
    private String nombre;
    private double beneficio;
    public Plataforma(String nombre, double beneficio) {
        this.nombre = nombre;
        this.beneficio = beneficio;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getBeneficio() {
        return beneficio;
    }
    public void setBeneficio(double beneficio) {
        this.beneficio = beneficio;
    }

}
