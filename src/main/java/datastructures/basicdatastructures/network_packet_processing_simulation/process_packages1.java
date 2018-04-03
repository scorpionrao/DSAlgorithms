package datastructures.basicdatastructures.network_packet_processing_simulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Request1 {
    int arrivalTime;
    int processTime;
    Request1(int arrivalTime, int processTime) {
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
    }
}

class Response1 {
    boolean isDropped;
    int processingStartTime;
    Response1(boolean isDropped, int processingStartTime) {
        this.isDropped = isDropped;
        this.processingStartTime = processingStartTime;
    }
}

class Buffer1 {
    int size;
    List<Integer> finishTimeOfPackets = new ArrayList<>();
    Buffer1(int size) {
        this.size = size;
    }

    /*
        remove completed packets
     */
    Response1 process(Request1 request1) {

        while(!finishTimeOfPackets.isEmpty() && finishTimeOfPackets.get(0) <= request1.arrivalTime) {
            finishTimeOfPackets.remove(0);
        }

        if(finishTimeOfPackets.size() == size) {
            return new Response1(true, -1);
        }

        int expectedStartTime;
        if(finishTimeOfPackets.isEmpty()) {
            expectedStartTime = request1.arrivalTime;
        } else {
            expectedStartTime = finishTimeOfPackets.get(finishTimeOfPackets.size() - 1);
            finishTimeOfPackets.add(expectedStartTime + request1.processTime);
        }
        return new Response1(false, expectedStartTime);
    }
}

class process_packages1 {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Buffer1 buffer1 = new Buffer1(size);

        ArrayList<Request1> request1s = new ArrayList<>();
        int requestCount = scanner.nextInt();
        for(int i = 0; i < requestCount; i++) {
            request1s.add(new Request1(scanner.nextInt(), scanner.nextInt()));
        }

        ArrayList<Response1> response1s = new ArrayList<>();
        for(Request1 request1 : request1s) {
            Response1 response1 = buffer1.process(request1);
            response1s.add(response1);
        }

        for(Response1 response1 : response1s) {
            if(response1.isDropped) {
                System.out.println(-1);
            } else {
                System.out.println(response1.processingStartTime);
            }
        }
    }
}
