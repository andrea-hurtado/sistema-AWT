# Proyecto AWT

## Descripción
Este proyecto tiene como objetivo la creación de un sistema de gestión de compras para una empresa, automatizando los procesos de adquisición de productos, gestión de proveedores y seguimiento de solicitudes de compra. En esta segunda fase del proyecto, se ha incorporado una Interfaz Gráfica de Usuario (GUI) utilizando la librería AWT de Java, como continuación de un sistema previamente desarrollado que solo contaba con interfaz en consola.

Este sistema está diseñado para optimizar los procesos internos de las empresas, permitiendo la gestión de compras de manera ágil y eficaz, con una transición exitosa de la interfaz de consola a una interfaz gráfica interactiva.

Implementando: Gestión de Proveedores y Productos: Los usuarios pueden registrar, editar y eliminar proveedores y productos, lo que facilita el manejo de inventarios.

Solicitud de Compras: Los usuarios pueden crear y gestionar solicitudes de compra, con la capacidad de calcular totales de compras.

Interfaz Gráfica de Usuario: Se ha implementado una interfaz visual e interactiva utilizando AWT para permitir una experiencia de usuario más amigable y accesible.

Búsquedas y Filtrados: La aplicación permite buscar proveedores, productos y solicitudes por diversos criterios, como el ID o nombre, mejorando la eficiencia en el manejo de datos.

## Objetivos del proyecto
Aplicar conceptos básicos de desarrollo de interfaces gráficas en Java (AWT).

Integrar la lógica orientada a objetos desarrollada previamente con una capa de presentación visual.

Comprender la manipulación de eventos en AWT mediante clases anónimas y adaptadores.

Mantener una mínima separación de responsabilidades entre la lógica de negocio y la interfaz gráfica.

Desarrollar un prototipo funcional de una aplicación de escritorio.

Elaborar un informe técnico que documente el desarrollo del proyecto y el diseño del prototipo.

## Funcionalidades
1. Ingresar datos del proveedor, productos y/o empleados.
2. Ingresar datos de una orden de compra con estado inicial SOLICITADA.
3. Visualizar la lista de órdenes registradas, incluyendo (pero no limitado a):
4. Subtotal.
5. Descuentos aplicados.
6. IVA.
7. Total de la orden.
8. Estado de la orden: SOLICITADA, EN_REVISIÓN, APROBADA, RECHAZADA.
9. Cambiar el estado de la orden de compra a: EN_REVISIÓN, APROBADA, RECHAZADA.
10. Limpiar los campos de los formularios.
11. Salir del sistema de forma controlada.

## Diagrama UML

El sistema está representado mediante un diagrama UML que muestra las relaciones entre las clases, incluyendo la herencia entre Persona y Proveedor, la implementación de la interfaz OperacionSolicitud por SolicitudCompra, y las enumeraciones para los estados de las solicitudes.

## Estructura del proyecto

```
ProyectoAWT/
├── .idea/                       
│   ├── .gitignore
│   ├── misc.xml
│   ├── modules.xml
│   ├── vcs.xml
│   └── workspace.xml
├── out/                            
├── src/                            
│   └── ec.edu.ups.erp/         
│       ├── controllers/          
│       │   └── Controller.java
│       ├── enums/                  
│       │   └── Estado.java         
│       ├── models/              
│       │   ├── Calculable.java    
│       │   ├── Documento.java     
│       │   ├── Empleado.java      
│       │   ├── EstadoSolicitud.java
│       │   ├── GestorCompras.java  
│       │   ├── GestorEmpleados.java 
│       │   ├── GestorUsuarios.java 
│       │   ├── Persona.java        
│       │   ├── Producto.java      
│       │   ├── Proveedor.java      
│       │   ├── SolicitudCompra.java 
│       │   └── Usuario.java       
│       └── vista/                 
│           ├── VentanaAgregarEmpleado.java
│           ├── VentanaAgregarProducto.java
│           ├── VentanaAgregarProveedor.java
│           ├── VentanaGestionEstados.java
│           ├── VentanaInicioSesion.java
│           ├── VentanaListarEmpleados.java
│           ├── VentanaMenu.java
│           ├── VentanaPrincipal.java
│           ├── VentanaRegistroUsuario.java
│           ├── VentanaSolicitud.java
│           ├── VentanaSolicitudCompra.java
│           └── Principal.java       
├── External Libraries/             
├── Scratches and Consoles/      
├── README.md                    
├── .gitignore                     
└── ProyectoERP.iml               
```
## Contribuciones

Este proyecto fue desarrollado como parte de una práctica de programación orientada a objetos.