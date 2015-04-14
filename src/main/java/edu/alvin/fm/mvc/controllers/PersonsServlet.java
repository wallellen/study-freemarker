package edu.alvin.fm.mvc.controllers;

import edu.alvin.fm.core.models.ErrorMessages;
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
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/persons")
public class PersonsServlet extends FreeMarkerSupportServlet {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> persons = getPersonList(req.getServletContext());

        // Save the model instance and variables into a map object.
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("persons", persons);

        // Call the method to process the freemarker template to html, and write the result into response scope
        template(arguments, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = Person.populate(req);
        ErrorMessages messages = new ErrorMessages();
        Map<String, Object> arguments = new HashMap<>();
        if (!person.check(messages)) {
            arguments.put(ERROR_MESSAGE, messages);
            arguments.put("person", person);
            template("PersonsNewGet", arguments, req, resp);
            return;
        }
        person.setId(atomicInteger.incrementAndGet());
        getPersonList(req.getServletContext()).add(person);
        resp.sendRedirect("/persons");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = Person.populate(req);
        ErrorMessages messages = new ErrorMessages();
        Map<String, Object> arguments = new HashMap<>();
        if (!person.check(messages)) {
            arguments.put(ERROR_MESSAGE, messages);
            arguments.put("person", person);
            template("PersonsEditGet", arguments, req, resp);
            return;
        }
        List<Person> persons = getPersonList(req.getServletContext());
        Optional<Person> personOptional = persons.stream().filter(item -> person.getId().equals(item.getId())).findAny();
        if (!personOptional.isPresent()) {
            messages.addMessage("id", "无效的用户ID");
            arguments.put(ERROR_MESSAGE, messages);
            arguments.put("person", person);
            template("PersonsEditGet", arguments, req, resp);
            return;
        }
        Person saved = personOptional.get();
        synchronized (saved) {
            saved.setName(person.getName());
            saved.setBirthday(person.getBirthday());
            saved.setEmail(person.getEmail());
            saved.setGender(person.getGender());
            saved.setTelephone(person.getTelephone());
        }
        resp.sendRedirect("/persons");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        List<Person> persons = getPersonList(req.getServletContext());
        synchronized (persons) {
            persons.removeIf(item -> id.equals(item.getId()));
        }
        resp.sendRedirect("/persons");
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
