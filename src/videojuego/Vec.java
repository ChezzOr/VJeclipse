/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego;

/**
 *
 * @author Christopher
 */
public class Vec {
    private double x;
    private double y;
    
    public Vec(){
        x=0.0;
        y=0.0;
    }

    public Vec(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vec(double minx, double maxx,double miny, double maxy){
        x=minx+Math.random()*(maxx-minx);
        y=miny+Math.random()*(maxy-miny);
    }
    
    public void add(Vec v){
        this.x+=v.x;
        this.y+=v.y;
    }
    
    public void scale(double s){
        x*=s;
        y*=s;
    }
    public void scalex(double s){
        x*=s;
    }
    
    public void scaley(double s){
        y*=s;
    }
    
    public double mag(){
        return Math.sqrt(x*x+y*y);
    }
    
    public void normalize(){
        scale(1.0/mag());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public int getIntX(){
        return (int)Math.round(x);
    }
    
    public int getIntY(){
        return (int)Math.round(y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
