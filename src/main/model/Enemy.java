package model;

import java.util.ArrayList;
import java.util.List;

// represents monster that the player fights having hp, size, and a dialogue
public class Enemy {
    protected int hp;
    protected String dialogue;
    protected int exp;

    // CONSTRAINT: 0 < size < 4
    // EFFECT: creates monster with given size and assigns dialogue and hp depending on size
    public Enemy(int hp, String dialogue, int exp) {
        this.hp = hp;
        this.dialogue = dialogue;
        this.exp = exp;
    }

    // getters
    public String getEnemyDialogue() {
        return dialogue;
    }

    public int getEnemyExp() {
        return exp;
    }

    public int getEnemyHp() {
        return hp;
    }

    //EFFECT: return true if monster hp <= 0, otherwise return false
    public boolean isDefeated() {
        return hp <= 0;
    }

    //CONSTRAINT: monster must have hp >= 0
    //MODIFIES: this
    public void attackEnemy() {
        hp -= 1;
    }

}
