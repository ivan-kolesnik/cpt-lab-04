package com.lab_04.command.Lamp;

import com.lab_04.command.DeviceCommand;
import com.lab_04.device.Device;
import com.lab_04.device.Lamp;

import java.util.List;

public class LampIncrementBrightnessCommand extends DeviceCommand {
    public LampIncrementBrightnessCommand(int id, List<Device> deviceList) {
        super(id, deviceList);
    }

    @Override
    public void execute() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        Lamp lamp = (Lamp) device;
        lamp.incrementBrightness();
    }

    @Override
    public void undo() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        Lamp lamp = (Lamp) device;
        lamp.decrementBrightness();
    }
}
