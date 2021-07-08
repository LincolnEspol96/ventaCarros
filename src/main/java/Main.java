
import java.util.Scanner;
import modelo.Helper;
import modelo.Vehiculo;
import modelo.Vendedor;

public class Main 
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int opcion=1;
        
        boolean salirMenu = false;
        boolean salirOpcionVendedor = false;
        boolean salirOpcionVehiculo = false;
        boolean salirOpcionOfertar  = false;
        boolean salirOpcionComprador = false;    
        
        boolean usuarioYClaveExistente;
        boolean correoYCorreoExistente;

        //Variables del Vendedor
        int opcionVendedor = 1;
        String nombres;
        String apellidos;
        String correoElectronico;
        String organizacion;
        String usuario;
        String clave;   
        
        //Variables Vehiculo
        int opcionVehiculo = 1;
        String placa;
        String marca;
        String modelo;
        String tipoMotor;
        int anio;
        double recorrido = 0;
        String color;
        String tipoCombustible;
        String vidrios;
        String transmision;
        double precio;

        //Mostrar el menu de opciones
        while(!salirMenu)
        {           
            salirOpcionVendedor  = false;
            salirOpcionComprador = false;
            salirOpcionVehiculo  = false; 
            salirOpcionOfertar   = false;
            
            System.out.println("\t\t\tMenu de Opciones");
            System.out.println("\n1. Vendedor ");
            System.out.println("2. Comprador");
            System.out.println("3. Salir\n\n");
            
            System.out.println("Escoja una opcion: ");
            opcion = sc.nextInt();
            
            if(opcion != 1 && opcion != 2 && opcion != 3)
                System.out.println("La opción escogida es incorrecta.");
            
            switch(opcion)
            {
                case 1:
                    while(!salirOpcionVendedor)
                    {
                        System.out.println("\n\n\tMenu del Vendedor");
                        System.out.println("\n1. Registrar un nuevo vendedor");
                        System.out.println("2. Ingresar un nuevo vehi­culo");
                        System.out.println("3. Aceptar oferta");
                        System.out.println("4. Regresar\n");                          

                        System.out.println("Escoja una opcion del vendedor: ");
                        opcionVendedor = sc.nextInt();                                                            
                        
                        if(opcionVendedor != 1 && opcionVendedor != 2 && opcionVendedor != 3 && opcionVendedor != 4)
                            System.out.println("La opción escogida es incorrecta.");
                        
                        switch(opcionVendedor)
                        {
                            case 1:
                                System.out.println("\n\n\t\t\tOpcion 1: Registrar un nuevo vendedor\n");
                                System.out.println("Ingrese sus nombres: ");
                                nombres = sc.next();
                                System.out.println("Ingrese sus apellidos: ");
                                apellidos = sc.next();
                                
                                do
                                {
                                    System.out.println("Ingrese su correo electronico: ");
                                    correoElectronico = sc.next();
                                    if(Helper.chequearCorreoElectronico(correoElectronico))
                                        System.out.println("El correo ya fue registrado en el archivo de texto");
                                }
                                while(Helper.chequearCorreoElectronico(correoElectronico));                                
                                System.out.println("Ingrese el nombre de la organizacion a la que pertenece: ");
                                organizacion = sc.next();
                                System.out.println("Ingrese un nickname para su usuario: ");
                                usuario = sc.next();
                                System.out.println("Ingrese una clave: ");
                                clave =sc.next();
                                Vendedor vendedor = new Vendedor(nombres,apellidos,correoElectronico,organizacion,usuario,clave);                                

                                // Verificar si ya existe el usuario y contraseña
                                usuarioYClaveExistente = Helper.chequearUsuarioContrasenia(usuario, clave);
                                if(usuarioYClaveExistente)
                                    System.out.println("El usuario y clave ya fueron registados en el archivo de texto");
                                else
                                    Helper.guardarVendedor(vendedor);                                    
                                break;
                                
                            case 2:
                                System.out.println("\n\n\t\t\tOpcion 2: Ingresar un nuevo vehi­culo\n");
                                System.out.println("Ingrese su usuario: ");
                                usuario = sc.next();
                                System.out.println("Ingrese su clave: ");
                                clave = sc.next();
                                
                                usuarioYClaveExistente = Helper.chequearUsuarioContrasenia(usuario, clave);
                                if(!usuarioYClaveExistente)
                                    System.out.println("El vendedor ingresado no esta registrado, no es posible registrar un vehiculo");                                    
                                
                                else //Registro de un vehiculo
                                {
                                    while(!salirOpcionVehiculo)
                                    {
                                        System.out.println("\n\n\t\t\tEscoja el tipo de vehiculo");
                                        System.out.println("\n1. Auto");
                                        System.out.println("2. Camioneta");
                                        System.out.println("3. Motocicleta");                                        
                                        System.out.println("4. Regresar\n");
                                        opcionVehiculo = sc.nextInt();                                        
                                        
                                        if(opcionVehiculo != 1 && opcionVendedor != 2 && opcionVehiculo != 3 && opcionVehiculo != 4 )
                                            System.out.println("La opción escogida es incorrecta.");
                                        else
                                        {
                                            if(opcionVehiculo == 4)
                                            {
                                                System.out.println("\n\nRegresando...");
                                                salirOpcionVehiculo = true;
                                                break;
                                            }
                                            else 
                                            {
                                                System.out.println("Ingrese la placa: ");
                                                placa = sc.next();    
                                                boolean verificarPlaca = Helper.chequearPlacaVehiculo(placa);
                                                if(verificarPlaca)
                                                    System.out.println("La placa ya existe en el sistema, no es posible ingresar el vehiculo");
                                                else                                                
                                                {
                                                    System.out.println("Ingrese la marca: ");
                                                    marca = sc.next();
                                                    System.out.println("Ingrese el modelo: ");
                                                    modelo = sc.next();
                                                    System.out.println("Ingrese el tipo de Motor: ");
                                                    tipoMotor = sc.next();
                                                    System.out.println("Ingrese el anio del vehiculo: ");
                                                    anio = sc.nextInt();
                                                    System.out.println("Ingrese el recorrido: ");
                                                    recorrido =sc.nextDouble();
                                                    System.out.println("Ingrese el color: ");
                                                    color =sc.next();
                                                    System.out.println("Ingrese el tipo de combustible: ");
                                                    tipoCombustible = sc.next();
                                                    System.out.println("Ingrese los vidrios: ");
                                                    vidrios = sc.next();
                                                    System.out.println("Ingrese la transmision: ");
                                                    transmision = sc.next();
                                                    System.out.println("Ingrese el precio del vehiculo: ");
                                                    precio = sc.nextDouble();                                                
                                                    Vehiculo vehiculo = new Vehiculo(opcionVehiculo, placa,marca,modelo,tipoMotor,anio,recorrido,color,tipoCombustible,vidrios,transmision,precio);                                
                                                    Helper.guardarVehiculo(vehiculo);
                                                }
                                            }                                            
                                        }                                                                                
                                    }
                                }
                                break;
                                
                            case 4:
                                System.out.println("\n\nRegresando...");
                                salirOpcionVendedor = true;
                                break;
                                
                        }
                    }          
            }
        }
    }            
    
}
