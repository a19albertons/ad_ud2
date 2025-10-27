-- asegura que cada vez que se crea un contedor limpo, testdb e usuario estarán dispoñibles.

-- =========================================
-- Script de inicialización para MySQL
-- =========================================

-- Crear a base de datos de prácticas
CREATE DATABASE IF NOT EXISTS testdb
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

-- Crear usuario de acceso remoto
CREATE USER IF NOT EXISTS 'usuario'@'%' IDENTIFIED BY 'usuario123';

-- Conceder permisos sobre a base de datos
GRANT ALL PRIVILEGES ON testdb.* TO 'usuario'@'%';

-- Activar os cambios
FLUSH PRIVILEGES;