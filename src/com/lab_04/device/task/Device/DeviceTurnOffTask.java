package com.lab_04.device.task.Device;

import com.lab_04.device.Device;
import com.lab_04.device.task.DeviceTask;

public class DeviceTurnOffTask extends DeviceTask {
    public DeviceTurnOffTask(Device device) {
        super(device);
    }

    public void run() {
        _device.turnOff();
    }
}
