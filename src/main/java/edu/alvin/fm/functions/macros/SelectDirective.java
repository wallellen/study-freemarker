package edu.alvin.fm.functions.macros;

import edu.alvin.fm.core.utils.Strings.Strings;
import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelIterator;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Objects;

public class SelectDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        Writer out = env.getOut();
        ParameterBuilder builder = new ParameterBuilder(params, out);
        out.append("<select");

        Object items = null;
        String selected = null;
        String key = null, text = null;
        for (Object entry : params.entrySet()) {
            // noinspection unchecked
            Map.Entry<String, Object> useEntry = (Map.Entry<String, Object>) entry;
            switch (useEntry.getKey()) {
            case "items":
                items = useEntry.getValue();
                break;
            case "key":
                key = useEntry.getValue().toString();
                break;
            case "text":
                text = useEntry.getValue().toString();
                break;
            case "selected":
                selected = useEntry.getValue().toString();
                break;
            default:
                builder.writeParameter(useEntry.getKey());
                break;
            }
        }
        out.append(">");

        if (body != null) {
            body.render(out);
        }

        if (items != null) {
            if (Strings.isNullOrEmpty(key)) {
                TemplateHashModelEx useItems = (TemplateHashModelEx) items;
                TemplateModelIterator iter = useItems.keys().iterator();
                while (iter.hasNext()) {
                    String keyValue = iter.next().toString();
                    String textValue = useItems.get(keyValue).toString();
                    out.write(makeOption(keyValue, textValue, selected));
                }
            } else {
                SimpleSequence useItems = (SimpleSequence) items;
                for (int i = 0; i < useItems.size(); i++) {
                    StringModel item = (StringModel) useItems.get(i);
                    String keyValue = item.get(key).toString();
                    String textValue = item.get(text).toString();
                    out.write(makeOption(keyValue, textValue, selected));
                }
            }
        }
        out.append("</select>");
    }

    private static String makeOption(String keyValue, String textValue, String selected) {
        if (keyValue == null || textValue == null) {
            return "";
        }
        return "<option value=\""
                + keyValue
                + "\""
                + (Objects.equals(keyValue, selected) ? " selected" : "")
                + ">"
                + textValue
                + "</option>";
    }

    static class ParameterBuilder {
        private Map parameters;
        private Writer out;

        public ParameterBuilder(Map parameters, Writer out) {
            this.parameters = parameters;
            this.out = out;
        }

        public void writeParameter(String key) throws IOException {
            if (parameters.containsKey(key)) {
                out.write(" ");
                out.write(key);
                out.write("=\"");
                out.write(Objects.toString(parameters.get(key)));
                out.write("\"");
            }
        }
    }
}
