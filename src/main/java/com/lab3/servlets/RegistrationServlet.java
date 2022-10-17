package com.lab3.servlets;

import com.lab3.accounts.AccountService;
import com.lab3.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    AccountService ac = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if(user != null){
            resp.sendRedirect("/Servlet-part3/files?path=/zzz/xxx/TestDir/users/" + user.toString());
            return;
        }

        req.getRequestDispatcher("registrationPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");

        if(login.length() > 0 && email.length() > 0 && pass.length() > 0) {
            ac.addNewUser(new UserProfile(login, email, pass));
        }

        doGet(req, resp);
    }
}
