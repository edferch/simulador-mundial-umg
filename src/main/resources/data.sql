-- Paises
INSERT INTO paises (nombre) VALUES ('México');
INSERT INTO paises (nombre) VALUES ('Sudáfrica');
INSERT INTO paises (nombre) VALUES ('Corea del Sur');
INSERT INTO paises (nombre) VALUES ('Chequia');
INSERT INTO paises (nombre) VALUES ('Canadá');
INSERT INTO paises (nombre) VALUES ('Bosnia y Herzegovina');
INSERT INTO paises (nombre) VALUES ('Catar');
INSERT INTO paises (nombre) VALUES ('Suiza');
INSERT INTO paises (nombre) VALUES ('Brasil');
INSERT INTO paises (nombre) VALUES ('Marruecos');
INSERT INTO paises (nombre) VALUES ('Haití');
INSERT INTO paises (nombre) VALUES ('Escocia');
INSERT INTO paises (nombre) VALUES ('Estados Unidos');
INSERT INTO paises (nombre) VALUES ('Paraguay');
INSERT INTO paises (nombre) VALUES ('Australia');
INSERT INTO paises (nombre) VALUES ('Turquía');
INSERT INTO paises (nombre) VALUES ('Alemania');
INSERT INTO paises (nombre) VALUES ('Curazao');
INSERT INTO paises (nombre) VALUES ('Costa de Marfil');
INSERT INTO paises (nombre) VALUES ('Ecuador');
INSERT INTO paises (nombre) VALUES ('Países Bajos');
INSERT INTO paises (nombre) VALUES ('Japón');
INSERT INTO paises (nombre) VALUES ('Suecia');
INSERT INTO paises (nombre) VALUES ('Túnez');
INSERT INTO paises (nombre) VALUES ('Bélgica');
INSERT INTO paises (nombre) VALUES ('Egipto');
INSERT INTO paises (nombre) VALUES ('Irán');
INSERT INTO paises (nombre) VALUES ('Nueva Zelanda');
INSERT INTO paises (nombre) VALUES ('España');
INSERT INTO paises (nombre) VALUES ('Cabo Verde');
INSERT INTO paises (nombre) VALUES ('Arabia Saudita');
INSERT INTO paises (nombre) VALUES ('Uruguay');
INSERT INTO paises (nombre) VALUES ('Francia');
INSERT INTO paises (nombre) VALUES ('Senegal');
INSERT INTO paises (nombre) VALUES ('Irak');
INSERT INTO paises (nombre) VALUES ('Noruega');
INSERT INTO paises (nombre) VALUES ('Argentina');
INSERT INTO paises (nombre) VALUES ('Argelia');
INSERT INTO paises (nombre) VALUES ('Austria');
INSERT INTO paises (nombre) VALUES ('Jordania');
INSERT INTO paises (nombre) VALUES ('Portugal');
INSERT INTO paises (nombre) VALUES ('RD del Congo');
INSERT INTO paises (nombre) VALUES ('Uzbekistán');
INSERT INTO paises (nombre) VALUES ('Colombia');
INSERT INTO paises (nombre) VALUES ('Inglaterra');
INSERT INTO paises (nombre) VALUES ('Croacia');
INSERT INTO paises (nombre) VALUES ('Ghana');
INSERT INTO paises (nombre) VALUES ('Guatemala');


INSERT INTO fases (descripcion) VALUES ('Grupos'), ('Octavos'), ('Cuartos'), ('Semifinal'), ('Final');
INSERT INTO posiciones (descripcion) VALUES ('Portero'), ('Defensa'), ('Mediocampista'), ('Delantero');
INSERT INTO estadios (nombre, ciudad) VALUES ('Estadio Doroteo Guamuch', 'Ciudad de Guatemala'), ('Lusail', 'Doha');

