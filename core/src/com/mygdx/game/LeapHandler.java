package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leapmotion.leap.*;
import com.mygdx.game.States.GSM;

public class LeapHandler{

    Controller controller;
    SampleListener listener;
    static Hand hand;
    static HandList hands;
    static GSM gameManager;

    public LeapHandler(GSM gameManager) {
        this.gameManager = gameManager;
        listener = new SampleListener();
        controller = new Controller();
        controller.addListener(listener);
    }

    static class SampleListener extends Listener {

        public void onConnect(Controller controller) {
                System.out.println("Connected");
                controller.enableGesture(Gesture.Type.TYPE_SWIPE);
                controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
                controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
                controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
            }

        public void onFrame(Controller controller) {
                Frame frame = controller.frame();
                hands = frame.hands();
                hand = hands.get(0);

            if(hand.stabilizedPalmPosition().getX()!=0&&hand.stabilizedPalmPosition().getY()!=0)
                gameManager.moveCommand(hand.stabilizedPalmPosition().getX()*2.5f + Gdx.graphics.getWidth()/2,
                        hand.stabilizedPalmPosition().getY()*1.2f,
                        hand.stabilizedPalmPosition().getZ());

            if(hand .pinchStrength() >= 0.6)
                gameManager.pinchCommand();

            GestureList gestures = frame.gestures();

            for(int i =0; i < gestures.count(); i++){
                Gesture gesture = gestures.get(i);

                controller.config().setFloat("Gesture.Swipe.MinVelocity", 10f);
                controller.config().setFloat("Gesture.Swipe.MinLength", 10f);
                controller.config().save();

                switch(gesture.type()){
                    case TYPE_CIRCLE:
                        CircleGesture circle = new CircleGesture(gesture);

                        Boolean isclockwise = false;
                        if(circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/4)
                            isclockwise= true;
                        else
                            isclockwise = false;

                        double sweptAngle = 0;
                        if(circle.state() != Gesture.State.STATE_START){
                            CircleGesture previous = new CircleGesture(controller.frame(1).gesture(circle.id()));
                            sweptAngle = (circle.progress() - previous.progress()) * 2 * Math.PI;
                        }

                          controller.config().setFloat("Gesture.Circle.MinArc", 6f);
                        controller.config().save();

                        gameManager.circleCommand(isclockwise, sweptAngle, hand.stabilizedPalmPosition().getX(),hand.stabilizedPalmPosition().getY());
                        //System.out.println("Circle ID: " + circle.id() + " State: " + circle.state() + " Progress: " + circle.progress() + " " + clockwiseness);
                        break;

                        case TYPE_SWIPE:
                        SwipeGesture swipe = new SwipeGesture(gesture);

                        gameManager.swipeCommand(swipe.direction(), swipe.speed(), hand.palmPosition().getX(),hand.palmPosition().getY());
						/*System.out.println("Swipe ID: " + swipe.id() +
								" State: " + swipe.state() +
								" Swipe Position: " + swipe.position() +
								" Direction: " + swipe.direction() +
								" Speed: " + swipe.speed());*/
                        break;
                }
            }
        }
    }
}

