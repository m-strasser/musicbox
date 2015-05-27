/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicbox;

/**
 *
 * @author mstrasser
 */
public class Settings {
    private int lowerTempo = 70;
    private int upperTempo = 170;
    private int lowerWLThreshold = 4;
    private int upperWLThreshold = 6;
    
    public int getLowerTempo() { return this.lowerTempo; }
    public int getUpperTempo() { return this.upperTempo; }
    public int getLowerWLThreshold() { return this.lowerWLThreshold; }
    public int getUpperWLThreshold() { return this.upperWLThreshold; }
}
