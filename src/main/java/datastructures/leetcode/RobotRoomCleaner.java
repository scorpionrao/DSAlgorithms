package datastructures.leetcode;

import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner {

    public interface Robot {
        boolean makeMove();
        void turnLeft();
        void turnRight();
        void clean();
    }

    public static void cleanRoom(Robot robot) {
        Set<String> cleanedRooms = new HashSet<>();
        int currentDirection = 0;
        int startRow = 1;
        int startCol = 3;
        cleanRoomBacktrack(robot, cleanedRooms, startRow, startCol, currentDirection);
    }

    private static int[][] room = {
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1}
    };

    private static void cleanRoomBacktrack(Robot robot, Set<String> cleanedRooms,
                                           int currentRow, int currentCol,
                                           int currentDirection) {

        String currentRoom = currentRow + "->" + currentCol;
        if(cleanedRooms.contains(currentRoom)
            || currentRow < 0
            || currentCol < 0
            || currentRow >= room.length
            || currentCol >= room[0].length
            || room[currentRow][currentCol] == 0) {
            return;
        }

        robot.clean();
        cleanedRooms.add(currentRoom);

        for(int option = 0; option < 4; option++) {
            if(robot.makeMove()) {
                int nextRow = currentRow;
                int nextCol = currentCol;
                switch (currentDirection) {
                    case 0:
                        nextRow = currentRow - 1;
                        break;
                    case 90:
                        nextCol = currentCol + 1;
                        break;
                    case 180:
                        nextRow = currentRow + 1;
                        break;
                    case 270:
                        nextCol = currentCol - 1;
                        break;
                    default:
                        break;
                }

                cleanRoomBacktrack(robot, cleanedRooms, nextRow, nextCol, currentDirection);
                // Unmake the move
                robot.turnLeft();
                robot.turnLeft();
                robot.makeMove();
                robot.turnRight();
                robot.turnRight();
            }
            // always turn right for next direction - could be ***ANY***
            robot.turnRight();
            currentDirection = currentDirection + 90;
            currentDirection = currentDirection % 360;
        }
    }

    public static void main(String[] args) {
        
    }
}
