CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR( 255 ) NOT NULL UNIQUE,
    username VARCHAR( 255 ) NOT NULL UNIQUE,
    name VARCHAR( 255 ) NOT NULL,
    surname VARCHAR( 255 ),
    birthdate DATE NOT NULL,
    password VARCHAR( 255 ) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Comentarios
COMMENT ON TABLE users IS 'Table for users';
COMMENT ON COLUMN users.id IS 'Primary key';
COMMENT ON COLUMN users.email IS 'Email of the user';
COMMENT ON COLUMN users.username IS 'Username of the user';
COMMENT ON COLUMN users.name IS 'Name of the user';
COMMENT ON COLUMN users.surname IS 'Surname of the user';
COMMENT ON COLUMN users.birthdate IS 'Birthdate of the user';
COMMENT ON COLUMN users.password IS 'Password of the user';
COMMENT ON COLUMN users.created_at IS 'Creation date of the user';
COMMENT ON COLUMN users.updated_at IS 'Last update date of the user';
