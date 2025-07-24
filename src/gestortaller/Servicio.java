package gestortaller;

public class Servicio {
    private String descripcion;
    private double precio;
    private int duracion; // en minutos
    private String tipo;
    private String garantia;

    public Servicio(String desc, double pre, int dur, String tip, String gar) {
        descripcion = desc;
        precio = pre;
        duracion = dur;
        tipo = tip;
        garantia = gar;
    }

    // Getters
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public int getDuracion() { return duracion; }
    public String getTipo() { return tipo; }
    public String getGarantia() { return garantia; }

    // Setters
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setGarantia(String garantia) { this.garantia = garantia; }
} 