package aoc.wasteland;

import aoc.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NavSolver extends Solver {
    private Direction[] directions;
    private Map<String, Node> nodes;

    final Pattern pattern = Pattern.compile("[A-Z0-9]{3}");
    public void parseInput(List<String> lines) {
        this.directions = parseDirections(lines.get(0));
        this.nodes = lines.subList(2,lines.size()).stream().map(this::parseNode).collect(Collectors.toMap(Node::getKey, Function.identity()));
        nodes.values().forEach(node -> {
            node.setNeighbours(nodes.get(node.getLeft()), nodes.get(node.getRight()));
        });
    }

    private Direction[] parseDirections(String line) {
        return line.chars().mapToObj(c -> Direction.fromChar((char) c)).toArray(Direction[]::new);
    }

    private Node parseNode(String line){
        Matcher matcher = pattern.matcher(line);
        var keys = new ArrayList<String>();
        while (matcher.find()){
            keys.add(matcher.group());
        }
        return new Node(keys.get(0), keys.get(1), keys.get(2));
    }

    public int countSteps() {
        return countSteps("AAA", true);
    }

    public int countSteps(String key, boolean strict) {
        var count = 0;
        var result = "";
        var currentNode = nodes.get(key);
        while(
                (strict && !"ZZZ".equals(result))
                || !result.endsWith("Z")

        ) {
            for (Direction direction : directions) {
                currentNode = currentNode.goTo(direction);
                count++;
            }
            result = currentNode.getKey();
        }
        return count;
    }

    public long countSimultaneousSteps() {
        var startingNodes = getAllStartingNodes();
        return startingNodes.stream().map(node -> countSteps(node, false)).mapToLong(Long::valueOf).reduce(this::kleinsteGemeneVeelvoud).orElseThrow();
    }

    private long kleinsteGemeneVeelvoud(long a, long b) {
        var lower = Math.min(a, b);
        var higher = Math.max(a, b);
        var kgv = higher;
        while (kgv % lower != 0){
            kgv += higher;
        }
        return kgv;
    }

    public List<String> getAllStartingNodes() {
        return nodes.keySet().stream().filter(key -> key.endsWith("A")).toList();
    }

    public Direction[] getDirections() { return directions; }
}
