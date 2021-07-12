
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Comprador;
import modelo.Helper;
import modelo.Oferta;
import modelo.Vehiculo;
import modelo.Vendedor;

public class Main 
{
    static Scanner sc=new Scanner(System.in);
    static int opcion=1;

    static boolean salirMenu = false;
    static boolean salirOpcionVendedor = false;
    static boolean salirOpcionVehiculo = false;
    static boolean salirOpcionOfertar  = false;
    static boolean salirOpcionComprador = false;    

    static boolean usuarioYClaveExistente;
    static boolean correoYCorreoExistente;

    //Variables del Vendedor
    static int opcionVendedor = 1;
    static String nombres;
    static String apellidos;
    static String correoElectronico;
    static String organizacion;
    static String usuario;
    static String clave;   
    static ArrayList<Vendedor> listaVendedores = new ArrayList<>();

    //Variables Vehiculo
    static int opcionVehiculo = 1;
    static String placa;
    static String marca;
    static String modelo;
    static String tipoMotor;
    static int anio;
    static double recorrido = 0;
    static String color;
    static String tipoCombustible;
    static String vidrios;
    static String transmision;
    static double precio;
    static ArrayList<Vehiculo> listaVehiculos;

     //Variables Oferta
    static ArrayList<Oferta> listaOfertas = new ArrayList<>();        
    static int opcionNumOferta;   
       
    
    //Variables Comprador 
    static int opcionComprador = 1;
    static int tipoVehiculoBusqueda;
    static double recorridoVehiculoBusqueda;
    static int anioVehiculoBusqueda;
    static double precioVehiculoBusqueda;
    static ArrayList<Comprador> listaCompradores = new ArrayList<>();            
    static Comprador compradorSeleccionado = new Comprador();
    
