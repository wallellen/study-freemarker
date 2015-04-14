package edu.alvin.fm.mvc.models;

import edu.alvin.fm.core.models.ErrorMessages;
import edu.alvin.fm.core.utils.Strings.Strings;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

@SuppressWarnings("UnusedDeclaration")
public class Person {
    private Integer id = 0;
    private String name;
    private String gender;
    private LocalDateTime birthday;
    private String telephone;
    private String email;

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
        this.name = name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return (int) birthday.until(LocalDateTime.now(ZoneOffset.UTC), ChronoUnit.YEARS);
    }

    public static Person populate(HttpServletRequest req) {
        Person person = new Person();
        String id = req.getParameter("id");
        if (!Strings.isNullOrEmpty(id)) {
            person.id = Integer.parseInt(id);
        }
        person.name = req.getParameter("name");
        person.gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        if (!Strings.isNullOrEmpty(birthday)) {
            person.birthday = LocalDateTime.ofInstant(Instant.parse(birthday), ZoneOffset.UTC);
        }
        person.telephone = req.getParameter("telephone");
        person.email = req.getParameter("email");
        return person;
    }

    public boolean check(ErrorMessages messages) {
        if (Strings.isNullOrEmpty(name)) {
            messages.addMessage("name", "无效的姓名");
        }
        if (!Pattern.matches("^M|F$", gender)) {
            messages.addMessage("gender", "无效的性别");
        }
        long years = birthday.until(LocalDateTime.now(ZoneOffset.UTC), ChronoUnit.YEARS);
        if (years < 10 || years > 100) {
            messages.addMessage("birthday", "无效的生日");
        }
        if (!Strings.isNullOrEmpty(telephone)) {
            if (!Pattern.matches("^0?1\\d{10}$|^\\(?0\\d{2}[\\)\\-\\s]?\\d{8}$|^\\(?0\\d{3}[\\)\\-\\s]?\\d{7}$", telephone)) {
                messages.addMessage("telephone", "无效的电话号码");
            }
        }
        if (!Strings.isNullOrEmpty(email)) {
            if (!Pattern.matches("[a-zA-Z\\._0-9]+@[a-zA-Z\\._0-9]+(\\.[a-zA-Z]+)+", email)) {
                messages.addMessage("email", "无效的电子邮件");
            }
        }
        return messages.isEmpty();
    }
}
