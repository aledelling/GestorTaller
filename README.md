# 🏍️ Gestor de Taller de Motocicletas - POO Completo

## 📋 Descripción
Sistema avanzado de gestión de órdenes de trabajo para talleres de reparación de motocicletas. **Implementa los conceptos fundamentales de POO**: Herencia, Polimorfismo, Instanciación y Patrones de Diseño de manera completa y funcional.

## 🎯 Conceptos POO Implementados

### 🏗️ **HERENCIA Y POLIMORFISMO**
- ✅ **3 Jerarquías Completas** con clases abstractas
- ✅ **Polimorfismo Dinámico** en tiempo de ejecución
- ✅ **Métodos Abstractos** y sobrescritura (@Override)
- ✅ **Casting e instanceof** seguros

### 🏭 **INSTANCIACIÓN AVANZADA**
- ✅ **Factory Pattern** - Creación centralizada y validada
- ✅ **Builder Pattern** - Construcción fluida de objetos complejos
- ✅ **Singleton Pattern** - Instancia única thread-safe
- ✅ **Object Pool Pattern** - Reutilización eficiente de objetos

## 🏗️ Arquitectura del Sistema

### **Jerarquías de Herencia**

#### **👥 Jerarquía de Personas**
```
Persona (abstracta)
├── Cliente (direccion, email)
└── Tecnico (especialidad, experiencia, tarifas)
```

#### **🏍️ Jerarquía de Vehículos**
```
Vehiculo (abstracta)
└── Motocicleta (cilindraje, tipoMotor, esDeportiva)
```

#### **🔧 Jerarquía de Servicios**
```
ServicioBase (abstracta)
├── Servicio (tipo, requiereRepuestos)
└── ServicioMantenimiento (kilometraje, preventivo)
```

### **Patrones de Instanciación**

#### **🏭 Factory Pattern**
- `PersonaFactory.java` - Creación centralizada con validaciones
- Creación en lotes y generación automática
- Contadores y estadísticas de creación

#### **🔧 Builder Pattern**
- `MotocicletaBuilder.java` - Construcción fluida paso a paso
- Configuraciones predefinidas (urbana, deportiva, touring)
- Validaciones de negocio integradas

#### **🎯 Singleton Pattern**
- `ConfiguracionSingleton.java` - Configuración global única
- Thread-safe con Double-Checked Locking
- Propiedades tipadas y validación de integridad

#### **🏊 Object Pool Pattern**
- `OrdenesPool.java` - Reutilización eficiente de objetos
- Thread-safe con estadísticas en tiempo real
- 100% eficiencia de reutilización demostrada

## 📦 Estructura del Proyecto
```
/workspace/
├── build.xml                      # Configuración de Ant
├── ordenes.txt                   # Archivo de persistencia
├── README.md                     # Este archivo
├── HERENCIA_POO.md              # Documentación de Herencia
├── INSTANCIACION_POO.md         # Documentación de Instanciación
└── src/gestortaller/
    ├── GestorTaller.java        # Clase principal (600+ líneas)
    │
    ├── Persona.java             # 🆕 Clase abstracta base para personas
    ├── Cliente.java             # ⬆️ Hereda de Persona
    ├── Tecnico.java             # ⬆️ Hereda de Persona
    │
    ├── Vehiculo.java            # 🆕 Clase abstracta base para vehículos
    ├── Motocicleta.java         # ⬆️ Hereda de Vehiculo
    │
    ├── ServicioBase.java        # 🆕 Clase abstracta base para servicios
    ├── Servicio.java            # ⬆️ Hereda de ServicioBase
    ├── ServicioMantenimiento.java # ⬆️ Hereda de Servicio
    │
    ├── PersonaFactory.java      # 🆕 Factory Pattern para personas
    ├── MotocicletaBuilder.java  # 🆕 Builder Pattern para motocicletas
    ├── ConfiguracionSingleton.java # 🆕 Singleton Pattern
    ├── OrdenesPool.java         # 🆕 Object Pool Pattern
    │
    └── Ordenes.java             # Modelo de orden de trabajo
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

## 🎯 Menú Principal Completo

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
10. 🆕 Demostrar Herencia y Polimorfismo
11. 🆕 Demostrar Instanciación POO
0. Salir
```

## 📋 Funcionalidades Principales

### **Gestión de Órdenes**
- ✅ Crear nuevas órdenes de trabajo
- ✅ Visualizar todas las órdenes en tabla formatada
- ✅ Buscar órdenes por número
- ✅ Marcar órdenes como completadas
- ✅ Persistencia automática en archivo

### **Gestión de Entidades con Herencia**
- ✅ **Personas**: Clientes y Técnicos con comportamiento especializado
- ✅ **Vehículos**: Motocicletas con cálculos dinámicos
- ✅ **Servicios**: Múltiples tipos con precios y descuentos automáticos
- ✅ Visualización polimórfica en tablas formatadas

### **Creación Avanzada de Objetos**
- ✅ **Factory**: Creación centralizada con validaciones
- ✅ **Builder**: Construcción fluida de motocicletas complejas
- ✅ **Singleton**: Configuración global thread-safe
- ✅ **Object Pool**: Reutilización eficiente de órdenes

