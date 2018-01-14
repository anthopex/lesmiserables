package graph;



import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GMLGraphFactory {
    public static Graph createFromGMLFile(String filepath) throws IOException {
        // Ouvre le GML.
        String text = new String(Files.readAllBytes(Paths.get(filepath)), StandardCharsets.UTF_8);

        // Créé un objet GML dont on peut récupérer les données
        List<List<Map<String, Object>>> gml = extractGml(text);
        return createFromGMLObject(gml);
    }

    public static List<List<Map<String, Object>>> extractGml(String text) {
        List<List<Map<String, Object>>> gml = new ArrayList<>();


        int indentationLevel = 0;
        List<Map<String, Object>> currentList = new ArrayList<>();
        Map<String, Object> currentObject = new HashMap<>();
        String lastObjectType = "  node";
        for (String currentLine : text.split("\r\n")) {
            if ("  [".equals(currentLine) || "[".equals(currentLine)) {
                indentationLevel++;
            } else if ("  ]".equals(currentLine) && indentationLevel == 2) {
                currentList.add(currentObject);
                indentationLevel--;
            } else if ("]".equals(currentLine) && indentationLevel == 1) {
                gml.add(currentList);
                indentationLevel--;
            } else if ("  node".equals(currentLine) || "  edge".equals(currentLine)) {
                currentObject = new HashMap<>();

                if (!lastObjectType.equals(currentLine)) {
                    gml.add(currentList);
                    currentList = new ArrayList<>();
                }

                lastObjectType = currentLine;
            } else {
                String[] currentSplittedLine = currentLine.split(" ");
                if (currentSplittedLine.length < 2) continue;
                currentObject.put(currentSplittedLine[currentSplittedLine.length - 2], currentSplittedLine[currentSplittedLine.length - 1].replaceAll("\"", ""));
            }
        }
        return gml;
    }

    private static Graph createFromGMLObject(List<List<Map<String, Object>>> gml) {
        // Créé un objet GML qui contient la liste des personnages + un itérateur pour les parcourir
        List<Map<String, Object>> characters = gml.get(0);

        // Créé un objet GML qui contient les données sur les relations + un itérateur pour les parcourir
        List<Map<String, Object>> relations = gml.get(1);

        Graph graph = new Graph();

        addCharactersToGraph(graph, characters);

        addEdgesBetweenCharacters(graph, relations);

        return graph;
    }

    private static void addCharactersToGraph(Graph graph,  List<Map<String, Object>> characters) {
        Map<Integer, Node> nodes = graph.getNodes();

        // Parcours les personnages et les ajoute au graph.
        for (Map<String, Object> currentChar : characters) {
            // Créé le personnage et l'ajout au graphe :
            Integer id = Integer.parseInt(currentChar.get("id").toString());
            nodes.put(id,
                    new Node(
                            currentChar.get("label").toString()
                    )
            );
        }
    }


    private static void addEdgesBetweenCharacters(Graph graph, List<Map<String, Object>> relations) {
        for (int i = 0; i < relations.size(); i++) {
            // Crée une liste contenant la correspondance (deux ou trois arrêts)
            Map<String, Object> relation = relations.get(i);


            int from = Integer.parseInt(relation.get("source").toString());
            int to = Integer.parseInt(relation.get("target").toString());
            int weight = Integer.parseInt(relation.get("value").toString());
            graph.addUndirectedEdgeBetweenNodes(from, to, weight);
        }
    }
}