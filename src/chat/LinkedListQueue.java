package chat;

public class LinkedListQueue<T> implements Queue<T> {

    private Node<T> front;
    private Node<T> back;
    int size;

    public LinkedListQueue() {
        front = null;
        back = null;
        size = 0;
    }

    @Override
    public void enqueue(T value) {
        Node<T> temp = new Node(value);
        if (size == 0) {
            front = temp;
            back = temp;
            front.setLink(null);
            back.setLink(null);
        } else {
            back.setLink(temp);
            back = temp;
            back.setLink(null);
        }
        size++;
    }

    @Override
    public T dequeue() throws Exception {
        if (size == 0) {
            throw new Exception("Nothing to dequeue");
        } else {

            T result = front.getValue();

            if (size == 1) {
                front = null;
                back = null;
                size--;
            } else {
                front = front.getLink();
                size--;
            }
            return result;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        front =null;
        back = null;
        size=0;
    }
    
    @Override
    public String toString() {
        String result = "Items in the queue: \n";
        Node<T> temp = front;
        while(temp  != null){
            result += temp.getValue() + " ";
            temp = temp.getLink();
        }
        return result;
    }
}
