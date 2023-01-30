/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springbootlambda.demo.controllers;

import com.google.gson.Gson;
import com.springbootlambda.demo.model.Producto;
import com.springbootlambda.demo.repository.ProductoRepository;
import java.util.List;
import javax.validation.Valid;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author danielcardenas
 */
@RestController
@EnableWebMvc
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;
    //private ProductoRepository productoRepository;

    @GetMapping()
    public List<Producto> getAllProductos() {
        return productoRepository.getAllProductos();
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<Gson> getProducto(@PathVariable String idProducto) {
        Producto productoEncontrado = productoRepository.getProducto(idProducto);
        if (productoEncontrado == null) {
            return new ResponseEntity(new JSONObject().put("error", "Producto no econtrado"), HttpStatus.NOT_FOUND);
        } else {
            productoRepository.deleteProducto(idProducto);
            //jsonObjeto.put("producto", productoEncontrado);
            return new ResponseEntity(new Gson().toJson(productoEncontrado), HttpStatus.OK);
        }

    }

    @PostMapping()
    public ResponseEntity<JSONObject> addProducto(@Valid @RequestBody Producto p, BindingResult bindingResult) {
        
             JSONObject jsonObjeto = new JSONObject();
            
            if(bindingResult.hasErrors()){
              
                bindingResult
                .getFieldErrors()
                .stream()
                .forEach(f -> jsonObjeto.appendField(f.getField(), f.getDefaultMessage()));
                return new ResponseEntity("Error al guardar el producto", HttpStatus.OK);
            } else {
                productoRepository.saveProducto(p);
                return new ResponseEntity(new Gson().toJson(p), HttpStatus.OK);
            }
         

    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<JSONObject> deleteProducto(@PathVariable String idProducto) {
        JSONObject jsonObjeto = new JSONObject();
        Producto productoEncontrado = productoRepository.getProducto(idProducto);

        if (productoEncontrado == null) {

            jsonObjeto.put("error", "Producto no econtrado");
            return new ResponseEntity(jsonObjeto, HttpStatus.NOT_FOUND);
        } else {
            productoRepository.deleteProducto(idProducto);
            jsonObjeto.put("ok", "Producto eliminado");
            return new ResponseEntity(jsonObjeto, HttpStatus.OK);
        }
    }

    @PutMapping
    public ResponseEntity<JSONObject> updateProducto(@RequestBody Producto p) {
        JSONObject jsonObjeto = new JSONObject();
        Producto productoEncontrado = productoRepository.getProducto(p.getIdProducto());

        if (productoEncontrado == null) {

            jsonObjeto.put("error", "Producto no econtrado");
            return new ResponseEntity(jsonObjeto, HttpStatus.NOT_FOUND);
        } else {
            productoRepository.updateProducto(p);
            jsonObjeto.put("ok", p);
            return new ResponseEntity(new Gson().toJson(p), HttpStatus.OK);
        }

    }

}
