# 📋 Sistema de Gestión de Usuarios - Club Paen

## 🎯 Descripción General

Sistema web desarrollado con **Spring Boot 3.x**, **MySQL** y **Thymeleaf** para gestionar usuarios del Club Paen - Escuela de Patinaje. Permite registrar y listar usuarios con una interfaz moderna y responsive.

---

## 📁 Estructura del Proyecto

```
v1/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/patinaje/v1/
│   │   │       ├── model/
│   │   │       │   └── Usuario.java          ✅ MODIFICADO
│   │   │       ├── repository/
│   │   │       │   └── UsuarioRepository.java ✅ EXISTENTE
│   │   │       ├── controller/
│   │   │       │   ├── UsuarioController.java ✅ NUEVO
│   │   │       │   └── HomeController.java
│   │   │       └── V1Application.java
│   │   └── resources/
│   │       ├── application.properties         ✅ ACTUALIZADO
│   │       └── templates/
│   │           ├── index.html
│   │           ├── usuarios-list.html         ✅ NUEVO
│   │           ├── usuarios-form.html         ✅ NUEVO
│   │           └── fragments/
│   │               ├── head.html
│   │               ├── navbar.html            ✅ MODIFICADO
│   │               ├── footer.html
│   │               └── scripts.html
└── pom.xml
```

---

## 🔧 Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 4.0.0**
- **Spring Data JPA**
- **Spring Web**
- **Thymeleaf**
- **MySQL 8.x**
- **Bootstrap 5.3.2**
- **Font Awesome 6.4.0**
- **Maven**

---

## 📦 Modelo de Datos: Usuario

### Tabla: `users`

| Campo          | Tipo          | Descripción                    | Restricciones       |
|----------------|---------------|--------------------------------|---------------------|
| `id`           | BIGINT        | ID único del usuario           | PK, AUTO_INCREMENT  |
| `first_name`   | VARCHAR(100)  | Nombre del usuario             | NOT NULL            |
| `last_name`    | VARCHAR(100)  | Apellido del usuario           | NOT NULL            |
| `email`        | VARCHAR(100)  | Correo electrónico             | NOT NULL, UNIQUE    |
| `handle`       | VARCHAR(50)   | Identificador (@usuario o doc) | Opcional            |
| `fecha_registro` | DATETIME    | Fecha de registro automática   | AUTO                |
| `activo`       | BOOLEAN       | Estado del usuario             | Default TRUE        |

### Código de la Entidad

```java
@Entity
@Table(name = "users")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(length = 50)
    private String handle;
    
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
    
    @Column(name = "activo")
    private Boolean activo = true;
    
    // Getters, setters, constructores...
}
```

---

## 🚀 Rutas del Sistema

### Rutas Principales

| Método | Ruta              | Descripción                        | Vista                |
|--------|-------------------|------------------------------------|----------------------|
| GET    | `/`               | Página de inicio                   | `index.html`         |
| GET    | `/usuarios`       | Lista todos los usuarios           | `usuarios-list.html` |
| GET    | `/usuarios/nuevo` | Formulario de registro             | `usuarios-form.html` |
| POST   | `/usuarios`       | Guarda un nuevo usuario            | Redirige a `/usuarios` |
| GET    | `/usuarios/eliminar/{id}` | Elimina un usuario    | Redirige a `/usuarios` |

### Navegación del Navbar

- **Inicio** → `/`
- **Programas** → `/programas`
- **Horarios** → `/horarios`
- **Galería** → `/galeria`
- **Contacto** → `/contacto`
- **Usuarios registrados** → `/usuarios` ✅ NUEVO

---

## 🎨 Interfaces de Usuario

### 1. Lista de Usuarios (`usuarios-list.html`)

**Características:**
- ✅ Tabla con columnas: `#`, `First`, `Last`, `Handle`
- ✅ Si no hay `handle`, muestra el `email`
- ✅ Botón "Registrar Nuevo Usuario"
- ✅ Botón de eliminar por usuario
- ✅ Contador total de usuarios
- ✅ Mensajes de éxito/error con alertas Bootstrap
- ✅ Diseño responsive con Bootstrap 5

**Ejemplo de tabla:**

```
| # | First  | Last   | Handle        | Acciones |
|---|--------|--------|---------------|----------|
| 1 | Juan   | Pérez  | @juanp        | 🗑️      |
| 2 | María  | López  | maria@mail.com| 🗑️      |
| 3 | Carlos | Ruiz   | @carlosr      | 🗑️      |
```

