/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springbootlambda.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

/**
 *
 * @author danielcardenas
 */

@DynamoDbBean
public class Producto {

    private String idProducto;
    
    @NotNull(message = "El campo nombre no debe ser nulo")
    @NotBlank(message = "El campo nombre no debe estar en blanco")
    private String nombre;
    
    @NotNull(message = "El campo descripcion no debe ser nulo")
    @NotBlank(message = "El campo descripcion no debe estar en blanco")
    private String descripcion;
    
    @NotNull(message = "El campo categoria no debe ser nulo")
    @NotBlank(message = "El campo categoria no debe estar en blanco")
    private String categoria;
    
    @NotNull(message = "El campo marca no debe ser nulo")
    @NotBlank(message = "El campo marca no debe estar en blanco")
    private String marca;
    
    @NotNull(message = "El campo modelo no debe ser nulo")
    @NotBlank(message = "El campo modelo no debe estar en blanco")
    private String modelo;
    
    @NotNull(message = "El campo precio no debe ser nulo")
    @NotBlank(message = "El campo precio no debe estar en blanco")
    @Positive(message = "El campo precio debe ser numérico")
    @Size(min = 0, message = "El campo precio debe ser mayor a 0")
    private String precio;
    
    @NotNull(message = "El campo descuento no debe ser nulo")
    @NotBlank(message = "El campo descuento no debe estar en blanco")
    @Positive(message = "El campo descuento debe ser numérico")
    @Size(min = 0, message = "El campo descuento debe ser mayor a 0")
    private String descuento;

    @DynamoDbPartitionKey
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public Producto() {
    }

    public Producto(String idProducto, String nombre, String descripcion, String categoria, String marca, String modelo, String precio, String descuento) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.descuento = descuento;
    }
    
    

}
