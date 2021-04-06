package edu.utn.ueed;

import edu.utn.ueed.model.Meter;
import edu.utn.ueed.model.Transmitter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication

public class UeedApplication {

	public static void main(String[] args) {

		Meter houseMeter = new Meter("Motorola","AX100");
		Transmitter serverTransmitter = new Transmitter(houseMeter,5);
		serverTransmitter.start();

		Meter shopMeter = new Meter("Zyxel","MP110");
		Transmitter serverTransmitter2 = new Transmitter(shopMeter,5);
		serverTransmitter2.start();



		//SpringApplication.run(UeedApplication.class, args);
	}
}
