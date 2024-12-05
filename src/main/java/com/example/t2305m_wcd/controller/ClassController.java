package com.example.t2305m_wcd.controller;

import com.example.t2305m_wcd.dao.ClassDAO;
import com.example.t2305m_wcd.entity.Class;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/class")
public class ClassController extends HttpServlet {
    private ClassDAO classDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        classDAO = new ClassDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";

        switch (action) {
            case "create":
                renderForm(req, resp, null);
                break;
            case "edit":
                Long id = Long.parseLong(req.getParameter("id"));
                Class c = classDAO.find(id);
                renderForm(req, resp, c);
                break;
            default:
                listClasses(req, resp);
                break;
        }
    }

    private void listClasses(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Class> list = classDAO.all();
        req.setAttribute("classes", list);
        RequestDispatcher rd = req.getRequestDispatcher("class/list.jsp");
        rd.forward(req, resp);
    }

    private void renderForm(HttpServletRequest req, HttpServletResponse resp, Class c)
            throws ServletException, IOException {
        req.setAttribute("class", c);
        RequestDispatcher rd = req.getRequestDispatcher("class/form.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        if (action.equals("create")) {
            classDAO.create(new Class(null, name));
        } else if (action.equals("edit")) {
            Long id = Long.parseLong(req.getParameter("id"));
            classDAO.update(new Class(id, name));
        }
        resp.sendRedirect("class");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        classDAO.delete(id);
        resp.sendRedirect("class");
    }
}