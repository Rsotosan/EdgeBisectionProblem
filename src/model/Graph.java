package model;

import java.io.*;
import java.util.ArrayList;

public class Graph {
    private int[][] adjMatrix;
    private ArrayList<Integer> adjList[];

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
        this.adjMatrix = new int[nVertex][nVertex];
        this.adjList = new ArrayList[nVertex];
        for(int i=0; i<nEdges; i++){
            line = reader.readLine();
            infoLine = line.split(" ");
            this.newEdge(Integer.valueOf(infoLine[0])-1, Integer.valueOf(infoLine[1])-1);
        }
    }

    private void newEdge(int v1, int v2){
        if(this.adjList[v1] == null){
            this.adjList[v1] = new ArrayList<Integer>();
        }
        this.adjList[v1].add(v2);
        this.adjMatrix[v1][v2] = 1;
    }

    public int[][] getAdjMatrix(){
        return this.adjMatrix;
    }

    public ArrayList<Integer>[] getAdjList(){
        return this.adjList;
    }


    public boolean adjacent(int v1, int v2){
        return(getAdjMatrix()[v1][v2]==1);
    }

    public int order(){
        return adjMatrix.length;
    }

    public ArrayList<Integer> neighbors(int v) {
        return (adjList[v]==null)? new ArrayList<Integer>() : adjList[v];
    }
}
