CREATE TABLE users
(
    id         VARCHAR(32)                 NOT NULL,
    name       VARCHAR(100)                NOT NULL,
    document   VARCHAR(11)                 NOT NULL,
    email      VARCHAR(100)                NOT NULL,
    phone      VARCHAR(11)                 NOT NULL,
    birth_date date                        NOT NULL,
    active     BOOLEAN                     NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_users PRIMARY KEY (id)
);