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
#### Create user
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


#### Find all user
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

  
### Diagrama
![POST-CREATE](https://github.com/armenki/desafio-tecnico/assets/61056513/93023965-ce53-4815-bffb-5c61d85f19ae)
![GET- ALLUSERS](https://github.com/armenki/desafio-tecnico/assets/61056513/2ed70eea-5b9e-45d6-8b2a-adedad573c9e)
![GET-FINDBYID](https://github.com/armenki/desafio-tecnico/assets/61056513/2e430699-8733-49e8-b4f8-d6b06fe3f4ad)
![PUT-UPDATE](https://github.com/armenki/desafio-tecnico/assets/61056513/f0a52225-c3a5-486a-92d7-c076087d0d27)
![DELETE-DELETE](https://github.com/armenki/desafio-tecnico/assets/61056513/d4ba76c2-9c54-466b-b9fe-e2fe33b3f35f)









