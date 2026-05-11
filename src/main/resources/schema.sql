DROP TABLE IF EXISTS alineaciones CASCADE;
DROP TABLE IF EXISTS amonestaciones CASCADE;
DROP TABLE IF EXISTS goles CASCADE;
DROP TABLE IF EXISTS partidos CASCADE;
DROP TABLE IF EXISTS jugadores CASCADE;
DROP TABLE IF EXISTS equipos CASCADE;
DROP TABLE IF EXISTS posiciones CASCADE;
DROP TABLE IF EXISTS fases CASCADE;
DROP TABLE IF EXISTS estadios CASCADE;
DROP TABLE IF EXISTS paises CASCADE;

CREATE TABLE paises (
    id_pais SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE estadios (
    id_estadio SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ciudad VARCHAR(50) NOT NULL
);

CREATE TABLE fases (
    id_fase SERIAL PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE posiciones (
    id_posicion SERIAL PRIMARY KEY,
    descripcion VARCHAR(20) NOT NULL
);

CREATE TABLE equipos (
    id_equipo SERIAL PRIMARY KEY,
    nombre_equipo VARCHAR(50) NOT NULL,
    id_pais INT NOT NULL,
    grupo CHAR(1) NOT NULL,
    CONSTRAINT fk_equipo_pais FOREIGN KEY (id_pais) REFERENCES paises(id_pais)
);

CREATE TABLE jugadores (
    id_jugador SERIAL PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    numero_camiseta INT NOT NULL,
    id_equipo INT NOT NULL,
    id_posicion INT NOT NULL,
    CONSTRAINT fk_jugador_equipo FOREIGN KEY (id_equipo) REFERENCES equipos(id_equipo) ON DELETE CASCADE,
    CONSTRAINT fk_jugador_posicion FOREIGN KEY (id_posicion) REFERENCES posiciones(id_posicion)
);

CREATE TABLE partidos (
    id_partido SERIAL PRIMARY KEY,
    fecha_hora TIMESTAMP NOT NULL,
    id_estadio INT NOT NULL,
    id_fase INT NOT NULL,
    id_equipo_local INT NOT NULL,
    id_equipo_visitante INT NOT NULL,
    goles_local INT DEFAULT 0,
    goles_visitante INT DEFAULT 0,
    estado VARCHAR(50) DEFAULT 'PENDIENTE',
    CONSTRAINT fk_partido_estadio FOREIGN KEY (id_estadio) REFERENCES estadios(id_estadio),
    CONSTRAINT fk_partido_fase FOREIGN KEY (id_fase) REFERENCES fases(id_fase),
    CONSTRAINT fk_partido_local FOREIGN KEY (id_equipo_local) REFERENCES equipos(id_equipo),
    CONSTRAINT fk_partido_visitante FOREIGN KEY (id_equipo_visitante) REFERENCES equipos(id_equipo)
);

CREATE TABLE goles (
    id_partido INT NOT NULL,
    id_jugador INT NOT NULL,
    minuto INT NOT NULL,
    tipo_gol VARCHAR(20),
    PRIMARY KEY (id_partido, id_jugador, minuto),
    CONSTRAINT fk_gol_partido FOREIGN KEY (id_partido) REFERENCES partidos(id_partido) ON DELETE CASCADE,
    CONSTRAINT fk_gol_jugador FOREIGN KEY (id_jugador) REFERENCES jugadores(id_jugador) ON DELETE CASCADE
);

CREATE TABLE amonestaciones (
    id_partido INT NOT NULL,
    id_jugador INT NOT NULL,
    color_tarjeta VARCHAR(10) NOT NULL,
    minuto INT NOT NULL,
    PRIMARY KEY (id_partido, id_jugador, color_tarjeta),
    CONSTRAINT fk_tarjeta_partido FOREIGN KEY (id_partido) REFERENCES partidos(id_partido) ON DELETE CASCADE,
    CONSTRAINT fk_tarjeta_jugador FOREIGN KEY (id_jugador) REFERENCES jugadores(id_jugador) ON DELETE CASCADE
);