### **Características del Sistema**
- 🎨 Interfaz de consola con emojis y formato visual
- 📅 Manejo de fechas con validación (formato dd/MM/yyyy)
- 🔒 Validación de entrada de datos
- 💾 Persistencia en archivo de texto
- 🚀 Datos predefinidos listos para usar
- 📊 Estadísticas automáticas de todos los patrones

## 🎭 Demostraciones Interactivas

### **Opción 10: Herencia y Polimorfismo**
```
🎯 === DEMOSTRACIÓN DE HERENCIA Y POLIMORFISMO ===

👥 POLIMORFISMO CON PERSONAS:
🔹 Cliente: Nombre: Juan Pérez... - Email: juan@email.com
🔹 Técnico: Nombre: Pedro Ramírez... - Especialidad: Motor
   💼 Tarifa por hora: $23000
   📊 Nivel: Senior

🏍️ POLIMORFISMO CON VEHÍCULOS:
🔹 Motocicleta Deportiva: Yamaha MT-07 2020 - Azul
   💰 Costo mantenimiento: $95068
   📋 Licencia requerida: A
```

### **Opción 11: Instanciación POO**
```
🏭 === DEMOSTRACIÓN DE INSTANCIACIÓN POO ===

🏭 1. FACTORY PATTERN - Creación Centralizada
🏭 Factory: Creando Cliente #1 - Ana Silva
✅ Cliente creado exitosamente

🔧 2. BUILDER PATTERN - Construcción Fluida
🔧 Builder: Iniciando construcción de Honda CBF190R
✅ Motocicleta construida paso a paso

🎯 3. SINGLETON PATTERN - Instancia Única
✅ Misma instancia: true
💰 Precio hora base: $15000

🏊 4. OBJECT POOL PATTERN - Reutilización
📊 Eficiencia de reutilización: 100.0%
```

## 📊 Datos Predefinidos Incluidos

### **Personas (Herencia)**
- **5 Clientes**: Juan Pérez, María García, Carlos López, Ana Martínez, Luis Rodríguez
- **5 Técnicos**: Pedro Ramírez (Motor), Sandra Torres (Frenos), Miguel Vargas (Electricidad), etc.

### **Vehículos (Herencia)**
- **5 Motocicletas**: Yamaha MT-07, Honda CBR600RR, Kawasaki Ninja 400, KTM Duke 390, Yamaha R15

### **Servicios (Herencia)**
- **5 Servicios**: Cambio de aceite, Revisión de frenos, Ajuste de cadena, Cambio de llantas, Lavado

## 🔧 Estado del Proyecto

| Aspecto | Estado | Detalles |
|---------|--------|----------|
| **Compilación** | ✅ Sin errores | Todas las clases compilando correctamente |
| **Herencia** | ✅ Completa | 3 jerarquías con polimorfismo funcional |
| **Instanciación** | ✅ Completa | 4 patrones implementados y funcionando |
| **Funcionalidad** | ✅ 100% Operativa | 11 opciones del menú funcionando |
| **Documentación** | ✅ Completa | README + 2 documentos especializados |
| **Persistencia** | ✅ Operativa | Guardado/carga desde archivo |

## 📚 Documentación Especializada

- **[HERENCIA_POO.md](HERENCIA_POO.md)** - Documentación completa de Herencia y Polimorfismo
- **[INSTANCIACION_POO.md](INSTANCIACION_POO.md)** - Documentación detallada de Patrones de Instanciación

## 🎓 Conceptos POO Demostrados

### **Herencia**
- ✅ Clases abstractas (`abstract`)
- ✅ Herencia (`extends`)
- ✅ Métodos abstractos obligatorios
- ✅ Sobrescritura (`@Override`)
- ✅ Atributos protegidos (`protected`)
- ✅ Constructores con `super()`

### **Polimorfismo**
- ✅ Mismo método, comportamiento diferente
- ✅ Casting e `instanceof`
- ✅ Polimorfismo en arrays/listas
- ✅ Binding dinámico en tiempo de ejecución

### **Instanciación**
- ✅ Factory Pattern con validaciones
- ✅ Builder Pattern fluido
- ✅ Singleton thread-safe
- ✅ Object Pool eficiente

## 💡 Próximas Mejoras Sugeridas
1. **Base de Datos**: Migrar a SQLite o PostgreSQL
2. **Interfaz Gráfica**: Implementar GUI con JavaFX
3. **API REST**: Crear servicios web
4. **Testing**: Implementar pruebas unitarias con JUnit
5. **Microservicios**: Dividir en módulos independientes

## 📄 Licencia
Proyecto educativo - Uso libre para aprendizaje de POO

## 👨‍💻 Desarrollo
Proyecto desarrollado como ejemplo completo de **Programación Orientada a Objetos** en Java, implementando:
- **Herencia** y **Polimorfismo** completos
- **4 Patrones de Instanciación** esenciales
- **Demostraciones interactivas** de todos los conceptos
- **Código completamente funcional** y documentado

**¡El GestorTaller es un ejemplo integral de POO avanzada en Java!** 🚀✨