package com.bean;

public class Role {
  private int id;
  private int strength;
  private int speed;
  private int price;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Role(int stength, int speed, int price) {
	super();
	this.strength = stength;
	this.speed = speed;
	this.price = price;
}
public Role() {
	// TODO Auto-generated constructor stub
}
public int getStength() {
	return strength;
}
public void setStength(int stength) {
	this.strength = stength;
}
public int getSpeed() {
	return speed;
}
public void setSpeed(int speed) {
	this.speed = speed;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
}
