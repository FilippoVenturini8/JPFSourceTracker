package utils;

public class SetupInfo{
    private String startDir;
    private int nFiles;
    private int intervals;
    private int lastInterval;

    public SetupInfo(String startDir, int nFiles, int intervals, int lastInterval) {
        this.startDir = startDir;
        this.nFiles = nFiles;
        this.intervals = intervals;
        this.lastInterval = lastInterval;
    }

    public String startDir() {
        return startDir;
    }

    public int nFiles() {
        return nFiles;
    }

    public int intervals() {
        return intervals;
    }

    public int lastInterval() {
        return lastInterval;
    }
}
