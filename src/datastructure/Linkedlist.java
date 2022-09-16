package datastructure;
import java.util.Scanner;
public class Linkedlist {
    //Create a node for doubly linked list
    class Node{
        String data;
        Node prev;
        Node next;

        public Node(String data) {
            this.data = data;
        }
    }
    public int size = 0;
    //Initialize head and tail for the doubly linked list
    Node head = null;
    Node tail = null;
    //Create addNewNode() method to add a node into a list
    public void addNewNode(String data) {

        //Create node
        Node newNode = new Node(data);

        //Check whether our doubly linked list is empty or not
        if(head == null) {
            //The newNode is pointed by both head or tail
            head = newNode;
            tail = newNode;
            //It is first node so prev will point to null
            head.prev = null;
            //It is also last node so tail's next will point to null
            tail.next = null;
        }
        //Execute when the doubly linked list is not empty
        else {

            //The newly created node will be the last node, so now tail's next will point to that newly created node
            tail.next = newNode;
            //The tail is pointing to the second last node so the newly created node's prev will point to tail
            newNode.prev = tail;
            //The newly created node will become new tail because it is last node in the doubly linked list
            tail = newNode;
            //The newly created node will be the last node so tail's next will be null
            tail.next = null;
        }
    }

    public void addNewNodeInBegin(String data) {
        //Creating node
        Node newNode = new Node(data);

        //Checking whether the list is empty or not
        if(head == null)
        {
            //The newNode is pointed by both head or tail
            head = newNode;
            tail = newNode;
            //It is first node so prev will point to null
            head.prev = null;
            //It is also last node so tail's next will point to null
            tail.next = null;
        }
        ////Execute when the list is not empty
        else {
            //The head's prev will point to the newNode
            head.prev = newNode;
            //The newNode's next will point to the head
            newNode.next = head;
            //The newNode's prev will point to null because it will be the first node
            newNode.prev = null;
            //The newNode will become new head because now the newly created node is the first node of the list
            head = newNode;
        }
    }

    //Create firstNode() method for creating first node in the list
    public void firstNode(Node node){
        //The node will be pointed by both head and tail
        head = node;
        tail = node;
        //It is first node so prev will point to null
        head.prev = null;
        //It is also last node so tail's next will point to null
        tail.next = null;
    }

    //Create lastNode() method for adding node at last in the list
    public void lastNode(Node node){
        //The tail's next will point to that node
        tail.next = node;
        //The tail is pointing to the second last node so the newly created node's prev will point to tail
        node.prev = tail;
        //The newly created node will become new tail because it is last node in the doubly linked list
        tail = node;
        //The newly created node will be the last node so tail's next will be null
        tail.next = null;
    }

    //Create addNewNodeAtEnd() method to add a node at last in the list
    public void addNewNodeAtEnd(String data) {
        //Creating new node
        Node newNode = new Node(data);

        //Check whether the list is empty or not
        if(head == null)
        {
            //Call firstNode() method to make it first node in the list
            firstNode(newNode);

        }
        ////Execute when the list will not be empty
        else {
            //The newly created node will be the last node, so now tail's next will point to that newly created node
            tail.next = newNode;
            //The tail will point to the second last node so the newly created node's prev will point to tail
            newNode.prev = tail;
            //The newly created node will become new tail because it is last node in the list
            tail = newNode;
            //The newly created node will be the last node so tail's next will be null
            tail.next = null;
        }
    }

    //Create addNodeInBeginning() method for adding node at first position
    public void addNodeInBeginning(Node node){
        //The head's prev will point to the newNode
        head.prev = node;
        //The newNode's next will point to the head
        node.next = head;
        //The newNode's prev will point to null because it will be the first node
        node.prev = null;
        //The newNode will become new head because now the newly created node is the first node of the list
        head = node;
    }

