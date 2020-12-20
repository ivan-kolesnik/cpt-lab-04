package com.lab_04.command.Program;

import com.lab_04.device.Device;
import com.lab_04.device.Fridge;

import java.util.List;
import java.util.Scanner;

public class CreateFridgeCommand extends CreateDeviceCommand {
    public CreateFridgeCommand(Scanner in, List<Device> deviceList) {
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

        Fridge fridge = null;

        try {
            fridge = new Fridge.Builder()
                    .setManufacturer(manufacturer)
                    .setModel(model)
                    .setMinTemperature(minTemperature)
                    .setMaxTemperature(maxTemperature)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return fridge;
    }
}
