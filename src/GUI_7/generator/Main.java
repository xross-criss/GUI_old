package generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

class Generator {

    public static void main ( String[] args ) {
        try ( BufferedWriter bw = new BufferedWriter ( new FileWriter ( "Towary.txt", false ) ) ) {
            for ( int i = 0; i < 10000; i++ ) {
                bw.write ( i + " " + ThreadLocalRandom.current ().nextInt ( 0, 1000 ) );
                bw.newLine ();
            }
            bw.flush ();
            bw.close ();
        } catch ( IOException e ) {
            e.printStackTrace ();
        }
    }
}
