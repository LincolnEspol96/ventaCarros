package modelo;

public class Oferta {
    
    private String placa;
    private String correoOferta;
    private double precioOferta;
    private String ofertaEstado;
    
    public Oferta(String placa, String correoOferta, double precioOferta,String ofertaEstado)
    {
        this.placa = placa;
        this.correoOferta = correoOferta;
        this.precioOferta = precioOferta;
        this.ofertaEstado = ofertaEstado;
    }
    
    public String getPlaca()
    {
        return this.placa;
    }
    
    public String getCorreoOferta()
    {
        return this.correoOferta;
    }
    
    public double getPrecioOferta()
    {
        return this.precioOferta;
    }
    
    public String getOfertaEstado()
    {
        return this.ofertaEstado;
    }
}
