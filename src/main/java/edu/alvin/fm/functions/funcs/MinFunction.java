package edu.alvin.fm.functions.funcs;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

@SuppressWarnings("unchecked")
public class MinFunction implements TemplateMethodModelEx {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments == null) {
            return null;
        }
        Comparable min = null;
        for (Object obj : arguments) {
            if (min == null) {
                min = (Comparable) ((SimpleNumber) obj).getAsNumber();
            } else {
                Number right = ((SimpleNumber) obj).getAsNumber();
                if (min.compareTo(right) > 0) {
                    min = (Comparable) right;
                }
            }
        }
        return min;
    }
}