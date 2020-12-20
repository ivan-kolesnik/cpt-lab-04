package com.lab_04.command.Fridge;

import com.lab_04.command.DeviceCommand;
import com.lab_04.device.Device;
import com.lab_04.device.Fridge;

import java.util.List;

public class FridgeDecrementTemperatureCommand extends DeviceCommand {
    public FridgeDecrementTemperatureCommand(int id, List<Device> deviceList) {
        super(id, deviceList);
    }

    @Override
    public void execute() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        Fridge fridge = (Fridge) device;
        fridge.decrementTemperature();
    }

    @Override
    public void undo() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        Fridge fridge = (Fridge) device;
        fridge.incrementTemperature();
    }
}