    public static void main(String[] args)
    {        
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
                                
                            case 3:
                                System.out.println("\n\n\t\t\tOpcion 3: Aceptar oferta\n");
                                System.out.println("Ingrese su usuario: ");
                                usuario = sc.next();
                                System.out.println("Ingrese su clave: ");
                                clave = sc.next();
                                                                                                
                                usuarioYClaveExistente = Helper.chequearUsuarioContrasenia(usuario, clave);
                                if(!usuarioYClaveExistente)
                                    System.out.println("El vendedor ingresado no esta registrado, no es posible aceptar oferta");                                
                                else
                                {
                                    listaVendedores = Helper.getListaVendedores();
                                    vendedor = Helper.chequearVendedor(listaVendedores, usuario, clave);
                                    System.out.println("Ingrese la placa: ");
                                    placa = sc.next();    
                                    boolean verificarPlaca = Helper.chequearPlacaVehiculo(placa);
                                    if(!verificarPlaca)
                                        System.out.println("La placa del vehiculo ingresada no existe en el sistema...");
                                    else
                                    {
                                        System.out.println(Helper.getInformacionVehiculo(placa));
                                        listaOfertas = Helper.getOfertasVehiculo(placa);
                                        if(listaOfertas.isEmpty())
                                            System.out.println("No hay ofertas disponibles para la placa del vehiculo...");
                                        else          
                                        {
                                            listaVehiculos = Helper.getListaVehiculos();
                                            aceptarOfertaVehiculo(vendedor,listaOfertas, listaVehiculos,sc);                                        
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
                    break;
                    
                case 2:
                    while(!salirOpcionComprador)
                    {
                        System.out.println("\n\n\t\t\tOpciones del Comprador");
                        System.out.println("\n1. Registrar un nuevo Comprador");
                        System.out.println("2. Ofertar por un vehi­culo");                        
                        System.out.println("3. Regresar\n");                          

                        System.out.println("Escoja una opcion: ");
                        opcionComprador = sc.nextInt(); 
                        
                        switch(opcionComprador)
                        {
                            case 1:
                                System.out.println("\n\n\t\t\tOpcion 1: Registrar un nuevo comprador\n");
                                System.out.println("Ingrese sus nombres: ");
                                nombres = sc.next();
                                System.out.println("Ingrese sus apellidos: ");
                                apellidos = sc.next();
                                System.out.println("Ingrese su correo electronico: ");
                                correoElectronico = sc.next();
                                System.out.println("Ingrese una organizacion: ");
                                organizacion = sc.next();                                
                                System.out.println("Ingrese un nickname para su usuario: ");
                                usuario = sc.next();
                                System.out.println("Ingrese una clave: ");
                                clave =sc.next();
                                
                                Comprador comprador = new Comprador(nombres,apellidos,correoElectronico,organizacion,usuario,clave);                                                               
                                Helper.guardarComprador(comprador);                                                               
                                break;
                                
                            case 2:
                                System.out.println("\n\n\t\t\tOpcion 2: Ofertar por un vehiculo\n");
                                System.out.println("Ingrese su usuario: ");
                                usuario = sc.next();
                                System.out.println("Ingrese su clave: ");
                                clave = sc.next();
                                
                                usuarioYClaveExistente = Helper.chequearUsuarioContraseniaComprador(usuario, clave);
                                if(!usuarioYClaveExistente)
                                    System.out.println("El comprador ingresado no esta registrado, no es posible realizar oferta");                                
                                else
                                {
                                    listaCompradores = Helper.getListaCompradores();
                                    compradorSeleccionado = Helper.chequearComprador(listaCompradores, usuario, clave);
                                    if(compradorSeleccionado == null)
                                        System.out.println("El comprador seleccionado no existe.");                                
                                    else
                                    {
                                        ArrayList<Vehiculo> vehiculos = Helper.getListaVehiculos();
                                        ofertarPorVehiculo(compradorSeleccionado,vehiculos, sc);
                                    }
                                }                                
                                break;
                                
                            case 3:
                                System.out.println("\n\nRegresando...");
                                salirOpcionComprador = true;
                                break;                                
                        }                             
                        
                    }
                    break;
            }            
        }
    }
    
    public static void ofertarPorVehiculo(Comprador comprador,ArrayList<Vehiculo> vehiculos,Scanner sc)
    {
        String tipoVehiculo="-",rangoRecorrido,rangoAnio,rangoPrecio;                
        System.out.println("Por favor ingrese uno o varios criterios");
        do
        {            
            System.out.println("1. Auto");
            System.out.println("2. Camioneta");
            System.out.println("3. Motocicleta");                                        
            System.out.println("Ingrese el tipo de Vehiculo o NO para excluir filtro ");
            tipoVehiculo = sc.next();
        }        
        while(!tipoVehiculo.equalsIgnoreCase("1") && 
              !tipoVehiculo.equalsIgnoreCase("2") && 
              !tipoVehiculo.equalsIgnoreCase("3") && 
              !tipoVehiculo.equalsIgnoreCase("NO"));
        
        do
        {
            System.out.println("Rango recorrido (xxxxx-xxxxx) o NO para excluir filtro");
            rangoRecorrido = sc.next();
        }
        while(!Helper.validarRangoRecorrido(rangoRecorrido) && !rangoRecorrido.equalsIgnoreCase("NO"));        
        String[] recorridoPart = rangoRecorrido.split("-");
        String recorridoD = "";
        String recorridoH = "";  
        if(recorridoPart.length == 2)
        {
            recorridoD = recorridoPart[0].trim().isEmpty() ? "" : recorridoPart[0];
            recorridoH = recorridoPart[1].trim().isEmpty() ? "" : recorridoPart[1];  
        }        
        
        do
        {
            System.out.println("Rango año (xxxxx-xxxxx) o NO para excluir filtro");
            rangoAnio = sc.next();
        }
        while(!Helper.validarRangoAnio(rangoAnio) && !rangoAnio.equalsIgnoreCase("NO"));        
        String[] anioPart = rangoAnio.split("-");
        String anioD = "";
        String anioH = "";
        if(anioPart.length == 2)
        {
            anioD = anioPart[0].trim().isEmpty() ? "" : anioPart[0];
            anioH = anioPart[1].trim().isEmpty() ? "" : anioPart[1];
        }
                
        do
        {
            System.out.println("Rango Precios (xxxxx-xxxxx) o NO para excluir filtro");
            rangoPrecio = sc.next();
        }
        while(!Helper.validarRangoPrecio(rangoAnio) && !rangoPrecio.trim().equalsIgnoreCase("NO"));        
        String[] precioPart = rangoPrecio.split("-");
        String precioD = "";
        String precioH = "";
        if(precioPart.length == 2)
        {
            precioD = precioPart[0].trim().isEmpty() ? "" : precioPart[0];
            precioH = precioPart[1].trim().isEmpty() ? "" : precioPart[1];
        }
                                
        ArrayList<Vehiculo> vehiculosFiltrados = Helper.getListaVehiculosFiltrados(
                                                                                    vehiculos,
                                                                                    tipoVehiculo,
                                                                                    recorridoD,
                                                                                    recorridoH,
                                                                                    anioD,
                                                                                    anioH,
                                                                                    precioD,
                                                                                    precioH);
        
        System.out.println("Lista de Vehiculos para ofertar");      
                        
        String format = " %1$-5s %2$-5s %3$-10s %4$-10s %5$-5s %6$-10s %7$-10s %8$-10s\n";
        System.out.format(format,"No.","Marca","Modelo","Precio","Color","Transmisión","Año","Recorrido");
        String vehiculoEscogido;
        do
        {
            int i = 1;
            for(Vehiculo v : vehiculosFiltrados)
            {            
                System.out.format(format,
                                         i,
                                         v.getMarca(),
                                         v.getModelo(),
                                         v.getPrecio(),
                                         v.getColor(),
                                         v.getTransmision(),
                                         v.getAnio(),
                                         v.getRecorrido());
                i++;

            }       
            System.out.println("Escoger el vehiculo filtrado: "); 
            vehiculoEscogido = sc.next();                                    
        }
        while(vehiculoEscogido.trim().isEmpty());                        
        int indiceVehiculo = Integer.parseInt(vehiculoEscogido);
        Vehiculo v = vehiculosFiltrados.get(indiceVehiculo);
        
        String precioOferta = "";                
        do
        {
            System.out.println("\nIngrese el precio de la Oferta:");
            precioOferta = sc.next();
        }
        while(precioOferta.trim().isEmpty());
        double precioOf = Double.parseDouble(precioOferta);

        Oferta oferta = new Oferta(v.getPlaca(),comprador.getCorreoElectronico(), precioOf,"A");
        listaOfertas.add(oferta);   
        Helper.guardarOferta(oferta);
        String mensaje;
        mensaje  = "Oferta realizada por "+comprador.getNombres()+" sobre el vehiculo\n";
        mensaje += "Marca: "+v.getMarca()+"\n";
        mensaje += "Modelo: "+v.getModelo()+"\n";
        mensaje += "Precio: "+precioOf+"\n";
        Helper.enviarMail(comprador.getCorreoElectronico(),"Oferta de Vehiculo", mensaje);
    }
    
    public static void aceptarOfertaVehiculo(Vendedor vendedor, ArrayList<Oferta> ofertas, ArrayList<Vehiculo> vehiculos,Scanner sc)
    {                                                                        
        String opcionOfertaSeleccionada;
        System.out.println("Se han realizado "+ofertas.size()+" ofertas.");
                
        Oferta ofertaP;
        int i = 1;
        for(Oferta oferta : ofertas)
        {   
            System.out.println("Oferta "+i);
            System.out.println("Correo: "+oferta.getCorreoOferta());
            System.out.println("Precio Ofertado: "+oferta.getPrecioOferta());                

            do
            {
                System.out.println("1.- Siguiente Oferta");
                System.out.println("2.- Aceptar Oferta");
                opcionOfertaSeleccionada = sc.next();                                        
            }
            while(!opcionOfertaSeleccionada.equalsIgnoreCase("1") && !opcionOfertaSeleccionada.equalsIgnoreCase("2"));

            if(opcionOfertaSeleccionada.equalsIgnoreCase("1"))
                i++;
            else
            {
                ofertaP = ofertas.get(i - 1);                
                String placaSeleccionada = ofertaP.getPlaca();                
                Vehiculo v = Helper.getVehiculoPorPlaca(placaSeleccionada);
                Helper.eliminarVehiculoLista(placaSeleccionada);
                String mensaje;
                mensaje  = "Oferta aceptada en el vehiculo ";
                mensaje += "Marca: "+v.getMarca()+"\n";
                mensaje += "Modelo: "+v.getModelo()+"\n";
                mensaje += "Precio: "+ofertaP.getPrecioOferta()+"\n";
                Helper.enviarMail(vendedor.getCorreoElectronico(),"Oferta de Vehiculo aceptada", mensaje);
                return;
            }
        }                                           
    }
    
}
