package gestortaller;

public class ServicioMantenimiento extends Servicio {
    private int kilometrajeSugerido;
    private boolean esPreventivo;

    public ServicioMantenimiento(String descripcion, double precio, int duracion, String garantia, 
                                int kilometrajeSugerido, boolean esPreventivo) {
        super(descripcion, precio, duracion, "Mantenimiento", garantia, false);
        this.kilometrajeSugerido = kilometrajeSugerido;
        this.esPreventivo = esPreventivo;
    }

    // Getters específicos
    public int getKilometrajeSugerido() { return kilometrajeSugerido; }
    public boolean isEsPreventivo() { return esPreventivo; }

    // Setters específicos
    public void setKilometrajeSugerido(int kilometrajeSugerido) { this.kilometrajeSugerido = kilometrajeSugerido; }
    public void setEsPreventivo(boolean esPreventivo) { this.esPreventivo = esPreventivo; }

    @Override
    public String getMaterialesNecesarios() {
        return super.getMaterialesNecesarios() + ", manual de servicio";
    }

    @Override
    public double calcularPrecioConDescuento(double porcentajeDescuento) {
        // Los servicios preventivos tienen descuento adicional del 5%
        double descuentoAdicional = esPreventivo ? 5.0 : 0.0;
        return super.calcularPrecioConDescuento(porcentajeDescuento + descuentoAdicional);
    }

    public boolean esNecesarioSegunKilometraje(int kilometrajeActual) {
        return kilometrajeActual >= kilometrajeSugerido;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Cada %d km, %s)", 
                                              kilometrajeSugerido, 
                                              esPreventivo ? "Preventivo" : "Correctivo");
    }
}