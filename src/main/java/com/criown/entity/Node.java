package com.criown.entity;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node>{
    public int id;             //节点id
    public List<Edge> edges;   //节点的所有边
    public int dist;           //起点到该节点的最短距离
    public Node prev;          //最短路径中该节点的前驱节点
    public boolean visited;     //是否已被访问


    public Node(int id) {
        this.id = id;
        edges = new ArrayList<>();
        dist = Integer.MAX_VALUE;
        prev = null;
        visited = false;
    }
    public void addEdge(Node to, int weight) {
        Edge edge = new Edge(this, to, weight);
        edges.add(edge);
        to.edges.add(new Edge(to, this, weight));
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(dist, other.dist);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
              //  ", dist=" + dist +
               // ", prev=" + prev +
               // ", visited=" + visited +
              //  ", edges=" + edges.toString() +
                '}';
    }

    public static Edge findEdge(Node from, Node to){
        for(Edge e:from.edges)
        {
            if(e.to==to)
            {
                return e;
            }

        }
        return null;
    }
}
