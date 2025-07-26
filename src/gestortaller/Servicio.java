package gestortaller;

public class Servicio extends ServicioBase {
    private String tipo;
    private boolean requiereRepuestos;

    public Servicio(String descripcion, double precio, int duracion, String tipo, String garantia, boolean requiereRepuestos) {
        super(descripcion, precio, duracion, garantia);
        this.tipo = tipo;
        this.requiereRepuestos = requiereRepuestos;
    }

    // Constructor compatible con el código existente
    public Servicio(String desc, double pre, int dur, String tip, String gar) {
        super(desc, pre, dur, gar);
        this.tipo = tip;
        this.requiereRepuestos = determinarSiRequiereRepuestos(desc);
    }

    // Getters específicos
    public String getTipo() { return tipo; }
    public boolean isRequiereRepuestos() { return requiereRepuestos; }

    // Setters específicos
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setRequiereRepuestos(boolean requiereRepuestos) { this.requiereRepuestos = requiereRepuestos; }

    // Implementación de métodos abstractos
    @Override
    public String getCategoriaServicio() {
        return tipo;
    }

    @Override
    public String getMaterialesNecesarios() {
        switch (tipo.toLowerCase()) {
            case "mantenimiento":
                return "Aceite, filtros, herramientas básicas";
            case "seguridad":
                return "Pastillas de freno, líquido de frenos, herramientas especializadas";
            case "repuesto":
                return "Repuestos específicos, herramientas de instalación";
            case "estética":
                return "Productos de limpieza, ceras, paños";
            default:
                return "Herramientas básicas";
        }
    }

    @Override
    public double calcularPrecioConDescuento(double porcentajeDescuento) {
        double descuento = precio * (porcentajeDescuento / 100);
        return precio - descuento;
    }

    // Métodos específicos
    private boolean determinarSiRequiereRepuestos(String descripcion) {
        String desc = descripcion.toLowerCase();
        return desc.contains("cambio") || desc.contains("reemplazo") || 
               desc.contains("llanta") || desc.contains("pastilla");
    }

    public double calcularCostoTotal() {
        double costoManoObra = precio;
        double costoRepuestos = requiereRepuestos ? precio * 0.4 : 0; // 40% adicional si requiere repuestos
        return costoManoObra + costoRepuestos;
    }

    public String getNivelComplejidad() {
        if (duracion <= 30) return "Básico";
        else if (duracion <= 60) return "Intermedio";
        else if (duracion <= 120) return "Avanzado";
        else return "Especializado";
    }

    // Constantes predefinidas (usando constructor compatible)
    public static final Servicio SERVICIO1 = new Servicio("Cambio de aceite", 45000.0, 30, "Mantenimiento", "15 días");
    public static final Servicio SERVICIO2 = new Servicio("Revisión de frenos", 85000.0, 60, "Seguridad", "30 días");
    public static final Servicio SERVICIO3 = new Servicio("Ajuste de cadena", 25000.0, 20, "Mantenimiento", "7 días");
    public static final Servicio SERVICIO4 = new Servicio("Cambio de llantas", 180000.0, 45, "Repuesto", "90 días");
    public static final Servicio SERVICIO5 = new Servicio("Lavado completo", 15000.0, 40, "Estética", "No aplica");
} 