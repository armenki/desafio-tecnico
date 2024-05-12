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
```json
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
```json
"name": "Pedro Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter25",
"phones": [
      {
        "number": "46",
	"citycode": "5",
	"contrycode": "1"
      }
	]
}
```
#### Delete User
- http://localhost:8080/desafio/tecnico/users/delete/{id}

  
### Diagrama
![POST-CREATE](https://github.com/armenki/desafio-tecnico/assets/61056513/8d125f59-01f8-462f-9a2b-2de3fc7e192d)
![GET- ALLUSERS](https://github.com/armenki/desafio-tecnico/assets/61056513/0e6b930e-976b-4543-a253-e5a374773bb0)
![GET-FINDBYID](https://github.com/armenki/desafio-tecnico/assets/61056513/fd2e21bb-cda0-412f-9030-5f8cf9ac962f)
![DELETE-DELETE](https://github.com/armenki/desafio-tecnico/assets/61056513/af98424f-54d1-4da5-8d04-c852ab57e149)
![PUT-UPDATE](https://github.com/armenki/desafio-tecnico/assets/61056513/4f86f057-8c09-4db5-a1a7-3b79baec2479)