--Selecciones
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('México', 1, 'A');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Sudáfrica', 2, 'A');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Corea del Sur', 3, 'A');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Chequia', 4, 'A');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Canadá', 5, 'B');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Bosnia y Herzegovina', 6, 'B');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Catar', 7, 'B');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Suiza', 8, 'B');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Brasil', 9, 'C');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Marruecos', 10, 'C');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Haití', 11, 'C');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Escocia', 12, 'C');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Estados Unidos', 13, 'D');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Paraguay', 14, 'D');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Australia', 15, 'D');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Turquía', 16, 'D');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Alemania', 17, 'E');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Curazao', 18, 'E');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Costa de Marfil', 19, 'E');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Ecuador', 20, 'E');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Países Bajos', 21, 'F');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Japón', 22, 'F');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Suecia', 23, 'F');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Túnez', 24, 'F');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Bélgica', 25, 'G');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Egipto', 26, 'G');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Irán', 27, 'G');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Nueva Zelanda', 28, 'G');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('España', 29, 'H');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Cabo Verde', 30, 'H');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Arabia Saudita', 31, 'H');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Uruguay', 32, 'H');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Francia', 33, 'I');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Senegal', 34, 'I');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Irak', 35, 'I');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Noruega', 36, 'I');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Argentina', 37, 'J');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Argelia', 38, 'J');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Austria', 39, 'J');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Jordania', 40, 'J');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Portugal', 41, 'K');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('RD del Congo', 42, 'K');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Uzbekistán', 43, 'K');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Colombia', 44, 'K');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Inglaterra', 45, 'L');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Croacia', 46, 'L');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Ghana', 47, 'L');
INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES ('Guatemala', 48, 'L');

--Jugadores
--Mexico
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Luis Malagón', 1, 1, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Johan Vásquez', 2, 1, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('César Montes', 3, 1, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jesús Gallardo', 4, 1, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Israel Reyes', 5, 1, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Edson Álvarez', 6, 1, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Marcel Ruiz', 7, 1, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Roberto Alvarado', 11, 1, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Hirving Lozano', 8, 1, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Raúl Jiménez', 9, 1, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alexis Vega', 10, 1, 4);

--Sudafrica
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ronwen Williams', 1, 2, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Aubrey Modiba', 2, 2, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mothobi Mvala', 3, 2, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Siyabonga Ngezana', 4, 2, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Khuliso Mudau', 5, 2, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Teboho Mokoena', 6, 2, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sphephelo Sithole', 7, 2, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Bathusi Aubaas', 8, 2, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sipho Mbule', 9, 2, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lyle Foster', 10, 2, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Oswin Appollis', 11, 2, 4);

--Corea del Sur
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Hyeonwoo Jo', 1, 3, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Minjae Kim', 2, 3, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Yumin Cho', 3, 3, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Youngwoo Seol', 4, 3, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jaesung Lee', 5, 3, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Inbeom Hwang', 6, 3, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kangin Lee', 7, 3, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jens Castrop', 8, 3, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Heungmin Son', 9, 3, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Heechan Hwang', 10, 3, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Hyeongyu Oh', 11, 3, 4);

--Chequia
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Matěj Kovář', 1, 4, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ladislav Krejčí', 2, 4, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Vladimír Coufal', 3, 4, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jaroslav Zelený', 4, 4, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lukáš Provod', 5, 4, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lukáš Červ', 6, 4, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Tomáš Souček', 7, 4, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Pavel Šulc', 8, 4, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Václav Černý', 9, 4, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Adam Hložek', 10, 4, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Patrik Schick', 11, 4, 4);

--canada
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Dayne St. Clair', 1, 5, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alphonso Davies', 2, 5, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Richie Laryea', 3, 5, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Derek Cornelius', 4, 5, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Niko Sigur', 8, 5, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Stephen Eustáquio', 5, 5, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ismaël Koné', 6, 5, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jacob Shaffelburg', 7, 5, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Tajon Buchanan', 9, 5, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Cyle Larin', 10, 5, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jonathan David', 11, 5, 4);

--bosnia y herzegovina
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nikola Vasilj', 1, 6, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Amar Dedić', 2, 6, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sead Kolašinac', 3, 6, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Tarik Muharemović', 4, 6, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nikola Katić', 5, 6, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Benjamin Tahirović', 6, 6, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ivan Šunjić', 7, 6, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Esmir Bajraktarević', 9, 6, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ermedin Demirović', 8, 6, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Edin Džeko', 10, 6, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Amar Memić', 11, 6, 4);

