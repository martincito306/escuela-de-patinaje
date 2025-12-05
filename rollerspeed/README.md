# 🛼 Club Paen - Sistema de Gestión

Sistema web monolítico para la Escuela de Patinaje Club Paen en Envigado, Antioquia.

## 📋 Requisitos Previos

Antes de arrancar, necesitás tener instalado:

### 1. Java JDK 17

**Opción A: Descargar desde Oracle**
- Andá a: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
- Descargá el instalador para tu sistema operativo
- Instalalo y configurá la variable de entorno `JAVA_HOME`

**Opción B: Usar SDKMAN (Linux/Mac)**
```bash
curl -s "https://get.sdkman.io" | bash
sdk install java 17.0.9-tem
```

**Verificar instalación:**
```bash
java -version
# Debería mostrar algo como: java version "17.0.x"
```

### 2. Maven (Opcional - el proyecto incluye Maven Wrapper)

El proyecto ya trae Maven incluido con el wrapper (`mvnw` / `mvnw.cmd`), pero si querés instalarlo globalmente:

```bash
# Windows: descargá desde https://maven.apache.org/download.cgi
# Linux/Mac con SDKMAN:
sdk install maven
```

### 3. MySQL

**Windows:**
- Descargá MySQL Community Server desde: https://dev.mysql.com/downloads/mysql/
- Instalá usando el instalador (MySQL Installer for Windows)
- Durante la instalación, configurá un usuario `root` con contraseña

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install mysql-server
sudo mysql_secure_installation
```

**Mac:**
```bash
brew install mysql
brew services start mysql
```

**Verificar instalación:**
```bash
mysql --version
```

### 4. VS Code (Recomendado)

**Extensiones necesarias:**
1. **Extension Pack for Java** (Microsoft) - Incluye todo lo necesario para Java
2. **Spring Boot Extension Pack** (VMware) - Para trabajar con Spring Boot
3. **Thunder Client** (Opcional) - Para probar APIs
4. **MySQL** (Opcional) - Para ver la base de datos desde VS Code

**Instalar las extensiones:**
- Abrí VS Code
- Andá a la sección de extensiones (Ctrl+Shift+X)
- Buscá e instalá las extensiones mencionadas arriba

## 🚀 Configuración del Proyecto

### 1. Clonar o descargar el proyecto

Si tenés el código, descomprimilo en una carpeta. Por ejemplo:
```
C:\Users\TuUsuario\proyectos\rollerspeed
```

### 2. Configurar la base de datos

**Paso 1: Crear la base de datos**

Abrí MySQL desde la terminal:
```bash
mysql -u root -p
# Te va a pedir la contraseña que configuraste
```

Ejecutá estos comandos:
```sql
CREATE DATABASE clubpaen CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
SHOW DATABASES;
EXIT;
```

**Paso 2: Configurar las credenciales**

Abrí el archivo `src/main/resources/application.properties` y ajustá estos valores:

```properties
# Cambiá estos valores según tu configuración local
spring.datasource.url=jdbc:mysql://localhost:3306/clubpaen?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=America/Bogota
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD_AQUI    # ← Cambiá esto
```

**Nota:** Si MySQL está en otro puerto o servidor, ajustá la URL también.

### 3. Abrir el proyecto en VS Code

```bash
cd ruta/donde/esta/el/proyecto/rollerspeed
code .
```

VS Code debería detectar automáticamente que es un proyecto Maven/Spring Boot.

## ▶️ Ejecutar la Aplicación

### Opción 1: Desde la terminal (recomendado)

**Windows:**
```bash
cd rollerspeed
.\mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
cd rollerspeed
./mvnw spring-boot:run
```

### Opción 2: Desde VS Code

1. Abrí el archivo `RollerspeedApplication.java`
2. Buscá el botón **"Run"** arriba del método `main`
3. Clickeá en **"Run"** o **"Debug"**

### Opción 3: Usando Maven instalado globalmente

```bash
mvn clean install
mvn spring-boot:run
```

## 🌐 Acceder a la Aplicación

Una vez que arranque (vas a ver en la consola "Started RollerspeedApplication"), abrí tu navegador en:

```
http://localhost:8080
```

### Rutas disponibles:

- **Página principal:** `http://localhost:8080/`
- **Misión:** `http://localhost:8080/mision`
- **Visión:** `http://localhost:8080/vision`
- **Valores:** `http://localhost:8080/valores`
- **Servicios:** `http://localhost:8080/servicios`
- **Eventos:** `http://localhost:8080/eventos`
- **Registro de alumnos:** `http://localhost:8080/registro`

## 🔐 Usuario Administrador (para pruebas)

