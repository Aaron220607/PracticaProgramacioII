public class MaximoNumeroClientesException extends Exception{
    // Se lanza cuando se intenta añadir un cliente y el array de la agencia esta lleno
    public MaximoNumeroClientesException(String message) {
    super(message);
    }
}
