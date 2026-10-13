import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        List<String> posts = Arrays.asList(
            "Loving the sunny weather today! #Java #coding #LifeIsGood",
            "Debugging all day long... #java #programming #Coding",
            "Back to work! #Coding #java #LifeIsGood #DEV"
        );
        Map<String, Integer> hashtagCounts = new HashMap<>();
        Pattern pattern = Pattern.compile("#\\w+");

        for (String post : posts) {
            Matcher matcher = pattern.matcher(post);
            while (matcher.find()) {
                String hashtag = matcher.group().toLowerCase();
                hashtagCounts.put(hashtag, hashtagCounts.getOrDefault(hashtag, 0) + 1);
            }
        }
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(hashtagCounts.entrySet());
        sortedList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        System.out.println("=== HASHTAG FREQUENCY COUNTER ===");
        System.out.printf("%-20s %s\n", "Hashtag", "Frequency");
        System.out.println("---------------------------------");
        
        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.printf("%-20s %d\n", entry.getKey(), entry.getValue());
        }
    }
}