--qatar
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Meshaal Barsham', 1, 7, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sultan Albrake', 2, 7, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Boualem Khoukhi', 3, 7, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Pedro Miguel', 4, 7, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mohammed Mannai', 5, 7, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Karim Boudiaf', 6, 7, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Assim Madibo', 7, 7, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Edmílson Junior', 8, 7, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Akram Hassan Afif', 9, 7, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ahmed Al-Ganehi', 10, 7, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Almoez Ali', 11, 7, 4);

--suiza
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Gregor Kobel', 1, 8, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Manuel Akanji', 2, 8, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ricardo Rodríguez', 3, 8, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nico Elvedi', 4, 8, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Silvan Widmer', 5, 8, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Granit Xhaka', 6, 8, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Remo Freuler', 7, 8, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Fabian Rieder', 8, 8, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Breel Embolo', 9, 8, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Rubén Vargas', 10, 8, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Dan Ndoye', 11, 8, 4);

--brasil
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alisson', 1, 9, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Marquinhos', 2, 9, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Éder Militão', 3, 9, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Gabriel Magalhães', 4, 9, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Casemiro', 5, 9, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Bruno Guimarães', 6, 9, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Vinícius Júnior', 7, 9, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Rodrygo', 8, 9, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Neymar Jr', 9, 9, 4); -- Reemplazo de Matheus Cunha
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Raphinha', 10, 9, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Estêvão', 11, 9, 4);

--marruecos
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Yassine Bounou', 1, 10, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Achraf Hakimi', 2, 10, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Noussair Mazraoui', 3, 10, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nayef Aguerd', 4, 10, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Romain Saïss', 5, 10, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sofyan Amrabat', 6, 10, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Eliesse Ben Seghir', 7, 10, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Bilal El Khannouss', 8, 10, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ismael Saibari', 9, 10, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Youssef En-Nesyri', 10, 10, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Brahim Díaz', 11, 10, 4);

-- haití
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Johny Placide', 1, 11, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Carlens Arcus', 2, 11, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ricardo Adé', 3, 11, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Duke Lacroix', 4, 11, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Leverton Pierre', 5, 11, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Danley Jean Jacques', 6, 11, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jean-Ricner Bellegarde', 7, 11, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Josué Casimir', 8, 11, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ruben Providence', 9, 11, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Duckens Nazon', 10, 11, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Frantzdy Pierrot', 11, 11, 4);

--escocia
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Angus Gunn', 1, 12, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Aaron Hickey', 2, 12, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Andrew Robertson', 3, 12, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('John Souttar', 4, 12, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Grant Hanley', 5, 12, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Scott McTominay', 6, 12, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lewis Ferguson', 7, 12, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ryan Christie', 8, 12, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('John McGinn', 9, 12, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ché Adams', 10, 12, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ben Gannon-Doak', 11, 12, 4);

--Estados Unidos
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Matt Freese', 1, 13, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Chris Richards', 2, 13, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Tim Ream', 3, 13, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mark McKenzie', 4, 13, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Tyler Adams', 5, 13, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Weston McKennie', 6, 13, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Timothy Weah', 7, 13, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Malik Tillman', 8, 13, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Christian Pulisic', 9, 13, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Brenden Aaronson', 10, 13, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Folarin Balogun', 11, 13, 4);

--Paraguay
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Orlando Gill', 1, 14, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Gustavo Gómez', 2, 14, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Juan José Cáceres', 3, 14, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Omar Alderete', 4, 14, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Júnior Alonso', 5, 14, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Diego Gómez', 6, 14, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Andrés Cubas', 7, 14, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Julio Enciso', 8, 14, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Miguel Almirón', 9, 14, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ramón Sosa', 10, 14, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Antonio Sanabria', 11, 14, 4);

