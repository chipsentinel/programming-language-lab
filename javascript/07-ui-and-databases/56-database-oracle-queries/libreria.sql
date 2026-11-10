-- Base de datos de ejemplo para las lecciones 56 y 58 (Oracle): una librería.
-- No es un curso de bases de datos: solo se prepara el entorno para aprender a consultar desde código.
--
-- El archivo tiene DOS PARTES:
--   PARTE 1: se ejecuta conectado como SYSTEM (crea el usuario/esquema LIBRERIA).
--   PARTE 2: se ejecuta conectado como LIBRERIA (crea las tablas y las llena).
--
-- Cómo ejecutarlo en SQL Developer:  pegar la parte en el editor y pulsar "Run Script" (F5).
-- En Oracle 11g XE se conecta al servicio XE; en Oracle 18c/21c XE hay que conectarse al servicio XEPDB1
-- (y usar ORACLE_SERVICE=XEPDB1 en los programas).


-- ======================================================================
-- PARTE 1 (como SYSTEM): crear el usuario LIBRERIA y darle permisos
-- ======================================================================

CREATE USER libreria IDENTIFIED BY oracle          -- cambiar "oracle" por la clave que se prefiera
    DEFAULT TABLESPACE users
    QUOTA UNLIMITED ON users;

GRANT CREATE SESSION TO libreria;                  -- poder conectarse
GRANT CREATE TABLE, CREATE VIEW, CREATE SEQUENCE TO libreria;   -- poder crear objetos en su esquema

-- Después: crear en SQL Developer una conexión nueva (por ejemplo "base libreria") con el usuario LIBRERIA,
-- probarla con Test (debe decir Success) y conectarse con ella para ejecutar la PARTE 2.


-- ======================================================================
-- PARTE 2 (como LIBRERIA): tablas y datos
-- ======================================================================

CREATE TABLE autores (
    id_autor            NUMBER(4)    PRIMARY KEY,
    nombre              VARCHAR2(40) NOT NULL,
    apellido            VARCHAR2(40) NOT NULL,
    nacionalidad        VARCHAR2(30),
    libros_publicados   NUMBER(3)                  -- cantidad aproximada, solo de ejemplo
);

CREATE TABLE libros (
    id_libro            NUMBER(4)    PRIMARY KEY,
    titulo              VARCHAR2(80) NOT NULL,
    id_autor            NUMBER(4)    NOT NULL REFERENCES autores (id_autor),   -- a qué autor pertenece
    fecha_publicacion   DATE,                      -- fecha orientativa (solo el año es fiable)
    genero              VARCHAR2(30)
);

-- ----- Autores -----

INSERT INTO autores VALUES (1,  'Gabriel',  'García Márquez', 'Colombiana', 20);
INSERT INTO autores VALUES (2,  'Isabel',   'Allende',        'Chilena',    25);
INSERT INTO autores VALUES (3,  'Mario',    'Vargas Llosa',   'Peruana',    30);
INSERT INTO autores VALUES (4,  'Julio',    'Cortázar',       'Argentina',  18);
INSERT INTO autores VALUES (5,  'Jorge Luis', 'Borges',       'Argentina',  22);
INSERT INTO autores VALUES (6,  'Pablo',    'Neruda',         'Chilena',    35);
INSERT INTO autores VALUES (7,  'Miguel',   'de Cervantes',   'Española',   10);
INSERT INTO autores VALUES (8,  'Laura',    'Esquivel',       'Mexicana',   8);
INSERT INTO autores VALUES (9,  'Octavio',  'Paz',            'Mexicana',   28);
INSERT INTO autores VALUES (10, 'Carlos',   'Fuentes',        'Mexicana',   24);
INSERT INTO autores VALUES (11, 'Rosa',     'Montero',        'Española',   21);
INSERT INTO autores VALUES (12, 'Ernesto',  'Sabato',         'Argentina',  6);
INSERT INTO autores VALUES (13, 'Juan',     'Rulfo',          'Mexicana',   3);
INSERT INTO autores VALUES (14, 'Eduardo',  'Galeano',        'Uruguaya',   15);
INSERT INTO autores VALUES (15, 'Carmen',   'Laforet',        'Española',   9);

-- ----- Libros (la fecha se escribe con la palabra DATE y el formato año-mes-día) -----

INSERT INTO libros VALUES (1,  'Cien años de soledad',                           1,  DATE '1967-06-05', 'Novela');
INSERT INTO libros VALUES (2,  'El amor en los tiempos del cólera',              1,  DATE '1985-12-05', 'Novela');
INSERT INTO libros VALUES (3,  'La casa de los espíritus',                       2,  DATE '1982-01-01', 'Novela');
INSERT INTO libros VALUES (4,  'Eva Luna',                                       2,  DATE '1987-01-01', 'Novela');
INSERT INTO libros VALUES (5,  'La ciudad y los perros',                         3,  DATE '1963-10-01', 'Novela');
INSERT INTO libros VALUES (6,  'Conversación en La Catedral',                    3,  DATE '1969-01-01', 'Novela');
INSERT INTO libros VALUES (7,  'Rayuela',                                        4,  DATE '1963-06-28', 'Novela');
INSERT INTO libros VALUES (8,  'Bestiario',                                      4,  DATE '1951-01-01', 'Cuento');
INSERT INTO libros VALUES (9,  'Ficciones',                                      5,  DATE '1944-01-01', 'Cuento');
INSERT INTO libros VALUES (10, 'El Aleph',                                       5,  DATE '1949-01-01', 'Cuento');
INSERT INTO libros VALUES (11, 'Veinte poemas de amor y una canción desesperada', 6, DATE '1924-01-01', 'Poesía');
INSERT INTO libros VALUES (12, 'Don Quijote de la Mancha',                       7,  DATE '1605-01-16', 'Novela');
INSERT INTO libros VALUES (13, 'Como agua para chocolate',                       8,  DATE '1989-01-01', 'Novela');
INSERT INTO libros VALUES (14, 'El laberinto de la soledad',                     9,  DATE '1950-01-01', 'Ensayo');
INSERT INTO libros VALUES (15, 'La muerte de Artemio Cruz',                      10, DATE '1962-01-01', 'Novela');
INSERT INTO libros VALUES (16, 'La hija del caníbal',                            11, DATE '1997-01-01', 'Novela');
INSERT INTO libros VALUES (17, 'Sobre héroes y tumbas',                          12, DATE '1961-01-01', 'Novela');
INSERT INTO libros VALUES (18, 'Pedro Páramo',                                   13, DATE '1955-01-01', 'Novela');
INSERT INTO libros VALUES (19, 'Las venas abiertas de América Latina',           14, DATE '1971-01-01', 'Ensayo');
INSERT INTO libros VALUES (20, 'Nada',                                           15, DATE '1944-01-01', 'Novela');

COMMIT;     -- sin COMMIT los datos no quedan guardados para otras conexiones (como los programas)

-- ----- Comprobación: cada instrucción debe decir "1 fila insertada" -----

SELECT * FROM autores;
SELECT * FROM libros;
