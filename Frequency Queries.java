import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
public class Solution {
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> valueCounts = new HashMap<>();
        Map<Integer, Integer> freqCounts = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (List<Integer> query : queries) {
            int op = query.get(0);
            int val = query.get(1);

            if (op == 1) {
                int oldFreq = valueCounts.getOrDefault(val, 0);
                int newFreq = oldFreq + 1;

                valueCounts.put(val, newFreq);

                if (oldFreq > 0) {
                    freqCounts.put(oldFreq, freqCounts.get(oldFreq) - 1);
                }
                freqCounts.put(newFreq, freqCounts.getOrDefault(newFreq, 0) + 1);

            } else if (op == 2) {
                int oldFreq = valueCounts.getOrDefault(val, 0);

                if (oldFreq > 0) {
                    int newFreq = oldFreq - 1;

                    valueCounts.put(val, newFreq);

                    freqCounts.put(oldFreq, freqCounts.get(oldFreq) - 1);
                    if (newFreq > 0) {
                        freqCounts.put(newFreq, freqCounts.getOrDefault(newFreq, 0) + 1);
                    }
                }

            } else if (op == 3) {
                result.add(freqCounts.getOrDefault(val, 0) > 0 ? 1 : 0);
            }
        }

        return result;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
