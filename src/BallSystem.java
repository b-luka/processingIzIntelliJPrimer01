import processing.core.*;

import java.util.ArrayList;

public class BallSystem {
    private PApplet pApplet;

    ArrayList<Ball> balls;
    PImage boom;

    public BallSystem(PApplet _pApplet){
        this.pApplet = _pApplet;
        balls = new ArrayList<>();
        boom = pApplet.loadImage("boom.gif");
    }

    void run(Auto auto) {
        for (var ball : balls) {
            ball.move();
            ball.display();
            ball.spin();
            ball.ay += ball.ayDelta;
            auto.explode(ball);


        }
    }

    void removeBalls(Auto auto) {
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            if (b.isDead(auto)) {

                pApplet.image(boom, b.x - 100, b.y - 100);
                balls.remove(i);
            }
        }
    }

    void run(Avion avion) {
        for (var ball : balls) {
            ball.move();
            ball.display();
            ball.spin();
            ball.ay += ball.ayDelta;
            avion.explode(ball);


        }
    }

    void removeBalls(Avion avion) {
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            if (b.isDead(avion)) {

                pApplet.image(boom, b.x - 100, b.y - 100);
                balls.remove(i);
            }
        }
    }

    void run(BallSystem ballSystem) {
        for (var ball : balls) {
            ball.move();
            ball.display();
            ball.spin();
            ball.ay += ball.ayDelta;
            for(var ball2 : ballSystem.balls) {
                ball2.explode(ball);
            }


        }
    }

    void removeBalls(BallSystem ballSystem) {
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            for (int j = 0; j < ballSystem.balls.size(); j++) {
                if (b.isDead(ballSystem.balls.get(j))) {

                    pApplet.image(boom, b.x - 100, b.y - 100);
                    balls.remove(i);
                    ballSystem.balls.remove(j);
                }
            }
        }
    }

    void addBall(float x, float y, float vx, boolean auto) {
        balls.add(new Ball(pApplet, x, y, vx, auto));
    }

}