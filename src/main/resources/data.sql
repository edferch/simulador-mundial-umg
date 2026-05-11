-- 1. Llenar los Catálogos primero
INSERT INTO paises (nombre) VALUES ('Guatemala'), ('Argentina'), ('Brasil'), ('Francia');
INSERT INTO fases (descripcion) VALUES ('Grupos'), ('Octavos'), ('Cuartos'), ('Semifinal'), ('Final');
INSERT INTO posiciones (descripcion) VALUES ('Portero'), ('Defensa'), ('Mediocampista'), ('Delantero');
INSERT INTO estadios (nombre, ciudad) VALUES ('Estadio Doroteo Guamuch', 'Ciudad de Guatemala'), ('Lusail', 'Doha');

-- 2. Insertar Equipos (Usando el id_pais que creamos arriba)
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Selección de Guatemala', 1, 'A');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Selección de Argentina', 2, 'A');

-- 3. Insertar Jugadores (Usando el id_equipo y el id_posicion)
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nicholas Hagen', 1, 1, 1);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lionel Messi', 10, 2, 4);