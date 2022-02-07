package com.mycompany.crudodb;

import java.util.ArrayList;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import models.Carta;
import models.Pedidos;

/**
 *
 * @author chris
 */
public class CrudODB {

    private static EntityManagerFactory emfRestaurante;
    static {
        emfRestaurante = Persistence.createEntityManagerFactory("emfrestaurante.odb");
    }

    public void saveCarta(Carta c){
        EntityManager em = emfRestaurante.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }
    
    
    public void crearPedido() {
        Pedidos p = new Pedidos();

        //Hago escaneres para meter los datos
        String cliente;
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriba su nombre: ");
        cliente = sc.nextLine();

        //Converso la fecha
        java.util.Date ahora = new java.util.Date();
        java.sql.Date sqlFecha = new java.sql.Date(ahora.getTime());

        Long productoId;
        System.out.println("Escriba el id del producto de la carta que desea: ");
        productoId = sc.nextLong();

        p.setId(0L);
        p.setCliente(cliente);
        p.setFecha(sqlFecha);
        p.setEstado("Pendiente");
        p.setProductoId(productoId);

        EntityManager em = emfRestaurante.createEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminarPedido() {
        //Escaner para preguntar el pedido a eliminar
        Long id;
        System.out.println("Inserte el id del pedido que desee eliminar: ");
        Scanner sc = new Scanner(System.in);
        id = sc.nextLong();

        EntityManager em = emfRestaurante.createEntityManager();
        Pedidos p = em.find(Pedidos.class, id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        em.close();
    }

    public void marcarRecogido() {

        //Escaner para el pedido a marcar como recogido
        Long id;
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserte el id del pedido que desee marcar como recogido: ");
        id = sc.nextLong();

        EntityManager em = emfRestaurante.createEntityManager();
        Pedidos p = em.find(Pedidos.class, id);
        em.getTransaction().begin();
        p.setEstado("Recogido");
        em.getTransaction().commit();
        em.close();

        System.out.println("Su pedido ha sido marcado como recogido.");
    }

    public void listarPendientes() {

        //Converso la fecha
        java.util.Date ahora = new java.util.Date();
        java.sql.Date sqlFecha = new java.sql.Date(ahora.getTime());

        //Realizo la query para seleccionar los pedidos pendientes y de la fecha de hoy
        EntityManager em = emfRestaurante.createEntityManager();
        TypedQuery<Pedidos> q = em.createQuery("SELECT p FROM Pedidos p WHERE p.estado='Pendiente' and p.fecha=:fecha", Pedidos.class);
        q.setParameter("fecha", sqlFecha);
        ArrayList<Pedidos> pedidos = (ArrayList<Pedidos>) q.getResultList();
        pedidos.forEach((tar) -> System.out.println(tar));
        em.close();
    }
     public void listarCarta() {
        
        //Query para listar la carta
        EntityManager em = emfRestaurante.createEntityManager();
        TypedQuery<Carta> c = em.createQuery("SELECT c FROM Carta c", Carta.class);
        ArrayList<Carta> carta = (ArrayList<Carta>) c.getResultList();
        
        carta.forEach((tar) -> System.out.println(tar));
    }
    
    public void listarPedidos() {
        
        //Query para listar los pedidos
        EntityManager em = emfRestaurante.createEntityManager();
        TypedQuery<Pedidos> p = em.createQuery("SELECT p FROM Pedidos p", Pedidos.class);
        ArrayList<Pedidos> pedidos = (ArrayList<Pedidos>) p.getResultList();
        
        pedidos.forEach((tar) -> System.out.println(tar));
    }
}
