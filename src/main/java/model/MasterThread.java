package model;

import controller.Controller;
import utils.Flag;
import utils.Result;
import utils.SynchronizedQueue;
import utils.SynchronizedQueueImpl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class MasterThread extends Thread{
    public static final int N_WORKERS = 2;
    private static final int NF = 5;
    private final Controller controller;
    private final int nWorkers;
    private final SynchronizedQueue<String> files = new SynchronizedQueueImpl<>();
    private final SynchronizedQueue<Result> results = new SynchronizedQueueImpl<>();

    public MasterThread(Controller controller, int nWorkers) {
        this.controller = controller;
        this.nWorkers = nWorkers;
    }

    @Override
    public void run() {
        this.searchFiles();
        final Flag stopExecutionFlag = this.controller.getStopExecutionFlag();

        for(int i = 0; i < this.nWorkers; i++){
            new WorkerThread(this.files, this.results, stopExecutionFlag).start();
        }

        int nOfFiles = this.files.size();
        for (int i = 0; i < nOfFiles && !stopExecutionFlag.get(); i++){
            try {
                final Result result = this.results.blockingRemove();
                this.controller.getResults().add(result);
                this.controller.notifyObservers(ModelObserver.Event.RESULT_UPDATED);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.controller.notifyObservers(ModelObserver.Event.COMPUTATION_ENDED);
    }

    private void searchFiles(){
        for(int i = 0; i < NF; i++){
            this.files.add("file"+i);
        }
    }
}
