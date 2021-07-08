/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author linco
 */
public class Vendedor 
{
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private String organizacion;
    private String usuario;
    private String clave;
    
    public Vendedor(String nombres,
                    String apellidos, 
                    String correoElectronico, 
                    String organizacion, 
                    String usuario, 
                    String clave)
    {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.organizacion = organizacion;
        this.usuario = usuario;
        this.clave = clave;
    }
    
    public String getNombres()
    {
        return this.nombres;
    }
    
    public String getApellidos()
    {
        return this.apellidos;
    }
    
    public String getCorreoElectronico()
    {
        return this.correoElectronico;
    }
    
    public String getOrganizacion()
    {
        return this.organizacion;
    }
    
    public String getUsuario()
    {
        return this.usuario;
    }
    
    public String getClave()
    {
        return this.clave;
    }    
}
