package com.frankmoley.lil.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {

    public static void main(String... args){
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost:5433",
                "hplussport", "postgres", "password");
        try{
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Adams");
            customer.setEmail("jadams.wh.gov");
            customer.setAddress("1234 Main St");
            customer.setState("VA");
            customer.setPhone("(555) 555-9845");
            customer.setZipCode("01234");
            Customer dbCostumer = customerDAO.create(customer);
            System.out.println(dbCostumer);
            dbCostumer = customerDAO.findById(dbCostumer.getId());
            dbCostumer.setEmail("john.adams@wh.gov");
            dbCostumer = customerDAO.update(dbCostumer);
            System.out.println(dbCostumer);
            customerDAO.delete(dbCostumer.getId());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
