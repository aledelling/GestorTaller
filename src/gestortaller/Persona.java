package gestortaller;

public abstract class Persona {
    protected String nombre;
    protected String telefono;
    protected String documento;

    public Persona(String nombre, String telefono, String documento) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.documento = documento;
    }

    // Getters comunes
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getDocumento() { return documento; }

    // Setters comunes
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setDocumento(String documento) { this.documento = documento; }

    // Método abstracto que cada subclase debe implementar
    public abstract String getTipoPersona();
    public abstract String getInformacionEspecifica();

    // Método común para mostrar información básica
    public String getInformacionBasica() {
        return String.format("Nombre: %s, Teléfono: %s, Documento: %s", 
                           nombre, telefono, documento);
    }

    @Override
    public String toString() {
        return getInformacionBasica() + " - " + getInformacionEspecifica();
    }
}