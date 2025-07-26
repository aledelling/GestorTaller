package gestortaller;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Singleton Pattern: Gestión de configuración global del sistema
 * Garantiza una única instancia de configuración
 */
public class ConfiguracionSingleton {
    
    // Instancia única (Singleton)
    private static volatile ConfiguracionSingleton instancia;
    
    // Propiedades de configuración
    private Properties configuracion;
    private AtomicLong contadorInstanciaciones;
    private String version;
    private boolean modoDebug;
    
    // Constructor privado para prevenir instanciación externa
    private ConfiguracionSingleton() {
        System.out.println("🔧 Singleton: Inicializando configuración del sistema...");
        
        this.configuracion = new Properties();
        this.contadorInstanciaciones = new AtomicLong(0);
        this.version = "2.0.0";
        this.modoDebug = false;
        
        // Configuraciones por defecto
        inicializarConfiguracionPorDefecto();
        
        System.out.println("✅ Singleton: Configuración inicializada correctamente");
    }
    
    /**
     * Método getInstance con Double-Checked Locking
     * Thread-safe y eficiente
     */
    public static ConfiguracionSingleton getInstance() {
        if (instancia == null) {
            synchronized (ConfiguracionSingleton.class) {
                if (instancia == null) {
                    instancia = new ConfiguracionSingleton();
                    System.out.println("🎯 Singleton: Primera instancia creada");
                }
            }
        } else {
            System.out.println("♻️ Singleton: Reutilizando instancia existente");
        }
        
        instancia.contadorInstanciaciones.incrementAndGet();
        return instancia;
    }
    
    /**
     * Configuración por defecto del sistema
     */
    private void inicializarConfiguracionPorDefecto() {
        // Configuraciones de la aplicación
        configuracion.setProperty("app.nombre", "GestorTaller");
        configuracion.setProperty("app.version", version);
        configuracion.setProperty("app.autor", "Sistema POO");
        
        // Configuraciones de base de datos
        configuracion.setProperty("db.archivo", "ordenes.txt");
        configuracion.setProperty("db.backup", "true");
        configuracion.setProperty("db.autoguardado", "true");
        
        // Configuraciones de UI
        configuracion.setProperty("ui.idioma", "es");
        configuracion.setProperty("ui.tema", "clasico");
        configuracion.setProperty("ui.mostrar_emojis", "true");
        
        // Configuraciones de negocio
        configuracion.setProperty("negocio.precio_hora_base", "15000");
        configuracion.setProperty("negocio.descuento_maximo", "30");
        configuracion.setProperty("negocio.garantia_minima_dias", "7");
        
        System.out.println("📋 Singleton: " + configuracion.size() + " propiedades configuradas");
    }
    
    /**
     * Métodos de acceso a configuración
     */
    public String obtenerPropiedad(String clave) {
        String valor = configuracion.getProperty(clave);
        if (modoDebug) {
            System.out.println("🔍 Config: " + clave + " = " + valor);
        }
        return valor;
    }
    
    public String obtenerPropiedad(String clave, String valorPorDefecto) {
        return configuracion.getProperty(clave, valorPorDefecto);
    }
    
    public void establecerPropiedad(String clave, String valor) {
        String valorAnterior = configuracion.getProperty(clave);
        configuracion.setProperty(clave, valor);
        
        System.out.println("📝 Config: " + clave + " cambiado de '" + valorAnterior + "' a '" + valor + "'");
    }
    
    public int obtenerEntero(String clave, int valorPorDefecto) {
        try {
            return Integer.parseInt(obtenerPropiedad(clave, String.valueOf(valorPorDefecto)));
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Config: Error parseando entero para " + clave + ", usando por defecto: " + valorPorDefecto);
            return valorPorDefecto;
        }
    }
    
