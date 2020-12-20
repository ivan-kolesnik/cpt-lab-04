package com.lab_04.ui;

public enum MainMenuOp {
    MM_CREATE    (0),
    MM_SHOW      (1),
    MM_UPDATE    (2),
    MM_DELETE    (3),
    MM_UNDO      (4),

    MM_NOT_FOUND (5);

    private final int _cmd;

    MainMenuOp(int cmd) {
        _cmd = cmd;
    }

    public int getCmd() {
        return _cmd;
    }

    public static MainMenuOp fromCmd(int cmd) {
        for (MainMenuOp action : values()) {
            if (action._cmd == cmd) {
                return action;
            }
        }

        return MM_NOT_FOUND;
    }
}
