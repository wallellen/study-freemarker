package edu.alvin.fm.functions.controllers;

import edu.alvin.fm.core.servlet.FreeMarkerSupportServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/functions/list")
public class ListServlet extends FreeMarkerSupportServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> args = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (char c : "helloworld".toCharArray()) {
            list.add(String.valueOf(c));
        }
        args.put("list", list);
        template(args, req, resp);
    }
}
