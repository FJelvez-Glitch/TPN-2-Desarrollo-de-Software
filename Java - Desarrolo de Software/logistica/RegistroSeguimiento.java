package logistica;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroSeguimiento {
    private final LocalDateTime fechaHora;
    private final String accion;
    private final Sucursal sucursal;

    public RegistroSeguimiento(String accion, Sucursal sucursal) {
        this.fechaHora = LocalDateTime.now();
        this.accion = accion;
        this.sucursal = sucursal;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public String getDetalle() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String nombreSucursal = (sucursal != null) ? sucursal.getNombre() : "Destino/Cliente";
        return "[" + fechaHora.format(formatter) + "] " + accion + " en: " + nombreSucursal;
    }
}