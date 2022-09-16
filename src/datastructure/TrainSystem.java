package datastructure;
import java.util.*;

public class TrainSystem {       
    public static String destination,name;
    public static int tclass,noPassengers=0, noTickets,noAdults,noNotAdults=0;
    public static double totalTicketAmount; 
    public static int func;
    public static int maxFirstClassCount=5,maxSecondClassCount=8,firstClassCount=0,secondClassCount=0;
    
    private static datastructure.Queue TicketQueue = new Queue(10);
    private static datastructure.Queue EnterTrainQueue = new Queue(800);
    //Main method
    public static void main(String [] args){	
       System.out.println("*****-----------------------------------------------------------*****");
       System.out.println("\t\tWelcome To Our Railway System\n\tSri Devie Intercity Express Train - Colombo to Jaffna");
       System.out.println("*****-----------------------------------------------------------*****");
       getUserFunc();
    } 
    public static void getUserFunc(){        
        Scanner in=new Scanner(System.in);
        //Select Function by the staff
        System.out.println("\n****************************************************");
        System.out.println("Press 1 - To Get Passengers for Ticket Queue");
        System.out.println("Press 2 - To Provide Ticket to Passengers");
        System.out.println("Press 3 - To Allow Passengers to enter train");
        System.out.println("Press 4 - To Ticket count details");
        System.out.println("Press 5 - To Add or Remove box in the train");
        System.out.println("Press 6 - To View Graph of Stations");
        System.out.println("Press 7 - To Exit the System");
        System.out.println("****************************************************");
        
        System.out.print("\nPress 1 or 2 or 3 or 4 or 5 or 6 or 7 : ");
        func=in.nextInt(); //Get function number
        
        getFunction(func); //Call getFunction method 
    }
        
    public static int getFunction(int func){             
        switch(func){
            case 1:  //To Get Passengers for Ticket Queue
                System.out.println("No of passengers in the ticket queue: "+TicketQueue.size()); //No of passengers in the ticket queue
                TrainSystem.TicketQueue=getTicketQueue(TicketQueue);//Call function to add Passengers for TicketQueue & return the 
                                                                    //updated current TicketQueue   
                getUserFunc(); //Select Function by the staff again
                break;
            case 2: //To Provide Ticket to Passengers
                TrainSystem.TicketQueue=provideTicket(TicketQueue);  //Call function to provide Ticket to Passengers & return the 
                                                                    //updated current TicketQueue            
                getUserFunc(); //Select Function by the staff again
                break;
            case 3: //To Allow Passengers to enter train
                getEnterTrainQueue(EnterTrainQueue);
                getUserFunc(); //Select Function by the staff again
                break;
            case 4:
                stackdetails();
                getUserFunc(); //Select Function by the staff again
                break; 
            case 5:
                list();
                getUserFunc(); //Select Function by the staff again
                break;
            case 6:
                Wgraph();
                getUserFunc(); //Select Function by the staff again
                break;
            case 7:
                System.out.println("Thank You.");
                break;  
            default:
                System.out.println("Invalid Input");
         }
        return func; 
    }
    
    
    //Allow passengers to ticket queue according their valid destination.  
    //Keep maximum 10 passengers in the ticket counter due to the COVID-19 situation
public static Queue getTicketQueue(Queue TicketQueue){
System.out.println("***Attention***\n\t Due to the COVID pandemic situation, Please allow only 10 passengers in ticket counter queue\n");
Scanner in=new Scanner(System.in);
CheckStations checkStations1=new CheckStations(); //Create an object of CheckStations class. To check the destination is valid or not
        //Get passengers into the ticket queue until want to stop 
        while (true) {
            System.out.print("If you want to continue to get passengers for the ticket queue, 'ENTER y' else Enter Any word : ");
            String input = in.nextLine().toLowerCase(); 
            
            if (!input.equals("y")) {   // If want to stop to get the passengers in ticket queue in temperory, end the loop
                break;
            }
            // Maximum passengers in the ticket queue is 10. Until it reaches, get passenger's name and destination
           if(!TicketQueue.isFull()){             
                System.out.print("Enter the Name : ");
                name=in.nextLine();                 
                System.out.print("Enter the Destination : ");
                destination=in.nextLine();
                String noSpaceDestination = destination.replaceAll("\\s", "").toLowerCase();  //Convert destination 
                                                                                                           //to lowercase and remove space           
     boolean checkDesti=checkStations1.checkDestination(noSpaceDestination); // Check the passenger's destination is valid or not

                //If destination is valid only, allow the passenger to the ticket queue. Otherwise not allow to the queue
                if(checkDesti==true){
                    TicketQueue.enqueue(name);                  
                }                           
            }
            //If the ticket queue reaches maximum passengers(10) Keep others in waiting list
            else{
                System.out.println("***--- Sorry. The queue is full. Please wait for a while. ---***");
                break;
            }
        }       
        return TicketQueue; // Return current updated ticket queue
    }
    
