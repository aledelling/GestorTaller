package gestortaller;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.time.LocalDate;

/**
 * Object Pool Pattern: Pool de objetos Ordenes reutilizables
 * Mejora el rendimiento evitando crear/destruir objetos constantemente
 */
public class OrdenesPool {
    
    // Pool de objetos disponibles
    private final ConcurrentLinkedQueue<Ordenes> pool;
    
    // Contadores para estadísticas
    private final AtomicInteger objetosCreados;
    private final AtomicInteger objetosReutilizados;
    private final AtomicInteger objetosEnUso;
    
    // Configuración del pool
    private final int tamanioMaximo;
    private static final int TAMANIO_DEFECTO = 10;
    
    // Instancia Singleton del pool
    private static volatile OrdenesPool instancia;
    
    /**
     * Constructor privado
     */
    private OrdenesPool(int tamanioMaximo) {
        this.pool = new ConcurrentLinkedQueue<>();
        this.objetosCreados = new AtomicInteger(0);
        this.objetosReutilizados = new AtomicInteger(0);
        this.objetosEnUso = new AtomicInteger(0);
        this.tamanioMaximo = tamanioMaximo;
        
        System.out.println("🏊 Pool: Inicializando pool de órdenes (tamaño máximo: " + tamanioMaximo + ")");
        
        // Pre-llenar el pool con algunos objetos
        prellenarPool();
    }
    
    /**
     * Obtener instancia del pool (Singleton)
     */
    public static OrdenesPool getInstance() {
        return getInstance(TAMANIO_DEFECTO);
    }
    
    public static OrdenesPool getInstance(int tamanioMaximo) {
        if (instancia == null) {
            synchronized (OrdenesPool.class) {
                if (instancia == null) {
                    instancia = new OrdenesPool(tamanioMaximo);
                    System.out.println("🎯 Pool: Primera instancia del pool creada");
                }
            }
        }
        return instancia;
    }
    
    /**
     * Pre-llenar el pool con objetos por defecto
     */
    private void prellenarPool() {
        int objetosIniciales = Math.min(3, tamanioMaximo);
        
        for (int i = 0; i < objetosIniciales; i++) {
            Ordenes orden = crearOrdenVacia();
            pool.offer(orden);
        }
        
        System.out.println("🔧 Pool: Pre-llenado con " + objetosIniciales + " órdenes");
    }
    
    /**
     * Obtener una orden del pool
     */
    public Ordenes obtenerOrden() {
        Ordenes orden = pool.poll();
        
        if (orden != null) {
            // Reutilizar objeto existente
            objetosReutilizados.incrementAndGet();
            objetosEnUso.incrementAndGet();
            System.out.println("♻️ Pool: Reutilizando orden existente (en uso: " + objetosEnUso.get() + ")");
            return orden;
        } else {
            // Crear nuevo objeto si el pool está vacío
            orden = crearOrdenVacia();
            objetosCreados.incrementAndGet();
            objetosEnUso.incrementAndGet();
            System.out.println("🆕 Pool: Creando nueva orden (total creadas: " + objetosCreados.get() + ")");
            return orden;
        }
    }
    
    /**
     * Devolver una orden al pool
     */
    public void devolverOrden(Ordenes orden) {
        if (orden == null) {
            System.out.println("⚠️ Pool: Intento de devolver orden null");
            return;
        }
        
        // Limpiar la orden antes de devolverla al pool
        limpiarOrden(orden);
        
        // Solo devolver al pool si no hemos excedido el tamaño máximo
        if (pool.size() < tamanioMaximo) {
            pool.offer(orden);
            objetosEnUso.decrementAndGet();
            System.out.println("↩️ Pool: Orden devuelta al pool (disponibles: " + pool.size() + ")");
        } else {
            objetosEnUso.decrementAndGet();
            System.out.println("🗑️ Pool: Orden descartada (pool lleno)");
        }
    }
    
    /**
     * Configurar una orden obtenida del pool
     */
    public Ordenes configurarOrden(int numeroOrden, Motocicleta motocicleta, Servicio servicio,
                                  LocalDate fechaEntrada, LocalDate fechaSalida, Tecnico tecnico,
                                  String observaciones, boolean completado, Cliente cliente) {
        
        Ordenes orden = obtenerOrden();
        
        // Configurar la orden usando reflection o métodos setter
        // Como Ordenes no tiene setters públicos, necesitamos crear una nueva instancia
        // En un escenario real, modificaríamos Ordenes para soportar reutilización
        
        System.out.println("⚙️ Pool: Configurando orden #" + numeroOrden);
        
        return new Ordenes(numeroOrden, motocicleta, servicio, fechaEntrada, fechaSalida, 
                          tecnico, observaciones, completado, cliente);
    }
    
