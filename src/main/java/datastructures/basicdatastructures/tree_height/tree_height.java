package datastructures.basicdatastructures.tree_height;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class tree_height {

    class FastScanner {
        BufferedReader in;
        StringTokenizer tokenizer = new StringTokenizer("");

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tokenizer.hasMoreElements()) {
                tokenizer = new StringTokenizer(in.readLine());
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeHeight {

        int[] parent = null;
        boolean filesFlag = true;

        public void read() throws IOException {
            if(filesFlag) {
                File folder = new File("/Users/rgonugunta/git/vagrant/projects/Algorithms/src/datastructures/basicdatastructures/tests");
                if(folder.exists() && folder.isDirectory()) {
                    File[] files = folder.listFiles();
                    Arrays.sort(files, new Comparator<File>(){
                        @Override
                        public int compare(File f1, File f2) {return f1.getName().compareTo(f2.getName());}
                    });
                    for(File test : files) {
                        if(test.getName().endsWith(".a")) continue;
                        FileReader fileReader = new FileReader(test);
                        BufferedReader reader = new BufferedReader(fileReader);
                        int n = Integer.parseInt(reader.readLine());
                        parent = new int[n];
                        String[] parenIndexes = reader.readLine().split(" ");
                        for(int i = 0; i < parenIndexes.length; i++) {
                            parent[i] = Integer.parseInt(parenIndexes[i]);
                        }
                        measure();
                    }
                }
            } else {
                FastScanner scanner = new FastScanner();
                int numOfNodes = scanner.nextInt();
                parent = new int[numOfNodes];

                boolean rootExists = false;
                for (int i = 0; i < parent.length; i++) {
                    parent[i] = scanner.nextInt();
                    if (parent[i] >= parent.length) {
                        throw new RuntimeException("Invalid Input: Parent index does not exist");
                    } else if (parent[i] == i) {
                        throw new RuntimeException("Invalid Input: Parent index and index are same. Infinite SinglyLinkedListLoop");
                    }
                    if (!rootExists) {
                        rootExists = parent[i] == -1;
                    }
                }
                if (!rootExists) {
                    throw new RuntimeException("Invalid Input: Root does not exist");
                }
            }
        }

        /*
            O(N * H)
         */
        private int computeHeight() {

            int maxHeight = 0;
            for (int i = 0; i < parent.length; i++) {
                int heightAtIndex = 0;
                for (int j = i; j != -1; j = parent[j]) {
                    heightAtIndex++;
                }
                maxHeight = Math.max(maxHeight, heightAtIndex);
            }
            return maxHeight;

        }

        private int computeHeightFast() {
            int maxHeight = 0;
            int[] heights = new int[parent.length];
            for (int i = 0; i < parent.length; i++) {
                maxHeight = Math.max(maxHeight, usingDPHeightAt(i, heights));
            }
            return maxHeight;
        }

        private int usingDPHeightAt(int i, int[] heights) {
            // parent of root node only 1 exists
            if (parent[i] == -1) {
                heights[i] = 1;
                return 1;
            }
            if (heights[i] != 0) {
                // already computed, do nothing
                return heights[i];
            }
            // simply add 1 to parent height
            heights[i] = usingDPHeightAt(parent[i], heights) + 1;
            return heights[i];
        }

        private void measure() {
            long start = System.currentTimeMillis();
            int fast = computeHeightFast();
            long end = System.currentTimeMillis();
            long fastTimeTaken = end - start;

            start = System.currentTimeMillis();
            int naive = computeHeight();
            end = System.currentTimeMillis();
            long naiveTimeTaken = end - start;

            System.out.println("Fast(in ms):" + fastTimeTaken);
            System.out.println("Naive(in ms):" + naiveTimeTaken);
            System.out.println(naive == fast ? "OK" : "Error! naive = " + naive + " Fast = " + fast);
        }

    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            @Override
            public void run() {
                try {
                    new tree_height().run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "1", 1 << 26).start();

    }

    public void run() throws IOException {
        TreeHeight treeheight = new TreeHeight();
        treeheight.read();
        System.out.println(treeheight.computeHeightFast());
    }
}

