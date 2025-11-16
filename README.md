# Sistema de Gestión de una Clínica
&nbsp;&nbsp;El presente informe documenta la implementación completa del Sistema de Gestión de una Clínica Privada, un proyecto desarrollado en dos etapas. El objetivo general del mismo ha sido modelar un sistema robusto que maneje la gestión de pacientes y médicos (Etapa I) y se amplíe con un módulo de simulación de ambulancia con concurrencia y una interfaz gráfica de usuario (Etapa II).
&nbsp;&nbsp;Este informe detalla la estructura final del proyecto, la justificación de las decisiones de diseño, y la aplicación práctica de los patrones de diseño requeridos, presentando un Diagrama de Clases UML y e código representativo de las implementaciones clave.

&nbsp;&nbsp;A lo largo del proyecto se han utilizado diferentes patrones de diseño:
- Patrón Facade: para simplificar la interacción entre las distintas partes del sistema.
- Patrón Singleton: para garantizar que exista una única instancia de la clase Clinica, SistemaFacade y conexión a base de datos.
- Patrón Factory: con el objetivo de centralizar y simplificar la creación de aquellos objetos que presentan múltiples variantes según sus características, como lo son los médicos y los pacientes.
- Patrón Decorator: utilizado para calcular los honorarios de los médicos de acuerdo con las distintas combinaciones de características (especialidad, contratación y posgrado).
- Patrón Double Dispatch: para controlar el ingreso de pacientes a la sala de espera, de acuerdo a su rango etario y el del paciente que se encuentra actualmente en la sala privada se decide si el que ingresa toma su lugar o va al patio.
- Patrón State: La ambulancia puede encontrar en varias situaciones diferentes y cada una de estas determina que acciones están o no permitidas y como debe reaccionar la ambulancia.
- Patrón Observer: El patrón mantiene sincronizada la vista con el modelo, permitiendo que distintos componentes del sistema reciban notificaciones automáticas cuando ocurren cambios en el modelo. 
- Patrón MVC:    En el proyecto se implementa el patrón MVC para separar responsabilidades y mantener el código organizado, escalable y fácil de mantener. El modelo contiene la lógica del dominio de la clínica, la vista es la GUI del proyecto (muestra datos al usuario, recibe notificaciones de este, no ejecuta lógica de negocio y depende del Controlador para interactuar con el modelo), y el Controlador es el intermediario entre los dos anteriores.

