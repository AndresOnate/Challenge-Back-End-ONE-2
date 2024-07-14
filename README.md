# Challenge Back End ONE 2

# LiterAlura

## Descripción

**LiterAlura** es una aplicación de consola en Java que permite consultar y gestionar información sobre libros y autores. Utiliza una API externa para obtener datos sobre libros y guarda estos datos en una base de datos utilizando JPA y Hibernate. La aplicación incluye funcionalidades para buscar libros por título, listar libros y autores registrados, listar autores vivos en un determinado año y buscar libros por idioma.

## Estructura del Proyecto

El proyecto está dividido en las siguientes clases principales:

### 1. `Principal`
- **Ubicación**: `com.alura.challenge.LiterAlura.main.Principal`
- **Descripción**: Clase principal que gestiona la interacción con el usuario y las operaciones principales del programa, incluyendo la búsqueda de libros por título, la visualización de libros y autores registrados, y la búsqueda de autores vivos en un año determinado.

### 2. `Book`
- **Ubicación**: `com.alura.challenge.LiterAlura.model.Book`
- **Descripción**: Entidad que representa un libro en la base de datos. Contiene atributos como título, autor, idiomas y número de descargas.

### 3. `Author`
- **Ubicación**: `com.alura.challenge.LiterAlura.model.Author`
- **Descripción**: Entidad que representa un autor en la base de datos. Contiene atributos como nombre, año de nacimiento y año de fallecimiento. También tiene una relación con la entidad `Book`.

### 4. `BookRepository`
- **Ubicación**: `com.alura.challenge.LiterAlura.repository.BookRepository`
- **Descripción**: Interfaz para el manejo de operaciones CRUD sobre la entidad `Book` en la base de datos.

### 5. `AuthorRepository`
- **Ubicación**: `com.alura.challenge.LiterAlura.repository.AuthorRepository`
- **Descripción**: Interfaz para el manejo de operaciones CRUD sobre la entidad `Author` en la base de datos.

### 6. `ConsumoAPI`
- **Ubicación**: `com.alura.challenge.LiterAlura.service.ConsumoAPI`
- **Descripción**: Clase que maneja las solicitudes a la API externa para obtener datos sobre libros.

### 7. `ConvierteDatos`
- **Ubicación**: `com.alura.challenge.LiterAlura.service.ConvierteDatos`
- **Descripción**: Clase que convierte los datos obtenidos de la API en objetos Java.

## Configuración

1. **Clonar el repositorio**:
    ```sh
    git clone https://github.com/AndresOnate/Challenge-Back-End-ONE-2.git
    ```

2. **Configurar la base de datos**:
   - Asegúrate de tener una base de datos configurada y actualiza el archivo `application.properties` con los detalles de conexión a la base de datos.

3. **Compilar y ejecutar**:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

## Uso

Una vez que la aplicación esté en ejecución, puedes interactuar con ella a través de la consola:

![image](https://github.com/user-attachments/assets/b308265f-44d5-4285-a20a-62bccd92781a)


1. **Buscar libro por título**: Ingresa el título del libro y la aplicación buscará el libro en la API externa, lo guardará en la base de datos si es nuevo, y mostrará la información del libro.

2. **Listar libros registrados**: Muestra todos los libros que están registrados en la base de datos.

3. **Listar autores registrados**: Muestra todos los autores que están registrados en la base de datos.

4. **Listar autores vivos en un determinado año**: Ingresa un año y la aplicación mostrará todos los autores que estaban vivos en ese año.

5. **Buscar libros por idioma**: Ingresa un idioma y la aplicación mostrará todos los libros que están en el idioma seleccionado.


## Dependencias

- Spring Boot
- Hibernate / JPA
- Java 17 (o superior)
- Maven


