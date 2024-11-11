# Primer MInimo
## Descripción del Proyecto
Este es un sistema de juego virtual basado en un mapa del campus, que implementa la gestión de usuarios y el seguimiento de puntos de interés a través de una API REST. El juego se basa en un mapa del campus dividido en cuadrículas, donde los jugadores pueden moverse entre diferentes puntos de interés (como puertas, muros, puentes, etc.).

## Estructura del Proyecto
El proyecto utiliza una estructura Maven estándar y contiene los siguientes componentes:

### Componentes Principales
- Gestión de Usuarios
- Gestión de Puntos de Interés
- Seguimiento de Interacción Usuario-Punto


### Funcionalidades Completadas y Operativas

#### Gestión de Usuarios (UserService)
- Añadir nuevos usuarios
- Listar todos los usuarios ordenados alfabéticamente
- Consultar usuario por ID
- Generación aleatoria de ID

#### Gestión de Puntos de Interés (GameService)
- Añadir puntos de interés
- Obtener todos los puntos de interés
- Buscar puntos por tipo
- Obtener punto por coordenadas

#### Interacción Usuario-Punto
- Registrar paso de usuario por punto de interés
- Consultar puntos visitados por usuario
- Consultar usuarios que han pasado por un punto

#### Implementaciones Técnicas
- Implementación API REST
- Integración documentación Swagger
- Cobertura de pruebas unitarias
- Registro de logs con Log4j
-  Implementación patrón Singleton
- Manejo de excepciones

### Problemas Resueltos
1. Corrección en la generación de ID de usuario - Ahora genera IDs aleatorios correctamente
2. Solución al problema del formato de fecha - Ahora se almacena como String
3. Corrección del problema de resultados vacíos en la asociación usuario-punto
4. Optimización de la estructura de servicios, separando el servicio de usuarios del servicio de juego

### Problemas Conocidos
1. Validación de formato de fecha pendiente de mejora
2. Falta validación de entrada de usuario
3. Necesidad de más mecanismos de manejo de errores

### Funcionalidades Pendientes
1. Autenticación y autorización de usuarios
2. Persistencia de datos de usuario
3. Implementación de reglas de juego más complejas
4. Historial de actividades del usuario
5. Optimización de rendimiento y mecanismos de caché

