FROM postgres:11-alpine

ADD db/create-db.sql /docker-entrypoint-initdb.d