--australia
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mathew Ryan', 1, 15, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Harry Souttar', 2, 15, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Aziz Behich', 3, 15, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Cameron Burgess', 4, 15, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lewis Miller', 5, 15, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jackson Irvine', 6, 15, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Riley McGree', 7, 15, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Aiden O''Neill', 8, 15, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Connor Metcalfe', 9, 15, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kusini Yengi', 10, 15, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nestory Irankunda', 11, 15, 4);

--turquia
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Uğurcan Çakır', 1, 16, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mert Müldür', 2, 16, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Abdülkerim Bardakcı', 3, 16, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Merih Demiral', 4, 16, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ferdi Kadıoğlu', 5, 16, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Hakan Çalhanoğlu', 6, 16, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Orkun Kökçü', 7, 16, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Arda Güler', 8, 16, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Can Uzun', 9, 16, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kerem Aktürkoğlu', 10, 16, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kenan Yıldız', 11, 16, 4);


--alemania
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Marc-André ter Stegen', 1, 17, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Antonio Rüdiger', 2, 17, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jonathan Tah', 3, 17, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('David Raum', 4, 17, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Florian Wirtz', 5, 17, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Joshua Kimmich', 6, 17, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Leon Goretzka', 7, 17, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jamal Musiala', 8, 17, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Serge Gnabry', 9, 17, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kai Havertz', 10, 17, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nick Woltemade', 11, 17, 4);

--curazao
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Eloy Room', 1, 18, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Armando Obispo', 2, 18, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sherel Floranus', 3, 18, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Roshon van Eijma', 4, 18, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Shurandy Sambo', 5, 18, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Livano Comenencia', 6, 18, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Juninho Bacuna', 7, 18, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Leandro Bacuna', 8, 18, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kenji Gorré', 9, 18, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jürgen Locadia', 10, 18, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sontje Hansen', 11, 18, 4);

--costa de marfil
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Yahia Fofana', 1, 19, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ghislain Konan', 2, 19, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Wilfried Singo', 3, 19, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Evan Ndicka', 4, 19, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Willy Boly', 5, 19, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Franck Kessié', 6, 19, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Seko Fofana', 7, 19, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ibrahim Sangaré', 8, 19, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sébastien Haller', 9, 19, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Simon Adingra', 10, 19, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Evann Guessand', 11, 19, 4);

--ecuador
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Hernán Galíndez', 1, 20, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Piero Hincapié', 2, 20, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Pervis Estupiñán', 3, 20, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Willian Pacho', 4, 20, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ángelo Preciado', 5, 20, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kendry Páez', 6, 20, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Moisés Caicedo', 7, 20, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alan Franco', 8, 20, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Pedro Vite', 9, 20, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Gonzalo Plata', 10, 20, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Enner Valencia', 11, 20, 4);

--paises bajos
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Bart Verbruggen', 1, 21, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Virgil van Dijk', 2, 21, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Micky van de Ven', 3, 21, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Denzel Dumfries', 4, 21, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Tijjani Reijnders', 5, 21, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ryan Gravenberch', 6, 21, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Frenkie de Jong', 7, 21, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Xavi Simons', 8, 21, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Memphis Depay', 9, 21, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Donyell Malen', 10, 21, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Cody Gakpo', 11, 21, 4);

--japon
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Zion Suzuki', 1, 22, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Tsuyoshi Watanabe', 2, 22, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kaishu Sano', 3, 22, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ao Tanaka', 4, 22, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Daichi Kamada', 5, 22, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ritsu Doan', 6, 22, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Keito Nakamura', 7, 22, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Takumi Minamino', 8, 22, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Takefusa Kubo', 9, 22, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Shuto Machino', 10, 22, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ayase Ueda', 11, 22, 4);

--suecia
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Victor Johansson', 1, 23, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Isak Hien', 2, 23, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Emil Holm', 3, 23, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Victor Nilsson Lindelöf', 4, 23, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lucas Bergvall', 5, 23, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Yasin Ayari', 6, 23, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Daniel Svensson', 7, 23, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Dejan Kulusevski', 8, 23, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Anthony Elanga', 9, 23, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alexander Isak', 10, 23, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Viktor Gyökeres', 11, 23, 4);

