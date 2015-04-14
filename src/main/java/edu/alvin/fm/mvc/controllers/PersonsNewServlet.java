package edu.alvin.fm.mvc.controllers;

import edu.alvin.fm.core.servlet.FreeMarkerSupportServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/persons/new")
public class PersonsNewServlet extends FreeMarkerSupportServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Call the method to process the freemarker template to html, and write the result into response scope
        template(null, req, resp);
    }
}
