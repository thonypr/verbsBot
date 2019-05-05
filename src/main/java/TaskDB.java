public class TaskDB {
    private int id;
    private String name;
    private boolean is_active;

    public TaskDB(int id, String name, boolean is_active) {
        this.id = id;
        this.name = name;
        this.is_active = is_active;
    }

    public String getName() {
        return name;
    }

    public boolean getIsActive() {
        return is_active;
    }

    public int getId() {
        return id;
    }
}
