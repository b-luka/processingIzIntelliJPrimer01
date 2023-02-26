import processing.core.*;
public class Avion {
    private PApplet pApplet;
    PImage avion;
    PImage avionLevi;
    PImage avionDesni;
    float x;
    float y;
    float vx;
    int c;
    int hits;

    PImage boom;

    Avion(PApplet _pApplet, float _x, float _y, float _vx, int _c)
    {
        this.pApplet = _pApplet;
        x = _x;
        y = _y;
        vx = _vx;
        c = _c;
        avionLevi = pApplet.loadImage("avion.png");
        avionDesni = pApplet.loadImage("avion2.png");
        avion = avionLevi;
        //avion = loadImage("boom.gif");
        hits = 0;
        boom = pApplet.loadImage("boom.png");
    }

    void updateImage() {
        if (vx > 0) {
            avion = avionLevi;
        } else {
            avion = avionDesni;
        }
    }

    void display() {

        pApplet.stroke(0);
        pApplet.fill(c);
        pApplet.rectMode(pApplet.CENTER);
        pApplet.rect(x, y, 60, 30);
        pApplet.imageMode(pApplet.CENTER);
        pApplet.image(avion, x, y, 250, 200);
    }

    void move()
    {
        x += vx;
        if (x > pApplet.width - 110 || x < 110)
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