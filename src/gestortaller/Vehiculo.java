package gestortaller;

public abstract class Vehiculo {
    protected String marca;
    protected String modelo;
    protected String color;
    protected String placa;
    protected int anioFabricacion;

    public Vehiculo(String marca, String modelo, String color, String placa, int anioFabricacion) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.placa = placa;
        this.anioFabricacion = anioFabricacion;
    }

    // Getters comunes
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getColor() { return color; }
    public String getPlaca() { return placa; }
    public int getAnioFabricacion() { return anioFabricacion; }

    // Setters comunes
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setColor(String color) { this.color = color; }
    public void setPlaca(String placa) { this.placa = placa; }
    public void setAnioFabricacion(int anioFabricacion) { this.anioFabricacion = anioFabricacion; }

    // Métodos abstractos que cada subclase debe implementar
    public abstract String getTipoVehiculo();
    public abstract String getEspecificacionesTecnicas();
    public abstract double calcularCostoMantenimiento();

    // Método común
    public String getInformacionBasica() {
        return String.format("%s %s %d - %s (Placa: %s)", 
                           marca, modelo, anioFabricacion, color, placa);
    }

    public int calcularAntiguedad() {
        return java.time.Year.now().getValue() - anioFabricacion;
    }

    @Override
    public String toString() {
        return getInformacionBasica() + " - " + getEspecificacionesTecnicas();
    }
}