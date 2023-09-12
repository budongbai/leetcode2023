package medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/course-schedule-iv/">1462. 课程表 IV</a>
 */
public class Courses4 {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // 以邻接表形式表示图
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        // 记录每个节点的入度，找到图的访问入口
        int[] indgree = new int[numCourses];
        // 标识 课程a 是否为课程b的先决条件
        boolean[][] isPre = new boolean[numCourses][numCourses];
        for (int[] p : prerequisites) {
            ++indgree[p[1]];
            // 构建邻接表
            graph[p[0]].add(p[1]);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; ++i) {
            // 找到入度为0的课程
            if (indgree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int ne : graph[cur]) {
                isPre[cur][ne] = true;
                for (int i = 0; i < numCourses; ++i) {
                    // i 是 ne 的先决条件： i -> ne， 或者 cur -> ne， i -> cur 形成间接 i-> ne
                    isPre[i][ne] = isPre[i][ne] || isPre[i][cur];
                }
                --indgree[ne];
                // 入度为0时，加入遍历
                if (indgree[ne] == 0) {
                    queue.offer(ne);
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(isPre[query[0]][query[1]]);
        }
        return res;
    }

    public static void main(String[] args) {
        new Courses4().checkIfPrerequisite(3, new int[][]{{1,2},{2,0},{1,0}}, new int[][]{{1,0},{2,0}});
    }
}
