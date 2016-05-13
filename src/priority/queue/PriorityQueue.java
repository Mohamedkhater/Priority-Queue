/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priority.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author amr
 */
public class PriorityQueue {

    private ArrayList<Node> heap;
    private HashMap<Integer, Integer> samePriorities;
    private int index;

    public PriorityQueue() {
        heap = new ArrayList<>();
        index = 0;
    }

    public void enqueue(int priority, Object data) {
        Node child = new Node(priority, index++, data);
        heap.add(child);

        if (heap.size() > 1) { // heapify the branch only
            Node parent = heap.get(getParentIndex(heap.size() - 1));

            while (parent.getPriority() < child.getPriority()) {
                Collections.swap(heap, heap.indexOf(parent), heap.indexOf(child));

                int newChildIndex = getParentIndex(heap.indexOf(parent));
                int newParentIndex = getParentIndex(heap.indexOf(child));

                child = heap.get(newChildIndex);
                parent = heap.get(newParentIndex);
            }
        }
    }

    public Object dequeue() {
        if (heap.isEmpty()) {
            return null;
        }

        samePriorities = new HashMap<>();

        int rootPriority = heap.get(0).getPriority();

        for (int i = 1; i < heap.size(); i++) {
            if (rootPriority == heap.get(i).getPriority()) {
                samePriorities.put(heap.get(i).getIndex(), i);
            }
        }

        int minKey = samePriorities.isEmpty() ? -1 : Collections.min(samePriorities.keySet());

        if (minKey != -1) {
            int newIndex = heap.get(samePriorities.get(minKey)).getIndex();
            if (heap.get(0).getIndex() > newIndex) {
                Collections.swap(heap, 0, samePriorities.get(minKey));
            }
        }

        Object data = heap.get(0).getData();

        heap.set(0, heap.get(heap.size() - 1));

        heap.remove(heap.size() - 1);

        if (heap.size() > 1) {
            heapify(0);
        }

        return data;
    }

    private void heapify(int rootIndex) {
        Node rootNode = heap.get(rootIndex);
        int maxChildIndex = getMaxChild(rootIndex);

        if (maxChildIndex == -1) {
            return;
        }

        Node maxChild = heap.get(maxChildIndex);

        if (!isLeaf(rootIndex) && (rootNode.getPriority() < maxChild.getPriority())) {
            Collections.swap(heap, rootIndex, maxChildIndex);
            heapify(maxChildIndex);
        }

    }

    private int getMaxChild(int index) {

        if (getLeftChild(index) >= heap.size()) {
            return -1;
        }
        if (getRightChild(index) >= heap.size()) {
            return getLeftChild(index);
        } else if (heap.get(getLeftChild(index)).getPriority() > heap.get(getRightChild(index)).getPriority()) {
            return getLeftChild(index);
        } else {
            return getRightChild(index);
        }
    }

    private boolean isLeaf(int index) {
        return getLeftChild(index) >= heap.size();
    }

    private int getParentIndex(int childIndex) {
        /*
         * children are at 2*i+1, 2*i+2
         * because of 0 based indexing
         */
        return (childIndex - 1) / 2;
    }

    private int getLeftChild(int parentIndex) {
        return (2 * parentIndex) + 1;
    }

    private int getRightChild(int parentIndex) {
        return (2 * parentIndex) + 2;
    }

    @Override
    public String toString() {
        for (Node node : heap) {
            System.out.println(node.getData());
        }
        return "";
    }

}
