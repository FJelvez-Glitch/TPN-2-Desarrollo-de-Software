package logistica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Historial {
    private final List<RegistroSeguimiento> movimientos;

    public Historial() {
        this.movimientos = new ArrayList<>();
    }

    public void agregarMovimiento(String accion, Sucursal sucursal) {
        this.movimientos.add(new RegistroSeguimiento(accion, sucursal));
    }

    public void mostrarHistorialCompleto() {
        if (movimientos.isEmpty()) {
            System.out.println("Sin movimientos registrados.");
            return;
        }
        for (RegistroSeguimiento reg : movimientos) {
            System.out.println(reg.getDetalle());
        }
    }

    public void informarSucursalesVisitadas() {
        List<String> visitadas = new ArrayList<>();
        for (RegistroSeguimiento reg : movimientos) {
            if (reg.getSucursal() != null && !visitadas.contains(reg.getSucursal().getNombre())) {
                visitadas.add(reg.getSucursal().getNombre());
                System.out.println("- " + reg.getSucursal().getNombre());
            }
        }
    }

    public void mostrarUltimoMovimiento() {
        if (!movimientos.isEmpty()) {
            RegistroSeguimiento ultimo = movimientos.get(movimientos.size() - 1);
            System.out.println("Último movimiento: " + ultimo.getDetalle());
        } else {
            System.out.println("No hay movimientos registrados.");
        }
    }

    public List<RegistroSeguimiento> getMovimientos() {
        return Collections.unmodifiableList(movimientos);
    }
}