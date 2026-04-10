-- Anfitriones
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Estados Unidos', 'USA', 'Gregg Berhalter');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('México', 'MEX', 'Jaime Lozano');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Canadá', 'CAN', 'Mauro Biello');
-- Conmebol
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Argentina', 'ARG', 'Lionel Scaloni');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Brasil', 'BRA', 'Dorival Júnior');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Uruguay', 'URU', 'Marcelo Bielsa');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Colombia', 'COL', 'Néstor Lorenzo');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Ecuador', 'ECU', 'Félix Sánchez');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Venezuela', 'VEN', 'Fernando Batista');
-- Concacaf (Ejemplos)
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Guatemala', 'GUA', 'Luis Fernando Tena');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Costa Rica', 'CRC', 'Gustavo Alfaro');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Panamá', 'PAN', 'Thomas Christiansen');
-- UEFA (Ejemplos)
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Francia', 'FRA', 'Didier Deschamps');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Inglaterra', 'ENG', 'Gareth Southgate');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('España', 'ESP', 'Luis de la Fuente');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Alemania', 'GER', 'Julian Nagelsmann');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Portugal', 'POR', 'Roberto Martínez');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Italia', 'ITA', 'Luciano Spalletti');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Países Bajos', 'NED', 'Ronald Koeman');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Croacia', 'CRO', 'Zlatko Dalić');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Bélgica', 'BEL', 'Domenico Tedesco');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Suiza', 'SUI', 'Murat Yakin');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Dinamarca', 'DEN', 'Kasper Hjulmand');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Polonia', 'POL', 'Michał Probierz');
-- África (CAF)
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Marruecos', 'MAR', 'Walid Regragui');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Senegal', 'SEN', 'Aliou Cissé');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Egipto', 'EGY', 'Rui Vitória');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Nigeria', 'NGA', 'José Peseiro');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Argelia', 'ALG', 'Djamel Belmadi');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Camerún', 'CMR', 'Rigobert Song');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Ghana', 'GHA', 'Chris Hughton');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Costa de Marfil', 'CIV', 'Jean-Louis Gasset');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Túnez', 'TUN', 'Jalel Kadri');
-- Asia (AFC)
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Japón', 'JPN', 'Hajime Moriyasu');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Corea del Sur', 'KOR', 'Jürgen Klinsmann');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Irán', 'IRN', 'Amir Ghalenoei');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Arabia Saudita', 'KSA', 'Roberto Mancini');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Australia', 'AUS', 'Graham Arnold');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Qatar', 'QAT', 'Tintín Márquez');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Irak', 'IRQ', 'Jesús Casas');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Emiratos Árabes', 'UAE', 'Paulo Bento');
-- Oceanía (OFC)
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Nueva Zelanda', 'NZL', 'Darren Bazeley');
-- Completando los 48 con otros posibles clasificados
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Gales', 'WAL', 'Rob Page');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Suecia', 'SWE', 'Jon Dahl Tomasson');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Serbia', 'SRB', 'Dragan Stojković');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Mali', 'MLI', 'Éric Chelle');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Perú', 'PER', 'Jorge Fossati');
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Chile', 'CHI', 'Ricardo Gareca');

-- CARGA MASIVA DE JUGADORES (Fase 1)
-- Jugadores de Argentina (ID: 4)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Emiliano Martínez', 'Portero', 23, 4);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Lionel Messi', 'Delantero', 10, 4);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Julián Álvarez', 'Delantero', 9, 4);

-- Jugadores de Guatemala (ID: 10)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Nicholas Hagen', 'Portero', 1, 10);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Rubio Rubín', 'Delantero', 9, 10);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Aaron Herrera', 'Defensa', 2, 10);

-- Jugadores de Francia (ID: 13)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Kylian Mbappé', 'Delantero', 10, 13);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Antoine Griezmann', 'Mediocampista', 7, 13);

-- Jugadores de Brasil (ID: 5)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Vinícius Júnior', 'Delantero', 7, 5);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Alisson Becker', 'Portero', 1, 5);