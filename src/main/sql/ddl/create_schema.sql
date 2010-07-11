-- Create the "birt" user/schema.
-- Run as SYSTEM.

CREATE USER birt IDENTIFIED BY birt DEFAULT TABLESPACE users;
ALTER USER birt QUOTA UNLIMITED ON users;

GRANT CONNECT TO birt;
GRANT RESOURCE TO birt;

