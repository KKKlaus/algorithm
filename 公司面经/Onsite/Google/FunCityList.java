package Google;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class FunCityList {

    // 这个还不是最优。最优是遍历tickets(就是边），把他看成中间边，遍历旁边，记录前三大即可


    // 这个还不是最优。最优是遍历tickets(就是边），把他看成中间边，遍历旁边，记录前三大即可
    public int maxFun_1(List<City> cityList, List<City[]> tickets) {
        int n = cityList.size();

        // build graph
        HashMap<City, PriorityQueue<City>> graph = new HashMap<>();
        for (City city : cityList) {
            graph.put(city, new PriorityQueue<>((a, b) -> (b.fun - a.fun)));
        }
        for (City[] arr : tickets) {
            City city1 = arr[0], city2 = arr[1];
            graph.get(city1).offer(city2);
            graph.get(city2).offer(city1);
        }

        int maxFun = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                City city1 = cityList.get(i), city2 = cityList.get(j);
                PriorityQueue<City> pq1 = graph.get(city1), pq2 = graph.get(city2);
                if (pq1.isEmpty() && pq2.isEmpty()) continue;
                maxFun = Math.max(maxFun, city1.fun + city2.fun + pq1.peek().fun + pq2.peek().fun);
            }
        }
        return maxFun;

    }




    // 做法1：暴力dfs所有
    int[][] memo;
    int maxFun = 0;
    public int maxFun_2(List<City> cityList, List<City[]> tickets) {
        int n = cityList.size();
        memo = new int[n][n];

        // build graph
        HashMap<City, List<City>> graph = buildGraph(cityList, tickets);

        HashMap<City, Boolean> visited = new HashMap<>();
        for (City city : cityList) visited.put(city, false);

        for (City city : cityList) {
            dfs(city, 0, 4, graph, visited);
        }
        return maxFun;
    }

    private void dfs(City curCity, int fun, int k, HashMap<City, List<City>> graph, HashMap<City, Boolean> visited) {
        if (k == 0) {
            maxFun = Math.max(maxFun, fun);
            return;
        }

        for (City neighbor : graph.get(curCity)) {
            if (visited.get(neighbor)) continue;
            visited.put(neighbor, true);
            dfs(neighbor, fun + curCity.fun, k - 1, graph, visited);
            visited.put(neighbor, false);
        }
    }

    private HashMap<City, List<City>> buildGraph(List<City> cityList, List<City[]> tickets) {
        HashMap<City, List<City>> graph = new HashMap<>();
        for (City city : cityList) {
            graph.put(city, new ArrayList<>());
        }
        for (City[] arr : tickets) {
            City city1 = arr[0], city2 = arr[1];
            graph.get(city1).add(city2);
            graph.get(city2).add(city1);
        }
        return graph;
    }

    class City {
        char name;
        int fun;
        City(char c, int f) {
            this.name = c;
            this.fun = f;
        }
    }
}
