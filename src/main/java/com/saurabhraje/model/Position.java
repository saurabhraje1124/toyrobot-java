package com.saurabhraje.model;

public class Position {

	private int x;
	private int y;
	private int face;
	
	public Position () {
		this.x = -1;
		this.y = -1;
		this.face = -1;
	}
	
	public Position(int x, int y, int face) {
		this.x = x;
		this.y = y;
		this.face = face;
	}
	
	public void set (int x, int y, int face) {
		this.x = x;
		this.y = y;
		this.face = face;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getFace() {
		return face;
	}

	public void setFace(int face) {
		this.face = face;
	}
}
