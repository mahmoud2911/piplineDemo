package utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class JsonReader {
    static String dataPath = System.getProperty("user.dir") + "/src/main/java/data/";

    /**
     * jsonReader function takes two variables
     * 1- key that will retrieve the value of it
     * 2- second variable for the file name
     */
    public static String jsonReader(String key, String fileName) {
        File srcFile = new File(dataPath + fileName);
        JSONParser parser = new JSONParser();
        String value = null;
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(srcFile));
            for (Object jsonObj : jsonArray) {
                org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonObj;
                if (jsonObject.containsKey(key)) {
                    value = (String) jsonObject.get(key);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * jsonEnhancedReader function takes 3 variables
     * 1- key that will retrieve the value of it
     * 2- direct parent of the key
     * 3- file name to extract data from
     */

    public static String jsonReader(String key, String parentKey, String fileName) {
        File srcFile = new File(dataPath + fileName);
        String value = null;
        try {
            InputStream inputStream = new FileInputStream(srcFile);
            JSONTokener tokener = new JSONTokener(inputStream);
            Object obj = new JSONObject(tokener);

            if (obj instanceof JSONObject) {
                value = findValue((JSONObject) obj, key, parentKey);
            } else if (obj instanceof org.json.JSONArray) {
                org.json.JSONArray jsonArray = (org.json.JSONArray) obj;
                value = findValueInArray(jsonArray, key, parentKey);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    private static String findValue(JSONObject jsonObject, String key, String parentKey) throws JSONException {
        if (jsonObject.has(parentKey)) {
            Object parentValue = jsonObject.get(parentKey);
            if (parentValue instanceof JSONObject) {
                JSONObject parentObject = (JSONObject) parentValue;
                if (parentObject.has(key)) {
                    return parentObject.get(key).toString();
                }
            } else if (parentValue instanceof org.json.JSONArray) {
                org.json.JSONArray parentArray = (org.json.JSONArray) parentValue;
                for (int i = 0; i < parentArray.length(); i++) {
                    if (parentArray.getJSONObject(i).has(key)) {
                        return parentArray.getJSONObject(i).get(key).toString();
                    }
                }
            }
        }

        for (String nestedKey : jsonObject.keySet()) {
            Object nestedValue = jsonObject.get(nestedKey);
            if (nestedValue instanceof JSONObject) {
                String value = findValue((JSONObject) nestedValue, key, parentKey);
                if (value != null) {
                    return value;
                }
            } else if (nestedValue instanceof org.json.JSONArray) {
                org.json.JSONArray jsonArray = (org.json.JSONArray) nestedValue;
                String value = findValueInArray(jsonArray, key, parentKey);
                if (value != null) {
                    return value;
                }
            }
        }

        return null;
    }

    private static String findValueInArray(org.json.JSONArray jsonArray, String key, String parentKey) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            Object element = jsonArray.get(i);
            if (element instanceof JSONObject) {
                String value = findValue((JSONObject) element, key, parentKey);
                if (value != null) {
                    return value;
                }
            } else if (element instanceof org.json.JSONArray) {
                String value = findValueInArray((org.json.JSONArray) element, key, parentKey);
                if (value != null) {
                    return value;
                }
            }
        }
        return null;
    }

    /**
     * function to update a key to new value
     *
     * @param fileName the name if the file you want to update
     * @param key      existing key you want to update
     * @param newValue the new value you want to set to the key
     */
    public static void updateJsonKey(String fileName, String key, String newValue) {
        try {
            JSONTokener jsonTokener = new JSONTokener(new FileReader(dataPath + fileName));
            JSONObject jsonObject = new JSONObject(jsonTokener);
            updateKeyInJson(jsonObject, key, newValue);

            try (FileWriter file = new FileWriter(dataPath + fileName)) {
                file.write(jsonObject.toString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateKeyInJson(JSONObject jsonObject, String key, String newValue) {
        if (jsonObject.has(key)) {
            jsonObject.put(key, newValue);
        }
        for (String nestedKey : jsonObject.keySet()) {
            Object nestedValue = jsonObject.get(nestedKey);
            if (nestedValue instanceof JSONObject) {
                updateKeyInJson((JSONObject) nestedValue, key, newValue);
            } else if (nestedValue instanceof org.json.JSONArray) {
                org.json.JSONArray jsonArray = (org.json.JSONArray) nestedValue;
                for (int i = 0; i < jsonArray.length(); i++) {
                    if (jsonArray.get(i) instanceof JSONObject) {
                        updateKeyInJson(jsonArray.getJSONObject(i), key, newValue);
                    }
                }
            }
        }
    }
}