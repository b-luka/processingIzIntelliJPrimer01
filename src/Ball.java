import processing.core.*;
public class Ball
{
    private PApplet pApplet;
    PImage ball;
    PImage boom;
    float x;
    float y;
    float vx;
    float vy;
    float ay;
    float theta;
    float deltaTheta;
    boolean hitGround;
    boolean isAuto;
    float ayDelta;

    public Ball(PApplet _pApplet, float _x, float _y, float _vx, boolean auto) {
        this.pApplet = _pApplet;
        x = _x;
        y = _y;
        vx = _vx;
        vy = 0;
        ay = auto ? 0.1f : -0.1f;
        ayDelta = ay;
        theta = 0;
        hitGround = false;
        ball = pApplet.loadImage("ball.png");
        deltaTheta = pApplet.random(-0.3f, 0.3f);
        boom = pApplet.loadImage("boom.gif");
    }

    void display() {
        //stroke(0);
        //imageMode(CENTER);
        //image(ball, x, y, 100, 100);
    }

    void move()
    {

        if(!hitGround){


            x += vx;
            if (x > pApplet.width - 50 || x < 50)
            {
                vx = vx * (-1);
            }
            y += vy;
            vy = ay;
            if (y > pApplet.height - 50 || y < 50)
            {
                vy *= -1;
            }
            pApplet.imageMode(pApplet.CENTER);
            // Draw the image centred about [x, y]
            // Now we want to rotate the image 'in position'
            pApplet.pushMatrix(); // remember current drawing matrix)
            pApplet.translate(x, y);
            pApplet.rotate(theta); // rotate 45 degrees
            pApplet.image(ball, 0, 0, 50, 50);
            pApplet.popMatrix(); // restore previous graphics matrix
            // Restore image mode back to default (optional)
            pApplet.imageMode(pApplet.CORNER);

        }

        else {
            pApplet.imageMode(pApplet.CENTER);
            // Draw the image centred about [x, y]
            // Now we want to rotate the image 'in position'
            pApplet.pushMatrix(); // remember current drawing matrix)
            pApplet.translate(x, y);
            pApplet.rotate(theta); // rotate 45 degrees
            pApplet.image(ball, 0, 0, 50, 50);
            pApplet.popMatrix(); // restore previous graphics matrix
            // Restore image mode back to default (optional)
            pApplet.imageMode(pApplet.CORNER);
        }
    }

    void spin()
    {
        if (y > pApplet.height - 50 || y < 50)
        {
            hitGround = true;
        }
        if (!hitGround)
        {
            theta += deltaTheta;
        }
    }

    boolean isDead(Auto auto) {
        if ((!hitGround && auto.collideWithBall(this)) || hitGround) {
            return true;
        }
        return false;
    }

    boolean isDead(Avion avion) {
        if ((!hitGround && avion.collideWithBall(this)) || hitGround) {
            return true;
        }
        return false;
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
            return true;
        }
        return false;
    }


    boolean isDead(Ball ball) {
        if (ball.collideWithBall(this)) {
            return true;
        }
        return false;
    }
}