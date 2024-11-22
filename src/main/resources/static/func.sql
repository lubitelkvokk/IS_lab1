-- Рассчитать среднее значение поля rating для всех объектов.
CREATE OR REPLACE FUNCTION AVG_RAITING()
    RETURNS DOUBLE PRECISION
AS
$$
SELECT avg(rating)
FROM worker;
$$ LANGUAGE SQL;

SELECT avg_raiting();
DROP FUNCTION IF EXISTS get_workers_by_personid(integer);

-- Вернуть количество объектов, значение поля person которых равно заданному.
CREATE OR REPLACE FUNCTION get_workers_by_personid(int) RETURNS SETOF worker AS
$$
SELECT *
FROM worker
WHERE worker.person_id = $1;
$$ LANGUAGE SQL;

SELECT *
FROM get_workers_by_personid(104);

-- Вернуть массив объектов, значение поля name которых начинается с заданной подстроки.
CREATE OR REPLACE FUNCTION get_workers_by_substring_name(text) RETURNS SETOF worker AS
    $$
       SELECT *
        FROM worker
        WHERE position($1 in worker.name) = 1
    $$ LANGUAGE SQL;

