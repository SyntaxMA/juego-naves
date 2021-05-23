package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;


public class Alien {

    Texture texture = new Texture("alien.png");
    Texture explosion = new Texture("explosion.png");

    float x, y, w, h, vx,vy;
    Temporizador cambioVelocidad = new Temporizador(60);
    boolean explota = false;


    Alien(){
        x = 450;
        y = 300;
        w = 50;
        h = 70;
        vx = -2;
        vy = 0;
    }

    public void update() {
        y -= vy;
        x += vx;
        if (cambioVelocidad.suena()){
            vy = Utils.random.nextInt(3)+1;
            vx = Utils.random.nextInt(3)-1;
        }

        if (Utils.random.nextInt(10) == 0) {
            // balas.add
        }
    }

    void render(SpriteBatch batch) {
        if (explota) {
            batch.draw(explosion, x, y, w, h);
        } else {
            batch.draw(texture, x, y, w, h);
        }
    }
}