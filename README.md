### Programas y versiones
- java 17.0.10
- maven 3.9.6
- Eclipse 4.30.0
- springboot spring tools 4 (aka spring tool suite 4) 4.22.1
- lombok 1.18.3

### Compilación
- importar como proyecto existente de maven
- Desde el dashboard de springboot seleccionar el proyecto y start o por linea de comando `$ mvn spring-boot:run`

### Configuracion
- Expresiones regulares, mensajes de error y constantes se encuentran en el paquete utils clase constantes
- jwt se encuentra en la carpeta resources en el archivo application.yml

### Script
- al ser un base de datos h2 que solo utiliza memoria no se necesita los script ya que esta automaticamente crea la base de datos al momento de inicializar la aplicacion

### Endpoints
#### create user
- http://localhost:8080/desafio/tecnico/users/create
```
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter2",
"phones": [
			{
				"number": "1234567",
				"citycode": "1",
				"contrycode": "57"
			}
		]
}
```


#### find all user
- http://localhost:8080/desafio/tecnico/users/list

#### find By Id User
- http://localhost:8080/desafio/tecnico/users/{id}

#### Update user
- http://localhost:8080/desafio/tecnico/users/update/{id}
```
"name": "Pedro Rodriguez",
"email": "pedro@rodriguez.org",
"password": "abcdfeg3",
"phones": [
			{
				"number": "1234567",
				"citycode": "1",
				"contrycode": "57"
			}
		]
}
```
#### Delete User
- http://localhost:8080/desafio/tecnico/users/delete/{id}




