package org.example;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

/**
 * This class shows how to use the event queue system in JInput. It will show
 * how to get the controllers, how to get the event queue for a controller, and
 * how to read and process events from the queue.
 *
 * @author Endolf
 */
public class ReadAllEvents implements Runnable {
    private GamePad gamePad;

    public ReadAllEvents(GamePad gamePad) {
        this.gamePad = gamePad;
    }

    @Override
    public void run() {
        while (true) {
            /* Get the available controllers */
            Controller[] controllers = ControllerEnvironment
                    .getDefaultEnvironment().getControllers();
            if (controllers.length == 0) {
                System.out.println("Found no controllers.");
                System.exit(0);
            }

            for (int i = 0; i < controllers.length; i++) {
                /* Remember to poll each one */
                controllers[i].poll();

                /* Get the controllers event queue */
                EventQueue queue = controllers[i].getEventQueue();

                /* Create an event object for the underlying plugin to populate */
                Event event = new Event();

                /* For each object in the queue */
                while (queue.getNextEvent(event)) {
                    StringBuffer buffer = new StringBuffer(controllers[i]
                            .getName());
                    buffer.append(" at ");
                    buffer.append(event.getNanos()).append(", ");
                    Component comp = event.getComponent();
                    buffer.append(comp.getName()).append(" changed to ");
                    float value = event.getValue();

                    if (comp.getName().equals("Hat Switch")) {
                        //buffer.append(value);
                        //buffer.append(" (").append(interpretHatSwitch(value)).append(")");

                        if (value == Component.POV.OFF){
                            gamePad.dpad_right = false;
                            gamePad.dpad_up = false;
                            gamePad.dpad_down = false;
                            gamePad.dpad_left = false;
                            System.out.println("dpad unpressed");
                        } else if (value == Component.POV.UP) {
                            gamePad.dpad_up = true;
                            System.out.println("dpad up pressed");
                        } else if (value == Component.POV.UP_RIGHT) {

                        } else if (value == Component.POV.RIGHT) {
                            gamePad.dpad_right = true;
                            System.out.println("dpad right pressed");
                        } else if (value == Component.POV.DOWN_RIGHT) {

                        } else if (value == Component.POV.DOWN) {
                            gamePad.dpad_down = true;
                            System.out.println("dpad down pressed");
                        } else if (value == Component.POV.DOWN_LEFT) {

                        } else if (value == Component.POV.LEFT) {
                            gamePad.dpad_left = true;
                            System.out.println("dpad left pressed");
                        }


                    } else if (comp.isAnalog()) {

//                        System.out.println(comp.getName());
//                        System.out.println(buffer.toString());

                        if (value > 0.0001){
                            System.out.println("Left trigger: " + value);
                        } else if (value < 0.0001){
                            System.out.println("Right trigger: " + value);
                        }
//
                    } else {
//                        //buffer.append(value == 1.0f ? "On" : "Off");
                        if (comp.getName().equals("Button 1") && value == 1.0f){
                            System.out.println("B pressed");
                            gamePad.b = true;
                        } else if (comp.getName().equals("Button 1")) {
                            System.out.println("B unpressed");
                            gamePad.b = false;
                        } else if (comp.getName().equals("Button 2") && value == 1.0f) {
                            System.out.println("x pressed");
                            gamePad.x = true;
                        } else if (comp.getName().equals("Button 2")) {
                            System.out.println("x unpressed");
                            gamePad.x = false;
                        } else if (comp.getName().equals("Button 0") && value == 1.0f) {
                            System.out.println("A pressed");
                            gamePad.a = true;
                        } else if (comp.getName().equals("Button 0")) {
                            System.out.println("A unpressed");
                            gamePad.a = false;
                        } else if (comp.getName().equals("Button 3") && value == 1.0f) {
                            System.out.println("y pressed");
                            gamePad.y = true;
                        } else if (comp.getName().equals("Button 3")) {
                            System.out.println("y unpressed");
                            gamePad.y = false;
                        } else if (comp.getName().equals("Button 4") && value == 1.0f) {
                            System.out.println("left bumper pressed");
                            gamePad.left_bumper = true;
                        } else if (comp.getName().equals("Button 4")) {
                            System.out.println("left bumper unpressed");
                            gamePad.left_bumper = false;
                        } else if (comp.getName().equals("Button 5") && value == 1.0f) {
                            System.out.println("right bumper pressed");
                            gamePad.right_bumper = true;
                        } else if (comp.getName().equals("Button 5")) {
                            System.out.println("right bumper unpressed");
                            gamePad.right_bumper = false;
                        } else if (comp.getName().equals("Button 6") && value == 1.0f) {
                            System.out.println("back button pressed");
                            gamePad.back = true;
                        } else if (comp.getName().equals("Button 6")) {
                            System.out.println("back button unpressed");
                            gamePad.back = false;
                        } else if (comp.getName().equals("Button 7") && value == 1.0f) {
                            System.out.println("start pressed");
                            gamePad.start = true;
                        } else if (comp.getName().equals("Button 7")) {
                            System.out.println("start unpressed");
                            gamePad.start = false;
                        }
                    }
                    //System.out.println(buffer.toString());
                }
            }

        }
    }
//    private String interpretHatSwitch(float value) {
//        if (value == Component.POV.OFF) return "Center";
//        if (value == Component.POV.UP) return "Up";
//        if (value == Component.POV.UP_RIGHT) return "Up-Right";
//        if (value == Component.POV.RIGHT) return "Right";
//        if (value == Component.POV.DOWN_RIGHT) return "Down-Right";
//        if (value == Component.POV.DOWN) return "Down";
//        if (value == Component.POV.DOWN_LEFT) return "Down-Left";
//        if (value == Component.POV.LEFT) return "Left";
//        if (value == Component.POV.UP_LEFT) return "Up-Left";
//        return "Unknown";
//    }
}

