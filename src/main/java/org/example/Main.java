package org.example;

import config.FactoryConfiguration;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
      SaveCustomer();
      getCustomer();
      updateCustomer();
      deleteCustomer();
        getAllCustomer();

    }
    public static void getCustomer() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Customer customer = session.get(Customer.class, 2);
        System.out.println(customer.toString());
    }
    public static void SaveCustomer(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Customer customer = new Customer();
        customer.setName("bat");
        Transaction transaction = session.beginTransaction();
        session.persist(customer);
        transaction.commit();
    }
    public static void updateCustomer(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Customer customer = session.get(Customer.class, 2);
        Transaction transaction = session.beginTransaction();
        customer.setName("carrot");
        session.merge(customer);//can update with or without merge
        transaction.commit();
    }
    public static void deleteCustomer(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, 2);
//        session.delete(customer);
        session.remove(customer);//better option
        transaction.commit();
    }
    public static void getAllCustomer(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query<Customer> quary = session.createQuery("from Customer",Customer.class);
       List<Customer> customerList = quary.list();
       customerList.forEach(customer -> System.out.println(customer.toString()));
       transaction.commit();
    }
}