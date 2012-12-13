
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

--order by f1.path, f2.path;
-- 48069 rows selected


select * from dcc890.Fragment f1 where exists
(select f2.id from dcc890.Fragment f2
 where f2.id <> f1.id and f2.path = f1.path and f2.line = f1.line and f2.endLine = f1.endLine )


