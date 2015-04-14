package edu.alvin.fm.core.wrappers;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleDate;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ExtObjectWrapper extends DefaultObjectWrapper {

    public ExtObjectWrapper() {
        super(Configuration.VERSION_2_3_21);
    }

    @Override
    public TemplateModel wrap(Object obj) throws TemplateModelException {
        if (obj instanceof LocalDateTime) {
            return new SimpleDate(Timestamp.valueOf((LocalDateTime) obj));
        } else if (obj instanceof LocalDate) {
            return new SimpleDate(Date.valueOf((LocalDate) obj));
        } else if (obj instanceof LocalTime) {
            return new SimpleDate(Time.valueOf((LocalTime) obj));
        }
        return super.wrap(obj);
    }
}
