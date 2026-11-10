-- Base de datos de ejemplo para las lecciones 57 y 58 (SQL Server): una farmacia.
-- No es un curso de bases de datos: solo se prepara el entorno para aprender a conectarse y consultar desde código.
--
-- El archivo tiene DOS PARTES:
--   PARTE 1: crea las tres tablas (clientes, productos y ventas).
--   PARTE 2: las llena (20 clientes, 30 productos y 30 ventas).
--
-- Cómo ejecutarlo en SQL Server Management Studio (SSMS):
--   1. Conectarse al servidor y crear la base:  clic derecho en "Databases" > "New Database" > nombre: farmacia.
--      (o ejecutar la línea CREATE DATABASE de abajo).
--   2. Clic derecho en la base "farmacia" > "New Query", pegar la PARTE 1 y pulsar "Execute" (F5).
--      Debe decir "Commands completed successfully".
--   3. Borrar el editor, pegar la PARTE 2 y ejecutar: debe decir "(20 rows affected)", "(30 rows affected)", "(30 rows affected)".
--   4. Comprobar:  SELECT * FROM clientes;

-- CREATE DATABASE farmacia;     -- (solo si no se creó desde el menú)
-- GO
-- USE farmacia;
-- GO


-- ======================================================================
-- PARTE 1: tablas
-- ======================================================================

CREATE TABLE clientes (
    id_cliente  INT IDENTITY(1,1) PRIMARY KEY,
    nombre      VARCHAR(60)  NOT NULL,
    direccion   VARCHAR(80),
    telefono    VARCHAR(20)
);

CREATE TABLE productos (
    id_producto INT IDENTITY(1,1) PRIMARY KEY,
    nombre      VARCHAR(60)  NOT NULL,
    descripcion VARCHAR(120),
    precio      DECIMAL(8,2) NOT NULL,
    stock       INT NOT NULL                   -- unidades disponibles
);

CREATE TABLE ventas (
    id_venta    INT IDENTITY(1,1) PRIMARY KEY,
    id_cliente  INT NOT NULL REFERENCES clientes (id_cliente),    -- quién compra
    id_producto INT NOT NULL REFERENCES productos (id_producto),  -- qué compra
    cantidad    INT NOT NULL,
    fecha       DATE NOT NULL
);


-- ======================================================================
-- PARTE 2: datos
-- ======================================================================

INSERT INTO clientes (nombre, direccion, telefono) VALUES
    ('Sol Vargas', 'Calle Luna 21', '610000000'),
    ('Mateo Ortiz', 'Avenida Sur 10', '611111111'),
    ('Lucía Navarro', 'Calle Mayor 26', '612222222'),
    ('Daniel Cruz', 'Plaza Nueva 42', '613333333'),
    ('Valeria Pardo', 'Calle del Sol 4', '614444444'),
    ('Hugo Salas', 'Camino Viejo 5', '615555555'),
    ('Elena Ramos', 'Avenida Norte 53', '616666666'),
    ('Pablo Iglesias', 'Calle Río 35', '617777777'),
    ('Marta Rivas', 'Calle Olmo 7', '618888888'),
    ('Luis Herrera', 'Paseo Verde 24', '619999999'),
    ('Carmen Soto', 'Calle Roble 38', '621111110'),
    ('Pedro Molina', 'Avenida del Mar 4', '622222221'),
    ('Irene Castillo', 'Calle Pino 59', '623333332'),
    ('Andrés Vidal', 'Plaza Mayor 33', '624444443'),
    ('Noelia Prieto', 'Calle Alta 14', '625555554'),
    ('Sergio Lara', 'Calle Baja 3', '626666665'),
    ('Paula Medina', 'Paseo Alto 6', '627777776'),
    ('Adrián Gil', 'Calle Sauce 28', '628888887'),
    ('Clara Ibáñez', 'Avenida Este 27', '629999998'),
    ('Rubén Marín', 'Calle Huerta 5', '631111109');

