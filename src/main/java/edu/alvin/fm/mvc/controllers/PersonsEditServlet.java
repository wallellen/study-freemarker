package edu.alvin.fm.mvc.controllers;

import edu.alvin.fm.core.servlet.FreeMarkerSupportServlet;
import edu.alvin.fm.mvc.models.Person;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/persons/edit")
public class PersonsEditServlet extends FreeMarkerSupportServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        List<Person> persons = getPersonList(req.getServletContext());

        Optional<Person> personOptional;
        synchronized (persons) {
            personOptional = persons.stream().filter(item -> id.equals(item.getId())).findAny();
        }
        if (!personOptional.isPresent()) {
            resp.sendRedirect("/persons");
            return;
        }
        Map<String, Object> args = new HashMap<>();
        args.put("person", personOptional.get());
        template(args, req, resp);
    }

    private static List<Person> getPersonList(ServletContext app) {
        synchronized (app) {
            List<Person> persons = (List<Person>) app.getAttribute("persons");
            if (persons == null) {
                persons = new LinkedList<>();
                app.setAttribute("persons", persons);
            }
            return persons;
        }
    }
}