    public void addNodeToSpeciifiedPosition(int position, String data) {
        //Creating node
        Node newNode = new Node(data);

        //Checking whether our doubly linked list is empty or not
        //if(head == null) {
        if(head == null) {
            System.out.println("The specified location is not available");
        }
        //Execute when the list is not empty
        else {
            if(position == size+1){

                //Call lastNode() method to add the node at last in the list
                lastNode(newNode);

            }else if(position == 1){

                addNodeInBeginning(newNode);

            }else{
                //current node will point to head
                Node current = head;
                Node temp = null;

                //Iterate list till current points to the specified position
                for(int i = 1; i < position-1; i++){
                    current = current.next;
                }

                //The temp node points to the node that is next to current
                temp = current.next;
                temp.prev = current;

                //newNode will be added between current and temp
                current.next = newNode;
                newNode.prev = current;
                newNode.next = temp;
                temp.prev = newNode;
            }
            size++;
        }
    }

    //deleteFromStart() will delete a node from the beginning of the list
    public void deleteFromStart() {
        //Checks whether list is empty
        if(head == null) {
            return;
        }
        else {
            //Checks whether the list contains only one element
            if(head != tail) {
                //head will point to next node in the list
                head = head.next;
                //Previous node to current head will be made null
                head.prev = null;
            }
            //If the list contains only one element
            //then, it will remove node and now both head and tail will point to null
            else {
                head = tail = null;
            }
        }
    }

    //deleteFromEnd() will delete a node from the end of the list
    public void deleteFromEnd() {
        //Checks whether list is empty
        if(head == null) {
            return;
        }
        else {
            //Checks whether the list contains only one node
            if(head != tail) {
                //Previous node to the tail will become new tail
                tail = tail.prev;
                //Node next to current tail will be made null
                tail.next = null;
            }
            //If the list contains only one element
            //Then it will remove node and now both head and tail will point to null
            else {
                head = tail = null;
            }
        }
    }

    public Node deleteNode(Node del)
    {
        // base case
        if (head == null || del == null)
            return null;

        // If node to be deleted is head node
        if (head == del)
            head = del.next;

        // Change next only if node to be
        // deleted is NOT the last node
        if (del.next != null)
            del.next.prev = del.prev;

        // Change prev only if node to be
        // deleted is NOT the first node
        if (del.prev != null)
            del.prev.next = del.next;

        del = null;

        return head;
    }

    //deleteNodeAtGivenPos() will delete a node from the end of the list
    public void deleteNodeAtGivenPos(int position)
    {
        /* if list in NULL or
          invalid position is given */
        if (head == null || position <= 0)
            return;

        Node current = head;
        int i;

        /*
        * traverse up to the node at
          position 'n' from the beginning
        */
        for (i = 1; current != null && i < position; i++)
        {
            current = current.next;
        }

        // if 'n' is greater than the number of nodes
        // in the doubly linked list
        if (current == null)
            return;

        // delete the node pointed to by 'current'
        deleteNode(current);
    }


    //Create showData() method for displaying data of doubly linked list
    public void showData() {
        //intialize a new node current that will point to head
        Node current = head;
        //Check whether the doubly linked list is empty or not
        if(head == null) {
            //Print a statement and pass the control flow into the main() method
            System.out.println("List is empty");
            return;
        }
        //Print a statement
        //System.out.println("\nNodes of doubly linked list: ");
        //Iterate the doubly linked list using while
        while(current != null) {
            //Print tha data on that particular node and then increment the pointer for indicating next node
            System.out.print(current.data + "\n");
            current = current.next;
        }
    }

    public void optionselection(){
        System.out.println("\n-------------------------------------------------------------------------------------------");
        System.out.println("1. Add a new compartment in beginning to the link list");
        System.out.println("2. Add a new compartment in ending to the link list");
        System.out.println("3. Add a new compartment in specific position to the link list");
        System.out.println("4. Delete a beginning compartment from the link list");
        System.out.println("5. Delete a end compartment from the link list");
        System.out.println("6. Delete a compartment in a specific position from the link list");
        System.out.println("7. Exit");
        System.out.println("-------------------------------------------------------------------------------------------");

        System.out.println("\nEnter your choice : ");

    }
}

