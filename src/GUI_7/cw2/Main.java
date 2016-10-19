package cw2;

import java.util.concurrent.locks.*;

public class Main {

    public static void main ( String[] args ) {
        Teksty t = new Teksty ();
        Thread t1 = new Author ( t );
        Thread t2 = new Writer ( t );
        t1.start ();
        try {
            Thread.sleep ( 3000 );
        } catch ( Exception exc ) {
        }
        t2.start ();
    }

}