    //Function To provide ticket to the passengers who are in the ticket queue
   public static Queue provideTicket(Queue TicketQueue){
       Scanner in=new Scanner(System.in);
      GetTicketAmount getTicketAmount1=new GetTicketAmount(); //Create an object of GetTicketAmount class. To calculate total ticket amount
       
        if(TicketQueue.isEmpty()){        //If ticket queue is empty, display message     
                System.out.println("Sorry. There are no passengers in the ticker counter.");
        }
        else{          //If ticket queue is not empty
            System.out.print("\nNo of passengers in the ticket queue: "+TicketQueue.size()+"\nPress Enter"); //Get the no of passengers in  
                                                                                                             //the current ticket queue
           
            String input;
            
            //Loop to get details from passengers in the ticket queue for provide tickets
            while(true) {
                //If ticket queue is not empty, get passenger's destination,number of tickets, class, no of adults to provide tickets
                if(!TicketQueue.isEmpty()){
                    in.nextLine();
                    System.out.print("\nIf you want to continue to provide tickets to passengers, 'ENTER y' else Enter any word : ");
                    input = in.nextLine().replaceAll("\\s", "").toLowerCase();

  //If want to continue to provide tickets, get passenger's destination,number of tickets, class, no of adults to calculate ticket amount          
                    if (input.equals("y")) {      
                        System.out.println("Passenger name: "+TicketQueue.peek());            
                        System.out.print("Enter the Destination : ");
                        destination=in.nextLine(); //Get destination
                        System.out.print("Enter the number of tickets : ");
                        noTickets=in.nextInt(); //Get number of tickets
                        System.out.print("Enter the Class : ");
                        tclass=in.nextInt(); //Get class
                        System.out.print("Enter the number of adults : ");
                        noAdults=in.nextInt(); //Get number of adults
                        
                        noNotAdults=noTickets-noAdults;   //Find no of not adults to provide discount in ticket amount

                        //If passeger want to first class, check whether there are available seats in first class for all the certain passengers(according to the number of tickets) or not 
                        if(tclass==1){
                            if((firstClassCount+noTickets)<=maxFirstClassCount) //If there are available first class seats for this passenger and his/her family, 
                               TrainSystem.firstClassCount+=noTickets; //upadate current total no of first class passengers
                            
                            else{ //If exceed the max no of 1st class seats, ask passenger to change class or cancel class
                                System.out.print("Sorry first class is almost full. If you want to change your class, enter the new class (Enter 2 or 3). "
                                        + "Else cancel your ticket(Enter 0 or another number) ");
                                int newClass=in.nextInt();                          
                                tclass=newClass;
                                if(tclass==2){ //check whether there are available seats in second class or not
                                    if((secondClassCount+noTickets)<=maxSecondClassCount) //If there are available seats, upadate current total no of second class passengers
                                        TrainSystem.secondClassCount+=noTickets; 
                                    else{
                                        System.out.print("\nSorry second class is also almost full. If you want to change your class, enter the new class (Enter 3)."
                                        + "Else cancel your ticket(Enter 0)");
                                        int newClass2=in.nextInt();           //Get new class again               
                                        if(!(newClass2==1 || newClass2==2))   //If class except 1 or 2, upadate new class
                                            tclass=newClass2;
                                    }
                                }
                            }
                        }
                        
                        //If passeger want to second class, 
                        else if(tclass==2){
                            if((secondClassCount+noTickets)<=maxSecondClassCount) //If there are available 2nd class seats for this passenger and his/her family, 
                                TrainSystem.secondClassCount+=noTickets; //upadate current total no of second class passengers
                           
                            else{ //If exceed the max no of 2nd class seats, ask passenger to change class or cancel class
                                System.out.print("\nSorry second class is almost full. If you want to change your class, enter the new class (Enter 1 or 3). "
                                        + "Else cancel your ticket(Enter 0 or another number) ");
                                int newClass=in.nextInt();
                                if(newClass!=2)
                                    tclass=newClass; 
                        if(tclass==1)
                            if((firstClassCount+noTickets)<=maxFirstClassCount) //If there are available seats for this passenger and his/her family, 
                                   TrainSystem.firstClassCount+=noTickets; //upadate current total no of first class passengers
                                    else{
                                        System.out.print("\nFirst second class is also almost full. If you want to change your class, enter the new class (Enter 3)."
                                        + "Else cancel your ticket(Enter 0)");
                                        int newClass2=in.nextInt();           //Get new class again               
                                        if(!(newClass2==1 || newClass2==2))   //If class except 1 or 2, upadate new class
                                            tclass=newClass2;
                                    }
                            }
                        }                   
        if(tclass==1 || tclass==2 || tclass==3){ //If class equals to 1 or 2 or 3 only, get the ticket amount 
           //Call getData method in GetTicketAmount class & Pass data into parameters to get ticket amount
           totalTicketAmount=getTicketAmount1.getData(destination, tclass, noAdults, noNotAdults); 
           System.out.println("\nDear "+TicketQueue.peek()+", your total ticket amount is : Rs. "+totalTicketAmount); //Display the total Ticket amount 
           System.out.println("After get amount, Provide Ticket");                 
                 //After provide ticket, remove the passenger from the ticket counter queue and get the passenger for add to EnterTrainQueue 
                        String ticketBoughtPassenger=TicketQueue.dequeue();    
                        //Call addEnterTrainQueue method to Add the passenger into EnterTrainQueue 
                        addEnterTrainQueue(ticketBoughtPassenger,noTickets);
                        }
                        //Remove passengers who want to cancel the ticket from the ticket queue
                        else{
                            TicketQueue.dequeue();
                        }                                          
                    }
                    else //If want to stop to provide tickets, end loop
                        break;              
                }
                //If ticket queue is empty, display message and end loop
                else{
                    System.out.println("\nThere are no passengers in the ticker counter now.");
                    break;
                }
            }
        }
        
        return TicketQueue;
    }
    
