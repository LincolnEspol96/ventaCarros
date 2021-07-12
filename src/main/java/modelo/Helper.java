package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Helper {
    
    public static boolean chequearUsuarioContrasenia(String usuario, String clave)
    {       
        try          
        {
            String pathVendedor = "./vendedor.txt";
            String claveCifrada = toHexString(getSHA(clave)).trim();            
            File file = new File(pathVendedor);   
            BufferedReader br = new BufferedReader(new FileReader(file)); 
  
            String line; 
            while ((line = br.readLine()) != null)
            {
                String[] arrOfLines = line.split(";");
                if(arrOfLines.length < 6)
                    return false; //NO se grabo la cantidad correcta de campos
                String usuarioVerificar = arrOfLines[4].trim();
                String claveVerificar = arrOfLines[5].trim();
                if(usuario.trim().equalsIgnoreCase(usuarioVerificar) && claveCifrada.trim().equalsIgnoreCase(claveVerificar))
                    return true;  //Ya existe grabado ese usuario
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: "+e);
            return false;
        }       
        return false;
    }
    
    public static boolean chequearUsuarioContraseniaComprador(String usuario, String clave)
    {       
        try          
        {
            String pathVendedor = "./comprador.txt";
            String claveCifrada = toHexString(getSHA(clave)).trim();            
            File file = new File(pathVendedor);   
            BufferedReader br = new BufferedReader(new FileReader(file)); 
  
            String line; 
            while ((line = br.readLine()) != null)
            {
                String[] arrOfLines = line.split(";");
                if(arrOfLines.length < 6)
                    return false; //NO se grabo la cantidad correcta de campos
                String usuarioVerificar = arrOfLines[4].trim();
                String claveVerificar = arrOfLines[5].trim();
                if(usuario.trim().equalsIgnoreCase(usuarioVerificar) && claveCifrada.trim().equalsIgnoreCase(claveVerificar))
                    return true;  //Ya existe grabado ese usuario
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: "+e);
            return false;
        }       
        return false;
    }
    
    public static boolean chequearCorreoElectronico(String correo)
    {       
        try          
        {
            String pathVendedor = "./vendedor.txt";            
            File file = new File(pathVendedor);   
            BufferedReader br = new BufferedReader(new FileReader(file)); 
  
            String line; 
            while ((line = br.readLine()) != null)
            {
                String[] arrOfLines = line.split(";");
                if(arrOfLines.length < 6)
                    return false; //NO se grabo la cantidad correcta de campos
                String correoValidar = arrOfLines[2].trim();                
                if(correo.trim().equalsIgnoreCase(correoValidar))
                    return true;  //Ya existe grabado ese usuario y contraseña 
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: "+e);
            return false;
        }       
        return false;
    }
    
    
    public static Comprador chequearComprador(ArrayList<Comprador> listaCompradores,String usuario, String clave)
    {
        try
        {
            String claveCifrada = toHexString(getSHA(clave)).trim();
            
            if(listaCompradores == null)
                return null;

            for(Comprador c : listaCompradores)
            {            
                if(usuario.trim().equalsIgnoreCase(c.getUsuario()) && claveCifrada.trim().equalsIgnoreCase(c.getClave()))
                    return c;
            }            
            return null;
        }
        catch(Exception e)
        {
            
        }
        return null;
    }
    
    public static Vendedor chequearVendedor(ArrayList<Vendedor> listaVendedores,String usuario, String clave)
    {
        try
        {
            String claveCifrada = toHexString(getSHA(clave)).trim();
            for(Vendedor v : listaVendedores)
            {
                if(usuario.trim().equalsIgnoreCase(v.getUsuario().trim()) && claveCifrada.trim().equalsIgnoreCase(v.getClave().trim()))
                    return v;
            }            
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    
    
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    public static String toHexString(byte[] hash) 
    { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
        return hexString.toString();  
    } 
    
    public static void guardarVendedor(Vendedor vendedor)
    {
        try 
        {
            String content = "";
            content += vendedor.getNombres()+";";
            content += vendedor.getApellidos()+";";
            content += vendedor.getCorreoElectronico()+";";
            content += vendedor.getOrganizacion()+";";
            content += vendedor.getUsuario()+";";
            content += toHexString(getSHA(vendedor.getClave()));
        
            String pathVendedor = "./vendedor.txt";
            File file = new File(pathVendedor);            
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);            
            bw.write(content);            
            bw.newLine();
            bw.close();
        }
        catch(Exception e)
        {
            
        }
    }       
    
    public static void guardarVehiculo(Vehiculo vehiculo)
    {
        try 
        {
            String content = "";
            content += vehiculo.getTipoVehiculo()+";";
            content += vehiculo.getPlaca()+";";
            content += vehiculo.getMarca()+";";
            content += vehiculo.getModelo()+";";
            content += vehiculo.getTipoMotor()+";";
            content += vehiculo.getAnio()+";";
            content += vehiculo.getRecorrido()+";";
            content += vehiculo.getColor()+";";
            content += vehiculo.getTipoCombustible()+";";
            content += vehiculo.getVidrios()+";";
            content += vehiculo.getTransmision()+";";
            content += vehiculo.getPrecio();
        
            String pathVendedor = "./vehiculo.txt";
            File file = new File(pathVendedor);            
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);            
            bw.write(content);            
            bw.newLine();
            bw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: "+e);
        }
    }
    
    public static boolean chequearPlacaVehiculo(String placa)
    {       
        try          
        {
            String pathVendedor = "./vehiculo.txt";            
            File file = new File(pathVendedor);           
            
            BufferedReader br = new BufferedReader(new FileReader(file));            
            String line; 
            while ((line = br.readLine()) != null)
            {
                String[] arrOfLines = line.split(";");
                if(arrOfLines.length < 11)
                    return false; //NO se grabo la cantidad correcta de campos
                String placaVerificar = arrOfLines[1].trim();                
                if(placa.trim().equalsIgnoreCase(placaVerificar))
                    return true;  //Ya existe grabado la placa
            }
        }
        catch(FileNotFoundException fe)
        {                      
            return false;
        }
        catch(Exception e)
        {
            return false;
        }       
        return false;
    }
    
    public static String getInformacionVehiculo(String placa)
    {
        try          
        {
            String pathVendedor = "./vehiculo.txt";            
            File file = new File(pathVendedor);   
            BufferedReader br = new BufferedReader(new FileReader(file)); 
  
            String line; 
            while ((line = br.readLine()) != null)
            {
                String[] arrOfLines = line.split(";");
                if(arrOfLines.length < 11)
                    return ""; //NO se grabo la cantidad correcta de campos
                String placaVerificar = arrOfLines[0].trim();
                String marca = arrOfLines[1].trim();
                String modelo = arrOfLines[2].trim();
                String tipoMotor = arrOfLines[3].trim();
                String precio = arrOfLines[10].trim();
                if(placa.trim().equalsIgnoreCase(placaVerificar))
                    return marca+" "+modelo+" "+tipoMotor+" "+precio;  //se envia la informacion del vehiculo
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: "+e);
            return "Vehiculo no encontrado";
        }
        return "";
    }
       
    public static ArrayList<Vehiculo> getListaVehiculos()
    {
        Vehiculo vehiculo;
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();        
        String pathVehiculo = "./vehiculo.txt";            
        try 
        {
            File file = new File(pathVehiculo);   
            BufferedReader br = new BufferedReader(new FileReader(file)); 

            String line; 
            while ((line = br.readLine()) != null)
            {
                String[] arrOfLines = line.split(";");                                
                int tipoVehiculo       = Integer.parseInt(arrOfLines[0]);
                String placa           = arrOfLines[1];
                String marca           = arrOfLines[2];
                String modelo          = arrOfLines[3];
                String tipoMotor       = arrOfLines[4];
                int anio               = Integer.parseInt(arrOfLines[5]);
                double recorrido       = Double.parseDouble(arrOfLines[6]);                
                String color           = arrOfLines[7];
                String tipoCombustible = arrOfLines[8];
                String vidrios         = arrOfLines[9]; 
                String transmision     = arrOfLines[10];
                double precio          = Double.parseDouble(arrOfLines[11]);

                listaVehiculos.add(new Vehiculo(tipoVehiculo,
                                                placa,
                                                marca,
                                                modelo,
                                                tipoMotor,
                                                anio,
                                                recorrido,
                                                color,
                                                tipoCombustible,
                                                vidrios,
                                                transmision,
                                                precio));
            }
        }
        catch(Exception e)
        {
            return null;
        }        
        return listaVehiculos;        
    }
    
    public static ArrayList<Vendedor> getListaVendedores()
    {        
        ArrayList<Vendedor> listaVendedor = new ArrayList<>();        
        String pathVendedor = "./vendedor.txt";            
        try 
        {
            File file = new File(pathVendedor);   
            BufferedReader br = new BufferedReader(new FileReader(file)); 

            String line; 
            while ((line = br.readLine()) != null)
            {
                String[] arrOfLines = line.split(";");                                
                String nombres       = arrOfLines[0];
                String apellidos     = arrOfLines[1];
                String correo        = arrOfLines[2];
                String organizacion  = arrOfLines[3];
                String usuario       = arrOfLines[4];
                String clave         = arrOfLines[5];
               
                listaVendedor.add( new Vendedor(nombres,apellidos,correo,organizacion,usuario,clave));                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }        
        return listaVendedor;        
    }
    
    public static ArrayList<Comprador> getListaCompradores()
    {        
        ArrayList<Comprador> listaComprador = new ArrayList<>();        
        String pathComprador = "./comprador.txt";            
        try 
        {
            File file = new File(pathComprador);   
            BufferedReader br = new BufferedReader(new FileReader(file)); 

            String line; 
            while ((line = br.readLine()) != null)
            {
                String[] arrOfLines = line.split(";");                                
                String nombres       = arrOfLines[0];
                String apellidos     = arrOfLines[1];
                String correo        = arrOfLines[2];
                String organizacion  = arrOfLines[3];
                String usuario       = arrOfLines[4];
                String clave         = arrOfLines[5];                                
               
                listaComprador.add( new Comprador(nombres,apellidos,correo,organizacion,usuario,clave));                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }        
        return listaComprador;        
    }
    
    public static void eliminarVehiculoLista(String placa)            
    {
        ArrayList<Vehiculo> vehiculos = getListaVehiculos();
        ArrayList<Vehiculo> listaNuevaVehiculos = new ArrayList<>();
        for(Vehiculo ve : vehiculos)
        {
            if(!placa.trim().equalsIgnoreCase(placa))
                listaNuevaVehiculos.add(ve);
        }
        
        String FILE_PATH = "./vehiculo.txt";
        try
        {
            new FileWriter(FILE_PATH, false).close();
        }
        catch(Exception e)
        {
            
        }
        
        for(Vehiculo v: listaNuevaVehiculos)
            guardarVehiculo(v);        
    }
    
    public static Vehiculo getVehiculoPorPlaca(String placa)
    {
        String pathVehiculo = "./vehiculo.txt";            
        try 
        {
            File file = new File(pathVehiculo);   
            BufferedReader br = new BufferedReader(new FileReader(file)); 

            String line; 
            while ((line = br.readLine()) != null)
            {
                String[] arrOfLines = line.split(";");                                
                int tipoVehiculo       = Integer.parseInt(arrOfLines[0]);                
                String marca           = arrOfLines[2];
                String modelo          = arrOfLines[3];
                String tipoMotor       = arrOfLines[4];
                int anio               = Integer.parseInt(arrOfLines[5]);
                double recorrido       = Double.parseDouble(arrOfLines[6]);                
                String color           = arrOfLines[7];
                String tipoCombustible = arrOfLines[8];
                String vidrios         = arrOfLines[9]; 
                String transmision     = arrOfLines[10];
                double precio          = Double.parseDouble(arrOfLines[11]);
                return new Vehiculo(tipoVehiculo,placa,marca,modelo,tipoMotor,anio,recorrido,color,tipoCombustible,vidrios,transmision,precio);
            }
        }
        catch(Exception e)
        {
            return null;
        }        
        return null;        
    }
    
    public static ArrayList<Oferta> getOfertasVehiculo(String placa)
    {
        Oferta oferta;
        ArrayList<Oferta> listaOfertas = new ArrayList<>();
        try          
        {
            String pathVendedor = "./oferta.txt";            
            File file = new File(pathVendedor);   
            BufferedReader br = new BufferedReader(new FileReader(file)); 
  
            String line; 
            while ((line = br.readLine()) != null)
            {
                String[] arrOfLines = line.split(";");
                if(arrOfLines.length < 4)
                    return null; //NO se grabo la cantidad correcta de campos
                
                String placaVerificar = arrOfLines[0].trim();
                String correoOfertado = arrOfLines[1].trim();
                String precioOfertado = arrOfLines[2].trim();
                String ofertaEstado   = arrOfLines[3].trim();
                if(placa.trim().equalsIgnoreCase(placaVerificar))
                {
                    oferta = new Oferta(placa, correoOfertado,Double.parseDouble(precioOfertado),ofertaEstado);
                    listaOfertas.add(oferta);
                }                    
            }
            return listaOfertas;
        }
        catch(Exception e)
        {                       
            return listaOfertas;
        }
    }       
    
    public static void guardarComprador(Comprador comprador)
    {
        try 
        {
            String content = "";
            content += comprador.getNombres()+";";
            content += comprador.getApellidos()+";";
            content += comprador.getCorreoElectronico()+";";
            content += comprador.getOrganizacion()+";";
            content += comprador.getUsuario()+";";
            content += toHexString(getSHA(comprador.getClave()));
        
            String pathVendedor = "./comprador.txt";
            File file = new File(pathVendedor);            
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);            
            bw.write(content);            
            bw.newLine();
            bw.close();
        }
        catch(Exception e)
        {
            
        }
    }
    
    public static void guardarOferta(Oferta oferta)
    {                           
        try 
        {
            String content = "";
            content += oferta.getPlaca() +";";
            content += oferta.getCorreoOferta()+";";
            content += oferta.getPrecioOferta()+";";
            content += oferta.getOfertaEstado()+";";
        
            String pathVendedor = "./oferta.txt";
            File file = new File(pathVendedor);            
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);            
            bw.write(content);            
            bw.newLine();
            bw.close();
        }
        catch(Exception e)
        {
            
        }
    }
    
    public static boolean validarNumero(String numero)
    {
        if(numero == null)return false;
        if(numero.length() == 0)return false;
        for (int i = 0; i < numero.length(); i++)
        {
            if (!Character.isDigit(numero.charAt(i)))
                return false;
        }
        return true;
    }
    
    public static boolean validarRangoRecorrido(String rangoRecorrido)
    {        
        String[] recorridoPart = rangoRecorrido.split("-");
        if(recorridoPart.length != 2)
            return false;
        String recorridoDesde = recorridoPart[0];
        String recorridoHasta = recorridoPart[1];
        return validarNumero(recorridoDesde) && validarNumero(recorridoHasta);        
    }
    
    public static boolean validarRangoAnio(String rangoAnio)
    {        
        String[] anioPart = rangoAnio.split("-");
        if(anioPart.length != 2)
            return false;
        String anioDesde = anioPart[0];
        String anioHasta = anioPart[1];
        return validarNumero(anioDesde) && validarNumero(anioHasta);        
    }
    
    public static boolean validarRangoPrecio(String rangoPrecio)
    {        
        String[] precioPart = rangoPrecio.split("-");
        if(precioPart.length != 2)
            return false;
        String precioDesde = precioPart[0];
        String precioHasta = precioPart[1];
        return validarNumero(precioDesde) && validarNumero(precioHasta);        
    }
        
    public static ArrayList<Vehiculo> getListaVehiculosFiltrados(ArrayList<Vehiculo> vehiculos, 
                                        String tipoVehiculo,
                                        String recorridoDesde,
                                        String recorridoHasta,
                                        String anioDesde,
                                        String anioHasta,
                                        String precioDesde,
                                        String precioHasta)                                                      
    {
        ArrayList<Vehiculo> listaFiltroTipoVehiculo = new ArrayList<>();
        if(!tipoVehiculo.trim().isEmpty()  && !tipoVehiculo.equalsIgnoreCase("NO"))
        {
            int tipoV = Integer.parseInt(tipoVehiculo);
            for(Vehiculo v : vehiculos)
            {
                if(tipoV == v.getTipoVehiculo())
                    listaFiltroTipoVehiculo.add(v);
            }
            if(listaFiltroTipoVehiculo.isEmpty())
                return null;
        }
        else
            listaFiltroTipoVehiculo = vehiculos;
        
        ArrayList<Vehiculo> listaFiltroRangoRecorrido = new ArrayList<>();
        if(!recorridoDesde.trim().isEmpty() && !recorridoHasta.isEmpty())
        {
            double recorridoD = Double.parseDouble(recorridoDesde);
            double recorridoH = Double.parseDouble(recorridoHasta);
            for(Vehiculo v : listaFiltroTipoVehiculo)
            {
                if(recorridoD >= v.getPrecio() && recorridoH <= v.getPrecio())
                    listaFiltroRangoRecorrido.add(v);
            }
            if(listaFiltroRangoRecorrido.isEmpty())
                return null;
        }
        else
            listaFiltroRangoRecorrido = listaFiltroTipoVehiculo;
        
        ArrayList<Vehiculo> listaFiltroRangoAnio = new ArrayList<>();
        if(!anioDesde.trim().isEmpty() && !anioHasta.isEmpty())
        {
            int anioD = Integer.parseInt(anioDesde);
            int anioH = Integer.parseInt(anioHasta);
            for(Vehiculo v : listaFiltroRangoRecorrido)
            {
                if(anioD >= v.getAnio()&& anioH <= v.getAnio())
                    listaFiltroRangoAnio.add(v);
            }
            if(listaFiltroRangoAnio.isEmpty())
                return null;
        }
        else
            listaFiltroRangoAnio = listaFiltroRangoRecorrido;
        
        ArrayList<Vehiculo> listaFiltroRangoPrecio = new ArrayList<>();
        if(!precioDesde.trim().isEmpty() && !precioHasta.isEmpty())
        {
            double precioD = Double.parseDouble(precioDesde);
            double precioH = Double.parseDouble(precioHasta);
            for(Vehiculo v : listaFiltroRangoAnio)
            {
                if(precioD >= v.getPrecio()&& precioH <= v.getPrecio())
                    listaFiltroRangoPrecio.add(v);
            }
            if(listaFiltroRangoPrecio.isEmpty())
                return null;
        }
        else
            listaFiltroRangoPrecio = listaFiltroRangoAnio;   
        
        return listaFiltroRangoPrecio;
    }
    
    public static void enviarMail(String correo, String asunto, String mensaje) 
    { 
        //Correo disponible para usar.
        String remitente = "ventacarros1920@gmail.com"; 
        String usuario = "ventacarros1920";
        String clave = "Ov3rp0w3red";
        //Para la dirección nomcuenta@gmail.com 
        Properties props = System.getProperties(); 
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        //El servidor SMTP de Google 
        props.put("mail.smtp.user", remitente); 
        props.put("mail.smtp.clave", clave); 
        //La clave de la cuenta 
        props.put("mail.smtp.auth", "true"); 
        //Usar autenticación mediante usuario y clave 
        props.put("mail.smtp.starttls.enable", "true"); 
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //Para conectar de manera segura al servidor SMTP 
        props.put("mail.smtp.port", "587"); 
        //El puerto SMTP seguro de Google 
        Session session = Session.getDefaultInstance(props); 
        MimeMessage message = new MimeMessage(session); 
        try 
        { 
            message.setFrom(new InternetAddress(remitente)); 
            message.addRecipients(Message.RecipientType.TO,correo);         
        
            //Se podrían añadir varios de la misma manera 
            message.setSubject(asunto); 
            message.setText(mensaje); 
            Transport transport = session.getTransport("smtp"); 
            transport.connect("smtp.gmail.com",usuario,clave); 
            transport.sendMessage(message, message.getAllRecipients()); 
            transport.close(); 
            System.out.println("Información de oferta enviada exitosamente"); 
        }
        catch (MessagingException me) 
        { 
            me.printStackTrace(); 
            //Si se produce un error 
        }
    }
                   
}