    /**
     * Crear una orden vacía para el pool
     */
    private Ordenes crearOrdenVacia() {
        // Crear una orden con valores por defecto que puede ser reutilizada
        LocalDate fechaDefecto = LocalDate.now();
        
        return new Ordenes(
            0, // número temporal
            Motocicleta.MOTO1, // motocicleta por defecto
            Servicio.SERVICIO1, // servicio por defecto
            fechaDefecto,
            fechaDefecto,
            Tecnico.TECNICO1, // técnico por defecto
            "", // observaciones vacías
            false, // no completado
            Cliente.CLIENTE1 // cliente por defecto
        );
    }
    
    /**
     * Limpiar una orden antes de devolverla al pool
     */
    private void limpiarOrden(Ordenes orden) {
        // En un escenario real, aquí resetearíamos todos los campos
        // Como Ordenes no tiene métodos de reset, solo marcamos que está siendo limpiada
        System.out.println("🧹 Pool: Limpiando orden para reutilización");
    }
    
    /**
     * Obtener múltiples órdenes del pool
     */
    public Ordenes[] obtenerLoteOrdenes(int cantidad) {
        Ordenes[] ordenes = new Ordenes[cantidad];
        
        for (int i = 0; i < cantidad; i++) {
            ordenes[i] = obtenerOrden();
        }
        
        System.out.println("📦 Pool: Lote de " + cantidad + " órdenes obtenido");
        return ordenes;
    }
    
    /**
     * Devolver múltiples órdenes al pool
     */
    public void devolverLoteOrdenes(Ordenes[] ordenes) {
        for (Ordenes orden : ordenes) {
            if (orden != null) {
                devolverOrden(orden);
            }
        }
        
        System.out.println("📦 Pool: Lote de órdenes devuelto");
    }
    
    /**
     * Limpiar completamente el pool
     */
    public void limpiarPool() {
        int ordenesEliminadas = pool.size();
        pool.clear();
        
        System.out.println("🧹 Pool: Pool limpiado (" + ordenesEliminadas + " órdenes eliminadas)");
    }
    
    /**
     * Expandir el pool pre-llenándolo
     */
    public void expandirPool(int cantidadAdicional) {
        int objetosAgregados = 0;
        
        for (int i = 0; i < cantidadAdicional && pool.size() < tamanioMaximo; i++) {
            Ordenes orden = crearOrdenVacia();
            pool.offer(orden);
            objetosAgregados++;
        }
        
        System.out.println("📈 Pool: Expandido con " + objetosAgregados + " órdenes adicionales");
    }
    
    /**
     * Estadísticas del pool
     */
    public void mostrarEstadisticas() {
        System.out.println("📊 === ESTADÍSTICAS DEL OBJECT POOL ===");
        System.out.println("   Órdenes en pool: " + pool.size());
        System.out.println("   Órdenes en uso: " + objetosEnUso.get());
        System.out.println("   Total objetos creados: " + objetosCreados.get());
        System.out.println("   Total objetos reutilizados: " + objetosReutilizados.get());
        System.out.println("   Tamaño máximo del pool: " + tamanioMaximo);
        System.out.println("   Eficiencia de reutilización: " + calcularEficiencia() + "%");
    }
    
    /**
     * Calcular eficiencia de reutilización
     */
    private double calcularEficiencia() {
        int totalAccesos = objetosCreados.get() + objetosReutilizados.get();
        if (totalAccesos == 0) return 0.0;
        
        return (double) objetosReutilizados.get() / totalAccesos * 100.0;
    }
    
    /**
     * Información del estado actual
     */
    public PoolInfo obtenerInfo() {
        return new PoolInfo(
            pool.size(),
            objetosEnUso.get(),
            objetosCreados.get(),
            objetosReutilizados.get(),
            tamanioMaximo,
            calcularEficiencia()
        );
    }
    
    /**
     * Clase interna para información del pool
     */
    public static class PoolInfo {
        public final int ordenesDisponibles;
        public final int ordenesEnUso;
        public final int totalCreadas;
        public final int totalReutilizadas;
        public final int tamanioMaximo;
        public final double eficiencia;
        
        public PoolInfo(int disponibles, int enUso, int creadas, int reutilizadas, int maximo, double eficiencia) {
            this.ordenesDisponibles = disponibles;
            this.ordenesEnUso = enUso;
            this.totalCreadas = creadas;
            this.totalReutilizadas = reutilizadas;
            this.tamanioMaximo = maximo;
            this.eficiencia = eficiencia;
        }
        
        @Override
        public String toString() {
            return String.format("Pool[Disponibles:%d, EnUso:%d, Creadas:%d, Reutilizadas:%d, Eficiencia:%.1f%%]",
                               ordenesDisponibles, ordenesEnUso, totalCreadas, totalReutilizadas, eficiencia);
        }
    }
    
    /**
     * Resetear el pool (para testing)
     */
    public static synchronized void resetearPool() {
        if (instancia != null) {
            instancia.limpiarPool();
            instancia = null;
            System.out.println("🔄 Pool: Pool reseteado completamente");
        }
    }
}