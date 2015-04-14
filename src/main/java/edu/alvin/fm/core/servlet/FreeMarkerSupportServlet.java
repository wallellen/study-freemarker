package edu.alvin.fm.core.servlet;

import edu.alvin.fm.core.wrappers.ExtObjectWrapper;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MruCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FreeMarkerSupportServlet extends HttpServlet {
    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_21);
    private static final Map<String, String> TEMPLATES_MAP = new ConcurrentHashMap<>();

    protected static final String ERROR_MESSAGE = "ERROR_MESSAGE";

    static {
        CONFIGURATION.setDefaultEncoding("UTF-8");
        CONFIGURATION.setURLEscapingCharset("UTF-8");
        CONFIGURATION.setNumberFormat("#.##");
        CONFIGURATION.setCacheStorage(new MruCacheStorage(1024, 1024));
        CONFIGURATION.setObjectWrapper(new ExtObjectWrapper());

        // Get the class loader of current thread context
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // Get the root of resource path
        URL root = classLoader.getResource("/");
        if (root == null) {
            throw new IllegalAccessError("Can not get root path.");
        }
        File rootPath = new File(root.getFile());
        try {
            // Set the root of resource path as freemarker template path.
            CONFIGURATION.setTemplateLoader(new FileTemplateLoader(rootPath));
        } catch (IOException e) {
            throw new IllegalAccessError("Can not find root path.");
        }
    }

    private static String nameToCamelCase(String name) {
        boolean upper = true;
        StringBuilder b = new StringBuilder(name.length());
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c == '_') {
                upper = true;
                continue;
            }
            if (upper) {
                c = Character.toUpperCase(c);
                upper = false;
            } else {
                c = Character.toLowerCase(c);
            }
            b.append(c);
        }
        return b.toString();
    }

    /**
     * Process the freemarker template.
     */
    protected void template(String templateName,
                            Map<String, Object> params,
                            HttpServletRequest req,
                            HttpServletResponse resp)
            throws IOException, ServletException {
        // Get the class name as template file name
        if (templateName == null || templateName.isEmpty()) {
            templateName = getClass().getSimpleName();
            templateName = templateName.replaceFirst("Servlet$", "");
            templateName += nameToCamelCase(req.getMethod());
        }

        // Fetch first times
        String templateFile = TEMPLATES_MAP.get(templateName);
        // Check if the template file is cached
        if (templateFile == null) {
            String packageName = getClass().getPackage().getName().replace(".", "/");
            templateFile = "/"
                    + packageName.substring(0, packageName.lastIndexOf('/') + 1)
                    + "views/"
                    + templateName
                    + ".ftl";
            TEMPLATES_MAP.put(templateName, templateFile);
        }
        try {
            try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, req.getCharacterEncoding()))) {
                    // Process the freemarker template into html content, and write it into response
                    CONFIGURATION.getTemplate(templateFile).process(params, writer);
                }
                resp.getOutputStream().write(output.toByteArray());
            }
        } catch (TemplateException e) {
            throw new ServletException(e);
        }
    }

    protected void template(Map<String, Object> params,
                            HttpServletRequest req,
                            HttpServletResponse resp)
            throws IOException, ServletException {
        template(null, params, req, resp);
    }
}
