public interface IDatos{
    // INTERFAZ CON LAS DIFERENTES CONSTANTES

    // Menu
    int OPCION_SALIR_MENU = 9;

    // Tamaños máximos
    int MAX_VIAJES = 20;
    int MAX_CLIENTES = 20;
    int MAX_RESERVAS = 3;

    // Descuentos cliente normal
    // Para los porcentjes de descuento al ser un decremento
    // usamos el total que el cliente paga
    double DESCUENTO_CON_RESERVA = 0.9; // Paga 90% del precio original 10% descuento
    double DESCUENTO_SIN_RESERVA = 1.0; // 0% descuento, paga el precio original

    // Descuentos cliente frecuente
    double DESCUENTO_FRECUENTE_CON_RESERVA = 0.75; //75% precio original 25% descuento
    double DESCUENTO_FRECUENTE_SIN_RESERVA = 0.85; //85% precio original 15% descuento


    // Tipos de viaje
    int TIPO_CULTURAL = 0;

    // Filtros viajes culturales
    int MAX_NOCHES_CULTURAL = 4;
    int MAX_PRECIO_CULTURAL = 300;
    int MIN_NOCHES_PLUS = 3;
    // Metodo usado para pasar de un boolean a una String
    public default String BOOLEAN2STRING(boolean A){
        String srt = (A ?"Si":"No");
        return srt;
    }
}