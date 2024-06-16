package RailwayReservation;

import java.util.*;
public class TicketBooker {
	static int availablelowerberth = 2;
	static int availableMiddleBerth = 1;
	static int availableUpperBerth = 1;
	static int availableRAC = 1;
	static int availableWaitingList = 1;
	
	static Map<Integer, Passenger> map = new HashMap<Integer, Passenger>();// id's and passenger details
	
	static List<Integer> bookedTicketList = new ArrayList<Integer>();// booked tickets Id's
	
	static Queue<Integer> racQueue = new LinkedList<>(); // RAC Queue
	static Queue<Integer> waitingQueue = new LinkedList<>(); // waiting Queue
	
	static List<Integer> lowerberthSeats = new ArrayList<Integer>(Arrays.asList(1,2));
	static List<Integer> middleberthSeats = new ArrayList<Integer>(Arrays.asList(1));
	static List<Integer> upperberthSeats = new ArrayList<Integer>(Arrays.asList(1));
	static List<Integer> racSeats = new ArrayList<Integer>(Arrays.asList(1));
	static List<Integer> wLSeats = new ArrayList<Integer>(Arrays.asList(1));
	
	public void ticketBook(Passenger p, int seatNum, String allotedBerth) {
		p.number = seatNum;
		p.alloted = allotedBerth;
		
		map.put(p.passengerid, p);
		bookedTicketList.add(p.passengerid);
		System.out.println("***************  Ticket Booked Sucessfully  ****************");
	}
	// cancel ticket 
	public void ticketCancel(int id) {
		Passenger p = map.get(id);
		map.remove(Integer.valueOf(id));
		bookedTicketList.remove(Integer.valueOf(id));
		int position = p.number;
		System.out.println("****************  Cancelled Sucessfully  *************");
		
		if(p.alloted.equals("L")) {
			availablelowerberth++;
			lowerberthSeats.add(position);
		}else if(p.alloted.equals("M")) {
			availableMiddleBerth++;
			middleberthSeats.add(position);
		}else if(p.alloted.equals("U")) {
			availableUpperBerth++;
			upperberthSeats.add(position);
		}
		// check if RAC is there
		if(racQueue.size() > 0) {
			Passenger passengerFromRAC = map.get(racQueue.poll());
			int positionOfRac = passengerFromRAC.number;
			racQueue.remove(passengerFromRAC.passengerid);
			availableRAC++;
			racSeats.add(positionOfRac);
			
			if(waitingQueue.size() > 0) {
				Passenger passengerFromWL = map.get(waitingQueue.poll());
				int positionOfWL = passengerFromWL.number;
				
				waitingQueue.remove(passengerFromWL.passengerid);
				availableWaitingList++;
				wLSeats.add(positionOfWL);
				
				passengerFromWL.number = racSeats.get(0);
				passengerFromWL.alloted = "RAC";
				racSeats.remove(0);
				racQueue.add(passengerFromWL.passengerid);
				availableRAC--;
				
			}
			Main.bookTicket(passengerFromRAC);
		}
		
	}
	// add to RAC
	public void addToRAC(Passenger p, int seatNum, String allotedBerth) {
		p.number = seatNum;
		p.alloted = allotedBerth;
		
		map.put(p.passengerid, p);
		racQueue.add(p.passengerid);
		System.out.println("***********  added to RAC  ************");
	}
	
	// addto WL
	public void addToWL(Passenger p, int seatNum, String allotedBerth) {
		p.number = seatNum;
		p.alloted = allotedBerth;
		map.put(p.passengerid, p);
		waitingQueue.add(p.passengerid);
		System.out.println("*************  added to WL  ************");
	}
	
	
	// booked tickets
	public void bookedTickets() {
		for(Passenger p : map.values()) {
			System.out.println("Passenger Id : " + p.passengerid);
			System.out.println("Passenger name : "+p.name);
			System.out.println("Passenger age : " + p.age);
			System.out.println("Passenger Gender : "+p.gender);
			System.out.println("Passenger status : "+p.number+p.alloted);
			System.out.println("Total number of tickets filled : "+bookedTicketList.size());
			System.out.println("*************************************");
		}
	}
	
	
	// available tickets
	public void availableTickets() {
		System.out.println("Available Lower berth "+TicketBooker.availablelowerberth);
		System.out.println("Available Middle berth "+TicketBooker.availableMiddleBerth);
		System.out.println("Available Upper berth "+TicketBooker.availableUpperBerth);
		System.out.println("Available RAC "+TicketBooker.availableRAC);
		System.out.println("Available Waiting List "+TicketBooker.availableWaitingList);
	}
	
}

