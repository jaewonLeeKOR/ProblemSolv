import java.util.*;
import java.io.*;


/*
사막지도
선인장 구역
선인장이 가능한 한 늦게 비를 맞도록
*/

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[] answer = {};
        int[][] dropMap = new int[m][n];
        for(int i=0; i<dropMap.length; i++) {
            Arrays.fill(dropMap[i], Integer.MAX_VALUE);
        }
        for(int i=0; i<drops.length; i++) {
            dropMap[drops[i][0]][drops[i][1]] = i+1;
        }
        
        // 가로
        int[][] cells = new int[m][n];
        for(int i=0; i<cells.length; i++) {
            Arrays.fill(cells[i], Integer.MAX_VALUE);
        }
        for(int i=0; i<m; i++) {
            Deque<Integer> dq = new LinkedList<>();
            for(int j=0; j<w-1; j++) {
                while(!dq.isEmpty() && dq.getLast() > dropMap[i][j]) {
                    dq.pollLast();
                }
                if(dq.isEmpty() || dq.getLast() < dropMap[i][j])
                    dq.addLast(dropMap[i][j]);
            }
            for(int j=0; j+w<=n; j++) {
                while(!dq.isEmpty() && dq.getLast() > dropMap[i][j+w-1]) {
                    dq.pollLast();
                }
                if(dq.isEmpty() || dq.getLast() < dropMap[i][j+w-1])
                    dq.addLast(dropMap[i][j+w-1]);
                if(!dq.isEmpty())
                    cells[i][j] = dq.getFirst();
                if(!dq.isEmpty() && dq.getFirst() == dropMap[i][j]) {
                    dq.pollFirst();
                }
            }
        }
        
        // 세로
        int[][] rc = new int[m][n];
        for(int i=0; i<rc.length; i++) {
            Arrays.fill(rc[i], Integer.MAX_VALUE);
        }
        for(int j=0; j<n; j++) {
            Deque<Integer> dq = new LinkedList<>();
            for(int i=0; i<h-1; i++) {
                while(!dq.isEmpty() && dq.getLast() > cells[i][j]) {
                    dq.pollLast();
                }
                if(dq.isEmpty() || dq.getLast() < cells[i][j])
                    dq.addLast(cells[i][j]);
            }
            for(int i=0; i+h<=m; i++) {
                while(!dq.isEmpty() && dq.getLast() > cells[i+h-1][j]) {
                    dq.pollLast();
                }
                if(dq.isEmpty() || dq.getLast() < cells[i+h-1][j])
                    dq.addLast(cells[i+h-1][j]);
                if(!dq.isEmpty()) {
                    rc[i][j] = dq.getFirst();
                }
                if(!dq.isEmpty() && dq.getFirst() == cells[i][j]) {
                    dq.pollFirst();
                }
            }
        }
        
        // 정산
        int maxTime = -1;
        for(int i=0; i+h<=m; i++) {
            for(int j=0; j+w<=n; j++) {
                if(maxTime < rc[i][j]) {
                    maxTime = rc[i][j];
                    answer = new int[] {i,j};
                }
            }
        }
        
        
        // for(int i=0; i<dropMap.length; i++) {
        //     for(int j=0; j<dropMap[i].length; j++) {
        //         System.out.print(dropMap[i][j] == Integer.MAX_VALUE ? 0 : dropMap[i][j]);
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        // for(int i=0; i<cells.length; i++) {
        //     for(int j=0; j<cells[i].length; j++) {
        //         System.out.print(cells[i][j] == Integer.MAX_VALUE ? 0 : cells[i][j]);
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        // for(int i=0; i<rc.length; i++) {
        //     for(int j=0; j<rc[i].length; j++) {
        //         System.out.print(rc[i][j] == Integer.MAX_VALUE ? 0 : rc[i][j]);
        //     }
        //     System.out.println();
        // }
        return answer;
    }
}