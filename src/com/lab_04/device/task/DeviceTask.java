package com.lab_04.device.task;

import com.lab_04.device.Device;

import java.util.TimerTask;

public abstract class DeviceTask extends TimerTask {
    protected final Device _device;

    public DeviceTask(Device device) {
        _device = device;
    }
}
