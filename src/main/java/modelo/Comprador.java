package modelo;

public class Comprador {
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private String organizacion;
    private String usuario;
    private String clave;
    
    public Comprador(String nombres,
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
    
    public Comprador()
    {
        
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
