package com.criown.utils;

import com.criown.entity.Edge;
import com.criown.entity.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    public static void dijkstra(Node start) {
        start.dist = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(start);
        while (!queue.isEmpty())
        {
            Node current = queue.poll();
            if (current.visited) //判断是否已经被访问
            {
                continue;
            }
            current.visited = true;//标记为已被访问
            for (Edge edge : current.edges)
            {//查询所有边
                Node next = edge.to;
                int newDist = current.dist + edge.weight;
                if (current == edge.to && newDist < edge.from.dist)
                {
                    queue.remove(edge.from);
                    edge.from.dist = newDist;
                    edge.from.prev = current;
                    queue.offer(edge.from);
                }
                else if (newDist < next.dist) {
                    queue.remove(next);
                    next.dist = newDist;
                    next.prev = current;
                    queue.offer(next);
                }
            }
        }
    }

    public static List<Node> getPath(Node end) {
        List<Node> path = new ArrayList<>();
        for (Node node = end; node != null; node = node.prev) {
            path.add(0, node);
            System.out.print(node.id+"->");
        }
        System.out.println();
        System.out.println(path);
        return path;
    }
}
