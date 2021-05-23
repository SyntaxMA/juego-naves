package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sonido {


    public Music music = Gdx.audio.newMusic(Gdx.files.internal("corneria.mp3"));
    public Sound explotar = Gdx.audio.newSound(Gdx.files.internal("explotar.mp3"));
    public Sound disparo = Gdx.audio.newSound(Gdx.files.internal("disparo1.mp3"));
    public Sound disparo2 = Gdx.audio.newSound(Gdx.files.internal("disparo2.mp3"));
    public Sound disparo3= Gdx.audio.newSound(Gdx.files.internal("disparo3.mp3"));

}