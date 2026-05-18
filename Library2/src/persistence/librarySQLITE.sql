--------------------------------------------------------
--  Taules netejades i adaptades per a SQLite
--------------------------------------------------------

-- Activa el suport de Claus Foranes a SQLite (molt recomanable)
PRAGMA foreign_keys = ON;

-- Eliminar taules si ja existeixen per evitar errors al reexecutar
/*DROP TABLE IF EXISTS LOANS;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS PERSONS;*/

--------------------------------------------------------
--  DDL for Table BOOKS
--------------------------------------------------------
CREATE TABLE IF NOT EXISTS BOOKS (
    ID INTEGER PRIMARY KEY,
    TITLE TEXT NOT NULL,
    AUTHOR TEXT NOT NULL,
    GENRE TEXT,
    AVAILABLE INTEGER DEFAULT 1 CHECK (AVAILABLE IN (0, 1))
);

--------------------------------------------------------
--  DDL for Table PERSONS
--------------------------------------------------------
CREATE TABLE IF NOT EXISTS PERSONS (
    MAIL TEXT PRIMARY KEY,
    NAME TEXT NOT NULL,
    DNI TEXT NOT NULL UNIQUE,
    PASSWORD TEXT NOT NULL
);

--------------------------------------------------------
--  DDL for Table LOANS
--------------------------------------------------------
CREATE TABLE IF NOT EXISTS LOANS (
    ID_LOAN INTEGER PRIMARY KEY AUTOINCREMENT,
    BOOK_ID INTEGER NOT NULL,
    MEMBER_MAIL TEXT NOT NULL,
    LOAN_DATE TEXT DEFAULT (date('now')), -- Data actual en format YYYY-MM-DD
    DUE_DATE TEXT,
    RETURN_DATE TEXT,
    CONSTRAINT FK_LOANS_BOOKS FOREIGN KEY (BOOK_ID) REFERENCES BOOKS (ID),
    CONSTRAINT FK_LOANS_PERSONS FOREIGN KEY (MEMBER_MAIL) REFERENCES PERSONS (MAIL)
);

--------------------------------------------------------
--  INSERTS (DML)
--------------------------------------------------------

-- INSERTING into BOOKS
INSERT INTO BOOKS (ID, TITLE, AUTHOR, GENRE, AVAILABLE) VALUES (1, 'The Shadow of the Wind', 'Carlos Ruiz Zafon', 'Mystery', 0);
INSERT INTO BOOKS (ID, TITLE, AUTHOR, GENRE, AVAILABLE) VALUES (2, 'The Pillars of the Earth', 'Ken Follett', 'Historical', 0);
INSERT INTO BOOKS (ID, TITLE, AUTHOR, GENRE, AVAILABLE) VALUES (3, 'Clean Code', 'Robert C. Martin', 'Programming', 0);
INSERT INTO BOOKS (ID, TITLE, AUTHOR, GENRE, AVAILABLE) VALUES (4, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Classic', 0);

-- INSERTING into PERSONS
INSERT INTO PERSONS (MAIL, NAME, DNI, PASSWORD) VALUES ('juan.perez@email.com', 'Juan Perez', '12345678A', 'pass123');
INSERT INTO PERSONS (MAIL, NAME, DNI, PASSWORD) VALUES ('ana.garcia@email.com', 'Ana Garcia', '87654321B', 'admin2024');
INSERT INTO PERSONS (MAIL, NAME, DNI, PASSWORD) VALUES ('marta.lopez@email.com', 'Marta Lopez', '11223344C', 'marta77');

-- INSERTING into LOANS
-- Nota: S'ha adaptat el format de data al format estàndard de SQLite (YYYY-MM-DD)
INSERT INTO LOANS (ID_LOAN, BOOK_ID, MEMBER_MAIL, LOAN_DATE, DUE_DATE, RETURN_DATE) VALUES (1, 1, 'juan.perez@email.com', '2026-05-14', '2026-05-28', NULL);
INSERT INTO LOANS (ID_LOAN, BOOK_ID, MEMBER_MAIL, LOAN_DATE, DUE_DATE, RETURN_DATE) VALUES (2, 2, 'ana.garcia@email.com', '2026-05-14', '2026-05-28', NULL);
INSERT INTO LOANS (ID_LOAN, BOOK_ID, MEMBER_MAIL, LOAN_DATE, DUE_DATE, RETURN_DATE) VALUES (3, 3, 'marta.lopez@email.com', '2026-05-14', '2026-05-28', NULL);