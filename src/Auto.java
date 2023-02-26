import processing.core.PApplet;
import processing.core.*;
public class Auto {
    private PApplet pApplet;
    PImage autoLevi;
    PImage autoDesni;
    PImage auto;
    float x;
    float y;
    float vx;
    int c;
    int hits;

    PImage boom;

    Auto(PApplet _pApplet, float _x, float _y, float _vx, int _c)
    {
        this.pApplet = _pApplet;
        x = _x;
        y = _y;
        vx = _vx;
        c = _c;
        autoLevi = pApplet.loadImage("auto1.png");
        autoDesni = pApplet.loadImage("auto2.png");
        auto = autoLevi;
        boom = pApplet.loadImage("boom.png");
        hits = 0;
    }

    void updateImage() {
        if (vx > 0) {
            auto = autoLevi;
        } else {
            auto = autoDesni;
        }
    }

    void display() {

        pApplet.stroke(0);
        pApplet.fill(c);
        pApplet.rectMode(pApplet.CENTER);
        pApplet.rect(x, y, 60, 30);
        pApplet.imageMode(pApplet.CENTER);
        pApplet.image(auto, x, y);
    }

    void move()
    {
        x += vx;
        if (x > pApplet.width - 100 || x < 100)
        {
            vx = vx * (-1);
            updateImage();
        }
    }

    boolean collideWithBall (Ball ball) {
        float r1x = ball.x;
        float r1y = ball.y;
        float r2x = x;
        float r2y = y;
        float r1w = 50;
        float r1h = 50;
        float r2w = 200;
        float r2h = 106;
        if (r1x + r1w >= r2x &&     // r1 right edge past r2 left
                r1x <= r2x + r2w &&       // r1 left edge past r2 right
                r1y + r1h >= r2y &&       // r1 top edge past r2 bottom
                r1y <= r2y + r2h) {       // r1 bottom edge past r2 top
            return true;
        }
        return false;
    }

    boolean explode (Ball ball) {
        if (!ball.hitGround && collideWithBall(ball)) {
            pApplet.image(boom, x - 250, y - 250);
            hits++;
            return true;
        }
        return false;
    }
}