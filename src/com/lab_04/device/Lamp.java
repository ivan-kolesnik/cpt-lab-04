package com.lab_04.device;

import com.lab_04.operation.DeviceOperation;

import java.util.*;

public class Lamp extends Device {
    private final int _minBrightness;
    private final int _maxBrightness;
    private final int[] _colorTemperatureArr;

    private int _brightness;
    private int _colorTemperatureIndex;

    public static class Builder extends Device.Builder<Builder> {
        private int _minBrightness;
        private int _maxBrightness;
        private int[] _colorTemperatureArr;

        public Builder setMinBrightness(int v) {
            _minBrightness = v;
            return this;
        }

        public Builder setMaxBrightness(int v) {
            _maxBrightness = v;
            return this;
        }

        public Builder setColorTemperatureArr(int[] v) {
            _colorTemperatureArr = v;
            return this;
        }

        @Override
        public Lamp build() {
            return new Lamp(this);
        }
    }

    public Lamp(Builder builder) {
        super(builder);

        if (builder._minBrightness < 0) {
            throw new IllegalArgumentException("Min brightness cannot be less than 0");
        }

        if (builder._maxBrightness < builder._minBrightness) {
            throw new IllegalArgumentException("Max brightness cannot be less than min brightness");
        }

        if (builder._colorTemperatureArr.length == 0) {
            throw new IllegalArgumentException("Color temperature list cannot be empty");
        }

        for (int t: builder._colorTemperatureArr) {
            if (t < 0) {
                throw new IllegalArgumentException("Color temperature cannot be less than 0");
            }
        }

        _minBrightness = builder._minBrightness;
        _maxBrightness = builder._maxBrightness;

        _colorTemperatureArr = builder._colorTemperatureArr;
        Arrays.sort(_colorTemperatureArr);

        _colorTemperatureIndex = 0;

        _brightness = (_minBrightness + _maxBrightness) / 2;
    }

    public int getMinBrightness() {
        return _minBrightness;
    }

    public int getMaxBrightness() {
        return _maxBrightness;
    }

    public int[] getColorTemperatureArr() {
        return _colorTemperatureArr;
    }

    public int getColorTemperature() {
        return _colorTemperatureArr[_colorTemperatureIndex];
    }

    public void nextColorTemperature() {
        _colorTemperatureIndex += 1;
        if (_colorTemperatureIndex >= _colorTemperatureArr.length) {
            _colorTemperatureIndex = 0;
        }
    }

    public void prevColorTemperature() {
        _colorTemperatureIndex -= 1;
        if (_colorTemperatureIndex < 0) {
            _colorTemperatureIndex = _colorTemperatureArr.length;
        }
    }

    public int getBrightness() {
        return _brightness;
    }

    public void incrementBrightness() {
        if (_brightness < _maxBrightness) {
            _brightness += 1;
        }
    }

    public void decrementBrightness() {
        if (_brightness > _minBrightness) {
            _brightness -= 1;
        }
    }

    @Override
    public void accept(DeviceOperation visitor) {
        visitor.visitLamp(this);
    }
}
