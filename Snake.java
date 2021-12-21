
public class Snake {
	// Each snake will have its head at some number and its tail at a smaller number.
	private int start;
	private int end;

	public Snake(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
	public int hashCode(){
		return start;
	}
	public boolean equals(){
		if(this == obj)
		return true;
	
 		if(obj == null || obj.getClass()!= this.getClass())
		return false;
	  
		Snake obj= (Snake)obj;
		return (this.end=obj.getEnd());
	}
}