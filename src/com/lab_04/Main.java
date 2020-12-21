package com.lab_04;

import com.lab_04.ui.*;
import com.lab_04.device.*;
import com.lab_04.command.Command;
import com.lab_04.command.Program.*;
import com.lab_04.command.Device.*;
import com.lab_04.command.Lamp.*;
import com.lab_04.command.Fridge.*;
import com.lab_04.command.Stove.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static Scanner _in;

    private static List<Device> _deviceList;
    private static Remote _remote;

    private static SimpleDateFormat _dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    public static void main(String[] args) {
        Lamp lamp1 = new Lamp.Builder()
                .setManufacturer("Manufacturer 1")
                .setModel("Model 1")
                .setMinBrightness(10)
                .setMaxBrightness(200)
                .setColorTemperatureArr(new int[] { 2500, 3200, 3800, 4100, 4500 })
                .build();

        Lamp lamp2 = new Lamp.Builder()
                .setManufacturer("Manufacturer 2")
                .setModel("Model 2")
                .setMinBrightness(50)
                .setMaxBrightness(300)
                .setColorTemperatureArr(new int[] { 3200, 4100, 5000 })
                .build();

        _deviceList = new LinkedList<Device>();
        _deviceList.add(lamp1);
        _deviceList.add(lamp2);

        _in = new Scanner(System.in);
        _remote = new Remote();

        while (true) {
            try {
                Command command = mainMenu();
                if (command != null) {
                    _remote.submit(command);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError: incorrect type of provided data.");
                _in.nextLine();
            }
        }
    }

    private static Command mainMenu() {
        String help = getMainMenuHelp();
        System.out.println(help);

        int choice = _in.nextInt();
        _in.nextLine();

        MainMenuOp op = MainMenuOp.fromCmd(choice);

        switch (op) {
            case MM_CREATE:
                return createMenu();
            case MM_SHOW:
                return showMenu();
            case MM_UPDATE:
                return updateMenu();
            case MM_DELETE:
                return deleteMenu();
            case MM_UNDO:
                _remote.undo();
                return null;
            default:
                return null;
        }
    }

    // add items
    private static Command createMenu() {
        SelectDeviceTypeOp op = selectDeviceTypeMenu();

        return switch (op) {
            case SDT_LAMP   -> new CreateLampCommand(_in, _deviceList);
            case SDT_FRIDGE -> new CreateFridgeCommand(_in, _deviceList);
            case SDT_STOVE -> new CreateStoveCommand(_in, _deviceList);
            default -> null;
        };
    }

    private static Command showMenu() {
        String help = getShowMenuHelp();
        System.out.println(help);

        int choice = _in.nextInt();
        _in.nextLine();

        ShowMenuOp op = ShowMenuOp.fromCmd(choice);

        switch (op) {
            case SM_BY_ID:
                int id = selectDeviceIdMenu();
                return new PrintDeviceCommand(id, _deviceList);
            case SM_ALL:
                return new PrintDeviceListCommand(_deviceList);
            default:
                return null;
        }
    }

    // add items
    private static Command updateMenu() {
        SelectDeviceTypeOp op = selectDeviceTypeMenu();
        int id = selectDeviceIdMenu();

        return switch (op) {
            case SDT_LAMP   -> updateLampMenu(id);
            case SDT_FRIDGE -> updateFridgeMenu(id);
            case SDT_STOVE -> updateStoveMenu(id);
            default -> null;
        };
    }

    private static Command deleteMenu() {
        int id = selectDeviceIdMenu();
        return new DeleteDeviceCommand(id, _deviceList);
    }

    private static Command updateLampMenu(int id) {
        String help = getLampMenuHelp();
        System.out.println(help);

        int choice = _in.nextInt();
        _in.nextLine();

        LampMenuOp op = LampMenuOp.fromCmd(choice);
        Date date;

        switch (op) {
            case UM_LAMP_TURN_ON:
                return new DeviceTurnOnCommand(id, _deviceList);
            case UM_LAMP_TURN_OFF:
                return new DeviceTurnOffCommand(id, _deviceList);
            case UM_LAMP_NEXT_TEMP_COLOR:
                return new LampNextColorTemperatureCommand(id, _deviceList);
            case UM_LAMP_PREV_TEMP_COLOR:
                return new LampPrevColorTemperatureCommand(id, _deviceList);
            case UM_LAMP_INC_BRIGHTNESS:
                return new LampIncrementBrightnessCommand(id, _deviceList);
            case UM_LAMP_DEC_BRIGHTNESS:
                return new LampDecrementBrightnessCommand(id, _deviceList);
            case UM_LAMP_SCHED_TURN_ON:
                date = selectDateMenu();
                if (date == null) {
                    return null;
                }

                return new DeviceScheduledTurnOnCommand(id, _deviceList, date);
            case UM_LAMP_SCHED_TURN_OFF:
                date = selectDateMenu();
                if (date == null) {
                    return null;
                }

                return new DeviceScheduledTurnOffCommand(id, _deviceList, date);
            default:
                return null;
        }
    }

    private static Command updateFridgeMenu(int id) {
        String help = getFridgeMenuHelp();
        System.out.println(help);

        int choice = _in.nextInt();
        _in.nextLine();

        FridgeMenuOp op = FridgeMenuOp.fromCmd(choice);

        return switch (op) {
            case UM_FRIDGE_TURN_ON          -> new DeviceTurnOnCommand(id, _deviceList);
            case UM_FRIDGE_TURN_OFF         -> new DeviceTurnOffCommand(id, _deviceList);
            case UM_FRIDGE_INC_TEMPERATURE  -> new FridgeIncrementTemperatureCommand(id, _deviceList);
            case UM_FRIDGE_DEC_TEMPERATURE  -> new FridgeDecrementTemperatureCommand(id, _deviceList);
            default -> null;
        };
    }

    private static Command updateStoveMenu(int id) {
        String help = getStoveMenuHelp();
        System.out.println(help);

        int choice = _in.nextInt();
        _in.nextLine();

        StoveMenuOp op = StoveMenuOp.fromCmd(choice);
        int burnerId;

        switch (op) {
            case UM_STOVE_TURN_ON:
                return new DeviceTurnOnCommand(id, _deviceList);
            case UM_STOVE_TURN_OFF:
                return new DeviceTurnOffCommand(id, _deviceList);
            case UM_STOVE_TURN_ON_BURNER:
                burnerId = selectStoveBurnerIndexMenu();
                return new StoveTurnOnBurnerCommand(id, _deviceList, burnerId);
            case UM_STOVE_TURN_OFF_BURNER:
                burnerId = selectStoveBurnerIndexMenu();
                return new StoveTurnOffBurnerCommand(id, _deviceList, burnerId);
            default:
                return null;
        }
    }

    private static SelectDeviceTypeOp selectDeviceTypeMenu() {
        String help = getSelectDeviceTypeMenuHelp();
        System.out.println(help);

        int choice = _in.nextInt();
        _in.nextLine();

        return SelectDeviceTypeOp.fromCmd(choice);
    }

    private static int selectDeviceIdMenu() {
        String help = getSelectDeviceIdMenuHelp();
        System.out.println(help);

        int id = _in.nextInt();
        _in.nextLine();

        return id;
    }

    private static Date selectDateMenu() {
        String help = getSelectDateMenuHelp();
        System.out.println(help);

        String dateStr = _in.nextLine();
        Date date = null;

        try {
            date = _dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("\nFormat incorrect.");
        }

        return date;
    }

    private static int selectStoveBurnerIndexMenu() {
        String help = getSelectStoveBurnerIndexMenuHelp();
        System.out.println(help);

        int index = _in.nextInt();
        _in.nextLine();

        return index;
    }

    private static String getMainMenuHelp() {
        return  "\nWelcome to SmartHome.\n" +
                "Select operation:\n" +
                MainMenuOp.MM_CREATE.getCmd() + " - create" + "\n" +
                MainMenuOp.MM_SHOW.getCmd()   + " - show"   + "\n" +
                MainMenuOp.MM_UPDATE.getCmd() + " - update" + "\n" +
                MainMenuOp.MM_DELETE.getCmd() + " - delete" + "\n" +
                MainMenuOp.MM_UNDO.getCmd()   + " - undo operation";
    }

    private static String getShowMenuHelp() {
        return  "\nWhat do you want to see?\n" +
                ShowMenuOp.SM_BY_ID.getCmd() + " - specific device" + "\n" +
                ShowMenuOp.SM_ALL.getCmd()   + " - all devices";
    }

    private static String getSelectDeviceTypeMenuHelp() {
        return  "\nSelect device type:\n" +
                SelectDeviceTypeOp.SDT_LAMP.getCmd()    + " - lamp"     + "\n" +
                SelectDeviceTypeOp.SDT_FRIDGE.getCmd()  + " - fridge"   + "\n" +
                SelectDeviceTypeOp.SDT_STOVE.getCmd()   + " - stove";
    }

    private static String getSelectDeviceIdMenuHelp() {
        return  "\nEnter device id:";
    }

    private static String getSelectDateMenuHelp() {
        return  "\nEnter date in format MM-dd-yyyy HH:mm:ss.\n" +
                "(eg: 12-20-2020 22:59:37):";
    }

    private static String getSelectStoveBurnerIndexMenuHelp() {
        return  "\nEnter burner index:";
    }

    private static String getLampMenuHelp() {
        return  "\nWhat do you want to change?\n" +
                LampMenuOp.UM_LAMP_TURN_ON.getCmd()         + " - turn on"                  + "\n" +
                LampMenuOp.UM_LAMP_TURN_OFF.getCmd()        + " - turn off"                 + "\n" +
                LampMenuOp.UM_LAMP_NEXT_TEMP_COLOR.getCmd() + " - next temperature color"   + "\n" +
                LampMenuOp.UM_LAMP_PREV_TEMP_COLOR.getCmd() + " - prev temperature color"   + "\n" +
                LampMenuOp.UM_LAMP_INC_BRIGHTNESS.getCmd()  + " - increment brightness"     + "\n" +
                LampMenuOp.UM_LAMP_DEC_BRIGHTNESS.getCmd()  + " - decrement brightness"     + "\n" +
                LampMenuOp.UM_LAMP_SCHED_TURN_ON.getCmd()   + " - schedule turn on"         + "\n" +
                LampMenuOp.UM_LAMP_SCHED_TURN_OFF.getCmd()  + " - schedule turn off";
    }

    private static String getFridgeMenuHelp() {
        return  "\nWhat do you want to change?\n" +
                FridgeMenuOp.UM_FRIDGE_TURN_ON.getCmd()         + " - turn on"                  + "\n" +
                FridgeMenuOp.UM_FRIDGE_TURN_OFF.getCmd()        + " - turn off"                 + "\n" +
                FridgeMenuOp.UM_FRIDGE_INC_TEMPERATURE.getCmd() + " - increment temperature"    + "\n" +
                FridgeMenuOp.UM_FRIDGE_DEC_TEMPERATURE.getCmd() + " - decrement temperature";
    }

    private static String getStoveMenuHelp() {
        return  "\nWhat do you want to change?\n" +
                StoveMenuOp.UM_STOVE_TURN_ON.getCmd()           + " - turn on"                  + "\n" +
                StoveMenuOp.UM_STOVE_TURN_OFF.getCmd()          + " - turn off"                 + "\n" +
                StoveMenuOp.UM_STOVE_TURN_ON_BURNER.getCmd()    + " - turn specific burner on"  + "\n" +
                StoveMenuOp.UM_STOVE_TURN_OFF_BURNER.getCmd()   + " - turn specific burner off";
    }
}