INSERT INTO productos (nombre, descripcion, precio, stock) VALUES
    ('Paracetamol 500 mg', 'Analgésico y antipirético, caja de 20 comprimidos', 3.50, 120),
    ('Ibuprofeno 400 mg', 'Antiinflamatorio, caja de 30 comprimidos', 4.20, 95),
    ('Amoxicilina 500 mg', 'Antibiótico, caja de 12 cápsulas', 6.80, 40),
    ('Omeprazol 20 mg', 'Protector gástrico, caja de 28 cápsulas', 5.10, 70),
    ('Loratadina 10 mg', 'Antihistamínico, caja de 20 comprimidos', 4.60, 85),
    ('Vitamina C 1000 mg', 'Complemento alimenticio, tubo de 20 comprimidos efervescentes', 5.90, 60),
    ('Jarabe para la tos', 'Expectorante, frasco de 200 ml', 7.25, 45),
    ('Gotas oculares', 'Lubricante ocular, frasco de 10 ml', 8.40, 30),
    ('Crema hidratante', 'Cuidado de la piel, tubo de 100 ml', 9.95, 55),
    ('Protector solar SPF 50', 'Protección solar, frasco de 200 ml', 14.50, 35),
    ('Termómetro digital', 'Medición de la temperatura corporal', 6.50, 25),
    ('Tiritas surtidas', 'Apósitos adhesivos, caja de 40 unidades', 2.80, 150),
    ('Gasas estériles', 'Sobres de 10 unidades', 3.10, 100),
    ('Suero fisiológico', 'Monodosis, caja de 30 unidades', 3.95, 90),
    ('Alcohol 96º', 'Antiséptico, frasco de 250 ml', 2.40, 80),
    ('Agua oxigenada', 'Antiséptico, frasco de 250 ml', 1.90, 75),
    ('Pastillas para la garganta', 'Caja de 24 pastillas', 4.35, 110),
    ('Spray nasal', 'Descongestionante, frasco de 15 ml', 6.15, 50),
    ('Aspirina 500 mg', 'Analgésico, caja de 20 comprimidos', 3.75, 65),
    ('Vendaje elástico', 'Rollo de 5 m', 4.90, 45),
    ('Mascarillas quirúrgicas', 'Caja de 50 unidades', 7.00, 70),
    ('Gel desinfectante', 'Frasco de 500 ml', 5.50, 85),
    ('Colirio antialérgico', 'Frasco de 10 ml', 9.20, 28),
    ('Magnesio', 'Complemento alimenticio, caja de 60 comprimidos', 11.30, 40),
    ('Melatonina 1 mg', 'Complemento para el sueño, caja de 30 comprimidos', 8.60, 52),
    ('Pasta de dientes sensible', 'Tubo de 75 ml', 4.95, 95),
    ('Enjuague bucal', 'Frasco de 500 ml', 5.80, 66),
    ('Cepillo dental suave', 'Unidad', 2.20, 120),
    ('Test de embarazo', 'Caja de 1 unidad', 9.50, 22),
    ('Tensiómetro de brazo', 'Medición de la tensión arterial', 39.90, 12);

INSERT INTO ventas (id_cliente, id_producto, cantidad, fecha) VALUES
    (8, 3, 4, '2024-01-01'),
    (2, 27, 1, '2024-01-08'),
    (8, 21, 1, '2024-01-15'),
    (19, 19, 4, '2024-02-22'),
    (2, 8, 1, '2024-02-01'),
    (18, 28, 2, '2024-02-08'),
    (10, 14, 2, '2024-03-15'),
    (18, 4, 3, '2024-03-22'),
    (18, 27, 2, '2024-03-01'),
    (4, 19, 2, '2024-04-08'),
    (12, 4, 1, '2024-04-15'),
    (19, 2, 2, '2024-04-22'),
    (16, 22, 4, '2024-05-01'),
    (11, 15, 4, '2024-05-08'),
    (12, 10, 2, '2024-05-15'),
    (6, 23, 2, '2024-06-22'),
    (3, 19, 3, '2024-06-01'),
    (17, 16, 3, '2024-06-08'),
    (15, 10, 1, '2024-07-15'),
    (4, 17, 4, '2024-07-22'),
    (6, 25, 3, '2024-07-01'),
    (5, 30, 4, '2024-08-08'),
    (14, 2, 1, '2024-08-15'),
    (18, 19, 3, '2024-08-22'),
    (11, 23, 3, '2024-09-01'),
    (20, 16, 4, '2024-09-08'),
    (3, 27, 1, '2024-09-15'),
    (9, 16, 1, '2024-10-22'),
    (2, 24, 3, '2024-10-01'),
    (19, 22, 4, '2024-10-08');

-- Comprobación
SELECT * FROM clientes;
SELECT * FROM productos;
SELECT * FROM ventas;
