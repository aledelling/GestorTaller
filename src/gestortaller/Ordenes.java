package gestortaller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ordenes {
    private int numeroOrden;
    private Motocicleta motocicleta;
    private Servicio servicio;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Tecnico tecnico;
    private String observaciones;
    private boolean completado;
    private Cliente cliente;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Ordenes(int numOrden, Motocicleta moto, Servicio serv, LocalDate fechaEnt, LocalDate fechaSal, Tecnico tec, String obs, boolean comp, Cliente cli) {
        numeroOrden = numOrden;
        motocicleta = moto;
        servicio = serv;
        fechaEntrada = fechaEnt;
        fechaSalida = fechaSal;
        tecnico = tec;
        observaciones = obs;
        completado = comp;
        cliente = cli;
    }

    public int getnumeroOrden() { return numeroOrden; }
    public Motocicleta getMotocicleta() { return motocicleta; }
    public Servicio getServicio() { return servicio; }
    public LocalDate getfechaentrada() { return fechaEntrada; }
    public LocalDate getfechasalida() { return fechaSalida; }
    public Tecnico getTecnico() { return tecnico; }
    public String getobservaciones() { return observaciones; }
    public boolean isCompletado() { return completado; }
    public Cliente getCliente() { return cliente; }

    public void setfechaentrada(LocalDate fechaEntrada) { this.fechaEntrada = fechaEntrada; }
    public void setfechasalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }

    public void marcarComoCompletado() { completado = true; }

    public void mostrarInfo(int indice) {
        if (indice >= 0) System.out.println("[" + indice + "]");
        System.out.println("Orden #" + numeroOrden +
            " | Moto: " + motocicleta.getModelo() + " (" + motocicleta.getMarca() + ", " + motocicleta.getColor() + ", " + motocicleta.getPlaca() + ", " + motocicleta.getCilindraje() + "cc)" +
            " | Servicio: " + servicio.getDescripcion() + " ($" + servicio.getPrecio() + ", " + servicio.getDuracion() + "min, " + servicio.getTipo() + ", Garantía: " + servicio.getGarantia() + ")");
        System.out.println("Entrada: " + fechaEntrada.format(FORMATO_FECHA) + " | Salida: " + fechaSalida.format(FORMATO_FECHA) +
            " | Técnico: " + tecnico.getNombre() + " (" + tecnico.getCedula() + ", " + tecnico.getTelefono() + ", " + tecnico.getEspecialidad() + ", " + tecnico.getExperiencia() + " años)" );
        System.out.println("Cliente: " + (cliente != null ? cliente.getNombre() + " - " + cliente.getTelefono() + " - " + cliente.getDireccion() + " - " + cliente.getEmail() + " - " + cliente.getDocumento() : "No asignado"));
        System.out.println("Observaciones: " + observaciones + " | Completado: " + (completado ? "Sí" : "No"));
        System.out.println("--------------------------------------------------");
    }
} 