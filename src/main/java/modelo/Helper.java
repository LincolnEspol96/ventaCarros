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
                String placaVerificar = arrOfLines[0].trim();                
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
    
    public String getInformacionVehiculo(String placa)
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
       
    public ArrayList<Vehiculo> getListaVehiculosFiltrados(int tipoVehiculo, double recorrido, int anio, double precio)
    {
        Vehiculo vehiculo;
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        try          
        {
            String pathVehiculo = "./vehiculo.txt";            
            File file = new File(pathVehiculo);   
            BufferedReader br = new BufferedReader(new FileReader(file)); 
  
            String line; 
            while ((line = br.readLine()) != null)
            {
                String[] arrOfLines = line.split(";");
                if(arrOfLines.length < 11)
                    return null; //NO se grabo la cantidad correcta de campos
                
                int tipoVehiculoVerificar           = Integer.parseInt(arrOfLines[0]);
                double recorridoVehiculoVerificar   = Double.parseDouble(arrOfLines[6]);
                int anioVehiculoVerificar           = Integer.parseInt(arrOfLines[2]);
                double precioVehiculoVerificar      = Double.parseDouble(arrOfLines[10]);
                                    
            }
            return listaVehiculos;
        }
        catch(Exception e)
        {                       
            return listaVehiculos;
        }
    }
}
