package datastructures.binarysearchtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class tree_orders {

    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeOrders {
        int n;
        int[] key, left, right;
        ArrayList<Integer> result;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }
        }

        List<Integer> inOrder() {
            result = new ArrayList<>();
            inOrderTraversal(0);
            return result;
        }

        List<Integer> preOrder() {
            result = new ArrayList<>();
            preOrderTraversal(0);
            return result;
        }

        List<Integer> postOrder() {
            result = new ArrayList<>();
            postOrderTraversal(0);
            return result;
        }

        void inOrderTraversal(int index) {
            if(left[index] != -1) {
                inOrderTraversal(left[index]);
            }
            result.add(key[index]);
            if(right[index] != -1) {
                inOrderTraversal(right[index]);
            }
        }

        void preOrderTraversal(int index) {
            result.add(key[index]);
            if(left[index] != -1) {
                preOrderTraversal(left[index]);
            }
            if(right[index] != -1) {
                preOrderTraversal(right[index]);
            }
        }

        void postOrderTraversal(int index) {
            if(left[index] != -1) {
                postOrderTraversal(left[index]);
            }
            if(right[index] != -1) {
                postOrderTraversal(right[index]);
            }
            result.add(key[index]);
        }
    }

    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            @Override
            public void run() {
                try {
                    new tree_orders().run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    public void writeResponse(List<Integer> x) {
        for(Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeOrders treeOrders = new TreeOrders();
        treeOrders.read();
        writeResponse(treeOrders.inOrder());
        writeResponse(treeOrders.preOrder());
        writeResponse(treeOrders.postOrder());
    }

}
