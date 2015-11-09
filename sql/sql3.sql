select A.*, B.mycount
from all_data as A, data_count as B
where A.id = B.id
order by B.mycount desc