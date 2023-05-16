public class WorkerThread implements Runnable{

    private final String command;
    private final Taak currentTask;


    public WorkerThread(String command, Taak currentTask){
        this.command = command;
        this.currentTask = currentTask;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start -> " + command + "Tijd: " + this.currentTask.getTijdInMs() + "ms");
        processCommand();
        Main.updateProcessedTaskList(this.currentTask);
        System.out.println(Thread.currentThread().getName() + "-> klaar met taak");
    }

    public void processCommand(){
        try{
            Thread.sleep(this.currentTask.getTijdInMs());
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return this.command;
    }
}
