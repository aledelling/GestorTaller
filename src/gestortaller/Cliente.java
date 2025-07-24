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
} 