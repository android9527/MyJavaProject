package com.test.serializable;

public class CloneablePeople implements Cloneable{
	
	
	String test = "";
	
	
	People p ;
	

	@Override
	protected CloneablePeople clone(){
		// TODO Auto-generated method stub
		
		CloneablePeople p = null;
		try {
			p = (CloneablePeople) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}


	@Override
	public String toString() {
		return "CloneablePeople [test=" + test + ", p=" + p + "]";
	}
	
	
	
}