Por ahora hay un usuario administrador en memoria:

- **Usuario:** admin
- **Contraseña:** admin123

(Esto es solo para pruebas. En producción hay que cambiar esto obligatoriamente)

## 🗃️ Estructura del Proyecto

```
rollerspeed/
├── src/
│   ├── main/
│   │   ├── java/com/rollerspeed/
│   │   │   ├── RollerspeedApplication.java    # Clase principal
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java        # Configuración de seguridad
│   │   │   ├── controller/
│   │   │   │   ├── InstitucionalController.java  # Rutas públicas
│   │   │   │   └── RegistroController.java       # Registro de alumnos
│   │   │   ├── model/
│   │   │   │   └── Alumno.java                # Entidad Alumno
│   │   │   ├── repository/
│   │   │   │   └── AlumnoRepository.java      # Acceso a datos
│   │   │   └── service/
│   │   │       └── AlumnoService.java         # Lógica de negocio
│   │   └── resources/
│   │       ├── application.properties         # Configuración
│   │       └── templates/                     # Vistas HTML
│   │           ├── fragments/                 # Componentes reutilizables
│   │           │   ├── head.html
│   │           │   └── navbar.html
│   │           ├── index.html                 # Página principal
│   │           ├── mision.html
│   │           ├── vision.html
│   │           ├── valores.html
│   │           ├── servicios.html
│   │           ├── eventos.html
│   │           ├── registro.html
│   │           └── confirmacion.html
│   └── test/                                  # Tests (por ahora vacíos)
└── pom.xml                                    # Dependencias Maven
```

## 📦 Tecnologías Usadas

- **Spring Boot 3.4.2** - Framework principal
- **Java 17** - Lenguaje
- **Spring MVC** - Patrón arquitectónico
- **Thymeleaf** - Motor de plantillas para HTML
- **Spring Data JPA** - Acceso a base de datos
- **MySQL** - Base de datos
- **Spring Security** - Autenticación y autorización
- **Bootstrap 5.3.2** - Framework CSS
- **Font Awesome 6.4.0** - Íconos
- **Maven** - Gestor de dependencias

## 🐛 Solución de Problemas Comunes

### Error: "Could not find or load main class"
- Verificá que `JAVA_HOME` esté configurado correctamente
- Ejecutá: `.\mvnw.cmd clean install` y volvé a intentar

### Error: "Access denied for user 'root'@'localhost'"
- Revisá que la contraseña en `application.properties` sea correcta
- Verificá que MySQL esté corriendo: `mysql -u root -p`

### Error: "Port 8080 is already in use"
- Otro programa está usando el puerto 8080
- Opción 1: Cerrá ese programa
- Opción 2: Cambiá el puerto en `application.properties`:
  ```properties
  server.port=8081
  ```

### La aplicación arranca pero no veo cambios en el navegador
- Limpiá la caché del navegador (Ctrl+Shift+R)
- O abrí en ventana privada/incógnito

### No se crean las tablas en la base de datos
- Verificá que `spring.jpa.hibernate.ddl-auto=update` esté en `application.properties`
- Revisá los logs de la consola buscando errores de SQL

## 📚 Próximos Pasos (Para Expandir el MVP)

Este es un MVP funcional. Para hacerlo más completo podrías agregar:

1. **Panel de administración** para gestionar alumnos, instructores y pagos
2. **Dashboard para instructores** para ver sus clases y tomar asistencia
3. **Sistema de autenticación completo** con registro de usuarios
4. **Módulo de pagos** con integración a pasarelas de pago
5. **Reportes y estadísticas**
6. **Sistema de notificaciones** por email/SMS
7. **Calendario de clases** interactivo
8. **Galería de fotos** funcional con subida de imágenes
9. **Blog de noticias** con CRUD completo
10. **API REST** para futura app móvil

## 👨‍💻 Comandos Útiles

```bash
# Limpiar y compilar
.\mvnw.cmd clean compile

# Ejecutar tests (cuando los haya)
.\mvnw.cmd test

# Crear el JAR ejecutable
.\mvnw.cmd clean package

# Ejecutar el JAR generado
java -jar target/rollerspeed-0.0.1-SNAPSHOT.jar

# Ver dependencias
.\mvnw.cmd dependency:tree
```

## 📞 Contacto

Si tenés dudas o problemas, revisá:
- Los logs de la consola
- La documentación de Spring Boot: https://spring.io/projects/spring-boot
- Stack Overflow: https://stackoverflow.com/questions/tagged/spring-boot

---

¡Listo! Con esto deberías tener el sistema corriendo. Cualquier duda, revisá los comentarios en el código que están hechos para ser claros y fáciles de entender. 🚀
