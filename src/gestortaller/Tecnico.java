package gestortaller;

public class Tecnico {
    private String nombre;
    private String cedula;
    private String telefono;
    private String especialidad;
    private int experiencia; // años

    public Tecnico(String nom, String ced, String tel, String esp, int exp) {
        nombre = nom;
        cedula = ced;
        telefono = tel;
        especialidad = esp;
        experiencia = exp;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getTelefono() { return telefono; }
    public String getEspecialidad() { return especialidad; }
    public int getExperiencia() { return experiencia; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }
} 