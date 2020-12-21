package com.lab_04.command.TV;

import com.lab_04.command.DeviceCommand;
import com.lab_04.device.Device;
import com.lab_04.device.TV;

import java.util.List;

public class TVNextChannelCommand extends DeviceCommand {
    public TVNextChannelCommand(int id, List<Device> deviceList) {
        super(id, deviceList);
    }

    @Override
    public void execute() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        TV tv = (TV) device;
        tv.nextChannel();
    }

    @Override
    public void undo() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        TV tv = (TV) device;
        tv.prevChannel();
    }
}
