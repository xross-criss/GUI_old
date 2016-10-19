package cw1;

import java.io.*;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {

    public static void main ( String[] args ) {
        Container c = new Container ();
        Producer p1 = new Producer ( c );
        Consumer c1 = new Consumer ( c );
        p1.start ();
        c1.start ();
    }

}

class Container {

    public BlockingQueue < Item > content;
    public boolean isEnd;

    public Container () {
        content = new LinkedBlockingDeque <> ();
        isEnd = false;
    }

    public synchronized Queue < Item > content () {
        return content;
    }

    public synchronized boolean isEnd () {
        return isEnd;
    }

    public synchronized void setEnd ( boolean end ) {
        isEnd = end;
    }

}

class Consumer extends Thread {

    public Container container;
    public int size;
    public int count;

    public Consumer ( Container container ) {
        this.container = container;
        this.count = 0;
        this.size = 0;
    }

    public void run () {
        while ( !container.isEnd () || !container.content ().isEmpty () ) {
            synchronized ( container.content () ) {
                if ( !container.content ().isEmpty () ) {
                    size += container.content ().poll ().weight;
                    count++;
                    if ( count % 100 == 0 ) {
                        System.out.println ( "policzono wage " + count + " towarów");
                    }
                }
            }
        }
        System.out.println ( size );
    }
}

class Producer extends Thread {

    public Container container;
    public int count;

    public Producer ( Container container ) {
        this.container = container;
        this.count = 0;
    }

    public void run () {
        try ( BufferedReader br = new BufferedReader ( new FileReader ( "towary.txt" ) ) ) {
            String line;
            while ( ( line = br.readLine () ) != null ) {
                String[] s = line.split ( "\\s" );
                Item item = new Item ( Integer.valueOf ( s[ 0 ] ), Integer.valueOf ( s[ 1 ] ) );
                container.content ().add ( item );
                count++;
                if ( count % 200 == 0 ) {
                    System.out.println ( "utworzono " + count + " obiektów" );
                }
            }
            container.setEnd ( true );
            br.close ();
        } catch ( IOException e ) {
            e.printStackTrace ();
        }
/*        System.out.println ( "Producer putted " + count + " elements." );
        container.setEnd ( true );*/
    }

}

class Item {

    public int id;
    public int weight;

    public Item ( int id, int weight ) {
        this.id = id;
        this.weight = weight;
    }

    @Override
    public String toString () {
        return "ITEM(" + id + "," + weight + ")";
    }

}