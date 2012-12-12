create table dcc890.Fragment(
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	duplicationId INTEGER,
	line INTEGER,
	endLine INTEGER,
	path VARCHAR(255)
);

create index idx_path on dcc890.Fragment(path asc);

update dcc890.Fragment set endLine = (select lines from dcc890.Duplication dup where dup.id = duplicationId);
update dcc890.Fragment set endLine = (line + endLine - 1);
commit;

update dcc890.Fragment set path = SUBSTR(path, 43);
commit;
