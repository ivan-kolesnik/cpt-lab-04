package com.lab_04.ui;

public enum TVMenuOp {
    UM_TV_TURN_ON         (0),
    UM_TV_TURN_OFF        (1),
    UM_TV_NEXT_CHANNEL    (2),
    UM_TV_PREV_CHANNEL    (3),
    UM_TV_SCHED_TURN_ON   (4),
    UM_TV_SCHED_TURN_OFF  (5),

    UM_TV_NOT_FOUND       (6);

    private final int _cmd;

    TVMenuOp(int cmd) {
        _cmd = cmd;
    }

    public int getCmd() {
        return _cmd;
    }

    public static TVMenuOp fromCmd(int cmd) {
        for (TVMenuOp action : values()) {
            if (action._cmd == cmd) {
                return action;
            }
        }

        return UM_TV_NOT_FOUND;
    }
}
