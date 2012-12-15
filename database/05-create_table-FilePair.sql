create table dcc890.FilePair(
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	path1 VARCHAR(255),
	path2 VARCHAR(255)
);

create unique index unq_path1_path2 on dcc890.FilePair(path1 asc, path2 asc);

insert into dcc890.FilePair(path1, path2)
select path1, path2 
from dcc890.ClonePair
group by path1, path2
order by path1, path2;

commit;
