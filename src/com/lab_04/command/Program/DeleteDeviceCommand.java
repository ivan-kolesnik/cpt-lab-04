package com.lab_04.command.Program;

import com.lab_04.command.DeviceCommand;
import com.lab_04.device.Device;

import java.util.List;

public class DeleteDeviceCommand extends DeviceCommand {
    private Device _device;
    private int _index;

    public DeleteDeviceCommand(int id, List<Device> deviceList) {
        super(id, deviceList);
    }

    @Override
    public void execute() {
        _device = getDeviceById(_id, _deviceList);
        if (_device != null) {
            _index = _deviceList.indexOf(_device);
            _deviceList.remove(_device);
        }
    }

    @Override
    public void undo() {
        if (_device != null) {
            _deviceList.add(_index, _device);
        }
    }
}
