package com.bean;

public class User {
   private int id;
   private String name;
   private int password;
   private int score;
   private int gold;
 
   
public User(int id, String name, int score, int gold) {
	super();
	this.id = id;
	this.name = name;
	this.score = score;
	this.gold = gold;
}
public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
public int getGold() {
	return gold;
}
public void setGold(int gold) {
	this.gold = gold;
}
public User() {
	super();
}
public User(int id, int password) {
	super();
	this.id = id;
	this.password = password;
}
public User(int id, String name, int password) {
	super();
	this.id = id;
	this.name = name;
	this.password = password;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getPassword() {
	return password;
}
public void setPassword(int password) {
	this.password = password;
}
}
