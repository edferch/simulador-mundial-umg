# 🏆 World Cup Logistics & Simulator UMG

Este es el proyecto oficial del simulador del mundial para la Universidad Mariano Gálvez. El sistema utiliza una arquitectura de Backend en Java y un Frontend web para el motor de reproducción visual de los partidos.

## 🛠️ Tecnologías Utilizadas
* **Backend:** Java + Spring Boot (JPA / Hibernate)
* **Base de Datos:** PostgreSQL
* **Frontend:** HTML, CSS, JavaScript (Próximamente)

## 🚀 Instrucciones para el Equipo (Instalación)

Para correr este proyecto en tu máquina local, sigue estos pasos al pie de la letra:

### 1. Preparar la Base de Datos
1. Abre **pgAdmin 4** y conéctate a tu servidor local.
2. Crea una base de datos vacía llamada exactamente **`mundial_umg`** (todo en minúsculas).
3. No es necesario crear tablas a mano, el código en Java lo hará automáticamente gracias a JPA.

### 2. Configurar el Proyecto
1. Clona este repositorio en tu computadora:
   `git clone [URL_DEL_REPOSITORIO]`
2. Abre la carpeta del proyecto en **VS Code**.
3. Ve a la ruta: `src/main/resources/application.properties`.
4. Busca la línea `spring.datasource.password=` y coloca **tu contraseña personal** de PostgreSQL. *(Nota: ¡No subas tu contraseña a GitHub en futuros commits!)*

### 3. Levantar el Servidor
1. Ve a `src/main/java/com/umg/simulador_mundial/SimuladorMundialApplication.java`.
2. Dale clic al botón de **Run** en VS Code.
3. Si la terminal muestra `Started Application...`, revisa pgAdmin: la tabla `equipos` ya debería estar creada.