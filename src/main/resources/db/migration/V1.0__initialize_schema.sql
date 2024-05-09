CREATE TABLE products
(
    id          UUID         NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    modified_by VARCHAR(255) NOT NULL,
    created_by  VARCHAR(255) NOT NULL,
    name        VARCHAR(255),
    price       FLOAT        NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id          UUID         NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    modified_by VARCHAR(255) NOT NULL,
    created_by  VARCHAR(255) NOT NULL,
    name        VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id          UUID         NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    modified_by VARCHAR(255) NOT NULL,
    created_by  VARCHAR(255) NOT NULL,
    name        VARCHAR(255),
    email       VARCHAR(255),
    username    VARCHAR(255),
    password    VARCHAR(255),
    active      BOOLEAN      NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    role_id UUID NOT NULL,
    user_id UUID NOT NULL
);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);