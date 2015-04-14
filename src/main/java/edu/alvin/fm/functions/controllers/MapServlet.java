package edu.alvin.fm.functions.controllers;

import edu.alvin.fm.core.servlet.FreeMarkerSupportServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/functions/map")
public class MapServlet extends FreeMarkerSupportServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> args = new HashMap<>();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        map.put("c", "C");
        map.put("d", "D");
        args.put("map", map);
        template(args, req, resp);
    }
}
