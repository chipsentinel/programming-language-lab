-- Base de datos de ejemplo para las lecciones 53 y 54: un colegio.
-- No es un curso de bases de datos: solo se prepara el entorno para aprender a conectarse y consultar desde código.
--
-- Cómo ejecutarlo (MySQL 8):
--   1. Abrir MySQL Workbench y conectarse a la instancia local (usuario root, localhost:3306).
--   2. Pegar TODO este script y ejecutarlo (el rayo). Todas las líneas deben salir en verde.
--   (o desde la consola:  mysql -u root -p < colegio_amanecer.sql)

DROP DATABASE IF EXISTS colegio_amanecer;
CREATE DATABASE colegio_amanecer CHARACTER SET utf8mb4;
USE colegio_amanecer;

-- ===== Tablas =====

CREATE TABLE profesores (
    id_profesor INT PRIMARY KEY AUTO_INCREMENT,
    nombre      VARCHAR(60) NOT NULL,
    grado       VARCHAR(20) NOT NULL          -- grado en el que enseña
);

CREATE TABLE alumnos (
    id_alumno   INT PRIMARY KEY AUTO_INCREMENT,
    nombre      VARCHAR(40) NOT NULL,
    apellido    VARCHAR(40) NOT NULL,
    edad        INT NOT NULL,
    direccion   VARCHAR(80),
    telefono    VARCHAR(20),
    grado       VARCHAR(20) NOT NULL,         -- grado en el que está
    id_profesor INT NOT NULL,                 -- profesor que le toca
    FOREIGN KEY (id_profesor) REFERENCES profesores (id_profesor)
);

CREATE TABLE materias (
    id_materia  INT PRIMARY KEY AUTO_INCREMENT,
    materia     VARCHAR(40) NOT NULL,
    nivel       VARCHAR(20) NOT NULL,         -- nivel en el que se enseña
    horario     VARCHAR(30) NOT NULL,
    id_profesor INT NOT NULL,                 -- profesor que la imparte
    FOREIGN KEY (id_profesor) REFERENCES profesores (id_profesor)
);

CREATE TABLE alumno_materia (                 -- relación: qué alumno cursa qué materia
    id_alumno  INT NOT NULL,
    id_materia INT NOT NULL,
    PRIMARY KEY (id_alumno, id_materia),
    FOREIGN KEY (id_alumno)  REFERENCES alumnos (id_alumno),
    FOREIGN KEY (id_materia) REFERENCES materias (id_materia)
);

-- ===== Datos (primero las tablas, después los INSERT) =====

INSERT INTO profesores (nombre, grado) VALUES
    ('Marta Rivas',   'Primero'),
    ('Luis Herrera',  'Segundo'),
    ('Carmen Soto',   'Tercero'),
    ('Pedro Molina',  'Cuarto');

INSERT INTO alumnos (nombre, apellido, edad, direccion, telefono, grado, id_profesor) VALUES
    ('Sol',     'Vargas',   6,  'Calle Luna 12',        '600111222', 'Primero', 1),
    ('Mateo',   'Ortiz',    6,  'Avenida Sur 5',        '600222333', 'Primero', 1),
    ('Lucía',   'Navarro',  7,  'Calle Mayor 8',        '600333444', 'Segundo', 2),
    ('Daniel',  'Cruz',     7,  'Plaza Nueva 3',        '600444555', 'Segundo', 2),
    ('Valeria', 'Pardo',    8,  'Calle del Sol 21',     '600555666', 'Tercero', 3),
    ('Hugo',    'Salas',    8,  'Camino Viejo 9',       '600666777', 'Tercero', 3),
    ('Elena',   'Ramos',    9,  'Avenida Norte 40',     '600777888', 'Cuarto',  4),
    ('Pablo',   'Iglesias', 9,  'Calle Río 17',         '600888999', 'Cuarto',  4);

INSERT INTO materias (materia, nivel, horario, id_profesor) VALUES
    ('Matemáticas',     'Primario', 'Lunes 08:00',     1),
    ('Lengua',          'Primario', 'Martes 09:00',    2),
    ('Ciencias',        'Primario', 'Miércoles 10:00', 3),
    ('Historia',        'Primario', 'Jueves 11:00',    4),
    ('Educación física','Primario', 'Viernes 12:00',   1);

INSERT INTO alumno_materia (id_alumno, id_materia) VALUES
    (1, 1), (1, 2), (2, 1), (2, 5),
    (3, 2), (3, 3), (4, 2), (4, 5),
    (5, 3), (5, 4), (6, 3), (6, 1),
    (7, 4), (7, 1), (8, 4), (8, 5);

-- ===== Comprobación =====

SELECT * FROM alumnos;
SELECT * FROM profesores;
SELECT * FROM materias;
SELECT * FROM alumno_materia;
