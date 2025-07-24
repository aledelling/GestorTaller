package gestortaller;

public class Cliente {
    private String nombre;
    private String telefono;
    private String direccion;
    private String email;
    private String documento;

    public Cliente(String nom, String tel, String dir, String mail, String doc) {
        nombre = nom;
        telefono = tel;
        direccion = dir;
        email = mail;
        documento = doc;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public String getEmail() { return email; }
    public String getDocumento() { return documento; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setEmail(String email) { this.email = email; }
    public void setDocumento(String documento) { this.documento = documento; }

    // Constantes predefinidas
    public static final Cliente CLIENTE1 = new Cliente("Juan Pérez", "3001234567", "Calle 123 #45-67", "juan.perez@email.com", "12345678");
    public static final Cliente CLIENTE2 = new Cliente("María García", "3109876543", "Carrera 89 #12-34", "maria.garcia@email.com", "87654321");
    public static final Cliente CLIENTE3 = new Cliente("Carlos López", "3157891234", "Avenida 56 #78-90", "carlos.lopez@email.com", "11223344");
    public static final Cliente CLIENTE4 = new Cliente("Ana Martínez", "3208765432", "Calle 45 #23-56", "ana.martinez@email.com", "44332211");
    public static final Cliente CLIENTE5 = new Cliente("Luis Rodríguez", "3145678901", "Carrera 12 #34-78", "luis.rodriguez@email.com", "55667788");
} 