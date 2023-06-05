package day05javaswingrestclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ApiTodos {
     
    static final String BASE_URL = "http://localhost:8080/api"; // to check
    
    private final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();
     
    public ArrayList<Todo> getAllTodos() throws ApiErrorException {
        try {
            URL url = new URL(BASE_URL + "/todos"); // ex
            connect("GET", url);
            String jsonString = getString(url);
            //Using the GSON library parse the string straight into an arraylist of todos and return it
            Gson gson = new Gson();
            ArrayList<Todo> todos = gson.fromJson(jsonString, new TypeToken<ArrayList<Todo>>() {
            }.getType());
            System.out.println(todos);
           
            return todos;
        } catch (IOException e) {
            throw new ApiErrorException(e);
        }
    }      
    
    public long addNew(Todo todo) throws ApiErrorException {   
        
            try {
            URL url = new URL(BASE_URL + "/todos/");
            HttpURLConnection conn = connect("POST", url);
            return sendJson(todo, conn);
            
        } catch (IOException e) {
        throw new RuntimeException("Not implemented");
        }
    } 


    public boolean update(Todo todo) throws ApiErrorException{
        try {
            URL url = new URL(BASE_URL + "/todos/" + todo.getId());
            HttpURLConnection conn = connect("PUT", url);
            return updateJson(todo, conn);
            
        } catch (IOException e) {
        throw new RuntimeException("Not implemented");
        }
    }
    
    public boolean delete(int id) throws ApiErrorException {
        try {
            URL url = new URL(BASE_URL + "/todos/" + id);
            HttpURLConnection conn = connect("DELETE", url);
            return deleteJson(conn);
        } catch (IOException e) {
        throw new RuntimeException("Not implemented");
        }
    }
    
    // translate todo to a json string
    private long sendJson(Todo todo, HttpURLConnection conn) throws IOException, ApiErrorException {
        String json = gson.toJson(todo);
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        jsonObject.remove("id");

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonObject.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();

        if (responseCode != 200 && responseCode != 201) {
            StringBuilder errorBody = new StringBuilder();
            try (Scanner scanner = new Scanner(conn.getErrorStream())) {
                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    errorBody.append(scanner.nextLine());
                }
            }
            System.err.println(errorBody);
            throw new ApiErrorException("HttpResponseCode: " + responseCode);
        }
        StringBuilder responseBody = new StringBuilder();
        try (Scanner scanner = new Scanner(conn.getInputStream())) {
            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                responseBody.append(scanner.nextLine());
            }
        }

        System.out.println(responseBody);
        return gson.fromJson(responseBody.toString(), Todo.class).id;
    }
    
    private boolean updateJson(Todo todo, HttpURLConnection conn) throws IOException, ApiErrorException {
        String json = gson.toJson(todo);
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        //jsonObject.remove("id");

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonObject.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();

        if (responseCode != 200 && responseCode != 201) {
            StringBuilder errorBody = new StringBuilder();
            try (Scanner scanner = new Scanner(conn.getErrorStream())) {
                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    errorBody.append(scanner.nextLine());
                }
            }

            System.err.println(errorBody);
            throw new ApiErrorException("HttpResponseCode: " + responseCode);
        }
        StringBuilder responseBody = new StringBuilder();
        try (Scanner scanner = new Scanner(conn.getInputStream())) {
            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                responseBody.append(scanner.nextLine());
            }
        }

        System.out.println(responseBody);
        
        return gson.fromJson(responseBody.toString(), boolean.class);
        
      
    }
    
    private boolean deleteJson(HttpURLConnection conn) throws IOException, ApiErrorException {
        
        int responseCode = conn.getResponseCode();

        if (responseCode != 200 && responseCode != 201) {
            StringBuilder errorBody = new StringBuilder();
            try (Scanner scanner = new Scanner(conn.getErrorStream())) {
                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    errorBody.append(scanner.nextLine());
                }
            }

            System.err.println(errorBody);
            throw new ApiErrorException("HttpResponseCode: " + responseCode);
        }
        StringBuilder responseBody = new StringBuilder();
        try (Scanner scanner = new Scanner(conn.getInputStream())) {
            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                responseBody.append(scanner.nextLine());
            }
        }

        System.out.println(responseBody);
        
        return gson.fromJson(responseBody.toString(), boolean.class);
        
      
    }
    
    private String getString(URL url) throws ApiErrorException {
        StringBuilder jsonString = new StringBuilder();
        try (Scanner scanner = new Scanner(url.openStream())) {
            //Write all the JSON data into a string using a scanner
            while (scanner.hasNextLine()) {
                jsonString.append(scanner.nextLine());
            }

            return jsonString.toString();
        } catch (IOException e) {
            throw new ApiErrorException(e);
        }
    }
    
    private HttpURLConnection connect(String method, URL url) throws ApiErrorException { // takes the url and openconnetion to it
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method); // get/patch/post/delete...

            if (method.equals("GET") || method.equals("DELETE")) {
                conn.connect();
                int responseCode = conn.getResponseCode();
                if (responseCode != 200 && responseCode != 201) {
                    throw new ApiErrorException("HttpResponseCode: " + responseCode);
                }
            } else if (method.equals("PUT") || method.equals("POST") || method.equals("PATCH")) {
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
            } else {
                throw new ApiErrorException("Method not valid");
            }
            return conn;
        } catch (IOException e) {
            throw new ApiErrorException(e);
        }
    }

}
