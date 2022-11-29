package git.group.List;

import git.group.Builder.Builder;
import git.group.Comparator.Comparator;

import java.io.Serializable;
import java.util.ArrayList;


public class TList implements Serializable
{
    private class Node implements Serializable
    {
        public Node   next;
        public Object data;


        public Node(Object data)
        {
            this.data = data;
            this.next = null;
        }
    }

    private Node       head;
    private Node       tail;
    private int        size;
    private Builder    builder;
    private Comparator comparator;

    public TList(Builder builder)
    {
        this.builder    = builder;
        this.comparator = builder.getComparator();
        this.head       = null;
        this.tail       = null;
        this.size       = 0;
    }
    public TList(Builder[] arr) {
        for (int i=0; i<arr.length; i++)
        {
            pushEnd(arr[i]);
        }
    }
    public boolean clear()
    {
        if (head == null)
        {
            return false;
        }

        while (head != null)
        {
            delete(0);
        }

        return true;
    }

    public boolean pushFront(Object obj)
    {

        Node nNode = new Node(obj);

        if (head == null)
        {
            head = nNode;
            tail = nNode;
        }
        else
        {
            Node temp = head;
            head      = nNode;
            head.next = temp;
        }
        size++;
        return true;
    }

    public boolean pushEnd(Object data)
    {
        Node nNode = new Node(data);

        if (head == null)
        {
            head = nNode;
            tail = nNode;
        }
        else
        {
            tail.next = nNode;
            tail      = tail.next;
        }
        size++;
        return true;
    }

    private void pushEnd(TList toInsert)
    {
        if (toInsert != null)
        {
            tail.next = toInsert.head;
            tail      = toInsert.tail;
            size += toInsert.size;
        }
    }

    public boolean add(Object data, int index)
    {
        Node nNode = new Node(data);

        if (head == null)
        {
            head = nNode;
            tail = nNode;
        }
        else
        {
            Node temp, current;
            temp    = head;
            current = null;

            for (int i = 0; i < index; i++)
            {
                current = temp;
                temp    = temp.next;
            }

            current.next = nNode;
            nNode.next   = temp;
        }
        size++;
        return true;
    }

    public boolean delete(int index)
    {
        if (size < 0 || index < 0)
        {
            return false;
        }

        Node toDel, toDelPrev = null;

        if (head == null)
        {
            System.out.println("Список пуст");
            return false;
        }
        else
        {
            if (head != tail)
            {

                if (index > 0)
                {
                    toDelPrev = findNode(index - 1);
                    toDel     = toDelPrev.next;
                }
                else
                {
                    toDel = head;
                }

                if (toDelPrev != null)
                {
                    toDelPrev.next = toDel.next;
                    toDel          = null;
                }

                else
                {
                    head  = toDel.next;
                    toDel = null;
                }
            }
            else
            {
                head = tail = null;
            }
        }
        size--;
        return true;
    }

    public Object find(int index)
    {
        Object dataNode;
        Node   current = head;

        if (index == 0)
        {
            dataNode = current.data;
            return dataNode;
        }

        for (int i = 0; i < index; i++)
        {
            current = current.next;
        }
        dataNode = current.data;
        return dataNode;
    }


    public void forEach(DoIt func)
    {
        ArrayList arr = new ArrayList();

        for (Node cur = head; cur != null; cur = cur.next)
            arr.add(cur.data);

        for (int i=0; i<arr.size();i++) {
            String str;
            if (arr.get(i) == null) str = "null ";
            else str = arr.get(i).toString();
            func.doIt(str);
        }

    }

    public TList sort()
    {
        TList r = quicksort(this);
        return r;
    }


    private TList quicksort(TList list)
    {
        if (list == null)
        {
            return list;
        }
        Node head_ = list.head;
        Node it    = head_.next;
        if (it == null)
        {
            return list;
        }
        TList lesser  = null;
        TList greater = null;
        while (it != null)
        {
            int comp = comparator.compare(it.data, head_.data);
            if (comp < 0 || comp == 0)
            {
                if (lesser == null)
                {
                    lesser = new TList(builder);
                }
                lesser.pushEnd(it.data);
            }
            else
            {
                if (greater == null)
                {
                    greater = new TList(builder);
                }
                greater.pushEnd(it.data);
            }
            it = it.next;
        }

        lesser  = quicksort(lesser);
        greater = quicksort(greater);

        TList buf = new TList(builder);
        buf.pushEnd(head_.data);
        buf.pushEnd(greater);
        if (lesser == null)
        {
            return buf;
        }
        lesser.pushEnd(buf);
        return lesser;
    }


    private Node findNode(int id)
    {
        Node res = head;
        for (int i = 0; i < id; i++)
        {
            res = res.next;
        }
        return res;
    }



    public Builder getBuilder()
    {
        return builder;
    }

    public String toString() {
        Node cur = head;
        String str ="";
        for (int i = 0; i < size; i++) {
            str += (cur.data.toString());
            str+="\n";
            cur = cur.next;
        }
        return str;
    }

}
