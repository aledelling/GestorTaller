package gestortaller;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GestorTaller {

    private static final Scanner entrada = new Scanner(System.in);
    private static final String ARCHIVO = "ordenes.txt";
    private static final ArrayList<Ordenes> biblioteca = new ArrayList<>();
    // Listas para almacenar objetos nuevos
    private static final ArrayList<Motocicleta> motocicletasNuevas = new ArrayList<>();
    private static final ArrayList<Servicio> serviciosNuevos = new ArrayList<>();
    private static final ArrayList<Tecnico> tecnicosNuevos = new ArrayList<>();
    private static final ArrayList<Cliente> clientesNuevos = new ArrayList<>();
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        cargarDesdeArchivo();
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> agregarOrden();
                case 2 -> mostrarOrdenes();
                case 3 -> buscarOrden();
                case 4 -> marcarComoCompletada();
                case 5 -> guardarEnArchivo();
                case 6 -> mostrarClientes();
                case 7 -> mostrarTecnicos();
                case 8 -> mostrarMotocicletas();
                case 9 -> mostrarServicios();
                case 0 -> System.out.println("🚪 Saliendo del sistema...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);

        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- 🛠️ GESTIÓN DE ÓRDENES DE TALLER ---");
        System.out.println("1. Agregar orden");
        System.out.println("2. Mostrar todas las órdenes");
        System.out.println("3. Buscar orden por número");
        System.out.println("4. Marcar orden como completada");
        System.out.println("5. Guardar en archivo");
        System.out.println("6. Visualizar clientes");
        System.out.println("7. Visualizar técnicos");
        System.out.println("8. Visualizar motocicletas");
        System.out.println("9. Visualizar servicios");
        System.out.println("0. Salir");
    }

    private static void agregarOrden() {
        int numeroOrden = leerEntero("Número de Orden: ");

        // Cliente
        Cliente cliente = seleccionarCliente();

        // Motocicleta
        Motocicleta motocicletaObj = seleccionarMotocicleta();

        // Servicio
        Servicio servicioObj = seleccionarServicio();

        LocalDate fechaEntrada = leerFecha("Fecha de Entrada (dd/MM/yyyy): ");
        LocalDate fechaSalida = leerFecha("Fecha de Salida (dd/MM/yyyy): ");

        // Técnico
        Tecnico tecnicoObj = seleccionarTecnico();

        System.out.print("Observaciones: ");
        String observaciones = entrada.nextLine();

        boolean completado = leerBooleano("¿Completado? (sí/no): ");

        biblioteca.add(new Ordenes(numeroOrden, motocicletaObj, servicioObj, fechaEntrada, fechaSalida, tecnicoObj, observaciones, completado, cliente));
        System.out.println("✅ Nueva orden agregada.");
    }

    // Métodos para seleccionar o crear nuevos objetos
    private static Cliente seleccionarCliente() {
        while (true) {
            if (clientesNuevos.isEmpty()) {
                System.out.println("No hay clientes registrados. Debe registrar uno nuevo.");
                Cliente nuevo = crearNuevoCliente();
                clientesNuevos.add(nuevo);
                return nuevo;
            }
            System.out.println("Seleccione un cliente:");
            for (int i = 0; i < clientesNuevos.size(); i++) {
                Cliente c = clientesNuevos.get(i);
                System.out.println((i + 1) + ". " + c.getNombre() + " - " + c.getTelefono());
            }
            System.out.println((clientesNuevos.size() + 1) + ". Registrar nuevo cliente");
            int opCli = leerEntero("Opción: ");
            if (opCli >= 1 && opCli <= clientesNuevos.size()) {
                return clientesNuevos.get(opCli - 1);
            } else if (opCli == clientesNuevos.size() + 1) {
                Cliente nuevo = crearNuevoCliente();
                clientesNuevos.add(nuevo);
                return nuevo;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    private static Cliente crearNuevoCliente() {
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Teléfono: ");
        String telefono = entrada.nextLine();
        System.out.print("Dirección: ");
        String direccion = entrada.nextLine();
        System.out.print("Email: ");
        String email = entrada.nextLine();
        System.out.print("Documento: ");
        String documento = entrada.nextLine();
        return new Cliente(nombre, telefono, direccion, email, documento);
    }

    private static Motocicleta seleccionarMotocicleta() {
        while (true) {
            if (motocicletasNuevas.isEmpty()) {
                System.out.println("No hay motocicletas registradas. Debe registrar una nueva.");
                Motocicleta nueva = crearNuevaMotocicleta();
                motocicletasNuevas.add(nueva);
                return nueva;
            }
            System.out.println("Seleccione una motocicleta:");
            for (int i = 0; i < motocicletasNuevas.size(); i++) {
                Motocicleta m = motocicletasNuevas.get(i);
                System.out.println((i + 1) + ". " + m.getModelo() + " - " + m.getPlaca());
            }
            System.out.println((motocicletasNuevas.size() + 1) + ". Registrar nueva motocicleta");
            int opMoto = leerEntero("Opción: ");
            if (opMoto >= 1 && opMoto <= motocicletasNuevas.size()) {
                return motocicletasNuevas.get(opMoto - 1);
            } else if (opMoto == motocicletasNuevas.size() + 1) {
                Motocicleta nueva = crearNuevaMotocicleta();
                motocicletasNuevas.add(nueva);
                return nueva;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    private static Motocicleta crearNuevaMotocicleta() {
        System.out.print("Modelo: ");
        String modelo = entrada.nextLine();
        System.out.print("Marca: ");
        String marca = entrada.nextLine();
        System.out.print("Color: ");
        String color = entrada.nextLine();
        System.out.print("Placa: ");
        String placa = entrada.nextLine();
        int cilindraje = leerEntero("Cilindraje: ");
        return new Motocicleta(modelo, marca, color, placa, cilindraje);
    }

    private static Servicio seleccionarServicio() {
        while (true) {
            if (serviciosNuevos.isEmpty()) {
                System.out.println("No hay servicios registrados. Debe registrar uno nuevo.");
                Servicio nuevo = crearNuevoServicio();
                serviciosNuevos.add(nuevo);
                return nuevo;
            }
            System.out.println("Seleccione un servicio:");
            for (int i = 0; i < serviciosNuevos.size(); i++) {
                Servicio s = serviciosNuevos.get(i);
                System.out.println((i + 1) + ". " + s.getDescripcion());
            }
            System.out.println((serviciosNuevos.size() + 1) + ". Registrar nuevo servicio");
            int opServ = leerEntero("Opción: ");
            if (opServ >= 1 && opServ <= serviciosNuevos.size()) {
                return serviciosNuevos.get(opServ - 1);
            } else if (opServ == serviciosNuevos.size() + 1) {
                Servicio nuevo = crearNuevoServicio();
                serviciosNuevos.add(nuevo);
                return nuevo;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    private static Servicio crearNuevoServicio() {
        System.out.print("Descripción: ");
        String descripcion = entrada.nextLine();
        double precio = Double.parseDouble(entrada.nextLine());
        System.out.print("Precio: ");
        precio = Double.parseDouble(entrada.nextLine());
        int duracion = leerEntero("Duración (minutos): ");
        System.out.print("Tipo: ");
        String tipo = entrada.nextLine();
        System.out.print("Garantía: ");
        String garantia = entrada.nextLine();
        return new Servicio(descripcion, precio, duracion, tipo, garantia);
    }

    private static Tecnico seleccionarTecnico() {
        while (true) {
            if (tecnicosNuevos.isEmpty()) {
                System.out.println("No hay técnicos registrados. Debe registrar uno nuevo.");
                Tecnico nuevo = crearNuevoTecnico();
                tecnicosNuevos.add(nuevo);
                return nuevo;
            }
            System.out.println("Seleccione un técnico:");
            for (int i = 0; i < tecnicosNuevos.size(); i++) {
                Tecnico t = tecnicosNuevos.get(i);
                System.out.println((i + 1) + ". " + t.getNombre() + " - " + t.getEspecialidad());
            }
            System.out.println((tecnicosNuevos.size() + 1) + ". Registrar nuevo técnico");
            int opTec = leerEntero("Opción: ");
            if (opTec >= 1 && opTec <= tecnicosNuevos.size()) {
                return tecnicosNuevos.get(opTec - 1);
            } else if (opTec == tecnicosNuevos.size() + 1) {
                Tecnico nuevo = crearNuevoTecnico();
                tecnicosNuevos.add(nuevo);
                return nuevo;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    private static Tecnico crearNuevoTecnico() {
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Cédula: ");
        String cedula = entrada.nextLine();
        System.out.print("Teléfono: ");
        String telefono = entrada.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = entrada.nextLine();
        int experiencia = leerEntero("Años de experiencia: ");
        return new Tecnico(nombre, cedula, telefono, especialidad, experiencia);
    }

    private static void mostrarOrdenes() {
        if (biblioteca.isEmpty()) {
            System.out.println("📭 No hay órdenes registradas.");
            return;
        }
        String formato = "| %-3s | %-15s | %-10s | %-8s | %-8s | %-15s | %-10s | %-10s | %-15s | %-10s |\n";
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(formato, "ID", "Cliente", "Moto", "Marca", "Placa", "Servicio", "Entrada", "Salida", "Técnico", "Completado");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        for (Ordenes o : biblioteca) {
            System.out.printf(formato,
                o.getnumeroOrden(),
                o.getCliente() != null ? o.getCliente().getNombre() : "-",
                o.getMotocicleta().getModelo(),
                o.getMotocicleta().getMarca(),
                o.getMotocicleta().getPlaca(),
                o.getServicio().getDescripcion(),
                o.getfechaentrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                o.getfechasalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                o.getTecnico().getNombre(),
                o.isCompletado() ? "Sí" : "No"
            );
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }

    private static void mostrarClientes() {
        if (clientesNuevos.isEmpty()) {
            System.out.println("📭 No hay clientes registrados.");
            return;
        }
        String formato = "| %-15s | %-12s | %-20s | %-20s | %-12s |\n";
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.printf(formato, "Nombre", "Teléfono", "Dirección", "Email", "Documento");
        System.out.println("-----------------------------------------------------------------------------------------------");
        for (Cliente c : clientesNuevos) {
            System.out.printf(formato,
                c.getNombre(),
                c.getTelefono(),
                c.getDireccion(),
                c.getEmail(),
                c.getDocumento()
            );
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

    private static void mostrarTecnicos() {
        if (tecnicosNuevos.isEmpty()) {
            System.out.println("📭 No hay técnicos registrados.");
            return;
        }
        String formato = "| %-15s | %-10s | %-12s | %-15s | %-10s |\n";
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf(formato, "Nombre", "Cédula", "Teléfono", "Especialidad", "Experiencia");
        System.out.println("--------------------------------------------------------------------------");
        for (Tecnico t : tecnicosNuevos) {
            System.out.printf(formato,
                t.getNombre(),
                t.getCedula(),
                t.getTelefono(),
                t.getEspecialidad(),
                t.getExperiencia() + " años"
            );
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    private static void mostrarMotocicletas() {
        if (motocicletasNuevas.isEmpty()) {
            System.out.println("📭 No hay motocicletas registradas.");
            return;
        }
        String formato = "| %-10s | %-10s | %-8s | %-8s | %-10s |\n";
        System.out.println("--------------------------------------------------------------");
        System.out.printf(formato, "Modelo", "Marca", "Color", "Placa", "Cilindraje");
        System.out.println("--------------------------------------------------------------");
        for (Motocicleta m : motocicletasNuevas) {
            System.out.printf(formato,
                m.getModelo(),
                m.getMarca(),
                m.getColor(),
                m.getPlaca(),
                m.getCilindraje() + "cc"
            );
        }
        System.out.println("--------------------------------------------------------------");
    }

    private static void mostrarServicios() {
        if (serviciosNuevos.isEmpty()) {
            System.out.println("📭 No hay servicios registrados.");
            return;
        }
        String formato = "| %-20s | %-10s | %-8s | %-15s | %-10s |\n";
        System.out.println("----------------------------------------------------------------------------");
        System.out.printf(formato, "Descripción", "Precio", "Duración", "Tipo", "Garantía");
        System.out.println("----------------------------------------------------------------------------");
        for (Servicio s : serviciosNuevos) {
            System.out.printf(formato,
                s.getDescripcion(),
                "$" + s.getPrecio(),
                s.getDuracion() + " min",
                s.getTipo(),
                s.getGarantia()
            );
        }
        System.out.println("----------------------------------------------------------------------------");
    }

    private static void buscarOrden() {
        int numeroBuscado = leerEntero("Ingrese el número de orden a buscar: ");
        boolean encontrado = false;

        for (Ordenes o : biblioteca) {
            if (o.getnumeroOrden() == numeroBuscado) {
                o.mostrarInfo(-1); // sin índice
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("❌ No se encontró ninguna orden con ese número.");
        }
    }

    private static void marcarComoCompletada() {
        mostrarOrdenes();
        if (biblioteca.isEmpty()) return;

        int numero = leerEntero("Ingrese el número de orden a marcar como completada: ");
        for (Ordenes o : biblioteca) {
            if (o.getnumeroOrden() == numero) {
                o.marcarComoCompletado();
                System.out.println("✅ Orden marcada como completada.");
                return;
            }
        }

        System.out.println("❌ Orden no encontrada.");
    }

    private static void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Ordenes o : biblioteca) {
                Motocicleta m = o.getMotocicleta();
                Servicio s = o.getServicio();
                Tecnico t = o.getTecnico();
                Cliente c = o.getCliente();
                pw.println(o.getnumeroOrden() + ";"
                        + m.getModelo() + ";" + m.getMarca() + ";" + m.getColor() + ";" + m.getPlaca() + ";" + m.getCilindraje() + ";"
                        + s.getDescripcion() + ";" + s.getPrecio() + ";" + s.getDuracion() + ";" + s.getTipo() + ";" + s.getGarantia() + ";"
                        + o.getfechaentrada().format(FORMATO_FECHA) + ";" + o.getfechasalida().format(FORMATO_FECHA) + ";"
                        + t.getNombre() + ";" + t.getCedula() + ";" + t.getTelefono() + ";" + t.getEspecialidad() + ";" + t.getExperiencia() + ";"
                        + o.getobservaciones() + ";" + o.isCompletado() + ";"
                        + (c != null ? c.getNombre() + ";" + c.getTelefono() + ";" + c.getDireccion() + ";" + c.getEmail() + ";" + c.getDocumento() : ";;;;")
                );
            }
            System.out.println("💾 Datos guardados en " + ARCHIVO);
        } catch (IOException e) {
            System.out.println("❌ Error al guardar: " + e.getMessage());
        }
    }

    private static void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        try (Scanner fileScanner = new Scanner(archivo)) {
            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine();
                String[] datos = linea.split(";");

                if (datos.length >= 24) {
                    int i = 0;
                    int numeroOrden = Integer.parseInt(datos[i++]);
                    // Motocicleta
                    String modeloMoto = datos[i++];
                    String marcaMoto = datos[i++];
                    String colorMoto = datos[i++];
                    String placaMoto = datos[i++];
                    int cilindrajeMoto = Integer.parseInt(datos[i++]);
                    Motocicleta moto = new Motocicleta(modeloMoto, marcaMoto, colorMoto, placaMoto, cilindrajeMoto);
                    // Servicio
                    String descServicio = datos[i++];
                    double precioServicio = Double.parseDouble(datos[i++]);
                    int duracionServicio = Integer.parseInt(datos[i++]);
                    String tipoServicio = datos[i++];
                    String garantiaServicio = datos[i++];
                    Servicio servicio = new Servicio(descServicio, precioServicio, duracionServicio, tipoServicio, garantiaServicio);
                    // Fechas
                    LocalDate fechaEntrada = LocalDate.parse(datos[i++], FORMATO_FECHA);
                    LocalDate fechaSalida = LocalDate.parse(datos[i++], FORMATO_FECHA);
                    // Técnico
                    String nombreTecnico = datos[i++];
                    String cedulaTecnico = datos[i++];
                    String telefonoTecnico = datos[i++];
                    String especialidadTecnico = datos[i++];
                    int experienciaTecnico = Integer.parseInt(datos[i++]);
                    Tecnico tecnico = new Tecnico(nombreTecnico, cedulaTecnico, telefonoTecnico, especialidadTecnico, experienciaTecnico);
                    // Observaciones y completado
                    String observaciones = datos[i++];
                    boolean completado = Boolean.parseBoolean(datos[i++]);
                    // Cliente
                    String nombreCliente = datos[i++];
                    String telefonoCliente = datos[i++];
                    String direccionCliente = datos[i++];
                    String emailCliente = datos[i++];
                    String documentoCliente = datos[i++];
                    Cliente cliente = new Cliente(nombreCliente, telefonoCliente, direccionCliente, emailCliente, documentoCliente);

                    biblioteca.add(new Ordenes(numeroOrden, moto, servicio, fechaEntrada, fechaSalida, tecnico, observaciones, completado, cliente));
                }
            }
            System.out.println("📂 Datos cargados desde archivo.");
        } catch (Exception e) {
            System.out.println("❌ Error al cargar archivo: " + e.getMessage());
        }
    }

    private static Motocicleta buscarMotocicletaPorModelo(String modelo) {
        if (modelo.equals(Motocicleta.MOTO1.getModelo())) return Motocicleta.MOTO1;
        if (modelo.equals(Motocicleta.MOTO2.getModelo())) return Motocicleta.MOTO2;
        if (modelo.equals(Motocicleta.MOTO3.getModelo())) return Motocicleta.MOTO3;
        if (modelo.equals(Motocicleta.MOTO4.getModelo())) return Motocicleta.MOTO4;
        if (modelo.equals(Motocicleta.MOTO5.getModelo())) return Motocicleta.MOTO5;
        for (Motocicleta m : motocicletasNuevas) if (modelo.equals(m.getModelo())) return m;
        return null;
    }

    private static Servicio buscarServicioPorDescripcion(String desc) {
        if (desc.equals(Servicio.SERVICIO1.getDescripcion())) return Servicio.SERVICIO1;
        if (desc.equals(Servicio.SERVICIO2.getDescripcion())) return Servicio.SERVICIO2;
        if (desc.equals(Servicio.SERVICIO3.getDescripcion())) return Servicio.SERVICIO3;
        if (desc.equals(Servicio.SERVICIO4.getDescripcion())) return Servicio.SERVICIO4;
        if (desc.equals(Servicio.SERVICIO5.getDescripcion())) return Servicio.SERVICIO5;
        for (Servicio s : serviciosNuevos) if (desc.equals(s.getDescripcion())) return s;
        return null;
    }

    private static Tecnico buscarTecnicoPorNombre(String nombre) {
        if (nombre.equals(Tecnico.TECNICO1.getNombre())) return Tecnico.TECNICO1;
        if (nombre.equals(Tecnico.TECNICO2.getNombre())) return Tecnico.TECNICO2;
        if (nombre.equals(Tecnico.TECNICO3.getNombre())) return Tecnico.TECNICO3;
        if (nombre.equals(Tecnico.TECNICO4.getNombre())) return Tecnico.TECNICO4;
        if (nombre.equals(Tecnico.TECNICO5.getNombre())) return Tecnico.TECNICO5;
        for (Tecnico t : tecnicosNuevos) if (nombre.equals(t.getNombre())) return t;
        return null;
    }

    private static Cliente buscarClientePorNombre(String nombre) {
        if (nombre.equals(Cliente.CLIENTE1.getNombre())) return Cliente.CLIENTE1;
        if (nombre.equals(Cliente.CLIENTE2.getNombre())) return Cliente.CLIENTE2;
        if (nombre.equals(Cliente.CLIENTE3.getNombre())) return Cliente.CLIENTE3;
        if (nombre.equals(Cliente.CLIENTE4.getNombre())) return Cliente.CLIENTE4;
        if (nombre.equals(Cliente.CLIENTE5.getNombre())) return Cliente.CLIENTE5;
        for (Cliente c : clientesNuevos) if (nombre.equals(c.getNombre())) return c;
        return null;
    }

    // Métodos auxiliares
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❗ Ingrese un número válido.");
            }
        }
    }

    private static boolean leerBooleano(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String respuesta = entrada.nextLine().trim().toLowerCase();
            if (respuesta.equals("sí") || respuesta.equals("si") || respuesta.equals("true")) return true;
            if (respuesta.equals("no") || respuesta.equals("false")) return false;
            System.out.println("❗ Escribe 'sí' o 'no'.");
        }
    }

    private static LocalDate leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = entrada.nextLine();
            try {
                return LocalDate.parse(texto, FORMATO_FECHA);
            } catch (DateTimeParseException e) {
                System.out.println("❗ Formato incorrecto. Usa dd/MM/yyyy");
            }
        }
    }
} 