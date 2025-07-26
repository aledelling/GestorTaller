package gestortaller;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory Pattern: Fábrica para crear instancias de Persona
 * Centraliza la lógica de creación de objetos
 */
public class PersonaFactory {
    
    // Enumeración para tipos de persona
    public enum TipoPersona {
        CLIENTE, TECNICO
    }
    
    // Contador para IDs únicos
    private static int contadorClientes = 0;
    private static int contadorTecnicos = 0;
    
    /**
     * Método principal de la fábrica - Factory Method
     */
    public static Persona crearPersona(TipoPersona tipo, String nombre, String telefono, String documento, Object... parametrosAdicionales) {
        switch (tipo) {
            case CLIENTE:
                return crearCliente(nombre, telefono, documento, parametrosAdicionales);
            case TECNICO:
                return crearTecnico(nombre, telefono, documento, parametrosAdicionales);
            default:
                throw new IllegalArgumentException("Tipo de persona no válido: " + tipo);
        }
    }
    
    /**
     * Factory Method específico para clientes
     */
    private static Cliente crearCliente(String nombre, String telefono, String documento, Object... parametros) {
        String direccion = parametros.length > 0 ? (String) parametros[0] : "Dirección por defecto";
        String email = parametros.length > 1 ? (String) parametros[1] : generarEmailAutomatico(nombre);
        
        contadorClientes++;
        System.out.println("🏭 Factory: Creando Cliente #" + contadorClientes + " - " + nombre);
        
        return new Cliente(nombre, telefono, direccion, email, documento);
    }
    
    /**
     * Factory Method específico para técnicos
     */
    private static Tecnico crearTecnico(String nombre, String telefono, String documento, Object... parametros) {
        String especialidad = parametros.length > 0 ? (String) parametros[0] : "General";
        int experiencia = parametros.length > 1 ? (Integer) parametros[1] : 1;
        
        contadorTecnicos++;
        System.out.println("🏭 Factory: Creando Técnico #" + contadorTecnicos + " - " + nombre);
        
        return new Tecnico(nombre, documento, telefono, especialidad, experiencia);
    }
    
    /**
     * Método para crear lotes de personas
     */
    public static List<Persona> crearLotePersonas(TipoPersona tipo, int cantidad) {
        List<Persona> personas = new ArrayList<>();
        
        for (int i = 1; i <= cantidad; i++) {
            String nombre = generarNombreAutomatico(tipo, i);
            String telefono = generarTelefonoAutomatico();
            String documento = generarDocumentoAutomatico();
            
            if (tipo == TipoPersona.CLIENTE) {
                personas.add(crearPersona(tipo, nombre, telefono, documento, 
                    "Dirección Auto " + i, nombre.toLowerCase().replace(" ", ".") + "@auto.com"));
            } else {
                String[] especialidades = {"Motor", "Frenos", "Electricidad", "Transmisión", "Suspensión"};
                personas.add(crearPersona(tipo, nombre, telefono, documento, 
                    especialidades[i % especialidades.length], (i % 10) + 1));
            }
        }
        
        System.out.println("🏭 Factory: Lote de " + cantidad + " " + tipo + "s creado exitosamente");
        return personas;
    }
    
    /**
     * Patrón Factory con validación
     */
    public static Persona crearPersonaValidada(TipoPersona tipo, String nombre, String telefono, String documento, Object... parametros) {
        // Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (telefono == null || telefono.length() < 10) {
            throw new IllegalArgumentException("El teléfono debe tener al menos 10 dígitos");
        }
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento no puede estar vacío");
        }
        
        System.out.println("✅ Factory: Validaciones pasadas para " + nombre);
        return crearPersona(tipo, nombre, telefono, documento, parametros);
    }
    
    // Métodos auxiliares para generación automática
    private static String generarNombreAutomatico(TipoPersona tipo, int numero) {
        String[] nombresClientes = {"Ana López", "Carlos Ruiz", "María Torres", "Luis García", "Sandra Morales"};
        String[] nombresTecnicos = {"Pedro Herrera", "Laura Vega", "Miguel Santos", "Carmen Silva", "Roberto Díaz"};
        
        String[] nombres = (tipo == TipoPersona.CLIENTE) ? nombresClientes : nombresTecnicos;
        return nombres[(numero - 1) % nombres.length] + " " + numero;
    }
    
    private static String generarTelefonoAutomatico() {
        return "30" + String.format("%08d", (int)(Math.random() * 100000000));
    }
    
    private static String generarDocumentoAutomatico() {
        return String.format("%08d", (int)(Math.random() * 100000000));
    }
    
    private static String generarEmailAutomatico(String nombre) {
        return nombre.toLowerCase().replace(" ", ".") + "@gestortaller.com";
    }
    
    // Métodos de información del Factory
    public static void mostrarEstadisticas() {
        System.out.println("📊 Factory Statistics:");
        System.out.println("   Clientes creados: " + contadorClientes);
        System.out.println("   Técnicos creados: " + contadorTecnicos);
        System.out.println("   Total personas: " + (contadorClientes + contadorTecnicos));
    }
    
    public static void resetearContadores() {
        contadorClientes = 0;
        contadorTecnicos = 0;
        System.out.println("🔄 Factory: Contadores reseteados");
    }
}