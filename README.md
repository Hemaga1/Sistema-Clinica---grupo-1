# Sistema de Gestión de una Clínica
&nbsp;&nbsp;El sistema desarrollado corresponde a la primera etapa de un Sistema de Gestión de una Clínica Privada, que permite manejar información de médicos y pacientes, así como gestionar el ingreso, atención, internación, egreso y facturación de los pacientes.

&nbsp;&nbsp;El objetivo principal fue modelar el dominio de la clínica aplicando los principios de la Programación Orientada a Objetos, junto con patrones de diseño para estructurar el código de manera extensible y modular, los cuales serán explicados más adelante.

&nbsp;&nbsp;El sistema se ejecuta mediante un método main sin interfaz gráfica (en esta primera parte), simulando las distintas operaciones de la clínica a través de llamadas a los métodos del sistema.

&nbsp;&nbsp;A lo largo del proyecto se han utilizado diferentes patrones de diseño:
- Patrón Facade: para simplificar la interacción entre las distintas partes del sistema.
- Patrón Singleton: para garantizar que exista una única instancia de la clase Clinica y SistemaFacade.
- Patrón Factory: con el objetivo de centralizar y simplificar la creación de aquellos objetos que presentan múltiples variantes según sus características, como lo son los médicos y los pacientes.
- Patrón Decorator: utilizado para calcular los honorarios de los médicos de acuerdo con las distintas combinaciones de características (especialidad, contratación y posgrado).
- Patrón Double Dispatch: para controlar el ingreso de pacientes a la sala de espera, de acuerdo a su rango etario y el del paciente que se encuentra actualmente en la sala privada se decide si el que ingresa toma su lugar o va al patio.

## Estructura del proyecto
```
📁GestionClinica/
└── 📁src/
    └── 📁main/
        └── 📁java/
            ├── 📦excepciones
            │   ├── ⓔ DesocupacionPacienteInexistenteExcepcion
            │   ├── ⓔ InternacionCapacidadExcedidadExcepcion
            │   ├── ⓔ MedicoDuplicadoExcepcion
            │   ├── ⓔ MedicoNoRegistradoExcepcion
            │   ├── ⓔ PacienteDubplicadoExcepcion
            │   ├── ⓔ PacienteNoEstaEsperandoExcepcion
            │   ├── ⓔ PacienteNoRegistradoExcepcion
            │   ├── ⓔ PacienteSinAtenderExcepcion
            │   └── ⓔ SalaEsperaVaciaExcepcion
            ├── 📦factoria
            │   ├── © FactoryMedico
            │   └── © FactoryPaciente
            ├── 📦facturacion
            │   ├── © ConsultaMedica
            │   ├── © Factura
            │   ├── © PacienteAtendido
            │   ├── © RegistroPaciente
            │   └── © ReporteActividadMedica
            ├── 📦honorarios
            │   ├── © ContratacionPermanente
            │   ├── © ContratacionResidente
            │   ├── © EspecialidadCirugia
            │   ├── © EspecialidadClinica
            │   ├── © EspecialidadPediatria
            │   ├── © HonorarioDecorator
            │   ├── Ⓘ IMedico
            │   ├── © PosgradoDecorator
            │   └── © PosgradoMagister
            ├── 📦lugares
            │   ├── © Habitacion
            │   ├── © HabitacionCompartida
            │   ├── © HabitacionPrivada
            │   ├── © HabitacionTerapiaIntensiva
            │   ├── © Patio
            │   ├── © SalaDeEspera
            │   └── © SalaDeEsperaPrivada
            ├── 📦personas
            │   ├── © Domicilio
            │   ├── © Joven
            │   ├── © Mayor
            │   ├── © Medico
            │   ├── © Ninio
            │   ├── © Paciente
            │   └── © Persona
            ├── 📦prueba
            │   └── © Main
            └── 📦sistema
                ├── 📦ModuloAtencion
                │   ├── © ServicioInternaciones
                │   ├── © ServicioMedicos
                │   ├── © ServicioPacientes
                │   └── © SistemaAtencion
                ├── 📦ModuloEgreso
                │   └── © SistemaEgreso
                ├── 📦ModuloIngreso
                │   └── © SistemaIngreso
                ├── © Clinica
                └── © SistemaFacade  
```

