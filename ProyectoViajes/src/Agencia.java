class Agencia implements IDatos {
    // Gestiona los clientes y viajes de la agencia
    // y contiene todos los metodos para realizar las consultas
    private Cliente[] clientes;
    private Viaje[] viajes;
    private int nViajes;
    private int nClientes;
    public Agencia() {
        this.clientes = new Cliente[MAX_CLIENTES];
        this.viajes = new Viaje[MAX_VIAJES];
        nClientes = 0;
        nViajes = 0;
    }
    public int getnClientes(){
        return nClientes;
    }
    // Metodo usado en la lectura de ficheros para añadir un cliente en funcion de
    // si es o no cliente frecuente
    public void addCliente(String nif, String nombre, String tarjeta, boolean frecuente, int tarjataVip) {
        if (frecuente) {
            clientes[nClientes] = new ClienteFrecuente(nif, nombre, tarjeta, tarjataVip);
        } else {
            clientes[nClientes] = new Cliente(nif, nombre, tarjeta);
        }
        nClientes++;
    }
    // Metodo usado en la lectura de ficheros para añadir un viaje en funcion de
    // su tipo e si es excursion y v si es viaje largo
    public void addViaje(String tipo, String destino, String fechas, int precio, int plazas, int tiposviaje, int noches, boolean b1,
                         boolean b2, boolean b3) {
        if (tipo.equals("e")) {
            viajes[nViajes] = new Excursion(destino, fechas, precio, plazas, tiposviaje, b1, b2, b3);
        } else {
            viajes[nViajes] = new ViajeLargo(destino, fechas, precio, plazas, tiposviaje, noches, b1, b2);
        }
        nViajes++;

    }
    public String mostrarInfoVIajes() {
        String cadena = "";
        for (int i = 0; i < nViajes; i++) {
            cadena += viajes[i].toString() + "\n";
        }
        return cadena;
    }
    // Mostrar los viajes Culturales con un umbral de numero noches
    public String mostrarViajesCulturales() {
        String cadena = "";
        for (int i = 0; i < nViajes; i++) {
            if (viajes[i].getTipoViaje() == TIPO_CULTURAL && viajes[i] instanceof ViajeLargo) {
                // Necesitamos que sea viajes largo para poder sacar el numero de noches
                // si fuera excursion no tendria directamente ninguna noche
                ViajeLargo p = (ViajeLargo) viajes[i];
                if (p.getNumeroNoches() < MAX_NOCHES_CULTURAL && p.getPrecio() < MAX_PRECIO_CULTURAL) {
                    cadena += "El destino es: " + p.getDestino()+
                            " cuyo coste es de: "+ p.getPrecio()+ "€"+ "\n";
                }
            }
        }
        return cadena;
    }
    // Metodo en el que se aplica la dependencia con la Plataforma
    // para conocer el cobro de esta en funcion de los requisitos del enunciado
    public double calcularGananciaPlataforma(Plataforma c) {
        double ganacia = 0;
        for (int i = 0; i < nViajes; i++) {
            if (viajes[i] instanceof Excursion) {
                Excursion p = (Excursion) viajes[i];
                ganacia += p.getPrecio()*p.getPlazas();
            }
        }
        return ganacia * c.getBeneficio();
    }
    // Metodo en el que se aplica la dependencia con la Empresa
    // para calcular su cobor en funcion de los requisitos del enunciado
    public double cobroEmpresa(Empresa C) {
        double total = 0;
        int noches = 0;
        for (int i = 0; i < nViajes; i++) {
            if (viajes[i].tieneGuia()) {
                // Buscamos los viajes con guia para aplica el coste
                total += C.getCosteGestionGuia();
            }
            if (viajes[i] instanceof ViajeLargo) {
                // Obtenemos los viajes largos para aplica su costo y ver el numero de noches
                ViajeLargo p = (ViajeLargo) viajes[i];
                noches = p.getNumeroNoches();
                total += C.getTarifaBaseViajeLargo();
                if (noches > MIN_NOCHES_PLUS) {
                    // Si es mayor que el mino aplicamos una comision apartir de ese minimo
                    total += (noches - MIN_NOCHES_PLUS) * C.getPlusNocheExtra();
                    // noches - MIN_NOCHES_PLUS, esta operacion nos da la noches superiores al minimo
                }
            }
        }
        return total;
    }
    // Muestra el precio original y luego le aplica el descuento correspondiente
    public String precioViaje(String dni, String destino) {
        String mensaje = "";
        int idCliente = sacarDNI(dni);
        int idViaje = sacarDestino(destino);
        if(idCliente==-1){
            // Comprobamos que existe el cliente
            mensaje = "No existe dicho dni";
        } else if (idViaje==-1) {
            // Comprobamso que existe dicho viaje
            mensaje = "No existe dicho Viaje";
        } else  {
            double precioInicial = viajes[idViaje].getPrecio();
            double precio = calcularPrecio(idCliente, idViaje);
            // El descuento es calculado directamente aqui lo que hacemos es sacar el porcentaje aplicado
            // Ya que en la interfaz contamos con el decremento , no con el porcentaje
            double descuentoAplicado = 100-(clientes[idCliente].descuento() * 100) ;
            mensaje = " El precio de su viaje a " + destino +
                    " inicialmente es de " + precioInicial + "€ " +
                    " con el descuento aplicado sera " + precio + "€"
                    + " el descuento aplicado es de: " + descuentoAplicado + "% \n";
        }
        return mensaje;
    }
    // Metodo para hacer la reserva,lanza una excepcion de la clase Reserva
    public String hacerReserva(String dni,String destino) throws NumeroReservasException {
        int idCliente = sacarDNI(dni);
        int idViaje = sacarDestino(destino);
        double precio =calcularPrecio(idCliente,idViaje);
        String reserva = clientes[idCliente].addReserva(viajes[idViaje], precio);
        return  reserva;
    }
    // Muestra las reservas de un cliente
    public String listaReservasCliente(String dni) {
        String lista = " ";
        int idCliente = sacarDNI(dni);
        if (idCliente == -1)
            lista = "No se ha encontrado el Cliente";
        else
            lista = clientes[idCliente].consultarReserva();
        return lista;
    }
    // Muestra las reservas de un cliente frecuente, muestra aquellas que
    // contengan la presencia de guia local
    public String listaReservasFrecuentes(String dni) {
        String lista = " ";
        int idCliente = sacarDNI(dni);
        if (idCliente == -1) {
            lista = "No existe dicho DNI";
        } else {
            if (clientes[idCliente] instanceof ClienteFrecuente) {
                // Comprobamos que se es frecuente, pues el metodo para ver las reservas
                // con guia es un metodo de la clase cliente frecuente
                ClienteFrecuente cf = (ClienteFrecuente) clientes[idCliente];
                lista = cf.consultarViajeGuia();
            } else {
                lista = "El cliente no es frecuente";
            }
        }
        return lista;
    }
    // Metodos auxiliares para sacar el dni
    public int sacarDNI(String dni) {
        int id = -1;
        for (int i = 0; i < nClientes && id < 0; i++) {
            if (dni.equalsIgnoreCase(clientes[i].getNif())) {
                id = i;
            }
        }
        return id;
    }
    // Metodo auxiliar para sacar el destino
    public int sacarDestino(String destino) {
        int id = -1;
        for (int i = 0; i < nViajes && id < 0; i++) {
            if (destino.equalsIgnoreCase(viajes[i].getDestino()))
                id = i;
        }
        return id;
    }
    // Metodo auxiliar para calcular el precio  final de un cliente con un viaje especifico
    public double calcularPrecio(int idCliente, int idViaje) {
        // Calcula el precio final aplicando el descuento del cliente.
        // Gracias al polimorfismo, descuento() ejecuta el metodo correcto
        // segun sea Cliente normal o ClienteFrecuente
        double descuento = clientes[idCliente].descuento();
        double precio = viajes[idViaje].getPrecio() * descuento;
        return precio;
    }
}


