
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
select path1, path2, count(*) from dcc890.ClonePair group by path1, path2;
-- 19800 rows selected

-- Clone pairs únicos
select f1.path, f1.line, f1.endLine, f2.path, f2.line, f2.endLine from dcc890.ClonePair
join dcc890.Fragment f1 on (f1.id = fragment1)
join dcc890.Fragment f2 on (f2.id = fragment2)
group by f1.path, f1.line, f1.endLine, f2.path, f2.line, f2.endLine;
-- 59667 rows selected



