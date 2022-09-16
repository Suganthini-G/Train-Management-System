package datastructure;
import java.util.*;
//To get the ticket amount
public class GetTicketAmount {
    
    public static String destination,tclass,name;
    public static int noPassengers, noTickets,noAdults,noNotAdults;
    public static double amount; 
   
    int mainDivIndex;
    double basicTicketAmount=0,totalTicketAmount=0;
    
    //Get data from passengers and return total ticket amount
    public double getData(String destination,int tclass,int noAdults,int noNotAdults){        
        
        basicTicketAmount=findBasicTicketAmount_FromColomboFort(destination,tclass);   //Call function and get basic ticket amount according to the destination and class     
        totalTicketAmount=(noAdults*basicTicketAmount)+(noNotAdults*(basicTicketAmount*0.7)); //Find total ticket amount according to the  basic ticket amount and age
        
        return totalTicketAmount; //return total ticket amount
    }  
    //Find basic ticket amount according to the destination and class   
    public double findBasicTicketAmount_FromColomboFort(String destination,int tclass){
    
    //Ticket fares
    /*
        From Station    To Station              1st Class   2nd Class   3rd Class
      -------------------------------------------------------------------------------
        Colombo Fort    Kurunegala              LKR 1,000   LKR 500     LKR 300
        Colombo Fort    Maho                    LKR 1,200   LKR 600     LKR 400
        Colombo Fort    Anuradhapura            LKR 1,200   LKR 600     LKR 400
        Colombo Fort    Kilinochchi             LKR 1,500   LKR 800     LKR 600
        Colombo Fort    Jaffna/ Kankesanthurai  LKR 1,700   LKR 1,000   LKR 700
    */
        //Divide all stations by same amount 
        String[][]stationsByDivisions={{"maradana","polgahawela","kurunegala"},
            {"maho"},
            {"galgamuwa","tambuttegama","anuradhapuranewtown","anuradhapura"},
            {"medawachchiya","vavuniya","ariviyalnagar","kilinochchi"},
            {"kodikamam","jaffna","chunnakam","kankesanthurai"} };
        
        //The stations of change amount (Main Divisions)
        String [] mainDivisions={"kurunegala","maho","anuradhapura","kilinochchi","jaffna/kankesanthurai"};
        
        //First, second, third classs amount according to the Main Divisions 
        int amountPoints[][] = {{1000,500,300},{1200,600,400},{1200,600,400},{1500,800,600},{1700,1000,700}};
    
        //Get main division's index of passenger's destination
        for (int i = 0 ; i < 5; i++){
            for(int j = 0 ; j < stationsByDivisions[i].length ; j++)
            {
                if (stationsByDivisions[i][j].equals(destination))
                {
                    this.mainDivIndex=i;
                    break;
                }
            }
        } 
        
        //Find basic ticket amount according to the main divisions and class
        basicTicketAmount=amountPoints[mainDivIndex][tclass-1];
        
        return basicTicketAmount; //Return basicTicketAmount
    }
    
}
