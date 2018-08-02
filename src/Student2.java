public class Student2 extends Student{
  public static final int HASH_MULTIPLIER=37;
  public Student2(String aFirst, String aLast, int apID){
   	super(aFirst, aLast,apID); 
  }
  public int hashCode(){
  	return (int) Math.pow(HASH_MULTIPLIER,2)*getFirst().hashCode()+HASH_MULTIPLIER*getLast().hashCode()+new Integer(getID()).hashCode();
  }
}