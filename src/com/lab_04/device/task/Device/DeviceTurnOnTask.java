package com.lab_04.device.task.Device;

import com.lab_04.device.Device;
import com.lab_04.device.task.DeviceTask;

public class DeviceTurnOnTask extends DeviceTask {
    public DeviceTurnOnTask(Device device) {
        super(device);
    }

    public void run() {
        _device.turnOn();
    }
}
