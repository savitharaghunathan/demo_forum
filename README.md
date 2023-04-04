# demo_forum
Konveyor Forum Demo

# DB config: 
* Refer to [application settings](src/main/resources/application.properties)

# Local Development

## Start database using podman
```
podman run --network host -p 3306:3306 --name=mariadb -e MYSQL_ROOT_PASSWORD=mypassword -d docker.io/mariadb/server
```

## Create DB and User
Log in to MariaDB:
```
$ mysql -h localhost -P 3306 --protocol=tcp -u root -p
```

Create DB and users
```
CREATE DATABASE demodb;
CREATE USER demouser IDENTIFIED BY 'demo123';
GRANT ALL PRIVILEGES ON demodb.* TO 'demouser'@'%';
FLUSH PRIVILEGES;
```

## Start the application
```
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

