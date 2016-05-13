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
 * @param <T> Type of data in node
 */
public class PriorityQueue<T extends Object> {

    private ArrayList<Node<T>> heap;
    private HashMap<Integer, Integer> samePriorities;
    private int index;

    public PriorityQueue() {
        heap = new ArrayList<>();
        index = 0;
    }

    /**
     * Add a node to the queue
     *
     * @param priority Priority of input node
     * @param data Data of input node
     */
    public void enqueue(int priority, T data) {
        Node child = new Node(priority, index++, data);
        heap.add(child);

        if (heap.size() > 1) {
            Node parent = heap.get(getParentIndex(heap.size() - 1));

            while (parent.getPriority() < child.getPriority()) { // heapify the branch only
                Collections.swap(heap, heap.indexOf(parent), heap.indexOf(child));

                int newChildIndex = getParentIndex(heap.indexOf(parent));
                int newParentIndex = getParentIndex(heap.indexOf(child));

                child = heap.get(newChildIndex);
                parent = heap.get(newParentIndex);
            }
        }
    }

    /**
     * Remove a node from the queue
     *
     * @return Data of output node
     */
    public T dequeue() {
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
            int newRootIndex = heap.get(samePriorities.get(minKey)).getIndex();

            if (heap.get(0).getIndex() > newRootIndex) {
                Collections.swap(heap, 0, samePriorities.get(minKey));
            }
        }

        Object data = heap.get(0).getData();

        heap.set(0, heap.get(heap.size() - 1));

        heap.remove(heap.size() - 1);

        if (heap.size() > 1) {
            heapify(0);
        }

        return (T) data;
    }

    /**
     * Applies the heap algorithm to build a maximum heap
     *
     * @param rootIndex Index to start heap algorithm from
     */
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

    /**
     * Get the child node with maximum priority
     *
     * @param index Index of parent node
     * @return Index of child with maximum priority
     */
    private int getMaxChild(int index) {
        int leftChildIndex = getLeftChild(index);
        int rightChildIndex = getRightChild(index);

        if (leftChildIndex >= heap.size()) {
            return -1;
        }
        if (rightChildIndex >= heap.size()) {
            return leftChildIndex;
        } else if (heap.get(leftChildIndex).getPriority() > heap.get(rightChildIndex).getPriority()) {
            return leftChildIndex;
        } else {
            return rightChildIndex;
        }
    }

    /**
     * Checks if a node is a leaf or not
     *
     * @param index Index of node
     * @return True if node is a leaf, False otherwise
     */
    private boolean isLeaf(int index) {
        return getLeftChild(index) >= heap.size();
    }

    /**
     * Gets the index of a parent node
     *
     * @param childIndex Index of node
     * @return Index of parent node
     */
    private int getParentIndex(int childIndex) {
        /*
         * children are at 2*i+1, 2*i+2
         * because of 0 based indexing
         */
        return (childIndex - 1) / 2;
    }

    /**
     * Gets the index of the left child
     *
     * @param parentIndex Index of parent node
     * @return Index of left child node
     */
    private int getLeftChild(int parentIndex) {
        return (2 * parentIndex) + 1;
    }

    /**
     *
     * @param parentIndex Index of parent node
     * @return Index of left child node
     */
    private int getRightChild(int parentIndex) {
        return (2 * parentIndex) + 2;
    }

    @Override
    public String toString() {
        for (Node<T> node : heap) {
            System.out.println(node.getData());
        }
        return "";
    }

}
