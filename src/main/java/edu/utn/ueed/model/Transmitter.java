package edu.utn.ueed.model;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/*
In charge of sending consumption data to the server.

*** MODIFY SEND METHOD.
 */
public class Transmitter {
    private Thread newThread;
    private SerialTransmitter newSTransmitter;
    private Meter oneMeter;
    private int minutesInterval;
    private boolean initialized;

    public Transmitter(Meter oneMeter, int minutesInterval) {
        this.oneMeter = oneMeter;
        this.minutesInterval = minutesInterval;
        this.newSTransmitter = new SerialTransmitter(oneMeter,minutesInterval);
        this.newThread = new Thread(newSTransmitter);
        this.initialized = false;

    }

    public void start() {
        if (!initialized){
            this.newThread.start();
            this.initialized = true;
        }
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

        public void setRunning(boolean running) {
            this.running = running;
        }

        private void sendReport(double accConsumption){

            // remove this line
            System.out.println("Serial: " + oneMeter.getSerialId() + " - " + oneMeter.getBrand() + " " + oneMeter.getModel() +
                    "- Accumulated Consumption: " + accConsumption + " Kw.");
            /*
            code for sending report to server. (oneMeter POST to Web Service)
             */
        }

        @Override
        public void run() {
            while(true) {
                while (this.running) {
                    try {
                        TimeUnit.MINUTES.sleep(this.minutesInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.sendReport(this.oneMeter.takeMeasurement(LocalDateTime.now()));
                }
            }
      }
    }
}
