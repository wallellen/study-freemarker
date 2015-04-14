package edu.alvin.fm.functions.controllers;

import edu.alvin.fm.core.servlet.FreeMarkerSupportServlet;
import edu.alvin.fm.functions.funcs.MinFunction;
import edu.alvin.fm.functions.macros.SelectDirective;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/functions/user_define")
public class UserDefineServlet extends FreeMarkerSupportServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> args = new HashMap<>();
        // Global config
        // Configuration#setSharedVariable("min", new MinFunction());
        args.put("min", new MinFunction());
        args.put("items", makeItems());
        // Global config
        // Configuration#setSharedVariable("select", new SelectDirective());
        args.put("select", new SelectDirective());
        template(args, req, resp);
    }

    private List<Item> makeItems() {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            items.add(Item.of(i + 1, String.valueOf((char)('A' + i))));
        }
        return items;
    }

    public static class Item {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static Item of(Integer id, String name) {
            Item item = new Item();
            item.setId(id);
            item.setName(name);
            return item;
        }
    }
}
