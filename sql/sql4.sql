create view data_count as 
select id, count(id) as mycount from all_data group by id order by mycount desc