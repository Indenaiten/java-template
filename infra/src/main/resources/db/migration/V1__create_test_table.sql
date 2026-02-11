CREATE TABLE users (
    id UUID PRIMARY KEY,
    username VARCHAR( 255 ) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Comentarios
COMMENT ON TABLE users IS 'Table for users';
COMMENT ON COLUMN users.id IS 'Primary key';
COMMENT ON COLUMN users.username IS 'Username of the user';
COMMENT ON COLUMN users.created_at IS 'Creation date of the user';
COMMENT ON COLUMN users.updated_at IS 'Last update date of the user';

CREATE TABLE messages (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    owner UUID NOT NULL,
    text TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE messages
    ADD CONSTRAINT fk_owner FOREIGN KEY ( owner ) REFERENCES users ( id )
        ON DELETE CASCADE ON UPDATE CASCADE;

-- Comentarios
COMMENT ON TABLE messages IS 'Table for user messages';
COMMENT ON COLUMN messages.id IS 'Primary key';
COMMENT ON COLUMN messages.owner IS 'Owner of the message';
COMMENT ON COLUMN messages.text IS 'Message to store';
COMMENT ON COLUMN messages.created_at IS 'Creation date of the message';
COMMENT ON COLUMN messages.updated_at IS 'Last update date of the message';
