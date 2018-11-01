package live;

import java.io.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;

/////////////////////////// JukeBox.java ///////////////////////////


/**
 * This class represents the state of an iPod Shuffle-like JukeBox. To use this
 * JukeBox, a customer inserts money into the jukebox.
 *
 * The hardware behaves based on the state of this class, i.e. if isPlaying is set
 * to true, the current song will start playing.
 */
public class JukeBox {

    public MoneyIO cashIO;
    private List<String> songsList;
    private Integer costOfSong;
    private int currentSong;
    private boolean isPlaying;
    private double songsLeft;

    public JukeBox(List<String> songsList, int costOfSong, MoneyIO cashIO) {
        this.songsList = songsList;
        this.costOfSong = costOfSong;
        this.cashIO = cashIO;
        this.currentSong = 0;
        this.isPlaying = false;
        this.songsLeft = 0;
    }

    public int getCurrentSong() {
        return this.currentSong;
    }

    public List<String> getSongsList() {
        return this.songsList;
    }

    public void addMoney() throws StuckBillException {
        int value = this.cashIO.scanBill();

        if (value > 20) {
            this.cashIO.rejectBill();
        } else {
            this.songsLeft = value / this.costOfSong;
        }
    }

    public String getSong() {
        return this.songsList.get(this.currentSong);
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void togglePlayPause() {
        this.isPlaying = !this.isPlaying;
    }

    public void selectNextSong() {
        if (this.songsLeft >= 1) {
            this.songsLeft--;
            this.currentSong++;
        }
    }

    public void selectPrevSong() {
        if (this.songsLeft >= 1) {
            this.songsLeft--;
            this.currentSong--;
        }
    }

    public void printAllSongs() {
        System.out.println(this.songsList.toString());
    }

    public void eventSongFinished() {
        this.isPlaying = false;
        this.selectNextSong();
        this.togglePlayPause();
    }



    public static class JukeBoxTest {

        static class TestCashIO implements MoneyIO {

            int billAmount;
            boolean rejectBill;

            public TestCashIO(int billAmount, boolean rejectBill) {
                this.billAmount = billAmount;
                this.rejectBill = rejectBill;
            }

            @Override
            public int scanBill() throws StuckBillException {
                return this.billAmount;
            }

            @Override
            public boolean rejectBill() throws StuckBillException {
                return this.rejectBill;
            }
        }

        @Test
        public void testJukeBoxStart() {
            System.out.println("Test Begin");
            List<String> songs = new LinkedList<String>();

            String song1 = "Song 1";
            String song2 = "Song 2";
            String song3 = "Song 3";
            int costPerSong = 2;

            songs.add(song1);
            songs.add(song2);
            songs.add(song3);

            JukeBox jukeBox = new JukeBox(songs, costPerSong, new TestCashIO(10, false));
            assertTrue(jukeBox.getCurrentSong() == 0);
            assertFalse(jukeBox.isPlaying());

            List<String> actualSongs = jukeBox.getSongsList();
            assertNotNull(actualSongs);
            assertTrue(actualSongs.size() == 3);

            assertTrue(actualSongs.contains(song1));
            assertTrue(actualSongs.contains(song2));
            assertTrue(actualSongs.contains(song3));
            System.out.println("Test End");
        }

        @Test
        public void testItPrintsAllSongs() {
            // no assert here, add few
            System.out.println("Test Begin");
            LinkedList<String> songs = new LinkedList<String>();
            songs.add("Song 1");
            songs.add("Song 2");
            songs.add("Song 3");

            JukeBox jukeBox = new JukeBox(songs, 2, new TestCashIO(10, false));
            jukeBox.printAllSongs();
            System.out.println("Test End");
        }

        @Test
        public void testAddValidMoney() throws StuckBillException {
            System.out.println("Test Begin");
            List<String> songs = new LinkedList<String>();

            String song1 = "Song 1";
            String song2 = "Song 2";
            String song3 = "Song 3";
            int costPerSong = 2;

            songs.add(song1);
            songs.add(song2);
            songs.add(song3);

            JukeBox jukeBox = new JukeBox(songs, costPerSong, new TestCashIO(10, false));
            // Pre-state
            assertTrue(jukeBox.songsLeft == 0);
            // action
            jukeBox.addMoney();
            // post validation
            assertTrue(jukeBox.songsLeft == 1);
        }

    }

    public interface MoneyIO {
        int scanBill() throws StuckBillException;
        boolean rejectBill() throws StuckBillException;
    }




    /////////////////////////// CashIO.java ///////////////////////////
    ////////////////// VENDOR CODE - Do not modify. ///////////////////

    public static class RealCashIO implements MoneyIO {
        Scanner usbIn = new Scanner(System.in);
        PrintStream usbOut = System.out;

        public int scanBill() throws StuckBillException {
            usbOut.println("SCAN_BILL");
            String response = usbIn.nextLine().toLowerCase();
            if (response.equals("stuck_bill")) {
                throw new StuckBillException();
            } else {
                return Integer.parseInt(response);
            }
        }

        public boolean rejectBill() throws StuckBillException {
            usbOut.println("REJECT_BILL");
            String response = usbIn.nextLine().toLowerCase();
            if (response.equals("ok")) {
                return true;
            } else if (response.equals("stuck_bill")) {
                throw new StuckBillException();
            } else {
                return false;
            }
        }
    }

    @SuppressWarnings("serial")
    static class StuckBillException extends Exception {}

    public static void main(String[] args) {
        JUnitCore.runClasses(JukeBoxTest.class);
    }
}

