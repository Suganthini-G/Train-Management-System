package datastructure;
import java.util.LinkedList;
public class CheckStations {
    //To check whether the passenger's destination is in stations or not
    public boolean checkDestination(String destination) {
        //Create linkedlist to store all the stations
        LinkedList<String> stations=new LinkedList<>();
       
        stations.addFirst("kankesanthurai");
        stations.add("chunnakam");
        stations.add("jaffna");
        stations.add("kodikamam");
        stations.add("kilinochchi");
        stations.add("ariviyalnagar");
        stations.add("vavuniya");
        stations.add("medawachchiya");
        stations.add("anuradhapura");
        stations.add("anuradhapuranewtown");
        stations.add("tambuttegama");
        stations.add("galgamuwa");
        stations.add("maho");
        stations.add("kurunegala");
        stations.add("polgahawela");
        stations.add("maradana");        
        stations.addLast("colombo");
        //System.out.println("All Stations "+stations);    
        //Find index of destination in the stations linkedList
        int indexDest=stations.indexOf(destination);
        //If destination is in stations linkedList Display message and return true
        if (indexDest!=-1){
            System.out.println("--Valid Destination--\n");
            return true;
        }
        //If destination is not in stations linkedList Display message and return false
        else{
            System.out.println("***Sorry. This train cannot go to your destination***\n");
            return false;
        }
    }    
}
