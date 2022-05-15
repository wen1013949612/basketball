package com.bean;

public class computer {
 private int id;
 private int speed;
 private int strenth;
public computer(int speed, int strenth) {
	super();
	this.speed = speed;
	this.strenth = strenth;
}
public computer() {
	super();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getSpeed() {
	return speed;
}
public void setSpeed(int speed) {
	this.speed = speed;
}
public int getStrenth() {
	return strenth;
}
public void setStrenth(int strenth) {
	this.strenth = strenth;
}
 
}
