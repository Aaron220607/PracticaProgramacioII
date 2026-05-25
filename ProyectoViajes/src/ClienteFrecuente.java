class ClienteFrecuente extends Cliente{
    int numeroTarjetaPreferente;

    public ClienteFrecuente(String nif, String nombre, String numeroTarjeta,
                            int numeroTarjetaPreferente) {
        super(nif, nombre, numeroTarjeta);
        this.numeroTarjetaPreferente = numeroTarjetaPreferente;

    }

    public int getNumeroTarjetaPreferente() {
        return numeroTarjetaPreferente;
    }
    // Metodo exclusivo de clientes Frecuentes
    public String consultarViajeGuia(){
        String cadena = "";
        for(int i =0;i<nReservas;i++){
            if(reservas[i].isGuia()){
                cadena += reservas[i].toString()+"\n";
            }
        }
        return cadena;
    }

    public  double descuento(){
        double desuento;
        if(nReservas>0){
            desuento = DESCUENTO_FRECUENTE_CON_RESERVA;}   // Descuento de reserva y de frecuente
        else{
            desuento = DESCUENTO_FRECUENTE_SIN_RESERVA;}       // Adicionalmente al cliente se le hace un descuento por se frecuente
        return desuento;
    }
}