### 2. Formulario de Registro (`usuarios-form.html`)

**Campos del formulario:**
1. **Nombre** (obligatorio) - Texto
2. **Apellido** (obligatorio) - Texto
3. **Email** (obligatorio, único) - Email
4. **Handle** (opcional) - Texto

**Validaciones:**
- ✅ Email único (verifica en BD antes de guardar)
- ✅ Campos obligatorios con validación HTML5
- ✅ Mensajes de error informativos

---

## 🔌 Controlador: UsuarioController

### Métodos Principales

```java
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    // GET /usuarios - Lista todos los usuarios
    @GetMapping
    public String listarUsuarios(Model model)
    
    // GET /usuarios/nuevo - Muestra formulario
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model)
    
    // POST /usuarios - Guarda usuario
    @PostMapping
    public String guardarUsuario(@ModelAttribute Usuario usuario, 
                                RedirectAttributes redirectAttributes)
    
    // GET /usuarios/eliminar/{id} - Elimina usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id, 
                                 RedirectAttributes redirectAttributes)
}
```

### Funcionalidades

- ✅ **Validación de email único** antes de guardar
- ✅ **Mensajes flash** para éxito/error
- ✅ **Redirección automática** después de guardar
- ✅ **Confirmación JavaScript** antes de eliminar

---

## ⚙️ Configuración: application.properties

```properties
spring.application.name=v1
server.port=8085

# ========================================
# CONFIGURACION MYSQL
# ========================================
spring.datasource.url=jdbc:mysql://localhost:3306/patinajetdea?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=patroclo_mendez
spring.datasource.password=empandasconaji
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ========================================
# CONFIGURACION JPA/HIBERNATE
# ========================================
spring.jpa.hibernate.ddl-auto=update  # 🔥 CREA TABLA AUTOMÁTICAMENTE
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### ⚠️ IMPORTANTE: Base de Datos

**Opción 1: Usar base de datos actual (recomendado)**
```sql
-- Ya está configurado para: patinajetdea
-- No necesitas hacer nada, la tabla se creará automáticamente
```

**Opción 2: Crear nueva base de datos club_paen_db**
```sql
CREATE DATABASE IF NOT EXISTS club_paen_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Luego cambia en application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/club_paen_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
```

---

## 🏃 Cómo Ejecutar la Aplicación

### Prerequisitos

1. **MySQL** corriendo en puerto 3306
2. **Java 21** instalado
3. **Maven** (incluido con mvnw)

### Pasos

```powershell
# 1. Ir al directorio del proyecto
cd C:\Users\Marti\OneDrive\Desktop\trabajo\escuela-de-patinaje\v1

# 2. Compilar el proyecto (si no está compilado)
$env:JAVA_HOME="C:\Users\Marti\.vscode\extensions\redhat.java-1.50.0-win32-x64\jre\21.0.9-win32-x86_64"
.\mvnw.cmd clean compile -DskipTests

# 3. Ejecutar la aplicación (presiona F5 en VS Code o usa):
.\mvnw.cmd spring-boot:run

# 4. La aplicación estará disponible en:
# http://localhost:8085
```

### URLs de Acceso

- 🏠 **Inicio:** http://localhost:8085/
- 👥 **Lista de usuarios:** http://localhost:8085/usuarios
- ➕ **Registrar usuario:** http://localhost:8085/usuarios/nuevo

---

## 🧪 Pruebas de Funcionalidad

### Test 1: Verificar que la tabla se crea automáticamente

```sql
-- Conéctate a MySQL y verifica:
USE patinajetdea;
SHOW TABLES;
-- Deberías ver la tabla 'users'

