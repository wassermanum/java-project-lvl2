package hexlet.code;

public record Diff(String key, Object oldValue, Object newValue, Status status) {
}
