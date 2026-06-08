package com.calculator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/save")
public class SaveHistoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String expression = request.getParameter("expression");
        String result = request.getParameter("result");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/calculatordb",
                    "root",
                    "Gannibhai7542");

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO history(expression,result) VALUES(?,?)");

            ps.setString(1, expression);
            ps.setString(2, result);

            ps.executeUpdate();

            con.close();

            response.getWriter().println("Saved");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}