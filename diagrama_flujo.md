# Diagrama de Flujo: Simulador Mundial UMG

Este archivo contiene el diagrama de flujo lógico del sistema, basado en el controlador de simulación (`SimulacionController.java`) y la gestión de entidades. 
Puedes visualizar el siguiente gráfico usando cualquier visor de Markdown que soporte **Mermaid** (como GitHub o una extensión en VS Code).

```mermaid
graph TD
    %% Definición de Estilos
    classDef inicio fill:#28a745,stroke:#fff,stroke-width:2px,color:#fff;
    classDef proceso fill:#007bff,stroke:#fff,stroke-width:2px,color:#fff;
    classDef decision fill:#ffc107,stroke:#fff,stroke-width:2px,color:#000;
    classDef vista fill:#6f42c1,stroke:#fff,stroke-width:2px,color:#fff;

    A(["Inicio: Panel Principal"]) --> B{"¿Qué desea hacer el usuario?"}
    class A inicio;
    class B decision;

    %% Rama de Gestión de Datos (CRUD)
    B -->|Gestión de Catálogos| C1["Administrar Equipos"]:::proceso
    B -->|Gestión de Catálogos| C2["Administrar Jugadores"]:::proceso
    B -->|Gestión de Catálogos| C3["Ver Estadios"]:::vista

    %% Rama Principal de Simulación
    B -->|Ir a Simulación| D1["Cargar Dashboard Fase de Grupos"]:::vista
    
    D1 --> D2{"¿El sorteo ya fue realizado?"}:::decision
    D2 -- No --> D3["Ejecutar Sorteo"]:::proceso
    D3 --> D4["Limpiar BD, Asignar Grupos, Crear Partidos"]:::proceso
    D4 --> D1

    D2 -- Sí --> D5{"¿Fase de Grupos Terminada?"}:::decision
    D5 -- No --> D6["Simular Fase de Grupos"]:::proceso
    D6 --> D7["Generar Goles y Tarjetas, Actualizar Tablas"]:::proceso
    D7 --> D1

    D5 -- Sí --> D8["Ir a Fase Final Bracket"]:::vista
    D8 --> D9{"¿Existen llaves de Octavos de Final?"}:::decision
    
    D9 -- No --> D10["Lógica Matemática: Generar Octavos Desde Grupos"]:::proceso
    D10 --> D11["Clasifican 12 Líderes de Grupo y los 4 Mejores Segundos"]:::proceso
    D11 --> D8
    
    D9 -- Sí --> D12{"¿Final Finalizada? Torneo Terminado"}:::decision
    
    D12 -- No --> D13["Simular Llaves Actuales"]:::proceso
    D13 --> D14["Asignar Goles Aleatorios sin Empates"]:::proceso
    D14 --> D15["Avanzar Ganadores y Generar Siguiente Ronda"]:::proceso
    D15 --> D8
    
    D12 -- Sí --> D16(["Coronar Campeón del Torneo"]):::inicio
    D16 --> D17(["Mostrar Top Goleadores y Mejores Porteros"]):::inicio
    
    D17 -->|Reiniciar| A
```