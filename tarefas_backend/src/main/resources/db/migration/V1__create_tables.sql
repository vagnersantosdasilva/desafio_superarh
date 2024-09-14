CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    actived BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE listas_de_tarefas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    date_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    when_to_do TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES usuarios(id) ON DELETE CASCADE
);
CREATE TABLE tarefas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    list_task_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,  -- ENUM pode ser substituído por uma coluna VARCHAR e tratada na aplicação
    date_start TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_finish TIMESTAMP,
    FOREIGN KEY (list_task_id) REFERENCES listas_de_tarefas(id) ON DELETE CASCADE
);