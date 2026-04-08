-- Carga Masiva de Equipos
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Guatemala', 'GUA', 'Luis Fernando Tena');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Argentina', 'ARG', 'Lionel Scaloni');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Francia', 'FRA', 'Didier Deschamps');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Brasil', 'BRA', 'Dorival Júnior');

-- Carga Masiva de Jugadores (Ejemplos vinculados por ID de equipo)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Iker Mérida', 'Portero', 1, 1);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Lionel Messi', 'Delantero', 10, 2);

-- Partidos de prueba (Fase de Grupos)
-- Asumiendo que ID 1 es Guate, ID 2 Argentina, ID 3 Francia, ID 4 Brasil
INSERT INTO encuentros (local_id, visitante_id, goles_local, goles_visitante, estado) VALUES (1, 2, 0, 0, 'PENDIENTE');

INSERT INTO encuentros (local_id, visitante_id, goles_local, goles_visitante, estado) VALUES (3, 4, 0, 0, 'PENDIENTE');