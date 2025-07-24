package gestortaller;

public class Motocicleta {
    private String modelo;
    private String marca;
    private String color;
    private String placa;
    private int cilindraje;

    public Motocicleta(String mod, String mar, String col, String pla, int cil) {
        modelo = mod;
        marca = mar;
        color = col;
        placa = pla;
        cilindraje = cil;
    }

    // Getters
    public String getModelo() { return modelo; }
    public String getMarca() { return marca; }
    public String getColor() { return color; }
    public String getPlaca() { return placa; }
    public int getCilindraje() { return cilindraje; }

    // Setters
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setColor(String color) { this.color = color; }
    public void setPlaca(String placa) { this.placa = placa; }
    public void setCilindraje(int cilindraje) { this.cilindraje = cilindraje; }
} 