--tunez
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Aymen Dahmen', 1, 24, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Montassar Talbi', 2, 24, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Yassine Meriah', 3, 24, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ali Abdi', 4, 24, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ferjani Sassi', 5, 24, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ellyes Skhiri', 6, 24, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Aïssa Laïdouni', 7, 24, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Hannibal Mejbri', 8, 24, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Naïm Sliti', 9, 24, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Elias Achouri', 10, 24, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Hazem Mastouri', 11, 24, 4);


--belgica
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Thibaut Courtois', 1, 25, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Arthur Theate', 2, 25, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Timothy Castagne', 3, 25, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Maxim De Cuyper', 4, 25, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Youri Tielemans', 5, 25, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kevin De Bruyne', 6, 25, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Amadou Onana', 7, 25, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jérémy Doku', 8, 25, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Charles De Ketelaere', 9, 25, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Leandro Trossard', 10, 25, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Romelu Lukaku', 11, 25, 4);

--egipto
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mohamed El Shenawy', 1, 26, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mohamed Hany', 2, 26, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Yasser Ibrahim', 3, 26, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ramy Rabia', 4, 26, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Marwan Attia', 5, 26, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Zizo', 6, 26, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Hamdy Fathy', 7, 26, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Omar Marmoush', 8, 26, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mohamed Salah', 9, 26, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mostafa Mohamed', 10, 26, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Trezeguet', 11, 26, 4);

--iran
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alireza Beiranvand', 1, 27, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Shoja Khalilzadeh', 2, 27, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Milad Mohammadi', 3, 27, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ramin Rezaeian', 4, 27, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Hossein Kanaani', 5, 27, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Saeid Ezatolahi', 6, 27, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Saman Ghoddos', 7, 27, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mohammad Mohebi', 8, 27, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mehdi Taremi', 9, 27, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sardar Azmoun', 10, 27, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alireza Jahanbakhsh', 11, 27, 4);

--nueva zelanda
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Max Crocombe', 1, 28, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Michael Boxall', 2, 28, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Liberato Cacace', 3, 28, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Tim Payne', 4, 28, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Finn Surman', 5, 28, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Marko Stamenić', 6, 28, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Joe Bell', 7, 28, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sarpreet Singh', 8, 28, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Matthew Garbett', 9, 28, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Chris Wood', 10, 28, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Elijah Just', 11, 28, 4);

--españa
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Unai Simón', 1, 29, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Robin Le Normand', 2, 29, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Dean Huijsen', 3, 29, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Marc Cucurella', 4, 29, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Rodri', 5, 29, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Martín Zubimendi', 6, 29, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Pedri', 7, 29, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Fabián Ruiz', 8, 29, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lamine Yamal', 9, 29, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nico Williams', 10, 29, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mikel Oyarzabal', 11, 29, 4);

--cabo verde
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Vozinha', 1, 30, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Logan Costa', 2, 30, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Pico', 3, 30, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Steven Moreira', 4, 30, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('João Paulo', 5, 30, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kevin Pina', 6, 30, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jamiro Monteiro', 7, 30, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Yannick Semedo', 8, 30, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ryan Mendes', 9, 30, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jovane Cabral', 10, 30, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Dailon Livramento', 11, 30, 4);

--arabia saudita
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nawaf Alaqidi', 1, 31, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Hassan Altambakti', 2, 31, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jehad Thikri', 3, 31, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Saud Abdulhamid', 4, 31, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sami Alnajei', 5, 31, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Abdullah Alkhaibari', 6, 31, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Musab Aljuwayr', 7, 31, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Saleh Abu Alshamat', 10, 31, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Feras Albrikan', 8, 31, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Salem Aldawsari', 9, 31, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Saleh Alshehri', 11, 31, 4);

--uruguay
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sergio Rochet', 1, 32, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('José María Giménez', 2, 32, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ronald Araújo', 3, 32, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sebastián Cáceres', 4, 32, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mathías Olivera', 5, 32, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nahitan Nández', 6, 32, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Federico Valverde', 7, 32, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Rodrigo Bentancur', 8, 32, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Manuel Ugarte', 9, 32, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Luis Suárez', 10, 32, 4); -- Reemplazo de Facundo Pellistri
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Darwin Núñez', 11, 32, 4);


