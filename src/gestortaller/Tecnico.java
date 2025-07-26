package gestortaller;

public class Tecnico extends Persona {
    private String especialidad;
    private int experiencia; // años
    private String cedula; // Mantenemos cedula como atributo específico adicional

    // Constructor compatible con el código existente
    public Tecnico(String nom, String ced, String tel, String esp, int exp) {
        super(nom, tel, ced);
        this.cedula = ced;
        this.especialidad = esp;
        this.experiencia = exp;
    }

    // Getters específicos de Tecnico
    public String getEspecialidad() { return especialidad; }
    public int getExperiencia() { return experiencia; }
    public String getCedula() { return cedula; }

    // Setters específicos de Tecnico
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }
    public void setCedula(String cedula) { 
        this.cedula = cedula;
        this.documento = cedula; // Mantener sincronizado
    }

    // Implementación de métodos abstractos
    @Override
    public String getTipoPersona() {
        return "Técnico";
    }

    @Override
    public String getInformacionEspecifica() {
        return String.format("Especialidad: %s, Experiencia: %d años", especialidad, experiencia);
    }

    // Métodos específicos para técnicos
    public boolean esExperimentado() {
        return experiencia >= 5;
    }

    public String getNivelExperiencia() {
        if (experiencia < 2) return "Junior";
        else if (experiencia < 5) return "Intermedio";
        else if (experiencia < 10) return "Senior";
        else return "Experto";
    }

    public double calcularTarifaHora() {
        double tarifaBase = 15000; // Tarifa base por hora
        return tarifaBase + (experiencia * 1000); // +1000 por año de experiencia
    }

    // Constantes predefinidas (actualizadas)
    public static final Tecnico TECNICO1 = new Tecnico("Pedro Ramírez", "98765432", "3001112233", "Motor", 8);
    public static final Tecnico TECNICO2 = new Tecnico("Sandra Torres", "87654321", "3002223344", "Frenos", 5);
    public static final Tecnico TECNICO3 = new Tecnico("Miguel Vargas", "76543210", "3003334455", "Electricidad", 12);
    public static final Tecnico TECNICO4 = new Tecnico("Laura Jiménez", "65432109", "3004445566", "Transmisión", 7);
    public static final Tecnico TECNICO5 = new Tecnico("Roberto Castro", "54321098", "3005556677", "Suspensión", 10);
} 