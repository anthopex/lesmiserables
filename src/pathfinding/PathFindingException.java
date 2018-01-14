package pathfinding;

public class PathFindingException extends RuntimeException {
    public PathFindingException(String message) {
        super(message);
    }

    public PathFindingException(Exception reason) {
        super(reason);
    }

    public PathFindingException(String message, Exception reason) {
        super(message, reason);
    }
}
