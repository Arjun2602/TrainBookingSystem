package RailwayReservation;

public class Passenger {
	String name;
	int age;
	String cname;
	int cage;
	String gender;
	String berthPreference;
	static int id = 1;
	int passengerid = id++;
	int number;
	String alloted;
	
	public Passenger(String name, int age, String gender, String cName, int cAge, String bp){
		this.name = name;
		this.age = age;
		this.cname = cName;
		this.cage = cAge;
		this.gender = gender;
		this.berthPreference = bp;
		
		number = -1;
		alloted = " ";
	}
	
}
