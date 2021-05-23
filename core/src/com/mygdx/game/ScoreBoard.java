package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreBoard {
    static class Score{
        String nom;
        int punto;

        Score(String nom, int punto){
            this.nom = nom;
            this.punto = punto;
        }
    }
    char[] nombre = {'A', 'A','A', 'A'};
    int index = 0;
    private boolean saved;
    List<Score> scoreList = new ArrayList<>();
    int update(int puntos){
        if(index < 4 && Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            nombre[index]++;
            if(nombre[index] > 90) {
                nombre[index] = 65;
            }
        }
        if(index < 4 && Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            nombre[index]--;
            if(nombre[index] < 65) {
                nombre[index] = 90;
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if(index == 4) {
                return 1;
            } else if (index == 5) {
                return 2;
            }
            index++;
        }

        if(index > 3 && Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (index == 4) index = 5; else index = 4;
        }
        if(index > 3 && Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (index == 4) index = 5; else index = 4;
        }

        if(index > 3 && !saved) {
            guardarPuntuacion(puntos);
            saved = true;
        }
        return 0;
    }


    void render(SpriteBatch batch, BitmapFont font) {
        if(!saved) {
            font.draw(batch, "ESCRIBE TU NOMBRE", 150, 300);

            font.getData().setScale(3);
            font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            for (int i = 0; i < 4; i++) {
                if(index == i){
                    font.setColor(Color.RED);
                }
                font.draw(batch, ""+ nombre[i], 225+40*i, 250);
                font.setColor(Color.WHITE);
            }
            font.getData().setScale(2);
        }else {
            font.draw(batch, "PUNTUACION", 150, 400);

            for (int i = 0; i < 5 && i < ordenar.size(); i++) {
                font.draw(batch, ordenar.get(i).nom, 420, 340 - i * 40);
                font.draw(batch, "" + ordenar.get(i).punto, 600, 340 - i * 40);
            }
        }
    }

    void guardarPuntuacion(int puntuacion) {
        try {
            FileWriter fileWriter = new FileWriter("scores.txt", true);
            fileWriter.write(""+ nombre[0]+ nombre[1]+ nombre[2] + nombre[3] + "," + puntuacion + "\n");
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        leerPuntuaciones();
    }

    void leerPuntuaciones() {

        try {
            Scanner scanner = new Scanner(new File("scores.txt"));
            scanner.useDelimiter(",|\n");

            while (scanner.hasNext()) {
                String nombre = scanner.next();
                int puntos = scanner.nextInt();

                scoreList.add(new Score(nombre, puntos));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ordenarPuntuaciones();
    }

    List <Score> ordenar = new ArrayList<>();
    void ordenarPuntuaciones() {

        while (scoreList.size() > 0){
            int p = 0;
            for (int i = 0; i < scoreList.size(); i++) {
                if(scoreList.get(i).punto > scoreList.get(p).punto){
                    p = i;
                }
            }
            ordenar.add(scoreList.get(p));
            scoreList.remove(p);
        }
    }
}