package top100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Code207 {
    /**
     * 使用标准化的图结构来解决课程表问题
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph();
        Map<Integer, GraphNode> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];
            if (!map.containsKey(from)) {
                map.put(from, new GraphNode(from));
                graph.nodes.add(map.get(from));
            }
            if (!map.containsKey(to)) {
                map.put(to, new GraphNode(to));
                graph.nodes.add(map.get(to));
            }
            GraphNode fromNode = map.get(from);
            GraphNode toNode = map.get(to);
            fromNode.nexts.add(toNode);
            toNode.in++;
        }

        Queue<GraphNode> queue = new LinkedList<>();
        for (GraphNode node : map.values()) {
            if (node.in == 0) {
                queue.add(node);
            }
        }

        Set<GraphNode> set = new HashSet<>();
        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            set.add(node);
            for (int i = 0; i < node.nexts.size(); i++) {
                GraphNode to = (GraphNode) node.nexts.get(i);
                to.in--;
                if (to.in == 0 && !set.contains(to)) {
                    queue.add(to);

                }
            }
        }


        return set.size() == graph.nodes.size();
    }

    class Graph {
        List<GraphNode> nodes;

        Graph() {
            this.nodes = new ArrayList<>();
        }
    }

    class GraphNode<Integer> {
        Integer val;
        GraphNode from;
        List<GraphNode> nexts;
        int in;

        GraphNode(Integer val) {
            this.nexts = new ArrayList<GraphNode>();
            this.val = val;
            this.in = 0;
        }

        // 重写equals方法
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (obj instanceof GraphNode) {
                GraphNode node = (GraphNode) obj;
                return this.val.equals(node.val);
            }
            return false;
        }
    }

    /**
     * 临接表法表示图
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true; // 如果没有课程，直接返回true
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            inMap.put(prerequisites[i][0], 0); // 初始化入度为0
            inMap.put(prerequisites[i][1], 0);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            graph.computeIfAbsent(prerequisites[i][1], _ -> new ArrayList<>()).add(prerequisites[i][0]);
            inMap.put(prerequisites[i][0], inMap.get(prerequisites[i][0]) + 1);
        }
        Queue<Integer> queue = new LinkedList<>();
        // 找到所有入度为0的点
        for (Map.Entry<Integer, Integer> entry : inMap.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }
        int nodes = 0;
        while (!queue.isEmpty()) {
            nodes++;
            List<Integer> next = graph.get(queue.poll());
            if (next != null && !next.isEmpty()) {
                for (Integer node : next) {
                    inMap.put(node, inMap.get(node) - 1);
                    if (inMap.get(node) == 0) {
                        queue.add(node);
                    }
                }
            }
        }
        return nodes == inMap.size();
    }

    public static void main(String[] args) {
        Code207 code207 = new Code207();
        int numCourses = 5;
        int[][] prerequisites = {{1, 4}, {2, 4}, {3, 1}, {3, 2}};
        boolean result1 = code207.canFinish1(numCourses, prerequisites);
        boolean result2 = code207.canFinish2(numCourses, prerequisites);
        System.out.println("Result using Graph structure: " + result1);
        System.out.println("Result using Adjacency List: " + result2);
    }
}
