dist:
	mvn -DskipTests clean package

build: dist
	docker-compose build

up:
	docker-compose up -d

up-db:
	docker-compose up -d zip-db

up-app:
	docker-compose up -d zip

stop:
	docker-compose stop
	
down:
	docker-compose down
	
test:
	mvn test