    public boolean obtenerBooleano(String clave, boolean valorPorDefecto) {
        String valor = obtenerPropiedad(clave, String.valueOf(valorPorDefecto));
        return "true".equalsIgnoreCase(valor) || "sí".equalsIgnoreCase(valor) || "si".equalsIgnoreCase(valor);
    }
    
    public double obtenerDouble(String clave, double valorPorDefecto) {
        try {
            return Double.parseDouble(obtenerPropiedad(clave, String.valueOf(valorPorDefecto)));
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Config: Error parseando double para " + clave + ", usando por defecto: " + valorPorDefecto);
            return valorPorDefecto;
        }
    }
    
    /**
     * Métodos específicos del negocio
     */
    public double getPrecioHoraBase() {
        return obtenerDouble("negocio.precio_hora_base", 15000.0);
    }
    
    public int getDescuentoMaximo() {
        return obtenerEntero("negocio.descuento_maximo", 30);
    }
    
    public boolean isAutoguardadoActivo() {
        return obtenerBooleano("db.autoguardado", true);
    }
    
    public boolean isMostrarEmojis() {
        return obtenerBooleano("ui.mostrar_emojis", true);
    }
    
    /**
     * Métodos de gestión del Singleton
     */
    public void habilitarModoDebug(boolean debug) {
        this.modoDebug = debug;
        System.out.println("🐛 Config: Modo debug " + (debug ? "activado" : "desactivado"));
    }
    
    public long getContadorAccesos() {
        return contadorInstanciaciones.get();
    }
    
    public String getVersion() {
        return version;
    }
    
    public void mostrarEstadisticas() {
        System.out.println("📊 === ESTADÍSTICAS DEL SINGLETON ===");
        System.out.println("   Versión: " + version);
        System.out.println("   Accesos al Singleton: " + getContadorAccesos());
        System.out.println("   Propiedades configuradas: " + configuracion.size());
        System.out.println("   Modo Debug: " + (modoDebug ? "Activado" : "Desactivado"));
        System.out.println("   Instancia única: " + (instancia != null ? "Creada" : "No creada"));
    }
    
    public void mostrarTodasLasPropiedades() {
        System.out.println("📋 === CONFIGURACIÓN COMPLETA ===");
        configuracion.entrySet().stream()
            .sorted((e1, e2) -> e1.getKey().toString().compareTo(e2.getKey().toString()))
            .forEach(entry -> System.out.println("   " + entry.getKey() + " = " + entry.getValue()));
    }
    
    /**
     * Método para resetear el Singleton (solo para testing)
     * ¡Usar con precaución!
     */
    public static synchronized void resetearInstancia() {
        System.out.println("🔄 Singleton: ADVERTENCIA - Reseteando instancia");
        instancia = null;
    }
    
    /**
     * Configuraciones predefinidas rápidas
     */
    public void aplicarConfiguracionProduccion() {
        habilitarModoDebug(false);
        establecerPropiedad("db.backup", "true");
        establecerPropiedad("ui.mostrar_emojis", "true");
        System.out.println("🚀 Config: Configuración de producción aplicada");
    }
    
    public void aplicarConfiguracionDesarrollo() {
        habilitarModoDebug(true);
        establecerPropiedad("db.backup", "false");
        establecerPropiedad("ui.mostrar_emojis", "true");
        System.out.println("🛠️ Config: Configuración de desarrollo aplicada");
    }
    
    /**
     * Validación de integridad del Singleton
     */
    public boolean validarIntegridad() {
        boolean esValido = true;
        
        // Verificar propiedades críticas
        if (obtenerPropiedad("app.nombre") == null) {
            System.out.println("❌ Config: Falta propiedad crítica app.nombre");
            esValido = false;
        }
        
        if (getPrecioHoraBase() <= 0) {
            System.out.println("❌ Config: Precio hora base inválido");
            esValido = false;
        }
        
        if (esValido) {
            System.out.println("✅ Config: Integridad validada correctamente");
        }
        
        return esValido;
    }
}