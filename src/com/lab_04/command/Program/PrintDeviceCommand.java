package com.lab_04.command.Program;

import com.lab_04.device.Device;
import com.lab_04.command.DeviceCommand;
import com.lab_04.operation.PrintDeviceOperation;

import java.util.List;

public class PrintDeviceCommand extends DeviceCommand {
    private final PrintDeviceOperation _operation;

    public PrintDeviceCommand(int id, List<Device> deviceList) {
        super(id, deviceList);
        _operation = new PrintDeviceOperation();
    }

    @Override
    public void execute() {
        Device device = getDeviceById(_id, _deviceList);

        if (device != null) {
            device.accept(_operation);
            return;
        }

        System.out.println("\nDevice not found.");
    }

    @Override
    public void undo() {}
}
