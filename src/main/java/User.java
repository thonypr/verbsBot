public class User {
    private Long userId;
    private State state;

    public Long getUserId() {
        return userId;
    }

    public User(Long userId, State state) {
        this.state = state;
        this.userId = userId;
    }
}
