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

    // Constantes predefinidas
    public static final Tecnico TECNICO1 = new Tecnico("Pedro Ramírez", "98765432", "3001112233", "Motor", 8);
    public static final Tecnico TECNICO2 = new Tecnico("Sandra Torres", "87654321", "3002223344", "Frenos", 5);
    public static final Tecnico TECNICO3 = new Tecnico("Miguel Vargas", "76543210", "3003334455", "Electricidad", 12);
    public static final Tecnico TECNICO4 = new Tecnico("Laura Jiménez", "65432109", "3004445566", "Transmisión", 7);
    public static final Tecnico TECNICO5 = new Tecnico("Roberto Castro", "54321098", "3005556677", "Suspensión", 10);
} 