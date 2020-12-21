package com.lab_04.device;

import com.lab_04.operation.DeviceOperation;

public class AC extends Device {
    private final int _minTemperature;
    private final int _maxTemperature;
    private final int _minAirFlow;
    private final int _maxAirFlow;

    private int _temperature;
    private int _airFlow;

    public static class Builder extends Device.Builder<Builder> {
        private int _minTemperature;
        private int _maxTemperature;
        private int _minAirFlow;
        private int _maxAirFlow;

        public Builder setMinTemperature(int v) {
            _minTemperature = v;
            return this;
        }

        public Builder setMaxTemperature(int v) {
            _maxTemperature = v;
            return this;
        }

        public Builder setMinAirFlow(int v) {
            _minAirFlow = v;
            return this;
        }

        public Builder setMaxAirFlow(int v) {
            _maxAirFlow = v;
            return this;
        }

        @Override
        public AC build() {
            return new AC(this);
        }
    }

    public AC(Builder builder) {
        super(builder);

        if (builder._maxTemperature < builder._minTemperature) {
            throw new IllegalArgumentException("Max temperature cannot be less than min temperature");
        }

        if (builder._minAirFlow < 0) {
            throw new IllegalArgumentException("Min air flow cannot be less than 0");
        }

        if (builder._maxAirFlow < builder._minAirFlow) {
            throw new IllegalArgumentException("Max air flow cannot be less than min air flow");
        }

        _minTemperature = builder._minTemperature;
        _maxTemperature = builder._maxTemperature;

        _minAirFlow = builder._minAirFlow;
        _maxAirFlow = builder._maxAirFlow;

        _temperature = (_minTemperature + _maxTemperature) / 2;
        _airFlow = (_minAirFlow + _maxAirFlow) / 2;
    }

    public int getMinTemperature() {
        return _minTemperature;
    }

    public int getMaxTemperature() {
        return _maxTemperature;
    }

    public int getMinAirFlow() {
        return _minAirFlow;
    }

    public int getMaxAirFlow() {
        return _maxAirFlow;
    }

    public int getTemperature() {
        return _temperature;
    }

    public int getAirFlow() {
        return _airFlow;
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

    public void incrementAirFlow() {
        if (_airFlow < _maxAirFlow) {
            _airFlow += 1;
        }
    }

    public void decrementAirFlow() {
        if (_airFlow > _minAirFlow) {
            _airFlow -= 1;
        }
    }

    @Override
    public void accept(DeviceOperation visitor) {
        visitor.visitAC(this);
    }
}
