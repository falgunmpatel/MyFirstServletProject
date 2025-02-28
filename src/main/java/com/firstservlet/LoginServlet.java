package com.firstservlet;

import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
  description = "Login Servlet Testing",
  urlPatterns = { "/LoginServlet" },
  initParams = {
    //USERNAME MUST START WITH UPPERCASE AND LENGTH SHOULD BE MINIMUM 4
    //PASSWORD MUST CONTAIN ATLEAST 1 UPPERCASE, 1 LOWERCASE, 1 NUMBER, 1 SPECIAL CHARACTER AND LENGTH BETWEEN 8-20
    @WebInitParam(name = "user", value = "Falgun"),
    @WebInitParam(name = "password", value = "Falgun@123")
  }
)

public class LoginServlet extends HttpServlet{

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String nameRegex="^[A-Z]{1}[a-z]{3,}$";
    String passRegex="(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%]).{8,20}";
    String user = req.getParameter("user");
    String pwd = req.getParameter("pwd");


    String userID = getServletConfig().getInitParameter("user");
    String password = getServletConfig().getInitParameter("password");
    if((userID.equals(user) && password.equals(pwd))&&Pattern.matches(nameRegex,userID)&& Pattern.matches(passRegex,password)) {
      req.setAttribute("user",user);
      req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
    } else {
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
      PrintWriter out  = resp.getWriter();
      out.println("<font color = red> Either username or password is wrong</font>");
      rd.include(req, resp);
    }

  }

}
