INSERT INTO users (id, name, email, password, role) VALUES
    (gen_random_uuid(), 'Arthur Correia', 'arthurcorria0109@gmail.com', '$2a$10$Rx1e1/xYRGuPnQlFOm/PuePJHVMurSR8mACq1fkGLDIg6n2ZBlvZ6', 'ADMIN'),
    (gen_random_uuid(), 'Emmanuel Tavares', 'emmanuel.t2003@hotmail.com', '$2a$10$LY5.NltsvDkB4hYYbaj8N.pbqu2l1fDNb/RMw59tueWCwNFWG5N9C', 'ADMIN');