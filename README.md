# Api-Bank

## Construir .jar y contenedor docker
1. mvn clean
2. mvn install
3. docker build -t api-bank:0.0.1 .
4. docker run -d --name api-bank -p 8080:8080 api-bank:0.0.1

## Base de datos utilizada H2

1. Enlace: http://localhost:8080/h2-console
2. JDBC URL: jdbc:h2:mem:testdb
3. User Name: sa

## Importar json a postman
1. Abrir postman
2. Crear nuevo environment 
```
File > New... > Environment > Variable = url > Value = http://localhost:8080
```
3. Importar el archivo postman.json
```
File > Import...
```