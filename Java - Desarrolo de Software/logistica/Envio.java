package logistica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Envio {
    private final List<Paquete> paquetes; 
    private final String destino;
    private final List<RegistroSeguimiento> historial = new ArrayList<>();

    
    public Envio(String destino) {
        this.destino = destino;
        this.paquetes = new ArrayList<>();
    }



    public void agregarPaquete(Paquete paquete) {
        if (paquetes.size() >= 3) {
            throw new IllegalStateException("Un envío no puede transportar más de 3 paquetes.");
        }
        this.paquetes.add(paquete);
    }



    public void recibirEnSucursal(Sucursal sucursal) {
        if (paquetes.isEmpty()) {
            throw new IllegalStateException("No se puede procesar un envío sin paquetes.");
        }
        for (Paquete p : paquetes) {
            if (p.getEstado() == EstadoPaquete.recibido) {
                p.preparar(); 
            }
        }
        registrarMovimiento("Envío recibido", sucursal);
    }

    public void despacharDesdeSucursal(Sucursal sucursal) {
        if (paquetes.isEmpty()) {
            throw new IllegalStateException("No se puede despachar un envío sin paquetes.");
        }
        for (Paquete p : paquetes) {
            if (p.getEstado() != EstadoPaquete.en_viaje) {
                p.despachar();
            }
        }
        registrarMovimiento("Envío despachado", sucursal);
    }

    public void finalizarRecorrido() {
        for (Paquete p : paquetes) {
            p.entregar(); 
        }
        registrarMovimiento("Envío entregado al cliente en destino: " + destino, null);
    }

   

    private void registrarMovimiento(String accion, Sucursal sucursal) {
        this.historial.add(new RegistroSeguimiento(accion, sucursal));
    }

    public void mostrarHistorial() {
        System.out.println("--- Historial del Envío con destino a: " + destino + " ---");
        if (historial.isEmpty()) {
            System.out.println("Sin movimientos registrados.");
            return;
        }
        for (RegistroSeguimiento reg : historial) {
            System.out.println(reg.getDetalle());
        }
    }

    public void informarSucursalesVisitadas() {
        System.out.println("Sucursales visitadas en trayecto a " + destino + ":");
        List<String> visitadas = new ArrayList<>();
        for (RegistroSeguimiento reg : historial) {
            if (reg.getSucursal() != null && !visitadas.contains(reg.getSucursal().getNombre())) {
                visitadas.add(reg.getSucursal().getNombre());
                System.out.println("- " + reg.getSucursal().getNombre());
            }
        }
    }

    public void mostrarUltimoMovimiento() {
        if (!historial.isEmpty()) {
            RegistroSeguimiento ultimo = historial.get(historial.size() - 1);
            System.out.println("Último movimiento: " + ultimo.getDetalle());
        }
    }


    public void iniciarRecorrido() {
        if (paquetes.isEmpty()) {
            throw new IllegalStateException("No se puede iniciar un envío vacío.");
        }
        for (Paquete p : paquetes) {
            p.despachar(); 
        }
    }

    protected double calcularPesoTotal() {
        double pesoTotal = 0;
        for (Paquete p : paquetes) {
            pesoTotal += p.getPeso();
        }
        return pesoTotal;
    }

    public abstract double calcularCosto();

    public List<Paquete> getPaquetes() {
        return Collections.unmodifiableList(paquetes);
    }

    public String getDestino() { return destino; }
}