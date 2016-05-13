/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priority.queue;

/**
 *
 * @author amr
 */
public class PriorityQueueDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PriorityQueue pq = new PriorityQueue();

        /*for (int i = 1; i <= 10; i++) {
         pq.enqueue((int) (Math.random() * 10), ("Node " + i));
         }*/
        pq.enqueue(3, "Node 1");
        pq.enqueue(2, "Node 2");
        pq.enqueue(2, "Node 3");
        pq.enqueue(2, "Node 4");
        pq.enqueue(2, "Node 5");
        pq.enqueue(2, "Node 6");
        pq.enqueue(2, "Node 7");
        pq.enqueue(1, "Node 8");
        
        Object obj;
        while ((obj = pq.dequeue()) != null) {
            System.out.println(obj);
        }

    }

}