    //To add all the passengers into EnterTrainQueue to allow into the station 
    public static Queue addEnterTrainQueue(String name,int noTickets){  //Get the passenger who bought the ticket and get no of ticket he bought for his family / friends
        Scanner in=new Scanner(System.in);
        String otherPassenger;       
        TrainSystem.EnterTrainQueue.enqueue(name); //Add the passenger who bought the ticket into EnterTrainQueue
        
        //If he bought ticket for other family members or friends, provide others name also to add into the  EnterTrainQueue
        if(noTickets>=2){
            System.out.println("\nDear "+name+", Please say all the passenger for whom you ticket bought:");
            for(int x=2; x<=noTickets; x++){
                System.out.print("Enter Passenger name : ");
                otherPassenger=in.nextLine();
                TrainSystem.EnterTrainQueue.enqueue(otherPassenger);
            }
        }
        return EnterTrainQueue;
    }
  
    //Allow passengers who have valid tickets to enter into the station to get train
    public static Queue getEnterTrainQueue(Queue EnterTrainQueue){    
        Scanner in=new Scanner(System.in);
        String ans;
        if(EnterTrainQueue.isEmpty()){        //If Enter Train Queue is empty, display message     
            System.out.println("Sorry. There are no passengers in the entrance for enter train queue.");
        }
        else{
            while(true) {
                System.out.println("\nNo of passengers in the enter train queue: "+EnterTrainQueue.size()); //No of passengers in the Enter Train Queue 
                System.out.print("\nIf you want to continue to allow passengers to enter into the station according their tickets, 'ENTER y' else Enter any word : ");
                ans = in.nextLine().replaceAll("\\s", "").toLowerCase();

              if (ans.equals("y")) {                         
               System.out.println("\nFirst Passenger in the enter train queue: "+EnterTrainQueue.peek()); //First passenger in the Enter Train Queue   
               System.out.print("Check the ticket is valid or not. If valid, 'Press y' else press other word.");
               String input = in.nextLine().replaceAll("\\s", "").toLowerCase();
                    //If valid ticket, allow into station to get train and remove the passenger from Enter Train Queue
                    if(input.equals("y")){
                        System.out.println("Allow this passenger in.");
                        EnterTrainQueue.dequeue();
                    }
                    //If not valid ticket, not allow into station  and remove the passenger from Enter Train Queue
                    else{
                        System.out.println("Sorry. Your ticket is invalid.");
                        EnterTrainQueue.dequeue();
                    }
                }
                else
                    break;
            }
            
        }
        return EnterTrainQueue;
    }
    
