package edu.alvin.fm.core.models;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ErrorMessages {
    private Map<String, Set<String>> messageMap;

    public void addMessage(String key, String message) {
        if (messageMap == null) {
            messageMap = new HashMap<>();
        }
        Set<String> strings = messageMap.get(key);
        if (strings == null) {
            strings = new LinkedHashSet<>();
            messageMap.put(key, strings);
        }
        strings.add(message);
    }

    public String getMessage(String key) {
        if (messageMap == null) {
            return "";
        }
        Set<String> strings = messageMap.get(key);
        if (strings == null || strings.isEmpty()) {
            return "";
        }
        return strings.iterator().next();
    }

    public String[] getMessages(String key) {
        if (messageMap == null) {
            return null;
        }
        Set<String> strings = messageMap.get(key);
        if (strings == null || strings.isEmpty()) {
            return null;
        }
        return strings.toArray(new String[strings.size()]);
    }

    public boolean isEmpty() {
        return messageMap == null || messageMap.isEmpty();
    }
}
