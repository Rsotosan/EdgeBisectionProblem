package model;

import java.io.*;

public class Graph {
    private int[][] matrix;

    public void readGraph(File file) throws IOException {
        String line;
        String[] infoLine;
        int nVertex;
        int nEdges;
        BufferedReader reader = new BufferedReader(new FileReader(file));

        reader.readLine();
        line = reader.readLine();
        infoLine = line.split(" ");
        nVertex = Integer.valueOf(infoLine[1]);
        nEdges = Integer.valueOf(infoLine[2]);
        this.matrix = new int[nVertex][nVertex];
        for(int i=0; i<nEdges; i++){
            line = reader.readLine();
            infoLine = line.split(" ");
            this.newEdge(Integer.valueOf(infoLine[0])-1, Integer.valueOf(infoLine[1])-1);
        }
    }

    private void newEdge(int v1, int v2){
        this.matrix[v1][v2] = 1;
    }

    public int[][] getMatrix(){
        return this.matrix;
    }
}