--francia
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mike Maignan', 1, 33, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('William Saliba', 2, 33, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jules Koundé', 3, 33, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Théo Hernández', 4, 33, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Aurélien Tchouaméni', 5, 33, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Eduardo Camavinga', 6, 33, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ousmane Dembélé', 7, 33, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kylian Mbappé', 8, 33, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Bradley Barcola', 9, 33, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Désiré Doué', 10, 33, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Hugo Ekitiké', 11, 33, 4);

--senegal
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Édouard Mendy', 1, 34, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kalidou Koulibaly', 2, 34, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Moussa Niakhaté', 3, 34, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('El Hadji Malick Diouf', 4, 34, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Idrissa Gana Gueye', 5, 34, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Pape Matar Sarr', 6, 34, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Krépin Diatta', 9, 34, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sadio Mané', 7, 34, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Iliman Ndiaye', 8, 34, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ismaïla Sarr', 10, 34, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nicolas Jackson', 11, 34, 4);

--irak
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jalal Hassan', 1, 35, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Hussein Ali', 2, 35, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Akam Hashem', 3, 35, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Merchas Doski', 4, 35, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Zaid Tahseen', 5, 35, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Zidane Iqbal', 6, 35, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Amir Al-Ammari', 7, 35, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ibrahim Bayesh', 8, 35, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Aimar Sher', 10, 35, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ali Jasim', 9, 35, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mohanad Ali', 11, 35, 4);

--noruega
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ørjan Nyland', 1, 36, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Julian Ryerson', 2, 36, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kristoffer Vassbakk Ajer', 3, 36, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('David Møller Wolfe', 4, 36, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Martin Ødegaard', 5, 36, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sander Berge', 6, 36, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Patrick Berg', 7, 36, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Erling Haaland', 8, 36, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Antonio Nusa', 9, 36, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Oscar Bobb', 10, 36, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alexander Sørloth', 11, 36, 4);

--argentina
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Emiliano Martínez', 1, 37, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nahuel Molina', 2, 37, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Cristian Romero', 3, 37, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nicolás Otamendi', 4, 37, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Enzo Fernández', 5, 37, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alexis Mac Allister', 6, 37, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Rodrigo De Paul', 7, 37, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Julián Álvarez', 8, 37, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lionel Messi', 9, 37, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Giuliano Simeone', 10, 37, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lautaro Martínez', 11, 37, 4);

--argelia
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alexis Guendouz', 1, 38, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Rayan Aït-Nouri', 2, 38, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ramy Bensebaini', 3, 38, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Youcef Atal', 4, 38, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Aïssa Mandi', 5, 38, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nabil Bentaleb', 6, 38, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Saïd Benrahma', 8, 38, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Riyad Mahrez', 7, 38, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Amine Gouiri', 9, 38, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mohammed Amoura', 10, 38, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Baghdad Bounedjah', 11, 38, 4);

--austria
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alexander Schlager', 1, 39, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('David Alaba', 2, 39, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kevin Danso', 3, 39, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Philipp Lienhart', 4, 39, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Konrad Laimer', 5, 39, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nicolas Seiwald', 6, 39, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Marcel Sabitzer', 7, 39, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Florian Grillitsch', 8, 39, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Marko Arnautović', 9, 39, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Christoph Baumgartner', 10, 39, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Michael Gregoritsch', 11, 39, 4);

--jordania
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Yazeed Abulaila', 1, 40, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mohammad Abu Hashish', 2, 40, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Yazan Al-Arab', 3, 40, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Abdallah Nasib', 4, 40, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ibrahim Saadeh', 5, 40, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nizar Al-Rashdan', 6, 40, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Noor Al-Rawabdeh', 7, 40, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Yazan Al-Naimat', 8, 40, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mousa Al-Taamari', 9, 40, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mahmoud Al-Mardi', 10, 40, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ali Olwan', 11, 40, 4);


