package datastructures.basicdatastructures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Request {
    int arrivalTime;
    int processTime;
    Request(int arrivalTime, int processTime) {
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
    }
}

class Response {
    boolean isDropped;
    int processingStartTime;
    Response(boolean isDropped, int processingStartTime) {
        this.isDropped = isDropped;
        this.processingStartTime = processingStartTime;
    }
}

class Buffer {
    int size;
    List<Integer> finishTimeOfPackets = new ArrayList<>();
    Buffer(int size) {
        this.size = size;
    }

    /*
        remove completed packets
     */
    Response process(Request request) {

        while(!finishTimeOfPackets.isEmpty() && finishTimeOfPackets.get(0) <= request.arrivalTime) {
            finishTimeOfPackets.remove(0);
        }

        if(finishTimeOfPackets.size() == size) {
            return new Response(true, -1);
        }

        int expectedStartTime;
        if(finishTimeOfPackets.isEmpty()) {
            expectedStartTime = request.arrivalTime;
        } else {
            expectedStartTime = finishTimeOfPackets.get(finishTimeOfPackets.size() - 1);
        }
        finishTimeOfPackets.add(expectedStartTime + request.processTime);
        return new Response(false, expectedStartTime);
    }
}

class process_packages {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Buffer buffer = new Buffer(size);

        ArrayList<Request> requests = new ArrayList<>();
        int requestCount = scanner.nextInt();
        for(int i = 0; i < requestCount; i++) {
            requests.add(new Request(scanner.nextInt(), scanner.nextInt()));
        }

        ArrayList<Response> responses = new ArrayList<>();
        for(Request request : requests) {
            Response response = buffer.process(request);
            responses.add(response);
        }

        for(Response response : responses) {
            if(response.isDropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.processingStartTime);
            }
        }
    }
}
