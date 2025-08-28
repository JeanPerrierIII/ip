public class Task {
    private final String description;
    private boolean isMarked = false;

    public Task(String description) {
        this.description = description;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public void printStatus() {
        System.out.println("[" + (isMarked ? "X] " : " ] ") + description);
    }
}
