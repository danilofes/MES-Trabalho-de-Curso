create table dcc890.Fragment(
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	duplicationId INTEGER,
	line INTEGER,
	path VARCHAR(255)
);