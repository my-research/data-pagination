drop table if exists todos CASCADE;

CREATE TABLE todos
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    title     VARCHAR(255),
    content   VARCHAR(255),
    status    VARCHAR(50),
    category  VARCHAR(50),
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP,
    deletedAt TIMESTAMP,
    ownerId   INT
);
