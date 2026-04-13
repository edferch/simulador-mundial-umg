-- =========================================================
-- 1. REGISTRO DE LAS 48 SELECCIONES (ORDENADAS POR ID)
-- =========================================================
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Canadá', 'CAN', 'Jesse Marsch'); -- ID: 1
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Estados Unidos', 'USA', 'Mauricio Pochettino'); -- ID: 2
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('México', 'MEX', 'Javier Aguirre'); -- ID: 3
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Argentina', 'ARG', 'Lionel Scaloni'); -- ID: 4
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Brasil', 'BRA', 'Dorival Júnior'); -- ID: 5
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Uruguay', 'URU', 'Marcelo Bielsa'); -- ID: 6
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Colombia', 'COL', 'Néstor Lorenzo'); -- ID: 7
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Ecuador', 'ECU', 'Sebastián Beccacece'); -- ID: 8
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Paraguay', 'PAR', 'Gustavo Alfaro'); -- ID: 9
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Alemania', 'GER', 'Julian Nagelsmann'); -- ID: 10
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Bélgica', 'BEL', 'Domenico Tedesco'); -- ID: 11
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Croacia', 'CRO', 'Zlatko Dalić'); -- ID: 12
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Escocia', 'SCO', 'Steve Clarke'); -- ID: 13
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('España', 'ESP', 'Luis de la Fuente'); -- ID: 14
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Francia', 'FRA', 'Didier Deschamps'); -- ID: 15
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Inglaterra', 'ENG', 'Thomas Tuchel'); -- ID: 16
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Noruega', 'NOR', 'Ståle Solbakken'); -- ID: 17
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Países Bajos', 'NED', 'Ronald Koeman'); -- ID: 18
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Portugal', 'POR', 'Roberto Martínez'); -- ID: 19
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Suiza', 'SUI', 'Murat Yakin'); -- ID: 20
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Austria', 'AUT', 'Ralf Rangnick'); -- ID: 21
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Bosnia y Herz.', 'BIH', 'Sergej Barbarez'); -- ID: 22
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('República Checa', 'CZE', 'Ivan Hašek'); -- ID: 23
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Suecia', 'SWE', 'Jon Dahl Tomasson'); -- ID: 24
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Turquía', 'TUR', 'Vincenzo Montella'); -- ID: 25
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Marruecos', 'MAR', 'Walid Regragui'); -- ID: 26
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Senegal', 'SEN', 'Aliou Cissé'); -- ID: 27
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Túnez', 'TUN', 'Jalel Kadri'); -- ID: 28
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Argelia', 'ALG', 'Vladimir Petković'); -- ID: 29
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Egipto', 'EGY', 'Hossam Hassan'); -- ID: 30
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Nigeria', 'NGA', 'Augustine Eguavoen'); -- ID: 31
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Sudáfrica', 'RSA', 'Hugo Broos'); -- ID: 32
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Costa de Marfil', 'CIV', 'Emerse Faé'); -- ID: 33
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Cabo Verde', 'CPV', 'Bubista'); -- ID: 34
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('RD del Congo', 'COD', 'Sébastien Desabre'); -- ID: 35
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Japón', 'JPN', 'Hajime Moriyasu'); -- ID: 36
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Irán', 'IRN', 'Amir Ghalenoei'); -- ID: 37
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Corea del Sur', 'KOR', 'Hong Myung-bo'); -- ID: 38
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Australia', 'AUS', 'Tony Popovic'); -- ID: 39
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Catar', 'QAT', 'Tintín Márquez'); -- ID: 40
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Arabia Saudita', 'KSA', 'Roberto Mancini'); -- ID: 41
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Uzbekistán', 'UZB', 'Srečko Katanec'); -- ID: 42
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Jordania', 'JOR', 'Jamal Sellami'); -- ID: 43
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Irak', 'IRQ', 'Jesús Casas'); -- ID: 44
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Panamá', 'PAN', 'Thomas Christiansen'); -- ID: 45
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Guatemala', 'GUA', 'Luis Fernando Tena'); -- ID: 46
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Curazao', 'CUW', 'Dick Advocaat'); -- ID: 47
INSERT INTO equipos (nombre, abreviatura, entrenador) VALUES ('Nueva Zelanda', 'NZL', 'Darren Bazeley'); -- ID: 48

