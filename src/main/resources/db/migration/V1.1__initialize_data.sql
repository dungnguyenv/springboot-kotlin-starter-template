CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO base.roles (id, name, created_at, updated_at, created_by, modified_by)
VALUES (gen_random_uuid(), 'ADMIN', now(), now(), 'john', 'john'),
       (gen_random_uuid(), 'USER', now(), now(), 'john', 'john'),
       (gen_random_uuid(), 'BUILDER', now(), now(), 'john', 'john');

-- password: $2a$12$ZjOwnNB4Osp/11Y1yS4GYuEUU7p7VlzuRNW0VHABsiBMcq4fsmt6C
INSERT INTO base.users (id, name, email, username, password, active, created_at, updated_at, created_by, modified_by)
VALUES (gen_random_uuid(), 'John Doe', 'john.doe@example.com', 'john',
        '$2a$12$ZjOwnNB4Osp/11Y1yS4GYuEUU7p7VlzuRNW0VHABsiBMcq4fsmt6C', true, now(), now(), 'john', 'john'),
       (gen_random_uuid(), 'Jane Smith', 'jane.smith@example.com', 'jane',
        '$2a$12$ZjOwnNB4Osp/11Y1yS4GYuEUU7p7VlzuRNW0VHABsiBMcq4fsmt6C', true, now(), now(), 'john', 'john'),
       (gen_random_uuid(), 'Alice Johnson', 'alice.johnson@example.com', 'alice',
        '$2a$12$ZjOwnNB4Osp/11Y1yS4GYuEUU7p7VlzuRNW0VHABsiBMcq4fsmt6C', true, now(), now(), 'john', 'john');

INSERT INTO base.users_roles (user_id, role_id)
VALUES ((SELECT id FROM base.users WHERE username = 'john'), (SELECT id FROM base.roles WHERE name = 'ADMIN')),
       ((SELECT id FROM base.users WHERE username = 'jane'), (SELECT id FROM base.roles WHERE name = 'USER')),
       ((SELECT id FROM base.users WHERE username = 'alice'), (SELECT id FROM base.roles WHERE name = 'BUILDER'));
