
------------------------------------------------------------
-- 1. Пользователи
------------------------------------------------------------

CREATE TABLE IF NOT EXISTS usr (
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(255) NOT NULL UNIQUE,
    email         VARCHAR(255) NOT NULL,
    hash_password VARCHAR(255) NOT NULL,
    avatar_path   VARCHAR(255) NOT NULL DEFAULT '/avatars/default.png'
);

-- Индексы (username уже UNIQUE → индекс есть, но email можно проиндексировать)
CREATE INDEX IF NOT EXISTS idx_usr_email ON usr(email);

------------------------------------------------------------
-- 2. Комнаты и участники
------------------------------------------------------------

CREATE TABLE IF NOT EXISTS room (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    owner_id   BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT fk_room_owner
        FOREIGN KEY (owner_id) REFERENCES usr(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_room_owner_id ON room(owner_id);

CREATE TABLE IF NOT EXISTS room_member (
    user_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    role    VARCHAR(20) NOT NULL DEFAULT 'MEMBER',
    PRIMARY KEY (user_id, room_id),
    CONSTRAINT fk_room_member_user
        FOREIGN KEY (user_id) REFERENCES usr(id) ON DELETE CASCADE,
    CONSTRAINT fk_room_member_room
        FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_room_member_room_id ON room_member(room_id);
CREATE INDEX IF NOT EXISTS idx_room_member_user_id ON room_member(user_id);

------------------------------------------------------------
-- 3. ToDo-листы в комнате
------------------------------------------------------------

CREATE TABLE IF NOT EXISTS todo_list (
    id         BIGSERIAL PRIMARY KEY,
    room_id    BIGINT NOT NULL,
    name       VARCHAR(255) NOT NULL,
    created_by BIGINT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT fk_todo_list_room
        FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE,
    CONSTRAINT fk_todo_list_user
        FOREIGN KEY (created_by) REFERENCES usr(id) ON DELETE SET NULL
);

CREATE INDEX IF NOT EXISTS idx_todo_list_room_id ON todo_list(room_id);
CREATE INDEX IF NOT EXISTS idx_todo_list_created_by ON todo_list(created_by);

------------------------------------------------------------
-- 4. ToDo-задачи в листе
------------------------------------------------------------

CREATE TABLE IF NOT EXISTS todo_item (
    id         BIGSERIAL PRIMARY KEY,
    list_id    BIGINT NOT NULL,
    text       VARCHAR(500) NOT NULL,
    done       BOOLEAN NOT NULL DEFAULT FALSE,
    deadline   TIMESTAMP NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT fk_todo_item_list
        FOREIGN KEY (list_id) REFERENCES todo_list(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_todo_item_list_id ON todo_item(list_id);
CREATE INDEX IF NOT EXISTS idx_todo_item_done ON todo_item(done);