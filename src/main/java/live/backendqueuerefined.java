package live;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class backendqueuerefined {

    public static class JobTypeFactory {

        public static JobType createJobType(String str) {
            if(str.equals("jobType1")) {
                return new JobType1(str);
            }
            throw new IllegalArgumentException("Invalid JobType");
        }
    }

    public static abstract class JobType {

    }

    public static class JobType1 extends JobType {
        String str;
        public JobType1(String str) {
            this.str = str;
        }
    }

    public static class Request {

        public JobType jobType;
        public List<Object> params;

        public Request(String jobType, List<Object> params) {
            this.jobType = JobTypeFactory.createJobType(jobType);
            this.params = params;
        }
    }

    public static class Module {

        Queue<Request> requestQueue = new LinkedList<>();
        Map<JobType, EventHandler> map = new HashMap<>();

        private void register() {
            map.put(JobTypeFactory.createJobType("jobType1"), new Event1());
        }

        public void consume(String jobType, List<Object> params) {
            Request request = new Request(jobType, params);
            requestQueue.offer(request);
        }

        private void process() {
            while(!requestQueue.isEmpty()) {
                Request request = requestQueue.poll();
                JobType jobType = request.jobType;
                EventHandler eventHandler = map.get(jobType);
                eventHandler.process();
            }
        }

    }

    public interface EventHandler {
        void process();
    }

    public static class Event1 implements EventHandler {
        @Override
        public void process() {
            System.out.println("Processed from Event1");
        }
    }

    public static void main(String[] args) {
        Module module = new Module();
        module.register();
        module.consume("jobType1", new ArrayList<>());
        module.process();
    }
}
