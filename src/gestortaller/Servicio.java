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

    // Constantes predefinidas
    public static final Servicio SERVICIO1 = new Servicio("Cambio de aceite", 45000.0, 30, "Mantenimiento", "15 días");
    public static final Servicio SERVICIO2 = new Servicio("Revisión de frenos", 85000.0, 60, "Seguridad", "30 días");
    public static final Servicio SERVICIO3 = new Servicio("Ajuste de cadena", 25000.0, 20, "Mantenimiento", "7 días");
    public static final Servicio SERVICIO4 = new Servicio("Cambio de llantas", 180000.0, 45, "Repuesto", "90 días");
    public static final Servicio SERVICIO5 = new Servicio("Lavado completo", 15000.0, 40, "Estética", "No aplica");
} 