package com.criown.utils;



import com.criown.entity.Edge;
import com.criown.entity.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxTsp{

    public int cityNum; // 城市数量
    public int[][] distance; // 距离矩阵

    public int[] walked;//表示是否走过，走过置0
    public int[] choosed;//代表选过置0
    public List tspPath =new ArrayList<>();
    public Redisutils redisutils =new Redisutils();
    public TxTsp(int n) {
        cityNum = n;
    }

    //初始化 初始化邻接矩阵
    public void init(String filename) throws IOException {
        // 读取数据
        int[] x;// x坐标
        int[] y;// y坐标
        String strbuff;
        //System.out.println("test::"+filename);
        InputStream path = this.getClass().getResourceAsStream(filename);
        BufferedReader data = new BufferedReader(new InputStreamReader(path));

        distance = new int[cityNum][cityNum];
        x = new int[cityNum];
        y = new int[cityNum];
        for (int i = 0; i < cityNum; i++) {
            // 数据格式  1 6734 1453
            strbuff = data.readLine();//缓冲区 读一行
            // 字符分割
           // System.out.println("test2::"+strbuff);
            String[] strcol = strbuff.split(" ");
            x[i] = Integer.valueOf(strcol[1]);
            y[i] = Integer.valueOf(strcol[2]);
        }
        data.close();
        // 计算初步邻接矩阵
        //最优值为10628
        for (int i = 0; i < cityNum - 1; i++)
        {
            distance[i][i] = 0; // 对角线为0
            for (int j = i + 1; j < cityNum; j++) {
                double rij = Math
                        .sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j])
                                * (y[i] - y[j])) / 10.0);
                // 四舍五入，取整(欧式距离)
                //开平方 算直接距离
                int tij = (int) Math.round(rij);//取整结果
                if (tij < rij) {
                    distance[i][j] = tij + 1;
                    distance[j][i] = distance[i][j];
                } else {
                    distance[i][j] = tij;
                    distance[j][i] = distance[i][j];
                }
                //For test
                //distance[6][7]=10;
               // distance[7][6]=10;

            }
        }
        distance[cityNum - 1][cityNum - 1] = 0;
        //初始化选和走 从0点开始 0置0
        walked = new int[cityNum];
        walked[0] = 0;
        for (int i = 1; i < cityNum; i++) {
            walked[i] = 1;
        }

        choosed = new int[cityNum];
        for (int i = 0; i < cityNum; i++) {
            choosed[i] = 1;
        }

    }

    //选最小
    public int selectmin(int[] p){
        int j = 0, m = p[0], k = 0;//距离
        //寻找第一个可用节点，注意最后一次寻找，没有可用节点
        //表示是否走过，走过置0 未置1
        while (walked[j] == 0)
        {
            j++;
            //System.out.print(j+" ");
            if(j>=cityNum){
                //没有可用节点，退出
                m = p[0];
                break;
            }
            else{
                //找到
                m = p[j];
            }
        }
        //从可用节点J开始往后扫描，找出距离最小节点
        for (; j < cityNum; j++)
        {
            if (walked[j] == 1) {
                if (m >= p[j]) {
                    m = p[j];
                    k = j;
                }
            }
        }
        return k;
    }

    //算最优路径
    public void solve(){
        tspPath=new ArrayList<>();
        if(redisutils.judge()==1)
        {
            System.out.println("已存在redis中");
            tspPath=redisutils.getPath();
        }
        else
        {
            System.out.println("计算redis");
            int[] temp = new int[cityNum];
            String path="0";
            tspPath.add(0);
            int s=0;//计算距离
            int i=0;//当前节点
            int j=0;//下一个节点
            //默认从0开始
//          选过置0 未置1
            while(choosed[i]==1)
            {
                //复制一行
                for (int k = 0; k < cityNum; k++) {
                    temp[k] = distance[i][k];
                    //System.out.print(temp[k]+" ");
                }
                //选择下一个节点，要求：不是已经走过，并且与i不同
                j = selectmin(temp);
                //找出下一节点
                choosed[i] = 0;//行置0，表示已经选过
                walked[j] = 0;//列0，表示已经走过
                tspPath.add(j);
                path+="-->" + j;
                //System.out.println(i + "-->" + j);
                // System.out.println(distance[i][j]);
                s = s + distance[i][j];
                i = j;//当前节点指向下一跳节点
            }
            System.out.println();
            System.out.println("路径:" + path);
            System.out.println("总距离为:" + s);
            redisutils.setPath(tspPath);
            redisutils.choose(true);
        }
        System.out.println("Path::"+tspPath);
        System.out.println("========================");
    }

    //邻近矩阵展示
    public void showinit() {
        System.out.println("====邻接矩阵====");
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=====邻接矩阵======");
    }

    //建立图状结构
    public  Node[] buildGraph(int[][] matrix) {
        int n = matrix.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != Integer.MAX_VALUE) {
                    nodes[i].edges.add(new Edge(nodes[i], nodes[j], matrix[i][j]));
                    //nodes[i].edges.add(new Edge(nodes[j], nodes[i], matrix[i][j]));//双向
                }
            }
        }
        return nodes;
    }

    //最优路径省成本
    public void redo(List<Integer> list){
        int x,y;
        for(int i=0;i<list.size()-2;i++)
        {
            int j=i+1;
            x=list.get(i);
            y=list.get(j);
            distance[x][y] *=(0.8);
            distance[x][y] *=(0.8);
        }
    }

    //得到路径值
    public static int getValue(List<Node> path){
        int pathWeight=0;
        for(int n=0;n<path.size()-1;n++)
        {
            Node from=path.get(n);
            Node to=path.get(n+1);
            Edge edge=Node.findEdge(from ,to);
            pathWeight+=edge.weight;
        }

        return  pathWeight;
    }

    //计算初始化
    public static TxTsp initTsp() throws IOException {
        TxTsp ts = new TxTsp(13);//城市数
        ts.init("/jiangsu.txt");
        ts.solve();
        //ts.showinit();
        ts.redo(ts.tspPath);
        return ts;
    }

    //路径值
    public static int tsp(int from ,int to) throws IOException {
        TxTsp ts=initTsp();

        Node[] nodes= ts.buildGraph(ts.distance);
        Dijkstra.dijkstra(nodes[from]);
        List<Node> path = new ArrayList<>();
        path=Dijkstra.getPath(nodes[to]);//找对应线

        int pathWeight= ts.getValue(path);
        System.out.println("pathWeight::"+pathWeight);
        return pathWeight;
    }

    //快递路线
    public static List<Node> tsp_path(int from ,int to) throws IOException {
        TxTsp ts=initTsp();
        Node[] nodes= ts.buildGraph(ts.distance);
        Dijkstra.dijkstra(nodes[from]);
        List<Node> path = new ArrayList<>();
        path=Dijkstra.getPath(nodes[to]);//找对应线
        return path;
    }

    //tsp路线 for 主页
    public static List<Integer> getTspPath() throws IOException {
        TxTsp ts=initTsp();
        return ts.tspPath;
    }


    //6_>8
    public static void main() throws IOException {
        System.out.println("Start....");
        TxTsp ts = new TxTsp(13);//城市数
        ts.init("/jiangsu.txt");//
        ts.solve();
        ts.showinit();
        ts.redo(ts.tspPath);
        System.out.println("=========redo==============");
        ts.showinit();
        System.out.println("=========buildGraph========");
        Node[] nodes= ts.buildGraph(ts.distance);
        System.out.println("=========dijkstra==========");
        Dijkstra.dijkstra(nodes[6]);
        System.out.println(nodes[6]);
        System.out.println("=========getPath===========");
        Dijkstra.getPath(nodes[8]);

    }
}
