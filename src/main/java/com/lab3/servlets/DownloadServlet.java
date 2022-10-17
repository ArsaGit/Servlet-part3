package com.lab3.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try (PrintWriter out = resp.getWriter()) {
            String path = req.getParameter("filename");
            String name = Paths.get(path).getFileName().toString();

            //тип контента
            resp.setContentType("APPLICATION/OCTET-STREAM");
            //принудительное скачивание
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");

            FileInputStream ins = new FileInputStream(path);


            int i;
            while ((i = ins.read()) != -1) {
                out.write(i);
            }
            ins.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);
    }
}
