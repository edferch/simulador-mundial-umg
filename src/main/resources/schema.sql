DROP TABLE IF EXISTS goles;
DROP TABLE IF EXISTS encuentros;
DROP TABLE IF EXISTS estadios;
DROP TABLE IF EXISTS jugadores;
DROP TABLE IF EXISTS equipos;

CREATE TABLE equipos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    abreviatura VARCHAR(3) NOT NULL,
    entrenador VARCHAR(100),
    grupo VARCHAR(1)
);

CREATE TABLE jugadores (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    posicion VARCHAR(50),
    numero_camiseta INT,
    goles_anotados INT DEFAULT 0,
    goles_recibidos INT DEFAULT 0,
    equipo_id INT NOT NULL,
    CONSTRAINT fk_equipo FOREIGN KEY (equipo_id) REFERENCES equipos(id) ON DELETE CASCADE
);

CREATE TABLE estadios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    capacidad INT
);

CREATE TABLE encuentros (
    id SERIAL PRIMARY KEY,
    local_id INT NOT NULL,
    visitante_id INT NOT NULL,
    estadio_id INT,
    goles_local INT DEFAULT 0,
    goles_visitante INT DEFAULT 0,
    fecha_hora TIMESTAMP,
    estado VARCHAR(50),
    CONSTRAINT fk_local FOREIGN KEY (local_id) REFERENCES equipos(id),
    CONSTRAINT fk_visitante FOREIGN KEY (visitante_id) REFERENCES equipos(id),
    CONSTRAINT fk_estadio FOREIGN KEY (estadio_id) REFERENCES estadios(id)
);

-- Tabla renombrada a 'goles'
CREATE TABLE goles (
    id SERIAL PRIMARY KEY,
    minuto INT NOT NULL,
    jugador_id INT NOT NULL,
    encuentro_id INT NOT NULL,
    CONSTRAINT fk_goles_jugador FOREIGN KEY (jugador_id) REFERENCES jugadores(id),
    CONSTRAINT fk_goles_encuentro FOREIGN KEY (encuentro_id) REFERENCES encuentros(id) ON DELETE CASCADE
);