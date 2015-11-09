create view all_data as 
select A.id, B.user_id, B.created_at, A.language 
          from projects as A, watchers as B 
          where A.language = 'java' 
          and A.id = B.repo_id