
INSERT INTO Curatore (nome, cognome, data_nascita, luogo_nascita, numero_telefono, matricola)
VALUES	('Giulio', 'Carullo', '1997-10-10', 'Roma', '3926785467', 'GC1997'),
		('Gennaro', 'Gullo', '1990-03-11', 'Roma', '3222225467', 'RP1997'),
		('Gary', 'Soak', '1996-04-12', 'New York', '3926785467', 'GC1997');


INSERT INTO Artista (nome_artistico, nome, cognome, data_nascita, luogo_nascita, data_morte, luogo_morte, nazionalita)
VALUES	('Sandro Botticelli', 'Alessandro', 'Di Mariano', '1445-04-17 00:00:00', 'Firenze', '1510-04-17', 'Porto Ercole', 'Italia'),
		('Caravaggio', 'Michelangelo', 'Merisi', '1571-09-29 00:00:00', 'Milano', '1610-07-18', 'Spoleto', 'Italia'),
		(NULL, 'Antoon', 'Van Dyck', '1599-03-22 00:00:00', 'Anversa', '1641-12-09', 'Londra', 'Belgio');


INSERT INTO Collezione(nome, descrizione, curatore_id)
VALUES  ('Dipinti di Botticelli', 'Collezione avente dei dipinti di botticelli', 1),
		('Da Caravaggio ad Antoon Van Dyck', 'Collezione avente dipinti di Caravaggio e Antoon Van Dyck', 2);
		

INSERT INTO Opera (titolo, anno_realizzazione, descrizione, link, artista_id, collezione_id)
VALUES ('Nascita di Venere', 1485,
		'Dipinto a tempera su tela di lino (172,5 cm × 278,5 cm) di Sandro Botticelli. Realizzata per la villa medicea di Castello.',
		'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Sandro_Botticelli_-_La_nascita_di_Venere_-_Google_Art_Project_-_edited.jpg/1920px-Sandro_Botticelli_-_La_nascita_di_Venere_-_Google_Art_Project_-_edited.jpg',
		1, 1),
		('Primavera', 1480,
		'Dipinto a olio su tavola (203 x 314 cm) di Sandro Botticelli, databile per il 1478 circa. Realizzata per la villa medicea di Castello.',
		'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Botticelli-primavera.jpg/1920px-Botticelli-primavera.jpg',
		1, 1),
		('Scudo con testa di Medusa', 1597,
		'Commissionata dal cardinal del Monte per Ferdinando I de Medici. Si tratta di un dipinto a olio montato su uno scudo convesso di legno di pioppo.',
		'https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Caravaggio_-_Medusa_-_Google_Art_Project.jpg/1024px-Caravaggio_-_Medusa_-_Google_Art_Project.jpg',
		2, 2),
		('Autoritratto con Girasole', 1597,
		'Questo autoritratto fu eseguito da van Dyck durante il periodo di fama maggiore: era stato infatti da poco nominato Sir.',
		'https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Anthonyvandyckselfportrait.jpeg/1280px-Anthonyvandyckselfportrait.jpeg',
		3, 2);

SELECT * FROM CURATORE;
SELECT * FROM OPERA;
SELECT * FROM COLLEZIONE;
SELECT * FROM ARTISTA;
SELECT * FROM USERS;
SELECT * FROM ACCOUNT;


/* VVV NO VVV*/

INSERT INTO Account (username, password, ruolo, data_creazione, proprietario_id)
VALUES  ('carullo', 'carullo123', 'ADMIN', '2021-01-01 00:00:00', 1),
		('sturdy', 'sturdy123', 'ADMIN', '2021-01-01 00:00:00', 2),
		('art_master','master123', 'ADMIN', '2021-01-01 00:00:00', 3);

/* VVV PER IMPOSTARE UN UTENTE COME AMMINISTRATORE */

UPDATE Account
SET Ruolo = 'ADMIN'
WHERE Id = 1;

