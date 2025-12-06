-- ========================================
-- USUARIOS DE PRUEBA CON SPRING SECURITY
-- ========================================
-- Contraseñas cifradas con BCrypt:
-- admin123 = $2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy
-- 1234 = $2a$10$Xl0yhvzLIxJCDscn7/XE4.jxlEKqmXwG0bWnQxKBzXf3T7d6p7JKC

-- Usuario ADMIN
INSERT INTO users (username, password, email, nombre, apellido, telefono, rol, activo, fecha_registro) VALUES
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'admin@clubpaen.com', 'Administrador', 'Sistema', '300-000-0000', 'ADMIN', TRUE, NOW());

-- Usuario INSTRUCTOR
INSERT INTO users (username, password, email, nombre, apellido, telefono, rol, activo, fecha_registro) VALUES
('instructor', '$2a$10$Xl0yhvzLIxJCDscn7/XE4.jxlEKqmXwG0bWnQxKBzXf3T7d6p7JKC', 'instructor@clubpaen.com', 'Juan', 'Instructor', '300-111-1111', 'INSTRUCTOR', TRUE, NOW());

-- Usuario ESTUDIANTE
INSERT INTO users (username, password, email, nombre, apellido, telefono, rol, activo, fecha_registro) VALUES
('estudiante', '$2a$10$Xl0yhvzLIxJCDscn7/XE4.jxlEKqmXwG0bWnQxKBzXf3T7d6p7JKC', 'estudiante@clubpaen.com', 'María', 'Estudiante', '300-222-2222', 'ESTUDIANTE', TRUE, NOW());
