package app;

import logistica.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("CREACIÓN DE CLIENTE Y SUCURSALES");
            Cliente cliente = new Cliente("CLI-001", "Carlos Gómez");
            
            Sucursal viedma = new Sucursal("Sucursal Viedma");
            Sucursal bahia = new Sucursal("Sucursal Bahía Blanca");
            Sucursal bsas = new Sucursal("Sucursal Buenos Aires");

            System.out.println("CREACIÓN DE PAQUETES Y ENVÍO");
            Paquete paquete1 = new Paquete("PH-001", "Paquete 1", 2.5); 
            Paquete paquete2 = new Paquete("PH-002", "Paquete 2", 1.0);

            Envio envio = new EnvioEstandar("Buenos Aires");
            envio.agregarPaquete(paquete1);
            envio.agregarPaquete(paquete2);
            cliente.agregarEnvio(envio);

            System.out.println("Cliente registrado: " + cliente.getNombre());
            System.out.println("Envíos del cliente: " + cliente.getEnvios().size());

            System.out.println("\nRECORRIDO POR SUCURSALES");
            
            viedma.recibirEnvio(envio);
            viedma.despacharEnvio(envio);

            bahia.recibirEnvio(envio);
            bahia.despacharEnvio(envio);

            bsas.recibirEnvio(envio);
            bsas.despacharEnvio(envio);
            envio.finalizarRecorrido();

            System.out.println("CONSULTAS DE HISTORIAL");
            envio.mostrarHistorial();
            System.out.println();
            envio.informarSucursalesVisitadas();
            System.out.println();
            envio.mostrarUltimoMovimiento();

            System.out.println("\nPRUEBA DE VALIDACIONES");
            System.out.println("Intentando despachar un envío ya entregado...");
            bsas.despacharEnvio(envio);

        } catch (IllegalStateException e) {
            System.err.println("Error de validación capturado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error de argumento capturado: " + e.getMessage());
        }
    }
}