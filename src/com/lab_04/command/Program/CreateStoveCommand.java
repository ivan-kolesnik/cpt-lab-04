package com.lab_04.command.Program;

import com.lab_04.device.Device;
import com.lab_04.device.Fridge;
import com.lab_04.device.Stove;

import java.util.List;
import java.util.Scanner;

public class CreateStoveCommand extends CreateDeviceCommand {
    public CreateStoveCommand(Scanner in, List<Device> deviceList) {
        super(in, deviceList);
    }

    @Override
    protected Device readDevice() {
        System.out.print("\nEnter manufacturer : ");
        String manufacturer = _in.nextLine();

        System.out.print("Enter model        : ");
        String model = _in.nextLine();

        System.out.print("Enter burners count: ");
        int burnersCount = _in.nextInt();

        Stove stove = null;

        try {
            stove = new Stove.Builder()
                    .setManufacturer(manufacturer)
                    .setModel(model)
                    .setBurnersCount(burnersCount)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return stove;
    }
}
