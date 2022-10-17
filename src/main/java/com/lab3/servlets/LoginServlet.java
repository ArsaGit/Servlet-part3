package com.lab3.servlets;

import com.lab3.accounts.AccountService;
import com.lab3.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    AccountService ac = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if(user != null){
            resp.sendRedirect("/Servlet-part3/files?path=/zzz/xxx/TestDir/users/" + user.toString());
            return;
        }

        req.getRequestDispatcher("loginPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        if(login == null || pass == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = ac.getUserByLogin(login);
        if(profile == null || !profile.getPass().equals(pass)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        ac.addSession(req.getSession().getId(), profile);
        resp.setStatus(HttpServletResponse.SC_OK);

        String root_dir = "/zzz/xxx/TestDir/users/";

        File user_dir = new File(root_dir + login);
        if(!user_dir.exists()) user_dir.mkdir();

        HttpSession session = req.getSession();
        session.setAttribute("user", login);

        Cookie ck = new Cookie("sessionId", session.getId());
        resp.addCookie(ck);

        ac.addSession(session.getId(), profile);

        resp.sendRedirect("/Servlet-part3/files?path=" + root_dir + login); //+user_dir
    }
}
