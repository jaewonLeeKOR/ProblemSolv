import java.io.*;
import java.util.*;

/**
 * N개의 건물들의 왼쪽 x, 높이, 오른쪽 x
 * 높이가 변하는 지점에 대해서 x좌표와 높이를 출력
 *
 * 기존 높이, 변경된 높이
 */
public class Main {
    static int N;
    static List<List<Vector>> buildingVectors;
    static class Vector{
        int position;
        int height;
        Vector(int position, int height) {
            this.position = position;
            this.height = height;
        }
    }
    static List<Vector> func(int startIdx, int endIdx) {
        if(startIdx + 1 == endIdx) {
            return buildingVectors.get(startIdx);
        }
        List<Vector> newVector = new LinkedList<>();
        int curHeight = 0;
        int curLeftHeight = 0;
        int curRightHeight = 0;
        Queue<Vector> leftQ = new LinkedList<>(func(startIdx, (startIdx + endIdx)/2));
        Queue<Vector> rightQ = new LinkedList<>(func((startIdx + endIdx)/2, endIdx));
        while(!leftQ.isEmpty() || !rightQ.isEmpty()) {
            if(rightQ.isEmpty()) {
                Vector leftVector = leftQ.poll();
                newVector.add(leftVector);
                continue;
            }
            if(leftQ.isEmpty()) {
                Vector rightVector = rightQ.poll();
                newVector.add(rightVector);
                continue;
            }

            if(leftQ.peek().position == rightQ.peek().position) { // 같은 위치에서 높이 이동이 발생한 경우
                Vector leftVector = leftQ.poll();
                Vector rightVector = rightQ.poll();
                curLeftHeight = leftVector.height;
                curRightHeight = rightVector.height;
                if(curLeftHeight > curRightHeight) {
                    if(curHeight == curLeftHeight) continue; // 각 건물 상황이 바뀌더라도 높이가 유지 되는 경우
                    curHeight = curLeftHeight;

                    newVector.add(leftVector);
                }
                else {
                    if(curHeight == curRightHeight) continue; // 각 건물 상황이 바뀌더라도 높이가 유지 되는 경우
                    curHeight = curRightHeight;

                    newVector.add(rightVector);
                }
            }
            else if(leftQ.peek().position < rightQ.peek().position) {
                Vector leftVector = leftQ.poll();
                curLeftHeight = leftVector.height;
                if(curLeftHeight > curRightHeight) {
                    if(curHeight == curLeftHeight) continue; // 각 건물 상황이 바뀌더라도 높이가 유지 되는 경우
                    curHeight = curLeftHeight;

                    newVector.add(leftVector);
                }
                else {
                    if(curHeight == curRightHeight) continue; // 각 건물 상황이 바뀌더라도 높이가 유지 되는 경우
                    curHeight = curRightHeight;

                    newVector.add(new Vector(leftVector.position, curRightHeight));
                }
            }
            else if(leftQ.peek().position > rightQ.peek().position) {
                Vector rightVector = rightQ.poll();
                curRightHeight = rightVector.height;
                if(curLeftHeight > curRightHeight) {
                    if(curHeight == curLeftHeight) continue; // 각 건물 상황이 바뀌더라도 높이가 유지 되는 경우
                    curHeight = curLeftHeight;

                    newVector.add(new Vector(rightVector.position, curLeftHeight));
                }
                else {
                    if(curHeight == curRightHeight) continue; // 각 건물 상황이 바뀌더라도 높이가 유지 되는 경우
                    curHeight = curRightHeight;

                    newVector.add(rightVector);
                }
            }
        }
        return newVector;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buildingVectors = new ArrayList<>(N);
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            List<Vector> buildingVector = new LinkedList<>();
            buildingVector.add(new Vector(l, h));
            buildingVector.add(new Vector(r, 0));
            buildingVectors.add(buildingVector);
        }

        StringBuffer sb = new StringBuffer();
        List<Vector> result = func(0, N);
        for(Vector v : result) {
            sb.append(v.position).append(" ").append(v.height).append(" ");
        }
        System.out.print(sb);
    }
}
