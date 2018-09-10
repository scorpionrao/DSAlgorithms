package live;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ParkingLotProblem {

    // Will become generic if dealing with type of cars or type of spaces
    public static class ParkingLot {

        public static final int SPACES = 1000;
        public Queue<ParkingSpace> parkingSpaces = new ConcurrentLinkedQueue<>();
        public ParkingLot() {
            for(int i = 0; i < SPACES; i++) {
                ParkingSpace space = new ParkingSpace();
                space.setReservationEndTimeBefore();
                parkingSpaces.offer(space);
            }
        }

        public long reserve() {
            long result;
            ParkingSpace first = parkingSpaces.peek();
            if(first.reservationEndTime.before(new Date())) {
                ParkingSpace space = parkingSpaces.poll();
                space.setReservationEndTimeFromNow();
                parkingSpaces.offer(space);
                result = 0;
            } else {
                result = first.reservationEndTime.getTime() - System.currentTimeMillis();
            }
            System.out.println("Result:" + result);
            return result;
        }
    }

    public static class ParkingSpace {

        public Date reservationEndTime = null;

        public void setReservationEndTimeFromNow() {
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR_OF_DAY, 1);
            this.reservationEndTime = cal.getTime();
        }

        public void setReservationEndTimeBefore() {
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR_OF_DAY, -1);
            this.reservationEndTime = cal.getTime();
        }
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot();
        for(int i = 0; i < 1000; i++) {
            lot.reserve();
        }
    }

    /*
        CRUD actions: Is there any parking available - Single data structure ?
                        - Array (fixed size, O(N) to look for isVacant
                        - SinglyLinkedList (dynamic, O(N) to look for isVacant
                        - Sorted Array based on end time (fixed size, O(1)
                        - ArrayList (dynamic size, O(N)

                      Is there any parking available - Multiple data structure ?
                        - Array (fixed size, O(N/2)
                        - SinglyLinkedList (dynamic, O(1)
                        - ArrayList (dynamic, O(1)

                      Identify available and set endTime
                        - O(N) traverse and get end Time.

                        Two Linked Lists:
                        Is There any parking available: answered by available linked list
                        Identity endTime:   the node at the top is last added.
                        What happens if they want to renew ?
                        scan based on id O(N) - array
     */
}
