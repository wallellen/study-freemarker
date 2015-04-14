package edu.alvin.fm.functions.controllers;

import edu.alvin.fm.core.servlet.FreeMarkerSupportServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/functions")
public class IndexServlet extends FreeMarkerSupportServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/functions/string");
    }
}
