package com.lab_04.command.Program;

import com.lab_04.device.Device;
import com.lab_04.device.Lamp;

import java.util.List;
import java.util.Scanner;

public class CreateLampCommand extends CreateDeviceCommand {
    public CreateLampCommand(Scanner in, List<Device> deviceList) {
        super(in, deviceList);
    }

    @Override
    protected Device readDevice() {
        System.out.print("\nEnter manufacturer        : ");
        String manufacturer = _in.nextLine();

        System.out.print("Enter model               : ");
        String model = _in.nextLine();

        System.out.print("Enter min brightness      : ");
        int minBrightness = _in.nextInt();

        System.out.print("Enter max brightness      : ");
        int maxBrightness = _in.nextInt();

        System.out.print("Enter number of supported\n" +
                         "color temperatures        : ");
        int length = _in.nextInt();

        System.out.print("Enter color temperatures\n" +
                         "(separate with space)     : ");

        int[] arr = new int[length];
        for (int i = 0; i < length; i += 1) {
            arr[i] = _in.nextInt();
        }

        Lamp lamp = null;

        try {
            lamp = new Lamp.Builder()
                    .setManufacturer(manufacturer)
                    .setModel(model)
                    .setMinBrightness(minBrightness)
                    .setMaxBrightness(maxBrightness)
                    .setColorTemperatureArr(arr)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return lamp;
    }
}
