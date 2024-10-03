import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Gym {
    private List<User> users;
    private static final String FILE_PATH = "gym_users.json";
    private Gson gson;

    public Gym() {
        users = new ArrayList<>();
        gson = new Gson();
        loadDataFromFile();
    }

    public void addUser(User user) {
        users.add(user);
        saveDataToFile();
    }

    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public void saveDataToFile() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataFromFile() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
            users = gson.fromJson(reader, userListType);
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with an empty list.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

