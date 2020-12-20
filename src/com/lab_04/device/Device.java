package com.lab_04.device;

import com.lab_04.device.task.Device.DeviceTurnOffTask;
import com.lab_04.device.task.Device.DeviceTurnOnTask;
import com.lab_04.operation.DeviceOperation;

import java.util.Date;
import java.util.Timer;

public abstract class Device {
    private static int _idCounter = 1;

    private final int _id;
    private final String _manufacturer;
    private final String _model;

    private boolean _isTurnOn;

    private Timer _timer;

    public static abstract class Builder<T extends Builder<T>> {
        private String _manufacturer;
        private String _model;

        public T setManufacturer(String v) {
            _manufacturer = v;
            return (T) this;
        }

        public T setModel(String v) {
            _model = v;
            return (T) this;
        }

        public abstract Device build();
    }

    public Device(Builder<?> builder) throws IllegalArgumentException {
        if (builder._manufacturer == null
                || builder._manufacturer.trim().isEmpty()) {
            throw new IllegalArgumentException("Manufacturer cannot be empty");
        }

        if (builder._model == null
                || builder._model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be empty");
        }

        _id = _idCounter;
        _idCounter += 1;

        _manufacturer = builder._manufacturer;
        _model = builder._model;

        _isTurnOn = false;

        _timer = new Timer();
    }

    public int getId() {
        return _id;
    }

    public String getManufacturer() {
        return _manufacturer;
    }

    public String getModel() {
        return _model;
    }

    public boolean isTurnOn() {
        return _isTurnOn;
    }

    public void turnOn() {
        _isTurnOn = true;
    }

    public void turnOff() {
        _isTurnOn = false;
    }

    public DeviceTurnOnTask scheduleTurnOn(Date date) {
        DeviceTurnOnTask task = new DeviceTurnOnTask(this);
        _timer.schedule(task, date);

        return task;
    }

    public DeviceTurnOffTask scheduleTurnOff(Date date) {
        DeviceTurnOffTask task = new DeviceTurnOffTask(this);
        _timer.schedule(task, date);

        return task;
    }

    public abstract void accept(DeviceOperation visitor);
}
