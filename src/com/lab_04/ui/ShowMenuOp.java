package com.lab_04.ui;

public enum ShowMenuOp {
    SM_BY_ID(0),
    SM_ALL(1),
    SM_NOT_FOUND(2);

    private final int _cmd;

    ShowMenuOp(int cmd) {
        _cmd = cmd;
    }

    public int getCmd() {
        return _cmd;
    }

    public static ShowMenuOp fromCmd(int cmd) {
        for (ShowMenuOp action : values()) {
            if (action._cmd == cmd) {
                return action;
            }
        }

        return SM_NOT_FOUND;
    }
}
