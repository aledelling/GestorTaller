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

    // Constantes predefinidas
    public static final Motocicleta MOTO1 = new Motocicleta("MT-07", "Yamaha", "Azul", "ABC123", 689);
    public static final Motocicleta MOTO2 = new Motocicleta("CBR600RR", "Honda", "Rojo", "DEF456", 599);
    public static final Motocicleta MOTO3 = new Motocicleta("Ninja 400", "Kawasaki", "Verde", "GHI789", 399);
    public static final Motocicleta MOTO4 = new Motocicleta("Duke 390", "KTM", "Naranja", "JKL012", 373);
    public static final Motocicleta MOTO5 = new Motocicleta("R15 V4", "Yamaha", "Negro", "MNO345", 155);
} 