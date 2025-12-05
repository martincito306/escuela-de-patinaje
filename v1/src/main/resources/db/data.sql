-- ========================================
-- DATOS DE PRUEBA SIMPLIFICADOS PARA CLUB PAEN
-- Base de datos: patinajetdea
-- Solo programas
-- ========================================

-- ========================================
-- PROGRAMAS (5 programas)
-- ========================================
INSERT INTO programas (nombre, descripcion, nivel, edad_minima, edad_maxima, duracion_meses, costo_mensual, costo_matricula, dias_semana, icono, activo) VALUES
('Patinaje Infantil', 'Programa diseñado para niños que inician en el patinaje. Aprenden fundamentos básicos, equilibrio y técnicas de desplazamiento en un ambiente divertido y seguro.', 'Principiante', 5, 10, 6, 120000.00, 80000.00, 'Lunes, Miércoles, Viernes', 'fa-child', TRUE),
('Patinaje Juvenil', 'Programa para jóvenes con fundamentos básicos. Se trabaja técnica, velocidad y preparación para competencias recreativas.', 'Básico', 11, 16, 6, 150000.00, 100000.00, 'Martes, Jueves, Sábado', 'fa-skating', TRUE),
('Patinaje Adultos', 'Clases para adultos principiantes o recreativos. Enfoque en salud, diversión y aprendizaje de patinaje como actividad deportiva.', 'Principiante', 17, 65, 6, 130000.00, 90000.00, 'Lunes, Miércoles', 'fa-user', TRUE),
('Entrenamiento Competencia', 'Entrenamiento intensivo para competiciones regionales y nacionales. Requiere nivel avanzado y compromiso de tiempo completo.', 'Competencia', 10, 25, 12, 250000.00, 150000.00, 'Lunes a Viernes', 'fa-trophy', TRUE),
('Patinaje Velocidad', 'Especialización en velocidad sobre ruedas. Técnicas avanzadas de carrera, resistencia y competencia en pista.', 'Avanzado', 12, 30, 12, 220000.00, 120000.00, 'Martes, Jueves, Sábado', 'fa-forward', TRUE);
