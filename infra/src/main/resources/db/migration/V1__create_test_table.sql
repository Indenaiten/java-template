CREATE TABLE tests (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    message TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Comentarios
COMMENT ON TABLE tests IS 'Table for proof that project works';
COMMENT ON COLUMN tests.id IS 'Primary key';
COMMENT ON COLUMN tests.message IS 'Message to store';
