/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigInteger; 
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
/**
 *
 * @author Lenovo
 */
public class Vendedor {
   String nombres;
   String apellidos;
   String organizacion;
   String correo;
   String clave;

   Scanner sc =new Scanner(System.in);
   
   
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vendedor other = (Vendedor) obj;
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return true;
    }

    
   
    public  void registrarVendedor() throws NoSuchAlgorithmException{
        
        ArrayList vendedor = new ArrayList();
        File archivo; //manipulacion alarchivo
        FileWriter  escribir;  //escribir en archivo
        PrintWriter linea; //escribir archivo
        archivo = new File("vendedor.txt");
        String nombre,apellido,organizacion,correo,clave;
        if (!archivo.exists()){
            try {
                archivo.createNewFile();
                System.out.println("ESCRIBA SUS NOMBRES ");
                nombre=sc.nextLine();
                
                System.out.println("ESCRIBA SUS APELLIDOS ");
                apellido=sc.nextLine();

                System.out.println("ESCRIBA ORGANIZACION ");
                organizacion=sc.nextLine();
                
                System.out.println("ESCRIBA CORREO ");
                correo=sc.nextLine();
                
                System.out.println("ESCRIBA CLAVE ");
                clave=sc.nextLine();
                
                
                vendedor.add(nombre);
                vendedor.add(apellido);
                vendedor.add(organizacion);
                vendedor.add(correo);
                vendedor.add(clave);
                
                String inc=clave;
                vendedor.add( toHexString(getClave(inc)));
                escribir = new FileWriter(archivo,true);
                linea = new PrintWriter(escribir);
                linea.println(vendedor);
                linea.close();
                escribir.close();
            
            
            } catch (IOException ex) {
                Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
       }else{
            try {
                                     
                System.out.println("ESCRIBA SUS NOMBRES ");
                nombre=sc.nextLine();
                
                System.out.println("ESCRIBA SUS APELLIDOS ");
                apellido=sc.nextLine();

                System.out.println("ESCRIBA ORGANIZACION ");
                organizacion=sc.nextLine();
                
                System.out.println("ESCRIBA CORREO ");
                correo=sc.nextLine();
                
                //// leer el archivo 
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String lin;
                vendedor.add(nombre);
                vendedor.add(apellido);
                vendedor.add(organizacion);
                while((lin=br.readLine())!=null){
                                   
                    while (lin.contains(correo)){
                    System.out.println("ERROR AL REGISTRAR ");
                    System.out.println("ESE CORREO YA ESTA REGISTRADO");
                    System.out.println("ESCRIBA CORREO NUEVAMENTE ");
                     correo=sc.nextLine();
                     
                     
                    }
                                  
                }
    
                
                br.close();
                fr.close();
                         vendedor.add(correo);
                         System.out.println("ESCRIBA CLAVE ");
                         clave=sc.nextLine();
                         String inc=clave;
                        
                         vendedor.add( toHexString(getClave(inc)));                  
                
                
                 
                escribir = new FileWriter(archivo,true);
                linea = new PrintWriter(escribir);
                linea.println(vendedor);
                linea.close();
                escribir.close();
                System.out.println("INGRESO EXITOSO ");
            
            } catch (IOException ex) {
                Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }
        
         }
  
   
     

   public static byte[] getClave(String input) throws NoSuchAlgorithmException
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
   
   
   
   



 





}
   
       
         
        
       
        
        
        
        
        
    
    









