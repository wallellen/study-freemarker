package edu.alvin.fm.functions.controllers;

import edu.alvin.fm.core.servlet.FreeMarkerSupportServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/functions/datetime")
public class DatetimeServlet extends FreeMarkerSupportServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> args = new HashMap<>();
        args.put("datetime", LocalDateTime.now());
        template(args, req, resp);
    }
}
