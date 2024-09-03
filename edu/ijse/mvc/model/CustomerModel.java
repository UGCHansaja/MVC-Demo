/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.model;

import edu.ijse.mvc.db.DBConnection;
import edu.ijse.mvc.dto.CustomerDto;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Charuka
 */
public class CustomerModel {
        public ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Customer";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rst = statement.executeQuery();

        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        while (rst.next()) {            
            CustomerDto dto = new CustomerDto();
            dto.setCustID(rst.getString("CustID"));
            dto.setCustTitle(rst.getString("CustTitle"));
            dto.setCustName(rst.getString("CustName"));
            dto.setDOB(rst.getDate("DOB"));
            dto.setSalary(rst.getDouble("salary"));
            dto.setCustAddress(rst.getString("CustAddress"));
            dto.setCity(rst.getString("City"));
            dto.setProvince(rst.getString("Province"));
            dto.setPostalCode(rst.getString("PostalCode"));
            

            customerDtos.add(dto);
        }

        return customerDtos;
    }
     
    public String saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, dto.getCustID());
        statement.setString(2, dto.getCustTitle());
        statement.setString(3, dto.getCustName());
        statement.setDate(4, dto.getDOB());
        statement.setDouble(5, dto.getSalary());
        statement.setString(6, dto.getCustAddress());
        statement.setString(7, dto.getCity());
        statement.setString(8, dto.getProvince());
        statement.setString(9, dto.getPostalCode());
        
        
        int result = statement.executeUpdate();
        return result > 0?"Successful" : "Failed";
    }
}
