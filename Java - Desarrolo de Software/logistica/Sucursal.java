package logistica;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {
    private final String nombre;
    private final List<Envio> enviosActivos;

    public Sucursal(String nombre) {
        this.nombre = nombre;
        this.enviosActivos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirEnvio(Envio envio) {
        if (enviosActivos.contains(envio)) {
            throw new IllegalStateException("El envío ya está registrado en " + nombre);
        }
        enviosActivos.add(envio);
        envio.recibirEnSucursal(this);
    }

    public void despacharEnvio(Envio envio) {
        if (!enviosActivos.contains(envio)) {
            throw new IllegalStateException("El envío no se encuentra en " + nombre);
        }
        enviosActivos.remove(envio);
        envio.despacharDesdeSucursal(this);
    }
}
