package gestortaller;

public class Motocicleta extends Vehiculo {
    private int cilindraje;
    private String tipoMotor; // 2T, 4T
    private boolean esDeportiva;

    public Motocicleta(String marca, String modelo, String color, String placa, int anioFabricacion, 
                      int cilindraje, String tipoMotor, boolean esDeportiva) {
        super(marca, modelo, color, placa, anioFabricacion);
        this.cilindraje = cilindraje;
        this.tipoMotor = tipoMotor;
        this.esDeportiva = esDeportiva;
    }

    // Constructor compatible con el código existente
    public Motocicleta(String mod, String mar, String col, String pla, int cil) {
        super(mar, mod, col, pla, 2020); // Año por defecto
        this.cilindraje = cil;
        this.tipoMotor = "4T"; // Tipo por defecto
        this.esDeportiva = cil > 400; // Es deportiva si tiene más de 400cc
    }

    // Getters específicos
    public int getCilindraje() { return cilindraje; }
    public String getTipoMotor() { return tipoMotor; }
    public boolean isEsDeportiva() { return esDeportiva; }

    // Setters específicos
    public void setCilindraje(int cilindraje) { this.cilindraje = cilindraje; }
    public void setTipoMotor(String tipoMotor) { this.tipoMotor = tipoMotor; }
    public void setEsDeportiva(boolean esDeportiva) { this.esDeportiva = esDeportiva; }

    // Implementación de métodos abstractos
    @Override
    public String getTipoVehiculo() {
        return esDeportiva ? "Motocicleta Deportiva" : "Motocicleta Estándar";
    }

    @Override
    public String getEspecificacionesTecnicas() {
        return String.format("Cilindraje: %dcc, Motor: %s, Tipo: %s", 
                           cilindraje, tipoMotor, getTipoVehiculo());
    }

    @Override
    public double calcularCostoMantenimiento() {
        double costoBase = 50000; // Costo base
        double factorCilindraje = cilindraje * 0.1; // Factor por cilindraje
        double factorAntiguedad = calcularAntiguedad() * 5000; // Factor por antigüedad
        double factorDeportiva = esDeportiva ? 20000 : 0; // Extra por deportiva
        
        return costoBase + factorCilindraje + factorAntiguedad + factorDeportiva;
    }

    // Métodos específicos para motocicletas
    public String getCategoriaLicencia() {
        if (cilindraje <= 125) return "A1";
        else if (cilindraje <= 400) return "A2";
        else return "A";
    }

    public boolean requiereMantenimientoEspecial() {
        return esDeportiva || cilindraje > 600;
    }

    // Constantes predefinidas (usando constructor compatible)
    public static final Motocicleta MOTO1 = new Motocicleta("MT-07", "Yamaha", "Azul", "ABC123", 689);
    public static final Motocicleta MOTO2 = new Motocicleta("CBR600RR", "Honda", "Rojo", "DEF456", 599);
    public static final Motocicleta MOTO3 = new Motocicleta("Ninja 400", "Kawasaki", "Verde", "GHI789", 399);
    public static final Motocicleta MOTO4 = new Motocicleta("Duke 390", "KTM", "Naranja", "JKL012", 373);
    public static final Motocicleta MOTO5 = new Motocicleta("R15 V4", "Yamaha", "Negro", "MNO345", 155);
} 