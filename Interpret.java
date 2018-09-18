import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Interpret {

    private HashMap<Character, Integer> frequency;

    public Interpret() {

        frequency = new HashMap<>();

    }

    public HashMap getFrequency() {

        return frequency;

    }

    public void updateKey(char key) {

        frequency.put(key, (frequency.get(key)+1));

    }

    public void setFrequency(BufferedReader br) {
        char current;
        try {
            while(true) {
                current = (char) br.read();
                if (current == (char) -1) {
                    break;
                }
                if (frequency.containsKey(current)) updateKey(current);
                else {
                    frequency.put(current, 1);
                }
            }
        }
        catch(IOException e) {
                throw(new RuntimeException(e));
        }
    }

    public void createBinaryString(BufferedReader br, HashMap prefixes, StringBuilder output) {
        char current;
        try {
            while (true) {
                current = (char) br.read();
                if (current == (char) -1) {
                    break;
                }
                if (prefixes.containsKey(current))
                    output.append(prefixes.get(current));
            }
        }
        catch(IOException e) {
            throw (new RuntimeException(e));
        }
    }

    public void printAll() {
        Object[] arr = frequency.entrySet().toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public void fillPQ(PriorityQueue<H_Node> priorityQueue) {
        String s;
        H_Node h;
        char s_char;
        int s_int;
        Object[] arr = frequency.entrySet().toArray();
        for (int i = 0; i < arr.length; i++) {
            s = arr[i].toString();
            s_char = s.charAt(0);
            s_int = Integer.valueOf(s.substring(2, s.length()));
            h = new H_Node(s_char, s_int);
            priorityQueue.add(h);
        }
    }

    public int getSize() {

        return frequency.size();

    }
}