-- =========================================================
-- 2. CARGA DE JUGADORES (CONCILIADOS CON LOS IDS ANTERIORES)
-- =========================================================
-- Canadá (1)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Alphonso Davies', 'Defensa', 19, 1);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Jonathan David', 'Delantero', 20, 1);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Stephen Eustáquio', 'Mediocampista', 7, 1);
-- Estados Unidos (2)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Christian Pulisic', 'Delantero', 10, 2);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Weston McKennie', 'Mediocampista', 8, 2);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Matt Turner', 'Portero', 1, 2);
-- México (3)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Luis Malagón', 'Portero', 1, 3);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Edson Álvarez', 'Mediocampista', 4, 3);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Santiago Giménez', 'Delantero', 11, 3);
-- Argentina (4)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Emiliano Martínez', 'Portero', 23, 4);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Lionel Messi', 'Delantero', 10, 4);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Julián Álvarez', 'Delantero', 9, 4);
-- Brasil (5)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Alisson Becker', 'Portero', 1, 5);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Vinícius Júnior', 'Delantero', 7, 5);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Rodrygo Goes', 'Delantero', 10, 5);
-- Uruguay (6)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Sergio Rochet', 'Portero', 1, 6);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Federico Valverde', 'Mediocampista', 15, 6);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Darwin Núñez', 'Delantero', 9, 6);
-- Colombia (7)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Camilo Vargas', 'Portero', 12, 7);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('James Rodríguez', 'Mediocampista', 10, 7);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Luis Díaz', 'Delantero', 7, 7);
-- Ecuador (8)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Hernán Galíndez', 'Portero', 1, 8);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Moisés Caicedo', 'Mediocampista', 23, 8);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Enner Valencia', 'Delantero', 13, 8);
-- Paraguay (9)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Roberto Fernández', 'Portero', 1, 9);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Miguel Almirón', 'Delantero', 10, 9);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Julio Enciso', 'Delantero', 19, 9);
-- Alemania (10)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Marc-André ter Stegen', 'Portero', 1, 10);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Jamal Musiala', 'Mediocampista', 10, 10);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Florian Wirtz', 'Mediocampista', 17, 10);
-- Bélgica (11)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Koen Casteels', 'Portero', 1, 11);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Kevin De Bruyne', 'Mediocampista', 7, 11);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Jeremy Doku', 'Delantero', 11, 11);
-- Croacia (12)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Dominik Livakovic', 'Portero', 1, 12);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Luka Modric', 'Mediocampista', 10, 12);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Josko Gvardiol', 'Defensa', 4, 12);
-- Escocia (13)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Angus Gunn', 'Portero', 1, 13);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Scott McTominay', 'Mediocampista', 4, 13);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Andrew Robertson', 'Defensa', 3, 13);
-- España (14)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Unai Simón', 'Portero', 23, 14);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Lamine Yamal', 'Delantero', 19, 14);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Rodri Hernández', 'Mediocampista', 16, 14);
-- Francia (15)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Mike Maignan', 'Portero', 16, 15);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Kylian Mbappé', 'Delantero', 10, 15);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Antoine Griezmann', 'Delantero', 7, 15);
-- Inglaterra (16)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Jordan Pickford', 'Portero', 1, 16);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Jude Bellingham', 'Mediocampista', 10, 16);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Harry Kane', 'Delantero', 9, 16);
-- Noruega (17)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Ørjan Nyland', 'Portero', 1, 17);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Erling Haaland', 'Delantero', 9, 17);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Martin Ødegaard', 'Mediocampista', 10, 17);
-- Países Bajos (18)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Bart Verbruggen', 'Portero', 1, 18);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Virgil van Dijk', 'Defensa', 4, 18);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Xavi Simons', 'Mediocampista', 7, 18);
-- Portugal (19)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Diogo Costa', 'Portero', 22, 19);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Cristiano Ronaldo', 'Delantero', 7, 19);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Bruno Fernandes', 'Mediocampista', 8, 19);
-- Suiza (20)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Yann Sommer', 'Portero', 1, 20);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Granit Xhaka', 'Mediocampista', 10, 20);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Manuel Akanji', 'Defensa', 5, 20);
-- Austria (21)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Patrick Pentz', 'Portero', 13, 21);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Marcel Sabitzer', 'Mediocampista', 9, 21);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Konrad Laimer', 'Mediocampista', 24, 21);
-- Bosnia (22)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Nikola Vasilj', 'Portero', 1, 22);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Edin Dzeko', 'Delantero', 11, 22);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Ermedin Demirovic', 'Delantero', 10, 22);
-- Rep. Checa (23)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Matej Kovar', 'Portero', 1, 23);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Patrik Schick', 'Delantero', 10, 23);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Tomas Soucek', 'Mediocampista', 22, 23);
-- Suecia (24)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Robin Olsen', 'Portero', 1, 24);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Viktor Gyökeres', 'Delantero', 17, 24);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Alexander Isak', 'Delantero', 9, 24);
-- Turquía (25)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Mert Günok', 'Portero', 1, 25);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Arda Güler', 'Mediocampista', 8, 25);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Hakan Çalhanoglu', 'Mediocampista', 10, 25);
-- Marruecos (26)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Yassine Bounou', 'Portero', 1, 26);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Achraf Hakimi', 'Defensa', 2, 26);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Brahim Díaz', 'Mediocampista', 10, 26);
-- Senegal (27)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Édouard Mendy', 'Portero', 16, 27);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Sadio Mané', 'Delantero', 10, 27);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Nicolas Jackson', 'Delantero', 7, 27);
-- Túnez (28)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Aymen Dahmen', 'Portero', 16, 28);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Aissa Laïdouni', 'Mediocampista', 14, 28);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Elias Achouri', 'Delantero', 11, 28);
-- Argelia (29)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Anthony Mandrea', 'Portero', 1, 29);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Riyad Mahrez', 'Delantero', 7, 29);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Said Benrahma', 'Delantero', 10, 29);
-- Egipto (30)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Mohamed El Shenawy', 'Portero', 1, 30);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Mohamed Salah', 'Delantero', 10, 30);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Mostafa Mohamed', 'Delantero', 11, 30);
-- Nigeria (31)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Stanley Nwabali', 'Portero', 23, 31);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Victor Osimhen', 'Delantero', 9, 31);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Ademola Lookman', 'Delantero', 11, 31);
-- Sudáfrica (32)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Ronwen Williams', 'Portero', 1, 32);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Teboho Mokoena', 'Mediocampista', 4, 32);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Percy Tau', 'Delantero', 10, 32);
-- Costa de Marfil (33)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Yahia Fofana', 'Portero', 1, 33);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Franck Kessié', 'Mediocampista', 8, 33);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Simon Adingra', 'Delantero', 24, 33);
-- Cabo Verde (34)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Vozinha', 'Portero', 1, 34);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Ryan Mendes', 'Delantero', 20, 34);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Bebé', 'Delantero', 10, 34);
-- Congo (35)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Lionel Mpasi', 'Portero', 1, 35);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Chancel Mbemba', 'Defensa', 22, 35);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Yoane Wissa', 'Delantero', 20, 35);
-- Japón (36)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Zion Suzuki', 'Portero', 1, 36);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Takefusa Kubo', 'Mediocampista', 20, 36);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Kaoru Mitoma', 'Delantero', 7, 36);
-- Irán (37)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Alireza Beiranvand', 'Portero', 1, 37);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Mehdi Taremi', 'Delantero', 9, 37);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Sardar Azmoun', 'Delantero', 20, 37);
-- Corea del Sur (38)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Jo Hyeon-woo', 'Portero', 21, 38);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Son Heung-min', 'Delantero', 7, 38);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Lee Kang-in', 'Mediocampista', 18, 38);
-- Australia (39)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Mathew Ryan', 'Portero', 1, 39);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Jackson Irvine', 'Mediocampista', 22, 39);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Craig Goodwin', 'Delantero', 23, 39);
-- Catar (40)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Meshaal Barsham', 'Portero', 22, 40);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Akram Afif', 'Delantero', 11, 40);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Almoez Ali', 'Delantero', 19, 40);
-- Arabia Saudita (41)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Mohammed Al-Owais', 'Portero', 21, 41);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Salem Al-Dawsari', 'Delantero', 10, 41);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Firas Al-Buraikan', 'Delantero', 9, 41);
-- Uzbekistán (42)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Utkir Yusupov', 'Portero', 1, 42);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Eldor Shomurodov', 'Delantero', 14, 42);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Abbosbek Fayzullaev', 'Mediocampista', 22, 42);
-- Jordania (43)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Yazeed Abulaila', 'Portero', 1, 43);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Mousa Al-Tamari', 'Delantero', 10, 43);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Yazan Al-Naimat', 'Delantero', 11, 43);
-- Irak (44)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Jalal Hassan', 'Portero', 1, 44);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Aymen Hussein', 'Delantero', 18, 44);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Ali Jasim', 'Mediocampista', 17, 44);
-- Panamá (45)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Orlando Mosquera', 'Portero', 22, 45);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Adalberto Carrasquilla', 'Mediocampista', 8, 45);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('José Fajardo', 'Delantero', 17, 45);
-- Guatemala (46)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Nicholas Hagen', 'Portero', 1, 46);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Oscar Santis', 'Delantero', 18, 46);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Rubio Rubin', 'Delantero', 9, 46);
-- Curazao (47)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Eloy Room', 'Portero', 1, 47);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Leandro Bacuna', 'Mediocampista', 10, 47);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Juninho Bacuna', 'Mediocampista', 7, 47);
-- Nueva Zelanda (48)
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Alex Paulsen', 'Portero', 1, 48);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Chris Wood', 'Delantero', 9, 48);
INSERT INTO jugadores (nombre, posicion, numero_camiseta, equipo_id) VALUES ('Matthew Garbett', 'Mediocampista', 10, 48);