DESCRIBE users;
-- Deberías ver todos los campos definidos
```

### Test 2: Registrar un usuario

1. Ve a http://localhost:8085/usuarios
2. Clic en "Registrar Nuevo Usuario"
3. Completa el formulario:
   - Nombre: Juan
   - Apellido: Pérez
   - Email: juan.perez@example.com
   - Handle: @juanp
4. Clic en "Guardar Usuario"
5. Verás el usuario en la tabla

### Test 3: Validación de email único

1. Intenta registrar otro usuario con el mismo email
2. Deberías ver un mensaje de error: "El email ya está registrado"

### Test 4: Eliminar usuario

1. En la tabla, clic en el botón 🗑️
2. Confirma la eliminación
3. El usuario desaparece de la lista

---

## 📝 Cambios Realizados en el Proyecto

### Archivos MODIFICADOS

1. **`Usuario.java`**
   - ✅ Cambió tabla de `usuarios` a `users`
   - ✅ Cambió campo `nombre` a `firstName` y `lastName`
   - ✅ Eliminó campo `password`
   - ✅ Agregó campo `handle`
   - ✅ Mantiene `email`, `fechaRegistro`, `activo`

2. **`navbar.html`**
   - ✅ Agregó enlace "Usuarios registrados" → `/usuarios`
   - ✅ Eliminó enlaces duplicados y mal formados

3. **`application.properties`**
   - ✅ Agregó comentario sobre `club_paen_db`
   - ✅ Mantiene configuración actual de `patinajetdea`

### Archivos NUEVOS

1. **`UsuarioController.java`**
   - ✅ Gestiona todas las rutas de usuarios
   - ✅ CRUD completo: listar, crear, eliminar

2. **`usuarios-list.html`**
   - ✅ Tabla con diseño similar al solicitado
   - ✅ Columnas: #, First, Last, Handle
   - ✅ Integrada con fragments de navbar y footer

3. **`usuarios-form.html`**
   - ✅ Formulario de registro con 4 campos
   - ✅ Validaciones HTML5 y backend
   - ✅ Diseño moderno con Bootstrap 5

### Archivos SIN CAMBIOS

- ✅ `UsuarioRepository.java` - Ya tenía los métodos necesarios
- ✅ `HomeController.java` - Mantiene ruta `/`
- ✅ Otros controladores, modelos y repositories

---

## 🎯 Características Implementadas

### ✅ Funcionalidades Completadas

- [x] Entidad `Usuario` con campos: id, firstName, lastName, email, handle
- [x] Tabla `users` creada automáticamente por JPA
- [x] Repository con métodos `save()`, `findAll()`, `existsByEmail()`
- [x] Controlador con rutas GET y POST para usuarios
- [x] Vista con tabla HTML estilo Bootstrap
- [x] Formulario de registro con validaciones
- [x] Botón "Usuarios registrados" en navbar
- [x] Redirección automática después de guardar
- [x] Mensajes de éxito/error con flash attributes
- [x] Validación de email único
- [x] Funcionalidad de eliminar usuarios
- [x] Diseño responsive con Bootstrap 5
- [x] Integración con fragments Thymeleaf existentes

### 🎨 Características de UI/UX

- [x] Tabla con columnas: #, First, Last, Handle
- [x] Muestra email si no hay handle
- [x] Contador de usuarios totales
- [x] Botones con iconos Font Awesome
- [x] Alertas de Bootstrap para feedback
- [x] Confirmación JavaScript para eliminar
- [x] Formulario con placeholders y ayudas
- [x] Navegación consistente con el resto del sitio

---

## 🐛 Troubleshooting

### Problema: La tabla no se crea

**Solución:**
```properties
# Verifica en application.properties:
spring.jpa.hibernate.ddl-auto=update  # NO debe ser 'none'
```

### Problema: Error de conexión MySQL

**Solución:**
```powershell
# Verifica que MySQL esté corriendo:
netstat -ano | Select-String ":3306"

# Verifica credenciales en application.properties
```

### Problema: Email duplicado

**Solución:** Es correcto, el sistema valida que cada email sea único. Usa otro email.

---

## 📚 Recursos Adicionales

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
- [Bootstrap 5 Documentation](https://getbootstrap.com/docs/5.3)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)

---

## 👨‍💻 Próximos Pasos (Opcionales)

Si quieres mejorar el sistema, considera:

1. **Editar usuarios**: Agregar ruta GET `/usuarios/editar/{id}` y formulario
2. **Búsqueda**: Filtrar usuarios por nombre o email
3. **Paginación**: Si hay muchos usuarios, agregar paginación
4. **Exportar**: Descargar lista de usuarios en CSV o PDF
5. **Validaciones**: Agregar más validaciones (teléfono, edad, etc.)
6. **Roles**: Implementar roles de usuario (admin, usuario, etc.)
7. **Autenticación**: Agregar login para acceso restringido

---

## 📄 Licencia

Proyecto educativo para Club Paen - Escuela de Patinaje Envigado

---

**✅ Sistema listo para usar! Ejecuta la aplicación y ve a http://localhost:8085/usuarios**
