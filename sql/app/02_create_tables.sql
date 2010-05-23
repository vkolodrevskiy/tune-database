-- Creates tables for 'testing_db' database
--
-- NOTE:  Must be connected to database 'testing_db'
-- ****************************************************************************

-- queries
CREATE TABLE template (
   id                         bigserial NOT NULL,

   template                   text NOT NULL,
   test_id                    bigint NOT NULL,

   PRIMARY KEY (id),
   UNIQUE (template, test_id)
) ;

COMMENT ON TABLE template IS
'Contains sql queries.';

-- tests
CREATE TABLE test (
   id                         bigserial NOT NULL,

   name                       varchar(100) NOT NULL,

   PRIMARY KEY (id),
   UNIQUE (name)
) ;

COMMENT ON TABLE test IS
'Tests, one test contains many queries.';

-- test results
CREATE TABLE result (
   id                         bigserial NOT NULL,

   test_id                    bigint NOT NULL,
   -- test duration time in ms
   duration                   bigint NOT NULL,
   -- average time of each query in ms
   average                    bigint NOT NULL,
   -- when test was run
   execution_date             timestamp NOT NULL,

   PRIMARY KEY (id)
) ;

COMMENT ON TABLE result IS
'Stores test results.';
