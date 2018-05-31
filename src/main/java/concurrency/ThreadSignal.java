package concurrency;

public class ThreadSignal {

    /* Signal via Shared Objects */
    public static class SignalObject {

        private boolean hasDataToProcess = false;

        public synchronized boolean hasDataToProcess() {
            return this.hasDataToProcess;
        }

        public synchronized void setHasDataToProcess(boolean hasDataToProcess) {
            this.hasDataToProcess = hasDataToProcess;
        }
    }

    public static class BusyWait {
        SignalObject signalObject = new SignalObject();

        public void doWait() {
            while(!signalObject.hasDataToProcess()) {
                // wait
            }
        }
    }



    /* Modified version of SignalObject - MonitorObject */
    public static class MonitorObject {}

    public static class MyWaitNotify {
        // Do not use String or Global objects. Instance objects is preferred.
        MonitorObject monitorObject = new MonitorObject();
        boolean isSignalled = false;

        public void doWait() throws InterruptedException {
            synchronized (monitorObject) {
                // "Spin" lock
                while(!isSignalled) {
                    monitorObject.wait();
                }
                // clear signal and continue running.
                isSignalled = false;
            }
        }

        public void doNotify() throws InterruptedException {
            synchronized (monitorObject) {
                isSignalled = true;
                monitorObject.notify();
            }
        }

    }

}
