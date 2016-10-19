public class StringTask implements Runnable {

    private volatile TaskState state;
    private Thread thread;
    private String result;
    private String taskName;
    private int number;

    public StringTask (String taskName, int number) {
        this.taskName = taskName;
        this.number = number;
        this.state = TaskState.CREATED;
        this.result = "";
    }


    /**
     * Obligatory
     *
     * @return result of task
     */
    public String getResult () {  //- zwracającą wynik konkatenacji
        return result;
    }

    /**
     * Returns state of current task
     *
     * @return current task state
     */
    public TaskState getState () { //- zwracającą stan zadania
        return state;
    }

    /**
     * Runs a task in external thread
     */
    public void start () {
        if (thread == null) {
            thread = new Thread(this, taskName);
            thread.start();
        }
    }


    /**
     * Obligatory
     */
    public void abort () {  //- przerywającą wykonanie kodzu zadania i działanie watku
        state = TaskState.ABORTED;
        thread.interrupt();
    }

    /**
     * Obligatory
     *
     * @return isDOne
     */
    public boolean isDone () { //- zwracająca true, jeśli wykonanie zadania się zakończyło normalnie lub przez przerwanie, false w przeciwnym razie
        return state == TaskState.READY || state == TaskState.ABORTED;
    }


    @Override
    public void run () {
        state = TaskState.RUNNING;
        for (int i = 0; i < number && state == TaskState.RUNNING && !thread.isInterrupted(); i++) {
            result += taskName;
        }
        if (state != TaskState.ABORTED) {
            state = TaskState.READY;
        }
    }

}