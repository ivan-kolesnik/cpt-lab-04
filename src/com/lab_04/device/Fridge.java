package com.lab_04.device;

import com.lab_04.operation.DeviceOperation;

public class Fridge extends Device {
    private final int _minTemperature;
    private final int _maxTemperature;

    private int _temperature;

    public static class Builder extends Device.Builder<Builder> {
        private int _minTemperature;
        private int _maxTemperature;

        public Builder setMinTemperature(int v) {
            _minTemperature = v;
            return this;
        }

        public Builder setMaxTemperature(int v) {
            _maxTemperature = v;
            return this;
        }

        @Override
        public Fridge build() {
            return new Fridge(this);
        }
    }

    public Fridge(Builder builder) {
        super(builder);

        if (builder._maxTemperature < builder._minTemperature) {
            throw new IllegalArgumentException("Max temperature cannot be less than min temperature");
        }

        _minTemperature = builder._minTemperature;
        _maxTemperature = builder._maxTemperature;

        _temperature = (_minTemperature + _maxTemperature) / 2;
    }

    public int getMinTemperature() {
        return _minTemperature;
    }

    public int getMaxTemperature() {
        return _maxTemperature;
    }

    public int getTemperature() {
        return _temperature;
    }

    public void incrementTemperature() {
        if (_temperature < _maxTemperature) {
            _temperature += 1;
        }
    }

    public void decrementTemperature() {
        if (_temperature > _minTemperature) {
            _temperature -= 1;
        }
    }

    @Override
    public void accept(DeviceOperation visitor) {
        visitor.visitFridge(this);
    }
}
