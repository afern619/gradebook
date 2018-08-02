public class Grade{
	private String letter;
	public Grade(String grade){
		letter=grade;
	}
	public String getLetter(){return letter;}
	public double getValue(){
		switch(letter){
			case "A": return 4;
			case "A-": return 3.667;
			case "B+": return 3.333;
			case "B": return 3;
			case "B-": return 2.667;
			case "C+": return 2.333;
			case "C": return 2;
			case "C-": return 1.667;
			case "F": return 0;
		}
		return 0;
	}
}