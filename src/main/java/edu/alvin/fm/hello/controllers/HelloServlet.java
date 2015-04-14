package edu.alvin.fm.hello.controllers;

import edu.alvin.fm.hello.models.Hello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Create model instance
        Hello hello = new Hello();
        hello.setName("Alvin");
        hello.setGender("M");

        // Save the model and variables into request scope
        req.setAttribute("model", hello);

        // Forward to freemarker template page, the 'FreeMarkerServlet' should be worked.
        req.getRequestDispatcher("hello/index.ftl").forward(req, resp);
    }
}