    public static void list() {
        Scanner scan = new Scanner(System.in);
        Linkedlist obj = new Linkedlist();
        int n,num;
        String[] nodelist;
        
        obj.addNewNode("AC");
        obj.addNewNode("Ac-Sleeping");
        obj.addNewNode("Firstclass");
        obj.addNewNode("Secondclass");
        obj.addNewNode("General");

        obj.optionselection();
        num = scan.nextInt();
        while(num != 7) {
            if (num == 1) {

                System.out.println("\nCompartment list before adding : ");
                obj.showData();
                System.out.print("\nEnter the size of the compartment : ");
                n = scan.nextInt();

                nodelist = new String[n];

                //Adding nodes into the doubly linked list
                for (int i=0; i<n; i++){
                    System.out.print("Enter your compartment name " + (i+1) + " : ");
                    nodelist[i] = scan.next();
                }

                for (int i=0; i<n; i++){
                    obj.addNewNodeInBegin(nodelist[i]);
                }

                System.out.println("\nUpdated Compartment list: ");

                //Calling showData() method for displaying doubly linked list data
                obj.showData();

                obj.optionselection();
                num = scan.nextInt();

            }

            else if (num == 2) {

                System.out.println("\nCompartment list before adding : ");

                obj.showData();

                System.out.print("\nEnter the size of the compartment : ");
                n = scan.nextInt();

                nodelist = new String[n];

                //Adding nodes at the end of the list
                for (int i=0; i<n; i++){
                    System.out.print("Enter your compartment name " + (i+1) + " : ");
                    nodelist[i] = scan.next();
                }

                for (int i=0; i<n; i++){
                    obj.addNewNodeAtEnd(nodelist[i]);
                }

                System.out.println("\nUpdated Compartment list: ");

                //Calling showData() method for displaying doubly linked list data
                obj.showData();

                obj.optionselection();
                num = scan.nextInt();

            }

            else if (num == 3) {

                System.out.println("\nCompartment list before adding : ");
                obj.showData();
                System.out.print("\nEnter the size of the compartment : ");
                n = scan.nextInt();

                nodelist = new String[n];

                int[] x = new int[n];
                //Adding nodes at the specified position
                for (int i=0; i<n; i++){
                    System.out.print("Enter your compartment name " + (i+1) + " : ");
                    nodelist[i] = scan.next();
                    System.out.print("Enter your position : ");
                    x[i] = scan.nextInt();
                }
                for (int i=0; i<n; i++){
                    obj.addNodeToSpeciifiedPosition(x[i],nodelist[i]);
                }
                System.out.println("\nUpdated compartment list: ");
                //Calling showData() method for displaying doubly linked list data
                obj.showData();

                obj.optionselection();
                num = scan.nextInt();

            }

            else if (num == 4) {
                System.out.println("\nCompartment list before deleting : ");
                obj.showData();
                //delete from start
                obj.deleteFromStart();

                System.out.println("\nUpdated compartment list: ");

                //Printing updated list
                obj.showData();
                obj.optionselection();
                num = scan.nextInt();
            }
            else if (num == 5) {
                System.out.println("\nCompartment list before deleting : ");
                obj.showData();
                // delete from end
                obj.deleteFromEnd();
                System.out.println("\nUpdated compartment list: ");
                //Printing updated list
                obj.showData();
                obj.optionselection();
                num = scan.nextInt();

            }
            else if (num == 6) {
                System.out.println("\nCompartment list before deleting : ");
                obj.showData();
                //Deleting nodes at the specified position
                System.out.print("\nEnter your position : ");
                int p = scan.nextInt();
                // deleting position
                obj.deleteNodeAtGivenPos(p);
                System.out.println("\nUpdated compartment list: ");
                //Printing updated list
                obj.showData();
                obj.optionselection();
                num = scan.nextInt();
            }
            else {
                System.out.println("\nError!Please enter correct number..\n");
                obj.optionselection();
                num = scan.nextInt();
            }
        }
    }
    
    public static void Wgraph() {
       
        System.out.println("vertex 1 - Kankesanthurai");
        System.out.println("vertex 2 - Vavuniya");
        System.out.println("vertex 3 - Medawachchiya");
        System.out.println("vertex 4 - Maho");
        System.out.println("vertex 5 - Polgahawela");
        System.out.println("vertex 6 - Colombo\n");

        int vertices = 6;
        
        WeightedGraph wgraph = new WeightedGraph();
        WeightedGraph.Graph graph = new WeightedGraph.Graph(vertices);

        int edges = 9;

        graph.addEgde(1 , 2 , 152);
        graph.addEgde(1 , 3 , 178);
        graph.addEgde(2 , 4 , 122);
        graph.addEgde(2 , 3 , 25);
        graph.addEgde(3 , 4 , 98);
        graph.addEgde(4 , 5 , 68);
        graph.addEgde(5 , 1 , 336);
        graph.addEgde(5 , 2 , 182);
        graph.addEgde(5 , 6 , 80);

        graph.printGraph();
    }
    
    public static void stackdetails() { 
      Scanner scan = new Scanner(System.in);
      
      System.out.print("\nEnter your ticket count : ");
        int n= scan.nextInt();
        
        System.out.println(" ");
      
      String[] name = new String[n+1];
      for(int i=1;i<=n;i++){
          System.out.print("Enter your name "+ i +": ");
        name[i]= scan.next();
      }
      
      Stack stack = new Stack(n);
      
      System.out.println(" ");
    
      for(int i=1;i<=n;i++){
        stack.push(name[i]);
      }

    // remove element from stack
    stack.pop();
    System.out.println("\n After cancelling ticket");
    stack.printStack();     
  }
    
}
