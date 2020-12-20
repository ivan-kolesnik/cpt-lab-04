package com.lab_04.command.Program;

import com.lab_04.command.Command;
import com.lab_04.device.Device;
import com.lab_04.operation.PrintDeviceOperation;

import java.util.List;

public class PrintDeviceListCommand implements Command {
    private final List<Device> _deviceList;
    private final PrintDeviceOperation _operation;

    public PrintDeviceListCommand(List<Device> deviceList) {
        _deviceList = deviceList;
        _operation = new PrintDeviceOperation();
    }

    @Override
    public void execute() {
        if (_deviceList.isEmpty()) {
            System.out.println("\nDevices not found.");
            return;
        }

        for (Device device: _deviceList) {
            device.accept(_operation);
        }
    }

    @Override
    public void undo() {}
}
