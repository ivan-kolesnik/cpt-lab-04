package com.lab_04.device;

import com.lab_04.operation.DeviceOperation;

import java.util.Arrays;

public class TV extends Device {
    private final String[] _channelArr;
    private int _channelIndex;

    public static class Builder extends Device.Builder<Builder> {
        private String[] _channelArr;

        public Builder setChannelArr(String[] v) {
            _channelArr = v;
            return this;
        }

        @Override
        public TV build() {
            return new TV(this);
        }
    }

    public TV(Builder builder) {
        super(builder);

        if (builder._channelArr.length == 0) {
            throw new IllegalArgumentException("Channel list cannot be empty");
        }

        boolean isUnique = true;

        for (int i = 0; i < builder._channelArr.length - 1; i += 1) {
            for (int j = i + 1; j < builder._channelArr.length; j += 1) {
                String iStr = builder._channelArr[i];
                String jStr = builder._channelArr[j];

                if (iStr.equals(jStr)) {
                    isUnique = false;
                    break;
                }
            }
        }

        if (!isUnique) {
            throw new IllegalArgumentException("Channel must be unique");
        }

        _channelArr = builder._channelArr;
        _channelIndex = 0;
    }

    public String[] getChannelArr() {
        return _channelArr;
    }

    public String getCurrentChannel() {
        return _channelArr[_channelIndex];
    }

    public void nextChannel() {
        _channelIndex += 1;
        if (_channelIndex >= _channelArr.length) {
            _channelIndex = 0;
        }
    }

    public void prevChannel() {
        _channelIndex -= 1;
        if (_channelIndex < 0) {
            _channelIndex = _channelArr.length;
        }
    }

    @Override
    public void accept(DeviceOperation visitor) {
        visitor.visitTV(this);
    }
}
