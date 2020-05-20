package it.polito.tdp.borders.model;

public class Border {
	
	private int state1n;
	private String state1a;
	private int state2n;
	private String state2a;
	private int year;
	
	public Border(int state1n, String state1a, int state2n, String state2a, int year) {
		super();
		this.state1n = state1n;
		this.state1a = state1a;
		this.state2n = state2n;
		this.state2a = state2a;
		this.year = year;
	}

	public int getState1n() {
		return state1n;
	}

	public void setState1n(int state1n) {
		this.state1n = state1n;
	}

	public String getState1a() {
		return state1a;
	}

	public void setState1a(String state1a) {
		this.state1a = state1a;
	}

	public int getState2n() {
		return state2n;
	}

	public void setState2n(int state2n) {
		this.state2n = state2n;
	}

	public String getState2a() {
		return state2a;
	}

	public void setState2a(String state2a) {
		this.state2a = state2a;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + state1n;
		result = prime * result + state2n;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		if (state1n != other.state1n)
			return false;
		if (state2n != other.state2n)
			return false;
		return true;
	}
	
	public String toString() {
		return state1a+" "+state2a+" "+year;
	}
	
}
