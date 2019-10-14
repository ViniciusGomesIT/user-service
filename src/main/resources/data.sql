INSERT INTO tbg_profile (id, profile_description)
SELECT * FROM (SELECT 2, 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT profile_description FROM tbg_profile WHERE profile_description = 'ROLE_ADMIN'
) LIMIT 1;

INSERT INTO tbg_profile (id, profile_description)
SELECT * FROM (SELECT 3, 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT profile_description FROM tbg_profile WHERE profile_description = 'ROLE_USER'
) LIMIT 1;

INSERT INTO tbg_user (id, nome, login, email, senha)
SELECT * FROM (SELECT 3, 'VINICIUS GOMES', 'ADMIN123', 'VINIGOMES47@EMAIL.COM', '$2a$10$GWCbUih2hMNNYrFL7RR2BeNntk9IaOMJvaPR.qZY51uKLVAsob912') AS tmp
WHERE NOT EXISTS (
    SELECT login FROM tbg_user WHERE login = 'ADMIN123'
) LIMIT 1;

INSERT INTO tbg_user_profile (id, user_id, autorization_id)
SELECT * FROM (SELECT 1, 3, 2) AS tmp
WHERE NOT EXISTS (
    SELECT id FROM tbg_user_profile WHERE id = 1
) LIMIT 1;