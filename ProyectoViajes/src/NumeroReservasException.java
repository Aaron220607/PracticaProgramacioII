public class NumeroReservasException extends Exception{
    // Se lanza cuando un cliente supera el maximo de 3 reservas permitidas
    public NumeroReservasException(String mensaje){
        super(mensaje);
    }
}
