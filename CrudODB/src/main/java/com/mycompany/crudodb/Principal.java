package com.mycompany.crudodb;

import java.util.Scanner;
import models.Carta;

/**
 *
 * @author chris
 */
public class Principal {
    public static void main(String[] args) {
        CrudODB crud = new CrudODB();
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        
//        Carta c = new Carta();
//        c.setNombre("Kebab");
//        c.setDescripcion("Rico");
//        c.setPrecio(5.00);
//        
//        Carta c2 = new Carta();
//        c2.setNombre("Montadito");
//        c2.setDescripcion("Montado de pollo");
//        c2.setPrecio(5.00);
//        
//        Carta c3 = new Carta();
//        c3.setNombre("Churros");
//        c3.setDescripcion("Con mucho aceite");
//        c3.setPrecio(5.00);
//        
//        Carta c4 = new Carta();
//        c.setNombre("Hamburguesa de cerdo");
//        c.setDescripcion("Sin tomate");
//        c.setPrecio(5.00);
//        
//        Carta c5 = new Carta();
//        c5.setNombre("Calzone");
//        c5.setDescripcion("Con mucho queso");
//        c5.setPrecio(5.00);
//        
//            crud.saveCarta(c);
//            crud.saveCarta(c2);
//            crud.saveCarta(c3);
//            crud.saveCarta(c4);
//            crud.saveCarta(c5);
        
        
        while(opcion!=6) {
            System.out.println("1.- Crear pedido");
            System.out.println("2.- Eliminar pedido");
            System.out.println("3.- Marcar pedido como recogido");
            System.out.println("4.- Listar pedidos pendientes");
            System.out.println("5.- Listar carta");
            System.out.println("6.- Salir");
            opcion = sc.nextInt();
            
            switch (opcion){
            
                case 1: crud.listarCarta();
                        crud.crearPedido();
                break;
                
                case 2: crud.listarPedidos();
                        crud.eliminarPedido();
                break;
                case 3: crud.listarPendientes();
                        crud.marcarRecogido();
                break;
                case 4: crud.listarPendientes();
                break;
                case 5: crud.listarCarta();
                break;
                case 6: System.out.println("¡Esperemos que haya disfrutado de nuestro servicio!");
                break;
                default: System.out.println("Opción incorrecta, elija otra opción");
            }
        }
    }
}
