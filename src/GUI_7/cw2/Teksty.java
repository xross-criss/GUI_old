package cw2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Teksty {

    Lock lock = new ReentrantLock ();
    Condition txtWritten = lock.newCondition();
    Condition txtSupplied = lock.newCondition();

    String txt = null;
    boolean newTxt = false;

    void setTextToWrite(String s) {
        lock.lock();
        try {
            if (txt != null) {
                while (newTxt == true)
                    txtWritten.await();
            }
            txt = s;
            newTxt = true;
            txtSupplied.signal();
        } catch (InterruptedException exc) {
        } finally {
            lock.unlock();
        }
    }


    String getTextToWrite() {
        lock.lock();
        try {
            while (newTxt == false)
                txtSupplied.await();
            newTxt = false;
            txtWritten.signal();
            return txt;
        } catch (InterruptedException exc) {
            return null;
        } finally {
            lock.unlock();
        }
    }


}