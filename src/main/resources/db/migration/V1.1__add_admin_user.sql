INSERT INTO profiles (id, name, surname, role, username, password, status, visible, created_date)
VALUES (gen_random_uuid(),
        'Admin',
        'User',
        'ROLE_ADMIN',
        'admin',
        '$2a$10$bfG7/4tj7J/uJLPMzT58oebrYXZsWrRz.bqYuUhh5orDcWO2oQDXK',
        true,
        true,
        now());
