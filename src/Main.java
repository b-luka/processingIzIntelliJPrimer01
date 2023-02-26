import processing.core.*;

public class Main extends PApplet {

    Auto auto1;
    Avion avion;
    //ArrayList <Ball> balls;
    BallSystem ballAuto;
    BallSystem ballsAvion;
    int hitsAvion, hitsAuto;
    PImage bg;

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    @Override
    public void settings() {
        size(1200, 600);
    }

    @Override
    public void setup() {
        auto1 = new Auto(this, 200, height - 100, 5, 255);
        avion = new Avion(this, 200, 100, 10, 255);
        //balls = new ArrayList<>();
        ballAuto = new BallSystem(this);
        ballsAvion = new BallSystem(this);
        hitsAvion = 0;
        hitsAuto = 0;
        bg = loadImage("bliss.jpg");
        bg.resize(width, height);
    }

    @Override
    public void draw() {
        background(bg);
        auto1.move();
        auto1.display();
        avion.move();
        avion.display();

        ballAuto.run(auto1);
        ballsAvion.run(avion);
        ballAuto.run(ballsAvion);


        fill(color(102, 51, 0));
        noStroke();
        rectMode(CORNERS);
        rect(0, height, width, height - 50);

        textSize(50);
        //hits = auto1.hits;
        fill(color(255, 0, 0));
        text("Hits: " + auto1.hits, 50, 50);
        fill(color(0, 255, 0));
        text("Hits: " + avion.hits, width - 175, 50);
        ballAuto.removeBalls(auto1);
        ballsAvion.removeBalls(avion);
        ballAuto.removeBalls(ballsAvion);
    }

    @Override
    public void mousePressed() {
        ballAuto.addBall(avion.x, avion.y, avion.vx, true);
    }

    @Override
    public void keyPressed() {
        if(key == ' ') {
            ballsAvion.addBall(auto1.x, auto1.y, auto1.vx, false);
        }
    }
}