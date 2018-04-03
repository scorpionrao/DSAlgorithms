package live;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class backendqueue {

/*
Design & implement an API that allows us to offload work into a queue, as well
as consume and complete jobs on the queue. The API should provide the following
functionality:

1) enqueue a job, which would consist of a job type and parameters

2) register something to handle jobs of a specific type
   (e.g. this could be a callback or class)

3) execute all jobs in the queue

Thoughts:

client sends to job request - job type and parameters

job types - agnostic to job type, registered handler for job type

client to queue - push notification

Objects: queue, interface event handler,

implement event handler for each job type

parameters: List<Object>

iteratively increase the scope

Abstract : JobType
Subclasses: Implementations of JobType

InputObject:
Members - JobType (abstract), List<Objects>

API signature:
Method Name: addToQueue
Parameters: InputObject
Return: void

Method Name: removeFromQueue
Return: InputObject

Interface:
process(List<Object>)


 */

    static class JobType {

        int type;
        public JobType(int type) {
            this.type = type;
        }

        void printType() {
            System.out.println(this.type);
        }
    }

    interface EventHandler {
        void process(List<Object> params);
    }

    static class Event1 implements EventHandler {

        @Override
        public void process(List<Object> params) {
            System.out.println("Processed within Event1");
        }

    }

    class Event2 implements EventHandler {
        @Override
        public void process(List<Object> params) {
            System.out.println("Processed within Event2");
        }
    }

    static class InputObject {
        JobType jobType;
        List<Object> params;
        public InputObject(JobType jobType, List<Object> params) {
            this.jobType = jobType;
            this.params = params;
        }
    }

    public static Queue<InputObject> queue = new LinkedList<InputObject>();
    public static Map<JobType, EventHandler> map = new HashMap<JobType, EventHandler>();

    public static void register(JobType jobType, EventHandler eventHandler) {
        map.put(jobType, eventHandler);
    }

    /*
    Method Name: addToQueue
    Parameters: InputObject
    Return: void
  */
    public static void addQueue(InputObject inputObject) {
        if(inputObject != null) {
            queue.offer(inputObject);
        }
    }

    /*
      This will be called post process
      Method Name: removeFromQueue
      Return: InputObject
      */
    public static void process() {
        InputObject inputObject = queue.poll();
        JobType jobType = inputObject.jobType;
        EventHandler eventHandler = map.get(jobType);
        if(eventHandler == null) {
            return;
        }

        if(inputObject.params == null) {
            return;
        }

        eventHandler.process(inputObject.params);

    }

    public static void main(String[] args) {
        JobType jobType1 = new JobType(1);
        //jobType1.printType();

        InputObject inputObject1 = new InputObject(jobType1, new ArrayList<Object>());
        addQueue(inputObject1);
        //queue.poll().jobType.printType();

        // register
        EventHandler event1 = new Event1();
        register(jobType1, event1);

        addQueue(inputObject1);
        process();

        //event1.process(new ArrayList<Object>());


    }
}
