 import java.util.*;

public class AdjacencyArray {

        public static void main(String[] args) {
            int V = 10;
            int EPercent = 200;
            int cases = 19;
            int runs = 1;
            int[][] test0 = new int[][]{{100, 200}, {200, 200}, {300, 200}, {400, 200}, {500, 200},
                    {600, 200}, {700, 200}, {800, 200}, {900, 200}, {1000, 200},
                    {2000, 200}, {2000, 200}, {4000, 200}, {5000, 200}, {6000, 200},
                    {7000, 200}, {8000, 200}, {9000, 200}, {10000, 200},};
            long time[] = MatrixCPUPerformance(test0, cases, runs);
            //System.out.println(MatrixCPUTime(V,EPercent));

            for (int i = 0; i < time.length; i++) {
                System.out.println("V = " + test0[i][0] + ", time = " + time[i]);
            }
        }

        // CPU time for one Dijkstra function call in MS
        public static long MatrixCPUTime(int V, int EPercent) {
            GraphMatrix graphMatrix = new GraphMatrix(V);
            for (int i = 0; i < V * EPercent / 100; i++) {
                graphMatrix.addRandomEdge(V);
            }
            long start = System.nanoTime();
            Dijkstra(graphMatrix.adjMatrix, V, 0);
            long time = (System.nanoTime() - start) / 1000;
            return time;
        }

        // Average CPU time for a number of Dijkstra function calls in MS
        public static long[] MatrixCPUPerformance(int[][] test, int cases, int runs) {
            int V;
            int EPercent;
            long[] result = new long[cases];
            long time = 0;
            for (int i = 0; i < cases; i++) {
                for (int j = 0; j < runs; j++) {
                    time += MatrixCPUTime(test[i][0], test[i][1]);
                }
                result[i] = time / runs;
            }
            return result;
        }

        public static void Dijkstra(int[][] graph, int V, int source) {
            int path_array[] = new int[V];
            Boolean sptSet[] = new Boolean[V];

            for (int i = 0; i < V; i++) {
                path_array[i] = Integer.MAX_VALUE;
                sptSet[i] = false;
            }

            path_array[source] = 0;
            // now find shortest path for all vertices
            for (int count = 0; count < V; count++) {
                int u = minDistance(V, path_array, sptSet);
                sptSet[u] = true;
                for (int i = 0; i < V; i++)
                    if (!sptSet[i] && graph[u][i] != 0 && path_array[u] != Integer.MAX_VALUE
                            && path_array[u] + graph[u][i] < path_array[i])
                        path_array[i] = path_array[u] + graph[u][i];
            }
            //printMinPath(V, path_array);
        }

        public static int minDistance(int V, int path_array[], Boolean sptSet[]) {
            int min = Integer.MAX_VALUE, min_index = -1;
            for (int i = 0; i < V; i++)
                if (!sptSet[i] && path_array[i] <= min) {
                    min = path_array[i];
                    min_index = i;
                }
            return min_index;
        }

        public static void printMinPath(int V, int path_array[]) {
            System.out.println("Vertex# \t Minimum Distance from Source");
            for (int i = 0; i < V; i++) {
                if (path_array[i] == Integer.MAX_VALUE)
                    System.out.println(i + " \t\t\t INFINITE");
                else
                    System.out.println(i + " \t\t\t " + path_array[i]);
            }
        }
}
class GraphMatrix{
        int V;
        int adjMatrix[][];
        private static Random rd = new Random();

        public GraphMatrix(int v) {
            this.V = v;
            this.adjMatrix = new int[v][v];
        }

        // Add random edges
        public void addRandomEdge(int v) {
            RandomEdge edge = new RandomEdge(v);
            adjMatrix[edge.source][edge.destination] = edge.weight;
        }

        public void printGraphMatrix() {
            for (int i = 0; i < this.V; i++) {
                for (int j = 0; j < this.V; j++) {
                    if (adjMatrix[i][j] != 0)
                        System.out.println("vertex " + i + " is connected to " +
                                j + " with weight " + adjMatrix[i][j]);
                }
            }
        }

    }

class RandomEdge {
        int V;
        int source;
        int destination;
        int weight;
        private static Random rd = new Random();

        public RandomEdge(int v) {
            V = v;
            do{
                source = rd.nextInt(v);
                destination = rd.nextInt(v);
            }while(source == destination);  // add an effective edge buy ensuring source != destination
            weight = rd.nextInt(10) + 1;  // add an effective edge buy ensuring weight > 0
        }
    }
