public class RangoException extends Exception {
    // Se lanza cuando el usuario introduce una opcion del menu fuera del rango 1-9
    public RangoException(String mensaje) {
        super(mensaje);
    }
}
