-- =====================================================================
-- V1: Esquema inicial de TaskFlow API
-- Traduce las entidades JPA a tablas. Hibernate (ddl-auto=validate)
-- comprueba que este esquema coincide con las entidades al arrancar.
-- =====================================================================

-- Listas de tareas (entidad TaskList)
CREATE TABLE task_list (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(2000),
    created_at  DATETIME(6)  NOT NULL,
    updated_at  DATETIME(6)  NOT NULL,
    CONSTRAINT pk_task_list PRIMARY KEY (id)
) ENGINE = InnoDB;

-- Usuarios (entidad User). Tabla 'users' en plural porque USER es
-- palabra reservada en varios SGBD.
CREATE TABLE users (
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    first_name    VARCHAR(100) NOT NULL,
    last_name     VARCHAR(100) NOT NULL,
    username      VARCHAR(50)  NOT NULL,
    email         VARCHAR(100) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    active        BIT(1)       NOT NULL DEFAULT b'1',
    created_at    DATETIME(6)  NOT NULL,
    updated_at    DATETIME(6)  NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uq_users_username UNIQUE (username),
    CONSTRAINT uq_users_email UNIQUE (email)
) ENGINE = InnoDB;

-- Etiquetas (entidad Tag)
CREATE TABLE tag (
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_tag PRIMARY KEY (id),
    CONSTRAINT uq_tag_name UNIQUE (name)
) ENGINE = InnoDB;

-- Tareas (entidad Task)
CREATE TABLE task (
    id               BIGINT       NOT NULL AUTO_INCREMENT,
    title            VARCHAR(100) NOT NULL,
    description      VARCHAR(2000),
    priority         VARCHAR(20)  NOT NULL,
    status           VARCHAR(20)  NOT NULL,
    due_date         DATETIME(6),
    task_list_id     BIGINT       NOT NULL,
    creator_id       BIGINT       NOT NULL,
    assigned_user_id BIGINT,
    created_at       DATETIME(6)  NOT NULL,
    updated_at       DATETIME(6)  NOT NULL,
    CONSTRAINT pk_task PRIMARY KEY (id),
    CONSTRAINT fk_task_task_list FOREIGN KEY (task_list_id) REFERENCES task_list (id),
    CONSTRAINT fk_task_creator FOREIGN KEY (creator_id) REFERENCES users (id),
    CONSTRAINT fk_task_assigned_user FOREIGN KEY (assigned_user_id) REFERENCES users (id)
) ENGINE = InnoDB;

-- Índices para las claves foráneas más consultadas (filtrar tareas por lista/usuario)
CREATE INDEX idx_task_task_list ON task (task_list_id);
CREATE INDEX idx_task_creator ON task (creator_id);
CREATE INDEX idx_task_assigned_user ON task (assigned_user_id);

-- Tabla puente N:M entre task y tag (relación @ManyToMany de Task.tags)
CREATE TABLE task_tag (
    task_id BIGINT NOT NULL,
    tag_id  BIGINT NOT NULL,
    CONSTRAINT pk_task_tag PRIMARY KEY (task_id, tag_id),
    CONSTRAINT fk_task_tag_task FOREIGN KEY (task_id) REFERENCES task (id) ON DELETE CASCADE,
    CONSTRAINT fk_task_tag_tag FOREIGN KEY (tag_id) REFERENCES tag (id)
) ENGINE = InnoDB;
