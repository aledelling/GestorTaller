package gestortaller;

public abstract class ServicioBase {
    protected String descripcion;
    protected double precio;
    protected int duracion; // en minutos
    protected String garantia;

    public ServicioBase(String descripcion, double precio, int duracion, String garantia) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracion = duracion;
        this.garantia = garantia;
    }

    // Getters comunes
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public int getDuracion() { return duracion; }
    public String getGarantia() { return garantia; }

    // Setters comunes
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
    public void setGarantia(String garantia) { this.garantia = garantia; }

    // Métodos abstractos
    public abstract String getCategoriaServicio();
    public abstract String getMaterialesNecesarios();
    public abstract double calcularPrecioConDescuento(double porcentajeDescuento);

    // Métodos comunes
    public String getTiempoEstimado() {
        int horas = duracion / 60;
        int minutos = duracion % 60;
        if (horas > 0) {
            return String.format("%dh %dmin", horas, minutos);
        } else {
            return String.format("%dmin", minutos);
        }
    }

    public boolean esServicioRapido() {
        return duracion <= 30;
    }

    @Override
    public String toString() {
        return String.format("%s - %s ($%.0f, %s, Garantía: %s)", 
                           getCategoriaServicio(), descripcion, precio, getTiempoEstimado(), garantia);
    }
}