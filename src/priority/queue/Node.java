/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priority.queue;

/**
 *
 * @author amr
 * @param <T>
 */
public class Node<T extends Object> {

    private int priority;
    private int index;
    private T data;

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    public Node(int priority, int index) {
        this.priority = priority;
        this.index = index;
        this.data = null;
    }

    public Node(int priority, int index, T data) {
        this.priority = priority;
        this.index = index;
        this.data = data;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

}
