package com.lab_04.command.AC;

import com.lab_04.command.DeviceCommand;
import com.lab_04.device.Device;
import com.lab_04.device.AC;

import java.util.List;

public class ACIncrementTemperatureCommand extends DeviceCommand {
    public ACIncrementTemperatureCommand(int id, List<Device> deviceList) {
        super(id, deviceList);
    }

    @Override
    public void execute() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        AC ac = (AC) device;
        ac.incrementTemperature();
    }

    @Override
    public void undo() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        AC ac = (AC) device;
        ac.decrementTemperature();
    }
}
