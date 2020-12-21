package com.lab_04.command.Program;

import com.lab_04.device.Device;
import com.lab_04.device.AC;

import java.util.List;
import java.util.Scanner;

public class CreateACCommand extends CreateDeviceCommand {
    public CreateACCommand(Scanner in, List<Device> deviceList) {
        super(in, deviceList);
    }

    @Override
    protected Device readDevice() {
        System.out.print("\nEnter manufacturer   : ");
        String manufacturer = _in.nextLine();

        System.out.print("Enter model          : ");
        String model = _in.nextLine();

        System.out.print("Enter min temperature: ");
        int minTemperature = _in.nextInt();

        System.out.print("Enter max temperature: ");
        int maxTemperature = _in.nextInt();

        System.out.print("Enter min air flow   : ");
        int minAirFlow = _in.nextInt();

        System.out.print("Enter max air flow   : ");
        int maxAirFlow = _in.nextInt();

        AC ac = null;

        try {
            ac = new AC.Builder()
                    .setManufacturer(manufacturer)
                    .setModel(model)
                    .setMinTemperature(minTemperature)
                    .setMaxTemperature(maxTemperature)
                    .setMinAirFlow(minAirFlow)
                    .setMaxAirFlow(maxAirFlow)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return ac;
    }
}
