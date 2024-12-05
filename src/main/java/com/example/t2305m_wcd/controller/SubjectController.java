package com.example.t2305m_wcd.controller;

import com.example.t2305m_wcd.dao.SubjectDAO;
import com.example.t2305m_wcd.entity.Subject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/subject")
public class SubjectController extends HttpServlet {
    private SubjectDAO subjectDAO;

    @Override
    public void init() throws ServletException {
        subjectDAO = new SubjectDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        switch (action) {
            case "create":
                renderForm(req, resp, null);
                break;
            case "edit":
                Long id = Long.parseLong(req.getParameter("id"));
                Subject subject = subjectDAO.find(id);
                renderForm(req, resp, subject);
                break;
            case "delete":
                deleteSubject(req, resp);
                break;
            default:
                listSubjects(req, resp);
                break;
        }
    }

    private void renderForm(HttpServletRequest req, HttpServletResponse resp, Subject subject)
            throws ServletException, IOException {
        req.setAttribute("subject", subject);
        RequestDispatcher rd = req.getRequestDispatcher("subject/form.jsp");
        rd.forward(req, resp);
    }

    private void listSubjects(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Subject> subjects = subjectDAO.all();
        req.setAttribute("subjects", subjects);
        RequestDispatcher rd = req.getRequestDispatcher("subject/list.jsp");
        rd.forward(req, resp);
    }

    private void deleteSubject(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        subjectDAO.delete(id);
        resp.sendRedirect("subject");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = req.getParameter("id") != null && !req.getParameter("id").isEmpty()
                ? Long.parseLong(req.getParameter("id"))
                : null;
        Subject subject = new Subject(
                id,
                req.getParameter("name"),
                req.getParameter("code"),
                Integer.parseInt(req.getParameter("hour"))
        );
        if (id == null) {
            subjectDAO.create(subject);
        } else {
            subjectDAO.update(subject);
        }
        resp.sendRedirect("subject");
    }
}