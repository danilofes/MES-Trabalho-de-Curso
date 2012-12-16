
--connect 'jdbc:derby://localhost:1527/dcc890';

-- Clone pair
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
and (f1.path < f2.path or (f1.path = f2.path and f1.id < f2.id))
;
-- 70945 rows selected

-- Num de fragmentos por loc.
select (endLine - line + 1), count(*) from dcc890.Fragment group by (endLine - line + 1);

-- File pair
select path1, path2 from dcc890.ClonePair
group by path1, path2
order by path1, path2;
-- 19800 rows selected

-- Clone pairs únicos
select f1.path, f1.line, f1.endLine, f2.path, f2.line, f2.endLine from dcc890.ClonePair
join dcc890.Fragment f1 on (f1.id = fragment1)
join dcc890.Fragment f2 on (f2.id = fragment2)
group by f1.path, f1.line, f1.endLine, f2.path, f2.line, f2.endLine;
-- 59667 rows selected

-- Clone pairs únicos por ferramenta
select f1.path, f1.line, f1.endLine, f2.path, f2.line, f2.endLine, du.cloneResultId from dcc890.ClonePair
join dcc890.Fragment f1 on (f1.id = fragment1)
join dcc890.Fragment f2 on (f2.id = fragment2)
join dcc890.Duplication du on (du.id = f1.duplicationId)
group by f1.path, f1.line, f1.endLine, f2.path, f2.line, f2.endLine, du.cloneResultId;
-- 61175 rows selected

-- Detalhes de um FilePair
select path1, path2 from dcc890.FilePair where id = 7292;
select
  'cp("'||trim(char(f1.line)) ||'-'|| trim(char(f1.endLine)) ||' -> '|| trim(char(f2.line)) ||'-'|| trim(char(f2.endLine))||'", "'
  ||rs.appName||'", '
  ||trim(char(cp.id))||').'
from dcc890.ClonePair cp
join dcc890.FilePair fp on (cp.path1 = fp.path1 and cp.path2 = fp.path2)
join dcc890.Fragment f1 on (f1.id = fragment1)
join dcc890.Fragment f2 on (f2.id = fragment2)
join dcc890.Duplication du on (du.id = f1.duplicationId)
join dcc890.CloneResult rs on (rs.id = du.cloneResultId)
where fp.id = 7292
order by f1.line;


