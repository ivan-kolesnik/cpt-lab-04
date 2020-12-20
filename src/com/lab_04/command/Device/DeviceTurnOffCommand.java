package com.lab_04.command.Device;

import com.lab_04.command.DeviceCommand;
import com.lab_04.device.Device;

import java.util.List;

public class DeviceTurnOffCommand extends DeviceCommand {
    private boolean prevState;

    public DeviceTurnOffCommand(int id, List<Device> deviceList) {
        super(id, deviceList);
    }

    @Override
    public void execute() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        prevState = device.isTurnOn();
        device.turnOff();
    }

    @Override
    public void undo() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        if (prevState) {
            device.turnOn();
        }
    }
}
