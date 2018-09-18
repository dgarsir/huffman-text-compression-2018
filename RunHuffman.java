import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.BitSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class RunHuffman {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter path of file to compress");
        File original_file = new File(input.nextLine());

        FileReader fr = null;

        try {
            fr = new FileReader(original_file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }

        BufferedReader br = new BufferedReader(fr);
        Interpret interpret = new Interpret();
        H_NodeComparator h_nodeComparator = new H_NodeComparator();
        PriorityQueue<H_Node> priorityQueue = new PriorityQueue<>(h_nodeComparator);
        H_Node h1, h2, h3;
        String code = "";
        HashMap<Character, String> prefixes = new HashMap<>();
        StringBuilder binaryStringOutput = new StringBuilder();
        OutputStream output = null;

        interpret.setFrequency(br);
        interpret.fillPQ(priorityQueue);

        while (priorityQueue.size() > 1) {
            h1 = new H_Node(priorityQueue.remove());
            h2 = new H_Node(priorityQueue.remove());
            h3 = new H_Node(H_Node.mergeNodes(h1, h2));
            priorityQueue.add(h3);
        }

        H_Node trie = new H_Node(priorityQueue.remove());
        H_Node.getPrefixes(trie, code, prefixes);

        for (Entry entry : prefixes.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Prefix: " 
            					+ entry.getValue());
        }

    	//output binary string creation below
    	
        try {
            fr = new FileReader(original_file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }

        br = new BufferedReader(fr);

        interpret.createBinaryString(br, prefixes, binaryStringOutput);

        //convert stringbuilder to string
        String binaryStringOutput2 = binaryStringOutput.toString();

        //create bitset
        BitSet binary_code = new BitSet(binaryStringOutput2.length());

        //copy binary string to bitset
        for (int i = 0; i < binaryStringOutput2.length(); i++) {
            if (binaryStringOutput2.charAt(i) == '1')
                binary_code.flip(i);
        }

        try {
            output = new FileOutputStream("/Users/School/Desktop/CSC220/compressed.bin");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File error");
            System.exit(0);
        }

        System.out.println("Attempting to write bin file....");

        try {
            output.write(binary_code.toByteArray());
        } catch (IOException ioException) {
            System.out.println("IO error");
            System.exit(0);
        }

        File compressed_file = new File("/Users/School/Desktop/CSC220/compressed.bin");


        System.out.println("Success. Results...");

        double original_file_size = original_file.length();
        double compressed_file_size = compressed_file.length();
        double percent_compression = 100-((compressed_file_size/original_file_size)*100);

        System.out.printf("Initial size: %f\nCompressed size: %f\nPercent compression: %f",
                original_file_size, compressed_file_size, percent_compression);

    }
}

