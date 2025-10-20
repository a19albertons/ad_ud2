-- Crear a base de datos testdb
CREATE DATABASE IF NOT EXISTS testdb
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;


-- Crear usuario de acceso remoto
CREATE USER IF NOT EXISTS 'usuario'@'%' IDENTIFIED BY 'usuario123';

-- Conceder permisos sobre a base de datos
GRANT ALL PRIVILEGES ON testdb.* TO 'usuario'@'%';

-- Activar os cambios
FLUSH PRIVILEGES;