package edu.utn.ueed.model;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Transmitter {
    private Thread newThread;
    private SerialTransmitter newSTransmitter;
    private Meter oneMeter;
    private int minutesInterval;

    public Transmitter(Meter oneMeter, int minutesInterval) {
        this.oneMeter = oneMeter;
        this.minutesInterval = minutesInterval;
        this.newSTransmitter = new SerialTransmitter(oneMeter,minutesInterval);
        this.newThread = new Thread(newSTransmitter);
        this.newThread.start();

    }

    public void start(){
        this.newSTransmitter.setRunning(true);
    }

    public void stop(){
        this.newSTransmitter.setRunning(false);
    }

    class SerialTransmitter implements Runnable {
        private int minutesInterval;
        private Meter oneMeter;
        private boolean running;

        public SerialTransmitter(Meter oneMeter,int minutesInterval) {
            this.minutesInterval = minutesInterval;
            this.oneMeter = oneMeter;
            this.running = false;
        }

        public int getMinutesInterval() {
            return minutesInterval;
        }

        public void setMinutesInterval(int minutesInterval) {
            this.minutesInterval = minutesInterval;
        }

        public boolean isRunning() {
            return running;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            while(true) {
                while (this.running) {
                    try {
                        TimeUnit.SECONDS.sleep(this.minutesInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LocalDateTime now = LocalDateTime.now();
                    double accMeasurement = this.oneMeter.getAccumulatedMeasurement(LocalDateTime.now());
                    System.out.println(now + ": " + accMeasurement + " Kw.");
                }
            }
      }
    }
}
