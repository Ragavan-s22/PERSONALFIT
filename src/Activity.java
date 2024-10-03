import java.io.Serializable;

public class Activity implements Serializable {
    private String name;
    private boolean completed;

    public Activity(String name) {
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return name + (completed ? " (Completed)" : "");
    }
}
