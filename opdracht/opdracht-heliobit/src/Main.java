import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    // Lijst met de taken en respectievelijke tijden. De parameter is voor het aantal taken die gegenereerd moeten worden.
    static Queue<Taak> taskList = getTaskList(5);
    // Lijst waar de taken op de nieuwe volgorde in worden gestopt.
    static Queue<Taak> processedTaskList = new LinkedList<>();

    // Maakt de pool en en start de threads. de eerste in de queue wordt steeds geselecteerd, en daar wordt een thread op losgelaten. vervolgens wordt deze taak verwijderd uit de lijst.
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        int counter = taskList.size();
        for (int i = 0; i < counter; i++) {
            if(taskList.peek() != null) {
                Taak currentTask = taskList.peek();
                Runnable worker = new WorkerThread(currentTask.getNaam() + ": ", currentTask);
                executor.execute(worker);
                taskList.remove();
            }
        }
        executor.shutdown();
        while(!executor.isTerminated()){

        }
        System.out.println("Klaar met alle taken!");
        System.out.println("----------------------------------------");
        System.out.println("Nieuwe lijst: ");

        // print de nieuwe gevulde queue in de juiste volgorde
        for(Taak taak: processedTaskList){
            System.out.println(taak.getNaam() + " " + taak.getTijdInMs() + "ms");
        }

    }

    // Maakt een nieuwe queue aan en vult deze met de taak objecten
    public static Queue<Taak> getTaskList(int amountOfTasks){
        Queue<Taak> tasklist = new LinkedList<>();

        for (int i = 1; i <= amountOfTasks; i++) {
            int timeInMs = generateTimeInMs();
            Taak taak = new Taak("Taak " + i, timeInMs);
            tasklist.add(taak);
        }
        return tasklist;
    }

    // Voegt de geprocesde taak toe aan de nieuwe queue
    public static void updateProcessedTaskList(Taak task){
        processedTaskList.add(task);
    }

    // genereert random getallen tussen de 100 en 1000 voor de tijd voor de taken
    public static int generateTimeInMs(){
        Random r = new Random();
        int low = 100;
        int high = 900;
        return r.nextInt(high-low) + low;
    }
}
