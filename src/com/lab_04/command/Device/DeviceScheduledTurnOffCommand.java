package com.lab_04.command.Device;

import com.lab_04.command.DeviceCommand;
import com.lab_04.device.Device;
import com.lab_04.device.task.Device.DeviceTurnOffTask;

import java.util.Date;
import java.util.List;

public class DeviceScheduledTurnOffCommand extends DeviceCommand {
    private final Date _date;
    private DeviceTurnOffTask _task;

    public DeviceScheduledTurnOffCommand(int id, List<Device> deviceList, Date date) {
        super(id, deviceList);

        _date = date;
    }

    @Override
    public void execute() {
        Device device = getDeviceById(_id, _deviceList);
        if (device == null) {
            return;
        }

        _task = device.scheduleTurnOff(_date);
    }

    @Override
    public void undo() {
        if (_task != null) {
            _task.cancel();
        }
    }
}
