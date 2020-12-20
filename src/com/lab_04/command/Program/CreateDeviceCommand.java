package com.lab_04.command.Program;

import com.lab_04.command.Command;
import com.lab_04.device.Device;

import java.util.List;
import java.util.Scanner;

public abstract class CreateDeviceCommand implements Command {
    protected final Scanner _in;
    protected final List<Device> _deviceList;
    private Device _device;

    public CreateDeviceCommand(Scanner in, List<Device> deviceList) {
        _in = in;
        _deviceList = deviceList;
    }

    @Override
    public void execute() {
        _device = readDevice();

        if (_device != null) {
            _deviceList.add(_device);
        }
    }

    @Override
    public void undo() {
        if (_device != null) {
            _deviceList.remove(_device);
        }
    }

    protected abstract Device readDevice();
}
