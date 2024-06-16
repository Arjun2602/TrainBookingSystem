package RailwayReservation;

import java.util.*;
public class Main {
	
	public static void cancelTicket(int id) {
		if(!TicketBooker.map.containsKey(id)) {
			System.out.println("********  Id not available  *********");
		}else {
			TicketBooker booker = new TicketBooker();
			booker.ticketCancel(id);
		}	
	}
	
	public static void bookTicket(Passenger p) {
		TicketBooker booker = new TicketBooker();
		if(TicketBooker.availableWaitingList == 0) {
			System.out.println("***************************");
			System.out.println("No Tickets Available....");
			System.out.println("***************************");
		}
		if(p.gender.equals("F") && TicketBooker.availablelowerberth>0) {
			if(p.cname != null) {
				System.out.println("Ladies with children.so, Lower berth prefred");
				booker.ticketBook(p,TicketBooker.lowerberthSeats.get(0), "L");
				TicketBooker.availablelowerberth--;
				TicketBooker.lowerberthSeats.remove(0);
			}else if(p.cname == null && TicketBooker.availablelowerberth>0) {
				System.out.println("*********  Lower berth Available  *********");
				booker.ticketBook(p,TicketBooker.lowerberthSeats.get(0), "L");
				TicketBooker.availablelowerberth--;
				TicketBooker.lowerberthSeats.remove(0);
			}
		}else if(p.age > 60 && TicketBooker.availablelowerberth>0 ) {
			System.out.println("You are senior citizen so lower berth provided");
			booker.ticketBook(p,TicketBooker.lowerberthSeats.get(0), "L");
			TicketBooker.availablelowerberth--;
			TicketBooker.lowerberthSeats.remove(0);
		}else if(p.age < 5) {
			System.out.println("******  No Tickets Available  *******");
		}
		// prefered berth booking
		else if(p.berthPreference.equals("L") && TicketBooker.availablelowerberth > 0  ||
		   p.berthPreference.equals("M") && TicketBooker.availableMiddleBerth > 0 ||
		   p.berthPreference.equals("U") && TicketBooker.availableUpperBerth > 0 ) {
			
			if(p.berthPreference.equals("L") && TicketBooker.availablelowerberth > 0) {
				System.out.println("**********  Lower Berth Available *************");
				booker.ticketBook(p,TicketBooker.lowerberthSeats.get(0) ,"L");
				
				TicketBooker.lowerberthSeats.remove(0);
				TicketBooker.availablelowerberth--;
				
			}else if(p.berthPreference.equals("M") && TicketBooker.availableMiddleBerth > 0) {
				System.out.println("**************  Middle Berth Available  ***************");
				booker.ticketBook(p, TicketBooker.middleberthSeats.get(0), "M");
				
				TicketBooker.middleberthSeats.remove(0);
				TicketBooker.availableMiddleBerth--;
				
			}else if(p.berthPreference.equals("U") && TicketBooker.availableUpperBerth > 0) {
				System.out.println("***************  upper Berth Available  **************");
				booker.ticketBook(p, TicketBooker.upperberthSeats.get(0), "U");
				
				TicketBooker.availableUpperBerth--;
				TicketBooker.upperberthSeats.remove(0);
			}
			// book available berth
		}else if(TicketBooker.availablelowerberth > 0) {
			System.out.println("Lower berth alloted ***************");
			booker.ticketBook(p,TicketBooker.lowerberthSeats.get(0),"L");
			
			TicketBooker.lowerberthSeats.remove(0);
			TicketBooker.availablelowerberth--;
			
		}else if(TicketBooker.availableMiddleBerth > 0) {
			System.out.println("***************  middle berth provided  ************");
			booker.ticketBook(p, TicketBooker.middleberthSeats.get(0), "M");
			
			TicketBooker.middleberthSeats.remove(0);
			TicketBooker.availableMiddleBerth--;
			
		}else if(TicketBooker.availableUpperBerth > 0) {
			System.out.println("**************** Upper berth provided  **********");
			booker.ticketBook(p, TicketBooker.upperberthSeats.get(0), "U");
			
			TicketBooker.availableUpperBerth--;
			TicketBooker.upperberthSeats.remove(0);
		}
		//if berth not available  go to RAC
		else if(TicketBooker.availableRAC > 0) {
			System.out.println("**********  RAC available  ************");
			booker.addToRAC(p, TicketBooker.racSeats.get(0),"RAC");
			
			TicketBooker.availableRAC--;
			TicketBooker.racSeats.remove(0);
			
		}
		//if RAC not available fill waiting list
		else if(TicketBooker.availableWaitingList > 0) {
			System.out.println("************  Waiting list available ***********");
			booker.addToWL(p,TicketBooker.wLSeats.get(0),"WL");
			
			TicketBooker.availableWaitingList--;
			TicketBooker.wLSeats.remove(0);
			
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1. Book");
			System.out.println("2. Cancel");
			System.out.println("3. Booked Tickets");
			System.out.println("4. Available Tickets");
			System.out.println("5. Exit");
			int choice = sc.nextInt();
			switch(choice) {
			// Book
			case 1:
			{
				System.out.print("Enter your name : ");
				String name = sc.next();
				System.out.print("Enter your age : ");
				int age = sc.nextInt();
				System.out.print("Enter your gender [ M / F ]: ");
				String gender = sc.next();
				if(gender.equals("F")) {
					System.out.print("Have children [ y / n ]: ");
					String gchoice = sc.next();
					if(gchoice.equals("y")){
						System.out.print("Enter children name : ");
						String childname = sc.next();
						System.out.print("Enter children age : ");
						int childage = sc.nextInt();
						System.out.print("Enter your berthpreference [L,M,U]: ");
						String berthpreference = sc.next();
						Passenger p = new Passenger(name, age, gender, childname, childage, berthpreference);
						bookTicket(p);
					}else if(gchoice.equals("n")) {
						System.out.print("Enter your berthpreference [L,M,U]: ");
						String berthpreference = sc.next();
						String childname = null;
						int childage = 0;
						Passenger p = new Passenger(name, age, gender, childname, childage, berthpreference);
						bookTicket(p);
					}
					break;
				}
				if(gender.equals("M")) {
					System.out.print("Enter your berthpreference [L,M,U]: ");
					String berthpreference = sc.next();
					String childname = null;
					int childage = 0;
					Passenger p = new Passenger(name, age, gender, childname, childage, berthpreference);
					bookTicket(p);
					
				}
				break;
			}
			// cancel tickets
			case 2:
			{
				System.out.print("Enter your Id : ");
				int id = sc.nextInt();
				cancelTicket(id);
				break;
			}
			// booked tickets
			case 3:
			{	if(TicketBooker.map.size() == 0) {
					System.out.println("*******  No Booked tickets available  ***********");
					break;
				}else {
					TicketBooker booker = new TicketBooker();
					booker.bookedTickets();
					break;
				}
				
			}
			// Available tickets
			case 4:
			{	
				TicketBooker booker = new TicketBooker();
				booker.availableTickets();
				break;
			}
			case 5:
			{
				flag = false;
				break;
			}
			}
		}

	}

}

