create table dcc890.ClonePair(
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	duplicationId INTEGER,
	fragment1 INTEGER,
	path1 VARCHAR(255),
	fragment2 INTEGER,
	path2 VARCHAR(255)
);

create index idx_path1_path2 on dcc890.ClonePair(path1 asc, path2 asc);

insert into dcc890.ClonePair(duplicationId, fragment1, path1, fragment2, path2)
select 
  f1.duplicationId,
  f1.id,
  f1.path,
  f2.id,
  f2.path
from dcc890.Fragment f1, dcc890.Fragment f2
where f1.duplicationId = f2.duplicationId
and f1.id <> f2.id
and (f1.path < f2.path or (f1.path = f2.path and f1.id < f2.id));
