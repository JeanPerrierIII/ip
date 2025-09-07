public abstract class Task {
    private final String description;
    private boolean isMarked = false;

    public Task(String description) {
        this.description = description;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public String toString() {
        return ("[" + (isMarked ? "X] " : " ] ") + description);
    }
}
