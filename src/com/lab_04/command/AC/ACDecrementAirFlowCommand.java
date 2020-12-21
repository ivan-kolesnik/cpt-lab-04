package com.lab_04.command.AC;

import com.lab_04.command.DeviceCommand;
import com.lab_04.device.AC;
import com.lab_04.device.Device;

import java.util.List;

public class ACDecrementAirFlowCommand extends DeviceCommand {
    public ACDecrementAirFlowCommand(int id, List<Device> deviceList) {
        super(id, deviceList);
    }

    @Override
    public void execute() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        AC ac = (AC) device;
        ac.decrementAirFlow();
    }

    @Override
    public void undo() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        AC ac = (AC) device;
        ac.incrementAirFlow();
    }
}
