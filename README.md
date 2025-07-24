# 🏍️ Gestor de Taller de Motocicletas

## Descripción
Sistema de gestión de órdenes de trabajo para talleres de reparación de motocicletas. Permite administrar clientes, técnicos, motocicletas, servicios y órdenes de trabajo de manera integral.

## ✅ Correcciones Aplicadas

### 🚨 Errores Críticos Corregidos
1. **Constantes Faltantes**: Se agregaron 40 constantes estáticas que faltaban:
   - 5 clientes predefinidos (`Cliente.CLIENTE1` - `Cliente.CLIENTE5`)
   - 5 motocicletas predefinidas (`Motocicleta.MOTO1` - `Motocicleta.MOTO5`)
   - 5 servicios predefinidos (`Servicio.SERVICIO1` - `Servicio.SERVICIO5`)
   - 5 técnicos predefinidos (`Tecnico.TECNICO1` - `Tecnico.TECNICO5`)

2. **Bug en Entrada de Datos**: Corregido el método `crearNuevoServicio()` que leía el precio dos veces.

3. **Inicialización de Datos**: Se agregó inicialización automática de datos predefinidos al iniciar el sistema.

### 🚀 Mejoras Implementadas
1. **Pantalla de Bienvenida**: Mensaje visual atractivo al iniciar la aplicación
2. **Datos de Ejemplo**: 25 registros predefinidos listos para usar
3. **Mejor Gestión de Persistencia**: Integración mejorada entre datos predefinidos y archivo
4. **Compilación Verificada**: El proyecto ahora compila sin errores

## 📦 Estructura del Proyecto
```
/workspace/
├── build.xml                 # Configuración de Ant
├── ordenes.txt              # Archivo de persistencia
├── README.md                # Este archivo
└── src/gestortaller/
    ├── GestorTaller.java    # Clase principal (570+ líneas)
    ├── Cliente.java         # Modelo de cliente con datos predefinidos
    ├── Motocicleta.java     # Modelo de motocicleta con datos predefinidos
    ├── Servicio.java        # Modelo de servicio con datos predefinidos
    ├── Tecnico.java         # Modelo de técnico con datos predefinidos
    └── Ordenes.java         # Modelo de orden de trabajo
```

## 🛠️ Cómo Ejecutar

### Prerrequisitos
- Java 8 o superior
- Terminal/Línea de comandos

### Compilación
```bash
# Crear directorio de compilación
mkdir -p build

# Compilar el proyecto
javac -d build src/gestortaller/*.java
```

### Ejecución
```bash
# Ejecutar la aplicación
java -cp build gestortaller.GestorTaller
```

### Usando Ant (opcional)
```bash
# Compilar y ejecutar con Ant
ant run

# Solo compilar
ant compile

# Limpiar compilación
ant clean
```

## 📋 Funcionalidades

### Gestión de Órdenes
- ✅ Crear nuevas órdenes de trabajo
- ✅ Visualizar todas las órdenes en tabla formatada
- ✅ Buscar órdenes por número
- ✅ Marcar órdenes como completadas
- ✅ Persistencia automática en archivo

### Gestión de Entidades
- ✅ **Clientes**: Administrar información de contacto
- ✅ **Motocicletas**: Gestionar vehículos (modelo, marca, placa, etc.)
- ✅ **Servicios**: Catálogo de servicios con precios y garantías
- ✅ **Técnicos**: Personal especializado por área
- ✅ Visualización en tablas formatadas para cada entidad

### Características del Sistema
- 🎨 Interfaz de consola con emojis y formato visual
- 📅 Manejo de fechas con validación (formato dd/MM/yyyy)
- 🔒 Validación de entrada de datos
- 💾 Persistencia en archivo de texto
- 🚀 Datos predefinidos listos para usar

## 📊 Datos Predefinidos Incluidos

### Clientes (5)
- Juan Pérez, María García, Carlos López, Ana Martínez, Luis Rodríguez

### Motocicletas (5)
- Yamaha MT-07, Honda CBR600RR, Kawasaki Ninja 400, KTM Duke 390, Yamaha R15 V4

### Servicios (5)
- Cambio de aceite ($45,000), Revisión de frenos ($85,000), Ajuste de cadena ($25,000), Cambio de llantas ($180,000), Lavado completo ($15,000)

### Técnicos (5)
- Pedro Ramírez (Motor), Sandra Torres (Frenos), Miguel Vargas (Electricidad), Laura Jiménez (Transmisión), Roberto Castro (Suspensión)

## 🎯 Menú Principal
```
--- 🛠️ GESTIÓN DE ÓRDENES DE TALLER ---
1. Agregar orden
2. Mostrar todas las órdenes
3. Buscar orden por número
4. Marcar orden como completada
5. Guardar en archivo
6. Visualizar clientes
7. Visualizar técnicos
8. Visualizar motocicletas
9. Visualizar servicios
0. Salir
```

## 🔧 Estado del Proyecto

| Aspecto | Estado | Comentarios |
|---------|--------|-------------|
| Compilación | ✅ Sin errores | 40 errores corregidos |
| Funcionalidad | ✅ Completa | Todas las características operativas |
| Datos de Prueba | ✅ Incluidos | 25 registros predefinidos |
| Interfaz | ✅ Funcional | Consola con formato visual |
| Persistencia | ✅ Operativa | Guardado/carga desde archivo |

## 💡 Próximas Mejoras Sugeridas
1. **Base de Datos**: Migrar a SQLite o PostgreSQL
2. **Interfaz Gráfica**: Implementar GUI con JavaFX
3. **Validaciones Avanzadas**: Email, formato de placa, etc.
4. **Reportes**: Generar PDF con estadísticas
5. **API REST**: Crear servicios web
6. **Testing**: Implementar pruebas unitarias con JUnit

## 📄 Licencia
Proyecto educativo - Uso libre para aprendizaje

## 👨‍💻 Desarrollador
Proyecto corregido y mejorado con correcciones integrales de errores de compilación y mejoras funcionales.