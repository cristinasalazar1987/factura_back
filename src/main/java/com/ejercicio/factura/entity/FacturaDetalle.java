package com.ejercicio.factura.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "factura_detalle")
public class FacturaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle") 
    private int idDetalle;

    @ManyToOne
    @JoinColumn(name = "idFactura", referencedColumnName = "idFactura", nullable = false)
    private FacturaCabecera facturaCabecera;

    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "id", nullable = false)
    private Producto producto;

    @Column(name = "cantidad") 
    private int cantidad;

    @Column(name = "subtotal") 
    private Double subtotal;

    @Column(name = "iva") 
    private Double iva;

    @Column(name = "total") 
    private Double total;

    // Getters y setters...

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public FacturaCabecera getFacturaCabecera() {
        return facturaCabecera;
    }

    public void setFacturaCabecera(FacturaCabecera facturaCabecera) {
        this.facturaCabecera = facturaCabecera;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
