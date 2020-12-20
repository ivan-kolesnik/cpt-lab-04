package com.lab_04.command;

import java.util.List;
import com.lab_04.device.Device;

public abstract class DeviceCommand implements Command {
    protected final List<Device> _deviceList;
    protected final int _id;

    public DeviceCommand(int id, List<Device> deviceList) {
        _id = id;
        _deviceList = deviceList;
    }

    protected Device getDeviceById(int id, List<Device> deviceList) {
        Device device = null;

        for (Device d: deviceList) {
            if (d.getId() == id) {
                device = d;
            }
        }

        return device;
    }
}
