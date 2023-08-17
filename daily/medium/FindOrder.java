package medium;

import java.util.*;

/**
 * NC218 检测循环依赖
 * 描述
 * 为了毕业你需要选择 n 门课程，这 n 门课程中存在一定的依赖关系，例如想要完成 B 课程，必须先完成 A 课程，请你找出一个可以完成全部课程的顺序，如果无论如何选择都无法完成全部课程则返回空数组。
 *
 * 依赖关系以如下方式输入：
 * [[2,1],[3,2]]
 * 即要完成课程 2 ，必须先完成 1 ， 要完成课程 3 ，必须先完成课程 2，答案 [1,2,3] 即可。
 * 但也可能出现类似
 * [[2,1],[1,2]]
 * 要完成课程 2 ，必须先完成 1 ，要完成课程 1 ，必须先完成课程 1 ，则无解，返回一个空数组即可。
 *
 */
public class FindOrder {
    //邻接表
    List<List<Integer>> graph;
    //判断是否存在循环依赖
    boolean valid = true;
    //记录课程状态，0表示未访问，1表示已访问，2表示已完成
    int[] visited;
    //记录可行的课程顺序
    int[] res;
    //res数组的游标
    int id;

    public int[] findOrder(int[][] prerequisites, int n) {
        //新建邻接表
        graph = new ArrayList<>();
        //初始化邻接表
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] cur : prerequisites) {
            //给对应邻接表添加后置课程
            graph.get(cur[1]).add(cur[0]);
        }
        visited = new int[n];
        res = new int[n];
        id = n - 1;

        //遍历所有课程，进行深度优先搜索
        for (int i = 0; i < n && valid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid ? res : new int[0];
    }

    private void dfs(int i) {
        //标记为已访问
        visited[i] = 1;
        for (int j : graph.get(i)) {
            //如果当前后置课程未访问，继续搜索
            if (visited[j] == 0) {
                dfs(j);
            }
            //存在循环依赖
            else if (visited[i] == 1) {
                valid = false;
                return;
            }
        }
        //标记为已完成
        visited[i] = 2;
        //已完成的一定是递归的最后一层，对应课程顺序的最后一门
        res[id--] = i;
    }

}