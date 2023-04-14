package com.criown.entity;

public class Edge {
    public Node from;      //边的起点
    public Node to;        //边的终点
    public int weight;     //边的权重

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from.id +
                ", to=" + to.id +
                ", weight=" + weight +
                '}';
    }
}
//Node{id=2, dist=2147483647, visited=false, edges=[Edge{from=2, to=0, weight=5010}, Edge{from=2, to=1, weight=1240}, Edge{from=2, to=2, weight=0}, Edge{from=2, to=3, weight=1290}, Edge{from=2, to=4, weight=2263}, Edge{from=2, to=5, weight=3833}, Edge{from=2, to=6, weight=3353}, Edge{from=2, to=7, weight=13034}, Edge{from=2, to=8, weight=7577}, Edge{from=2, to=9, weight=5732}]}
//=========dijkstra==========
//Node{id=2, dist=5010, visited=true,        edges=[Edge{from=2, to=0, weight=5010}, Edge{from=2, to=1, weight=1240}, Edge{from=2, to=2, weight=0}, Edge{from=2, to=3, weight=1290}, Edge{from=2, to=4, weight=2263}, Edge{from=2, to=5, weight=3833}, Edge{from=2, to=6, weight=3353}, Edge{from=2, to=7, weight=13034}, Edge{from=2, to=8, weight=7577}, Edge{from=2, to=9, weight=5732}]}