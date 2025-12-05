-- ========================================
-- DATOS DE PRUEBA SIMPLIFICADOS PARA CLUB PAEN
-- Base de datos: patinajetdea
-- Solo programas
-- ========================================

-- ========================================
-- PROGRAMAS (5 programas)
-- ========================================
INSERT INTO programas (nombre, descripcion, nivel, edad_minima, edad_maxima, duracion_meses, costo_mensual, costo_matricula, dias_semana, icono, activo) VALUES
('Patinaje Infantil', 'Programa dise�ado para ni�os que inician en el patinaje. Aprenden fundamentos b�sicos, equilibrio y t�cnicas de desplazamiento en un ambiente divertido y seguro.', 'Principiante', 5, 10, 6, 120000.00, 80000.00, 'Lunes, Mi�rcoles, Viernes', 'fa-child', TRUE),
('Patinaje Juvenil', 'Programa para j�venes con fundamentos b�sicos. Se trabaja t�cnica, velocidad y preparaci�n para competencias recreativas.', 'B�sico', 11, 16, 6, 150000.00, 100000.00, 'Martes, Jueves, S�bado', 'fa-skating', TRUE),
('Patinaje Adultos', 'Clases para adultos principiantes o recreativos. Enfoque en salud, diversi�n y aprendizaje de patinaje como actividad deportiva.', 'Principiante', 17, 65, 6, 130000.00, 90000.00, 'Lunes, Mi�rcoles', 'fa-user', TRUE),
('Entrenamiento Competencia', 'Entrenamiento intensivo para competiciones regionales y nacionales. Requiere nivel avanzado y compromiso de tiempo completo.', 'Competencia', 10, 25, 12, 250000.00, 150000.00, 'Lunes a Viernes', 'fa-trophy', TRUE),
('Patinaje Velocidad', 'Especializaci�n en velocidad sobre ruedas. T�cnicas avanzadas de carrera, resistencia y competencia en pista.', 'Avanzado', 12, 30, 12, 220000.00, 120000.00, 'Martes, Jueves, S�bado', 'fa-forward', TRUE);

-- ========================================
-- HORARIOS (4 horarios)
-- ========================================
INSERT INTO horarios (dias, hora_inicio, hora_fin, nivel, instructor, programa_id, cupo_maximo, cupo_actual, activo) VALUES
('Lunes y Mi�rcoles', '09:00:00', '10:30:00', 'Principiante', 'Carlos Rodr�guez', 1, 20, 5, TRUE),
('Martes y Jueves', '16:00:00', '17:30:00', 'Intermedio', 'Ana Mar�a L�pez', 2, 15, 8, TRUE),
('Lunes, Mi�rcoles y Viernes', '07:00:00', '09:00:00', 'Avanzado', 'Juan Pablo Henao', 4, 12, 10, TRUE),
('S�bados', '10:00:00', '12:00:00', 'Todos', 'Laura Mart�nez', 1, 25, 15, TRUE);
