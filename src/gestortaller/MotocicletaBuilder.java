package gestortaller;

/**
 * Builder Pattern: Constructor fluido para Motocicletas
 * Permite crear objetos complejos paso a paso
 */
public class MotocicletaBuilder {
    
    // Atributos requeridos
    private String marca;
    private String modelo;
    
    // Atributos opcionales con valores por defecto
    private String color = "Negro";
    private String placa = "XXX000";
    private int anioFabricacion = 2024;
    private int cilindraje = 150;
    private String tipoMotor = "4T";
    private boolean esDeportiva = false;
    
    // Constructor privado para forzar el uso del factory method
    private MotocicletaBuilder(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }
    
    /**
     * Factory method para iniciar la construcción
     */
    public static MotocicletaBuilder crear(String marca, String modelo) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca es requerida");
        }
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo es requerido");
        }
        
        System.out.println("🔧 Builder: Iniciando construcción de " + marca + " " + modelo);
        return new MotocicletaBuilder(marca, modelo);
    }
    
    /**
     * Métodos builder fluidos - devuelven this para encadenamiento
     */
    public MotocicletaBuilder conColor(String color) {
        this.color = color;
        System.out.println("🎨 Builder: Color establecido -> " + color);
        return this;
    }
    
    public MotocicletaBuilder conPlaca(String placa) {
        if (placa != null && placa.length() >= 6) {
            this.placa = placa;
            System.out.println("🏷️ Builder: Placa establecida -> " + placa);
        } else {
            System.out.println("⚠️ Builder: Placa inválida, usando por defecto");
        }
        return this;
    }
    
    public MotocicletaBuilder delAnio(int anio) {
        if (anio >= 1900 && anio <= 2030) {
            this.anioFabricacion = anio;
            System.out.println("📅 Builder: Año establecido -> " + anio);
        } else {
            System.out.println("⚠️ Builder: Año inválido, usando por defecto");
        }
        return this;
    }
    
    public MotocicletaBuilder conCilindraje(int cilindraje) {
        if (cilindraje > 0 && cilindraje <= 2000) {
            this.cilindraje = cilindraje;
            // Auto-determinar si es deportiva basado en cilindraje
            this.esDeportiva = cilindraje > 400;
            System.out.println("⚙️ Builder: Cilindraje establecido -> " + cilindraje + "cc");
            System.out.println("🏁 Builder: Deportiva auto-determinada -> " + this.esDeportiva);
        } else {
            System.out.println("⚠️ Builder: Cilindraje inválido, usando por defecto");
        }
        return this;
    }
    
    public MotocicletaBuilder conTipoMotor(String tipoMotor) {
        if ("2T".equals(tipoMotor) || "4T".equals(tipoMotor)) {
            this.tipoMotor = tipoMotor;
            System.out.println("🔧 Builder: Tipo motor establecido -> " + tipoMotor);
        } else {
            System.out.println("⚠️ Builder: Tipo motor inválido, usando 4T por defecto");
        }
        return this;
    }
    
    public MotocicletaBuilder esDeportiva(boolean esDeportiva) {
        this.esDeportiva = esDeportiva;
        System.out.println("🏁 Builder: Deportiva establecida -> " + esDeportiva);
        return this;
    }
    
    /**
     * Métodos de configuración predefinida
     */
    public MotocicletaBuilder configuracionUrbana() {
        return this.conCilindraje(125)
                  .conTipoMotor("4T")
                  .esDeportiva(false);
    }
    
    public MotocicletaBuilder configuracionDeportiva() {
        return this.conCilindraje(600)
                  .conTipoMotor("4T")
                  .esDeportiva(true);
    }
    
    public MotocicletaBuilder configuracionTouring() {
        return this.conCilindraje(800)
                  .conTipoMotor("4T")
                  .esDeportiva(false);
    }
    
    /**
     * Validaciones antes de construir
     */
    private void validar() {
        if (marca == null || modelo == null) {
            throw new IllegalStateException("Marca y modelo son requeridos");
        }
        
        // Validaciones de lógica de negocio
        if (cilindraje > 1000 && "2T".equals(tipoMotor)) {
            System.out.println("⚠️ Builder: Advertencia - Motores 2T >1000cc son raros");
        }
        
        if (anioFabricacion < 2000 && esDeportiva) {
            System.out.println("⚠️ Builder: Advertencia - Deportivas muy antiguas");
        }
    }
    
    /**
     * Método build final - construye el objeto
     */
    public Motocicleta construir() {
        validar();
        
        System.out.println("✅ Builder: Construyendo motocicleta...");
        System.out.println("📋 Builder: Especificaciones finales:");
        System.out.println("   Marca: " + marca + ", Modelo: " + modelo);
        System.out.println("   Color: " + color + ", Placa: " + placa);
        System.out.println("   Año: " + anioFabricacion + ", Cilindraje: " + cilindraje + "cc");
        System.out.println("   Motor: " + tipoMotor + ", Deportiva: " + esDeportiva);
        
        // Crear usando el constructor completo de herencia
        Motocicleta motocicleta = new Motocicleta(marca, modelo, color, placa, anioFabricacion, 
                                                 cilindraje, tipoMotor, esDeportiva);
        
        System.out.println("🎉 Builder: ¡Motocicleta construida exitosamente!");
        return motocicleta;
    }
    
    /**
     * Método para obtener resumen sin construir
     */
    public String obtenerResumen() {
        return String.format("Motocicleta: %s %s %d (%dcc, %s, %s)", 
                           marca, modelo, anioFabricacion, cilindraje, 
                           tipoMotor, esDeportiva ? "Deportiva" : "Estándar");
    }
    
    /**
     * Builder pattern con copia
     */
    public static MotocicletaBuilder copiarDe(Motocicleta original) {
        System.out.println("📋 Builder: Copiando configuración de " + original.getModelo());
        
        return new MotocicletaBuilder(original.getMarca(), original.getModelo())
                .conColor(original.getColor())
                .conPlaca("COPIA-" + original.getPlaca().substring(0, 3))
                .delAnio(original.getAnioFabricacion())
                .conCilindraje(original.getCilindraje())
                .conTipoMotor(original.getTipoMotor())
                .esDeportiva(original.isEsDeportiva());
    }
}