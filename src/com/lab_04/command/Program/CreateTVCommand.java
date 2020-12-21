package com.lab_04.command.Program;

import com.lab_04.device.Device;
import com.lab_04.device.TV;

import java.util.List;
import java.util.Scanner;

public class CreateTVCommand extends CreateDeviceCommand {
    public CreateTVCommand(Scanner in, List<Device> deviceList) {
        super(in, deviceList);
    }

    @Override
    protected Device readDevice() {
        System.out.print("\nEnter manufacturer        : ");
        String manufacturer = _in.nextLine();

        System.out.print("Enter model               : ");
        String model = _in.nextLine();

        System.out.print("Enter number of supported\n" +
                         "channels                  : ");
        int length = _in.nextInt();
        _in.nextLine();

        System.out.print("Enter channels (separate\n" +
                         "with Enter):\n");

        String[] arr = new String[length];
        for (int i = 0; i < length; i += 1) {
            arr[i] = _in.nextLine();
        }

        TV tv = null;

        try {
            tv = new TV.Builder()
                    .setManufacturer(manufacturer)
                    .setModel(model)
                    .setChannelArr(arr)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return tv;
    }
}
