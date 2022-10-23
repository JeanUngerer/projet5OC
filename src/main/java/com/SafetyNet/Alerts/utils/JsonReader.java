package com.SafetyNet.Alerts.utils;


import com.SafetyNet.Alerts.constants.DataUrls;
import com.SafetyNet.Alerts.persons.service.domain.Persons;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.Reader;
        import java.net.URL;
        import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class JsonReader {

    private static ModelMapper modelMapper = new ModelMapper();

    private static final String URL = "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl(DataUrls.INITIAL_URL);
        //System.out.println(json.toString());
        //System.out.println(json.get("persons"));
        JSONArray jsonArray = json.getJSONArray("persons");
        jsonArray.length(); // see stream and lambda functions ->
        System.out.println(jsonArray.get(0));
        Runnable run = ( ) -> System.out.println();

        jsonArray.forEach(System.out::println);
        //jsonArray.forEach(e -> JSONObject(e).get("city"));

        //jsonArray.stream().

        //List list = (List) jsonArray.stream().map(jsonVar -> jsonVar.toString()).collect(Collectors.toList());

        ArrayList<Object> array = new ArrayList<>();
        jsonArray.forEach(array::add);
        System.out.println(jsonArray);
        //array.stream().map(var -> var.toString()).forEach();

        System.out.println("========================================================");
        System.out.println("========================================================");
        jsonArray.forEach(jsonElem -> System.out.println(modelMapper.map(jsonElem, Persons.class)));

    }

}
