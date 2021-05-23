package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Nave {
    Animacion animacion = new Animacion(16,
            new Texture("nave.png"),
            new Texture("nave1.png"),
            new Texture("nave2.png"),
            new Texture("nave3.png")
    );

    float x, y, w, h;
    List<Bala> balas = new ArrayList<>();
    List<SuperBala> superbalas = new ArrayList<>();
    List<Goldenbala> goldenbalas = new ArrayList<>();
    int vidas = 3;
    int puntos = 0;
    boolean muerto = false;
    Sonido sonido = new Sonido();

    Temporizador temporizadorFireRate = new Temporizador(10);
    Temporizador temporizadorRespawn = new Temporizador(120, false);

    Nave(){
        x = 280;
        y = 50;
        w = 50;
        h = 100;
    }

    void update(){
        for (Bala bala: balas) bala.update();
        for (SuperBala superbala: superbalas) superbala.update();
        for (Goldenbala goldenbala: goldenbalas) goldenbala.update();

        if(Gdx.input.isKeyPressed(Input.Keys.D)) x += 5;
        if(Gdx.input.isKeyPressed(Input.Keys.A)) x -= 5;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) y += 5;
        if(Gdx.input.isKeyPressed(Input.Keys.S)) y -= 5;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += 5;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) x -= 5;
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) y += 5;
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) y -= 5;

        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            balas.add(new Bala(x+w/2, y+h));
            sonido.disparo.play(0.2f);

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            superbalas.add(new SuperBala(x+w/2, y+h));
            sonido.disparo2.play(0.2f);

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            goldenbalas.add(new Goldenbala(x+w/2, y+h));
            sonido.disparo3.play(0.2f);

        }
        if (x<0) x = 600;
        if (x>600) x = 0;
        if (y<0) y = 0;
        if (y>585) y = 585;

        if (temporizadorRespawn.suena()) {
            muerto = false;
        }
    }

    void render(SpriteBatch batch){
        if (muerto) batch.setColor(1,1,1,0.25f);
        batch.draw(animacion.obtenerFrame(), x, y,w, h);
        if (muerto) batch.setColor(1,1,1,1f);

        for (Bala bala: balas) {
            bala.render(batch);
        }
        for (SuperBala superbala: superbalas) {
            superbala.render(batch);
        }
        for (Goldenbala goldenbala: goldenbalas) {
            goldenbala.render(batch);
        }
    }

    public void morir() {
        vidas--;
        muerto = true;
        temporizadorRespawn.activar();
    }

}