/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springbootlambda.demo.repository;

import com.springbootlambda.demo.model.Producto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

/**
 *
 * @author danielcardenas
 */

@Repository
public class ProductoRepository {
    
    @Autowired
    private DynamoDbEnhancedClient dynamoDbenhancedClient ;
    
    
    public List<Producto> getAllProductos() {
        Iterator<Producto> productos = getTable().scan().items().iterator();
        List<Producto> listaProductos = new ArrayList<>();
        while (productos.hasNext()) {
            Producto p = productos.next();
            listaProductos.add(p);
        }
        return listaProductos;
    }
    
    public Producto getProducto(String idProducto) {
        Key id = Key.builder()
                .partitionValue(idProducto)
                .build();
        return getTable().getItem(r -> r.key(id));
    }
    
    public void saveProducto(Producto p) {
        p.setIdProducto(UUID.randomUUID().toString());
        Double precio = (NumberUtils.isParsable(p.getPrecio()) == true)? Double.parseDouble(p.getPrecio()) : 0.0;
        Double descuento = (precio != 0.0)? precio * 0.1 : 0 ;
        p.setDescuento(descuento.toString());
        getTable().putItem(p);
        
    }
    
    public Producto deleteProducto(String idProducto) {
        Key id = Key.builder()
                .partitionValue(idProducto)
                .build();

        return getTable().deleteItem(id);
    }

    public void updateProducto(Producto p) {
        getTable().putItem(p);
    }
    
    private DynamoDbTable<Producto> getTable() {
        
        DynamoDbTable<Producto> tablaProducto =
            dynamoDbenhancedClient.table("producto",
                    TableSchema.fromBean(Producto.class));
        return tablaProducto;
      }
   
    
    
    
}
