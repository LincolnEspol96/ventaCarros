package modelo;

public class Vehiculo {
    
    private int tipoVehiculo;
    private String placa;
    private String marca;
    private String modelo;
    private String tipoMotor;
    private int anio;
    private double recorrido;
    private String color;
    private String tipoCombustible;
    private String vidrios;
    private String transmision;
    private double precio;
    
    public Vehiculo(int tipoVehiculo,String placa,String marca,String modelo,String tipoMotor,
                    int anio,double recorrido,String color,String tipoCombustible,
                    String vidrios,String transmision,double precio)
    {
        this.tipoVehiculo = tipoVehiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.vidrios = vidrios;
        this.transmision = transmision;
        this.precio = precio;
    }
    
    public int getTipoVehiculo()
    {
        return this.tipoVehiculo;
    }
    
    public String getPlaca()
    {
        return this.placa;
    }
    
    public String getMarca()
    {
        return this.marca;
    }
    
    public String getModelo()
    {
        return this.modelo;
    }
    
    public String getTipoMotor()
    {
        return this.tipoMotor;
    }
    
    public int getAnio()
    {
        return this.anio;
    }
    
    public double getRecorrido()
    {
        return this.recorrido;
    }
    
    public String getColor()
    {
        return this.color;
    }
    
    public String getTipoCombustible()
    {
        return this.tipoCombustible;
    }
    
    public String getVidrios()
    {
        return this.vidrios;
    }
    
    public String getTransmision()
    {
        return this.transmision;
    }
    
    public double getPrecio()
    {
        return this.precio;
    }
    
}
