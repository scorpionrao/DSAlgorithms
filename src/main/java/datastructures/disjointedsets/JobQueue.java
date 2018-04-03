package datastructures.disjointedsets;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    // Data Structures for input
    private int numWorkers;
    private int[] jobProcessingTimes;

    // Data structures for output
    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobProcessingTimes = new int[m];
        for (int i = 0; i < m; ++i) {
            jobProcessingTimes[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobProcessingTimes.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobProcessingTimes.length];
        startTime = new long[jobProcessingTimes.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobProcessingTimes.length; i++) {
            int duration = jobProcessingTimes[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }
    }

    public class Worker {
        private int id;
        private long nextFreeTime;
        Worker(int id) {
            this.id = id;
        }
        void setNextFreeTime(long nextFreeTime) {
            this.nextFreeTime = nextFreeTime;
        }
    }

    private void assignJobsFast() {
        // initialize outputs
        assignedWorker = new int[jobProcessingTimes.length];
        startTime = new long[jobProcessingTimes.length];
        // Have a priority queue that will spit out a worker based on id and nextFreeTime

        PriorityQueue<Worker> pq = new PriorityQueue<Worker>(numWorkers,
                new Comparator<Worker>(){
                    @Override
                    public int compare (Worker w1, Worker w2) {
                        if(w1.nextFreeTime == w2.nextFreeTime)
                            return w1.id - w2.id;
                        else
                            return (int) (w1.nextFreeTime - w2.nextFreeTime);
                    }
                });
        for (int i = 0; i < numWorkers; i++)
            pq.offer(new Worker(i));
        for (int i = 0; i < jobProcessingTimes.length; i++) {
            Worker freeThread = pq.poll();
            assignedWorker[i] = freeThread.id;
            startTime[i] = freeThread.nextFreeTime;
            freeThread.nextFreeTime += jobProcessingTimes[i];
            pq.offer(freeThread);
        }

    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobsFast();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
