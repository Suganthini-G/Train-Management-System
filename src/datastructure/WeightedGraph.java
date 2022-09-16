package datastructure;
import java.util.LinkedList;
import java.util.Scanner;

public class WeightedGraph {
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    static class Graph {
        int vertices;
        LinkedList<Edge> [] adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i <vertices ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEgde(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist[source].addFirst(edge); //for directed graph
        }

        public void printGraph(){
            for (int i = 0; i <vertices ; i++) {
                LinkedList<Edge> list = adjacencylist[i];
                for (int j = 0; j <list.size() ; j++) {
                    System.out.println("vertex-" + i + " is connected to " +
                            list.get(j).destination + " with weight " +  list.get(j).weight);
                }
            }
        }
    }
}        
        /*graph.addEgde(Kankesanthurai - 1 , Vavuniya - 2 , 152);
        graph.addEgde(Kankesanthurai - 1 , Medawachchiya- 3 , 178);
        graph.addEgde(Vavuniya - 2 , Maho - 4 , 122);
        graph.addEgde(Vavuniya - 2 , Medawachchiya - 3 , 25);
        graph.addEgde(Medawachchiya - 3 , Maho- 4 , 98);
        graph.addEgde(Maho - 4 , Polgahawela- 5 , 68);
        graph.addEgde(Polgahawela - 5 , Kankesanthurai - 1 , 336);
        graph.addEgde(Polgahawela - 5 , Vavuniya - 2 , 182);
        graph.addEgde(Polgahawela - 5 , Colombo - 6 , 80);*/