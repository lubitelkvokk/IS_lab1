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

SELECT *
FROM get_workers_by_substring_name('Иван');

-- Переместить сотрудника из одной организации в другую
-- с сохранением должности и заработной платы.
CREATE OR REPLACE FUNCTION change_worker_organization(worker_id integer,
                                                      o_id integer)
    RETURNS void
    LANGUAGE plpgsql
AS
$$
BEGIN
    IF NOT EXISTS(SELECT * FROM worker WHERE worker.id = worker_id)
    THEN
        RAISE EXCEPTION 'Nonexistent worker ID --> %', worker_id;
    END IF;
    IF NOT EXISTS(SELECT * FROM organization WHERE organization.id = o_id)
    THEN
        RAISE EXCEPTION 'Nonexistent organization ID --> %', o_id;
    END IF;
    UPDATE worker
    SET organization_id = o_id
    WHERE worker.id = worker_id;
END;
$$;

-- SELECT change_worker_organization(134, 4);

-- Проиндексировать заработную плату указанному сотруднику на заданный коэффициент.
CREATE OR REPLACE FUNCTION index_salary_to_worker_by_coefficient(worker_id integer,
                                                                 coeff double precision)
    RETURNS double precision
    LANGUAGE plpgsql
AS
$$
DECLARE
    new_salary double precision;
BEGIN
    IF NOT EXISTS(SELECT * FROM worker WHERE worker.id = worker_id)
    THEN
        RAISE EXCEPTION 'Nonexistent worker ID --> %', worker_id;
    END IF;
    UPDATE worker
    set salary = salary * coeff
    WHERE worker.id = worker_id
    RETURNING salary INTO new_salary;

    return new_salary;
END;
$$;

SELECT index_salary_to_worker_by_coefficient(1, 1.05);