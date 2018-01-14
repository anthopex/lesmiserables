package pathfinding;

import graph.Edge;

import java.util.List;

public class PathUtils {
    public static void printPath(List<Edge> path, int pathLength, String unit) {
        System.out.format("Weight : %s %s", pathLength, unit);
        System.out.println(); // TODO system.lineSeparator

        for (Edge edge: path) {
            System.out.println();
        }
    }

    public static void printPath(List<Edge> path, int pathLength) {
        printPath(path, pathLength, "unité(s)");
    }
}