package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/files")
public class FileExplorerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //текущая папка
        String pathStr = req.getParameter("path");
        Path path = Paths.get(pathStr);
        Path prevDir = path.getParent();

        //Список содержимого папки
        List<Item> allItems = new ArrayList<>();
        try {
            Files.walk(path,1).collect(Collectors.toList()).forEach(p -> allItems.add(new Item(p)));
        } catch (IOException e) {}

        if(allItems.size() != 0) allItems.remove(0);

        //вывод datetime
        req.setAttribute("date", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        //вывод текущей папки
        req.setAttribute("currDir", path);
        req.setAttribute("prevDir", prevDir);
        req.setAttribute("allItems", allItems);

        System.out.println(path);
        allItems.forEach(item -> System.out.println(item.getPath()));

        req.getRequestDispatcher("fileExplorerPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);
    }

}
