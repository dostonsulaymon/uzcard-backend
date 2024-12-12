INSERT INTO profiles (id, name, surname, role, username, password, status, created_date)
VALUES (
           gen_random_uuid(),
           'Admin',
           'User',
           'ROLE_ADMIN',
           'admin',
           '$2a$12$l.SNnwNvNFv5GAkXXgRS3ugfDJEZ4iRo7gb.Gf3NTtHaNnrnIgzTu',
           true,
           now()
       );
