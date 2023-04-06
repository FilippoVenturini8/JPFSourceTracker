package utils;

public class Result implements Comparable<Result>{
    private int lines;
    private String filePath;

    public Result(String filePath, int lines){
        this.filePath = filePath;
        this.lines = lines;
    }

    public int lines(){
        return this.lines;
    }

    public String filePath(){
        return this.filePath;
    }

    @Override
    public int compareTo(Result o) {
        return Integer.compare(o.lines, this.lines);
    }

    @Override
    public String toString() {
        return  "Lines : " + lines +
                " <= " + filePath;
    }
}
