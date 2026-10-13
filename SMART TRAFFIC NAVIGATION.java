import java.util.*;

class SmartTrafficNavigation {
    static class Road {
        String destination;
        double distanceKm;
        double trafficMultiplier; 
        public Road(String destination, double distanceKm, double trafficMultiplier) {
            this.destination = destination;
            this.distanceKm = distanceKm;
            this.trafficMultiplier = trafficMultiplier;
        }
        public double getTravelTime() {
            return distanceKm * trafficMultiplier;
        }
    }
    static class Node implements Comparable<Node> {
        String name;
        double travelTime;
        public Node(String name, double travelTime) {
            this.name = name;
            this.travelTime = travelTime;
        }
        @Override
        public int compareTo(Node other) {
            return Double.compare(this.travelTime, other.travelTime);
        }
    }
    private final Map<String, List<Road>> cityGraph = new HashMap<>();
    public void addLocation(String location) {
        cityGraph.putIfAbsent(location, new ArrayList<>());
    }
    public void addRoad(String source, String destination, double distanceKm, double trafficMultiplier) {
        addLocation(source);
        addLocation(destination);
        cityGraph.get(source).add(new Road(destination, distanceKm, trafficMultiplier));
        cityGraph.get(destination).add(new Road(source, distanceKm, trafficMultiplier)); 
    }
    public void findFastestRoute(String start, String end) {
        if (!cityGraph.containsKey(start) || !cityGraph.containsKey(end)) {
            System.out.println("Error: Start or destination location does not exist on the map.");
            return;
        }

        Map<String, Double> minTravelTime = new HashMap<>();
        Map<String, String> previousLocation = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (String location : cityGraph.keySet()) {
            minTravelTime.put(location, Double.MAX_VALUE);
        }
        minTravelTime.put(start, 0.0);
        pq.add(new Node(start, 0.0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            String currentLoc = current.name;
            if (currentLoc.equals(end)) break;
            if (current.travelTime > minTravelTime.get(currentLoc)) continue;
            for (Road road : cityGraph.get(currentLoc)) {
                double newTime = minTravelTime.get(currentLoc) + road.getTravelTime();
                if (newTime < minTravelTime.get(road.destination)) {
                    minTravelTime.put(road.destination, newTime);
                    previousLocation.put(road.destination, currentLoc);
                    pq.add(new Node(road.destination, newTime));
                }
            }
        }
        if (minTravelTime.get(end) == Double.MAX_VALUE) {
            System.out.println("No reachable route found from " + start + " to " + end);
            return;
        }
        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previousLocation.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        System.out.println("=== SMART TRAFFIC NAVIGATION ===");
        System.out.println("Optimal Route: " + String.join(" -> ", path));
        System.out.printf("Estimated Travel Cost/Time Index: %.2f units\n", minTravelTime.get(end));
    }
}
public class Main {
    public static void main(String[] args) {
        SmartTrafficNavigation nav = new SmartTrafficNavigation();
        nav.addRoad("Downtown", "Main Street", 5.0, 2.5); 
        nav.addRoad("Downtown", "Bypass Expressway", 8.0, 1.0);
        nav.addRoad("Main Street", "Uptown", 4.0, 2.0);
        nav.addRoad("Bypass Expressway", "Uptown", 3.0, 1.1);
        nav.addRoad("Uptown", "Airport", 10.0, 1.0);

        nav.findFastestRoute("Downtown", "Airport");
    }
}