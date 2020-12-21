package com.lab_04.command.Stove;

import com.lab_04.command.DeviceCommand;
import com.lab_04.device.Device;
import com.lab_04.device.Stove;

import java.util.List;

public class StoveTurnOnBurnerCommand extends DeviceCommand {
    private final int _burnerIndex;
    private boolean _prevState;

    public StoveTurnOnBurnerCommand(int id, List<Device> deviceList, int burnerIndex) {
        super(id, deviceList);

        _burnerIndex = burnerIndex;
    }

    @Override
    public void execute() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        Stove stove = (Stove) device;

        _prevState = stove.isBurnerTurnOn(_burnerIndex);
        stove.turnOnBurner(_burnerIndex);
    }

    @Override
    public void undo() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        if (!_prevState) {
            Stove stove = (Stove) device;
            stove.turnOffBurner(_burnerIndex);
        }
    }
}