--portugal
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Diogo Costa', 1, 41, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Rúben Dias', 2, 41, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nuno Mendes', 3, 41, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Vitinha', 4, 41, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Bernardo Silva', 5, 41, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Bruno Fernandes', 6, 41, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Rúben Neves', 7, 41, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Cristiano Ronaldo', 8, 41, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('João Félix', 9, 41, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Pedro Neto', 10, 41, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Rafael Leão', 11, 41, 4);

--congo
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lionel Mpasi', 1, 42, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Aaron Wan-Bissaka', 2, 42, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Axel Tuanzebe', 3, 42, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Arthur Masuaku', 4, 42, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Chancel Mbemba', 5, 42, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ngal''ayel Mukau', 6, 42, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Samuel Moutoussamy', 7, 42, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Noah Sadiki', 8, 42, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Théo Bongonda', 9, 42, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Yoane Wissa', 10, 42, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Cédric Bakambu', 11, 42, 4);

--uzbekistan
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Utkir Yusupov', 1, 43, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Abdukodir Khusanov', 2, 43, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Farrukh Sayfiev', 3, 43, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Sherzod Nasrullaev', 4, 43, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Husniddin Aliqulov', 5, 43, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Rustam Ashurmatov', 6, 43, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Khojiakbar Alijonov', 7, 43, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Odiljon Hamrobekov', 8, 43, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Otabek Shukurov', 9, 43, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Eldor Shomurodov', 10, 43, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Abbosbek Fayzullaev', 11, 43, 4);

--colombia
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Camilo Vargas', 1, 44, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Dávinson Sánchez', 2, 44, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Yerry Mina', 3, 44, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Daniel Muñoz', 4, 44, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('James Rodríguez', 5, 44, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jefferson Lerma', 6, 44, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Richard Ríos', 7, 44, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Juan Fernando Quintero', 8, 44, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Luis Díaz', 9, 44, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jhon Arias', 10, 44, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Luis Suárez', 11, 44, 4);

--inglaterra
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jordan Pickford', 1, 45, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Reece James', 2, 45, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('John Stones', 3, 45, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jude Bellingham', 4, 45, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Declan Rice', 5, 45, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jordan Henderson', 6, 45, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Phil Foden', 7, 45, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Harry Kane', 8, 45, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Bukayo Saka', 9, 45, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Cole Palmer', 10, 45, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Marcus Rashford', 11, 45, 4);

--croacia
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Dominik Livaković', 1, 46, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Duje Ćaleta-Car', 2, 46, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Joško Gvardiol', 3, 46, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Josip Stanišić', 4, 46, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ivan Perišić', 5, 46, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Luka Modrić', 6, 46, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mateo Kovačić', 7, 46, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lovro Majer', 8, 46, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mario Pašalić', 9, 46, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Ante Budimir', 10, 46, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Andrej Kramarić', 11, 46, 4);

--gahana
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Lawrence Ati Zigi', 1, 47, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alidu Seidu', 2, 47, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alexander Djiku', 3, 47, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Gideon Mensah', 4, 47, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mohammed Salisu', 5, 47, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Thomas Partey', 6, 47, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Mohammed Kudus', 8, 47, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Abdul Issahaku Fatawu', 7, 47, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Kamaldeen Sulemana', 9, 47, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Jordan Ayew', 10, 47, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Antoine Semenyo', 11, 47, 4);

--guatemala
-- Portero
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Iker Mérida', 1, 48, 1);
-- Defensas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('José Pinto', 2, 48, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('José Morales', 3, 48, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Aaron Herrera', 4, 48, 2);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('José Ardón', 5, 48, 2);
-- Mediocampistas
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Óscar Castellanos', 6, 48, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Alejandro Galindo', 7, 48, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Pedro Altán', 8, 48, 3);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Nathaniel Méndez-Laing', 10, 48, 3);
-- Delanteros
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Saul "Killer" Orozco', 9, 48, 4);
INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES ('Oscar Santis', 11, 48, 4);