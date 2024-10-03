import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {
    private String name;
    private String email;
    private int age;
    private int height; // in cm
    private int weight; // in kg
    private String password;
    private String dateOfBirth;
    private Map<String, Boolean> weeklyActivities;

    // Initializer block to ensure weeklyActivities is always initialized
    {
        weeklyActivities = new HashMap<>();
    }

    public User(String name, String email, int age, int height, int weight, String password, String dateOfBirth) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActivityCompleted(String dayOfWeek, boolean completed) {
        weeklyActivities.put(dayOfWeek, completed);
    }

    public double getWeeklyProgress() {
        if (weeklyActivities == null || weeklyActivities.isEmpty()) {
            return 0.0; // Return 0 if no activities recorded
        }

        int totalDays = 7;
        int completedDays = 0;

        for (boolean completed : weeklyActivities.values()) {
            if (completed) {
                completedDays++;
            }
        }

        return (double) completedDays / totalDays * 100.0;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
