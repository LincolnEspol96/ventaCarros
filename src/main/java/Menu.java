/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.espol.proyectopooo;
import ec.edu.espol.model.Comprador;

import ec.edu.espol.model.Vendedor;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
       
    
    static Scanner sc = new Scanner(System.in);
            public  static  int menuPrincipal(){
                    System.out.println("---------MENU PRINCIPAL---------");
                    System.out.println("1. VENDEDOR");
                    System.out.println("2. COMPRADOR");
                    System.out.println("3. SALIR");
                    System.out.println("---------ESCRIBA UNA DE LAS OPCIONES---------");
                    return sc.nextInt();}


            public  static  int menuVendedor(){
                        System.out.println("---------A INGRESADO EN LA OPCION VENDEDOR---------");
                        System.out.println("1. REGISTRAR NUEVO VENDEDOR");
                        System.out.println("2. INGRESAR UN NUEVO VEHICULO");
                        System.out.println("3. ACEPTAR OFERTA");
                        System.out.println("4. REGRESAR");
                        System.out.println("---------ESCRIBA UNA DE LAS OPCIONES---------");
                        return sc.nextInt();}

            public  static  int menuComprador(){
                        System.out.println("---------A INGRESADO EN LA OPCION COMPRADOR---------");
                        System.out.println("1. REGISTRAR UN NUEVO COMPRADOR");
                        System.out.println("2. OFERTAR POR VEHICULO");
                        System.out.println("3. REGRESAR");
                        System.out.println("---------ESCRIBA UNA DE LAS OPCIONES---------");
                        return sc.nextInt();}
  
    public static void main(String[] args) throws NoSuchAlgorithmException {
       boolean salir = false;
        while (!salir) {
          try {
                int opcion = menuPrincipal();
                int regresar=0;
                int regresar2=0;
                 switch (opcion) {
                     
                     case 1:
                      while (regresar<4){
                          
                          try{
                          int opcionVendedor =menuVendedor();
                          
                          switch(opcionVendedor){
                                case 1:
                                    Vendedor ven = new Vendedor();
                                    ven.registrarVendedor();
                                                                               
                                    break;
                                case 2:
                                    
                                    
                                    
                                    break;
                                case 3:
                                    System.out.println("pepa 3");
                                    break;
                                 case 4:
                                    regresar=5;
                                    break;
                                default:
                                    System.out.println("Solo números entre 1 y 4");
                            
                                    }
                                    }  catch (InputMismatchException e) {
                                       System.out.println("Debes insertar un número");
                                        sc.next();
                                    }}                      
                        
                                    break;
                          
                    case 2:
                        while (regresar2<3){
                        
                        try{ 
                        int opcionComprador=menuComprador();
                        
                            switch(opcionComprador){
                                case 1:
                                    Comprador com = new Comprador();
                                    com.registrarComprador();
                                    break;
                                case 2:
                                    System.out.println("pepa 2");
                                    break;
                                case 3:
                                    regresar2 = 4;
                                    break;
                                default:
                                    System.out.println("Solo números entre 1 y 3");
                            
                            }
                       }  catch (InputMismatchException e) {
                         System.out.println("Debes insertar un número");
                         sc.next();
                      }}                 
                        
                     break;
                      case 3:
                       salir = true;
                       System.out.println("GRACIAS POR USAR NUESTRA APLICACION");
                        break;
                    
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }    
                
                
            }catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sc.next();
            
            
                
                
                
                
                
            }}}

   



}
        
            
            