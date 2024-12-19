import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] inorder;
    static int[] postorder;
    static class Node {
        Node left, right;
        int num;
        Node(int num) {
            this.num = num;
        }
        void printPreorder(StringBuffer sb) {
            sb.append(num + " ");
            if(left != null)
                left.printPreorder(sb);
            if(right != null)
                right.printPreorder(sb);
        }
    }
    static boolean[] leftSet;
    static Node func(int inorderStart, int inorderEnd, int postorderStart, int postorderEnd) {
        if(inorderStart >= inorderEnd || postorderStart >= postorderEnd) return null;
        Node curNode = new Node(postorder[postorderEnd-1]);

        int inorderLeftEnd = -1;
        Arrays.fill(leftSet, false);
        for(int i=inorderStart; i<inorderEnd; i++) {
            if(inorder[i] == curNode.num) {
                inorderLeftEnd = i;
                break;
            }
            leftSet[inorder[i]] = true;
        }

        int postorderLeftEnd = -1;
        for(int i=postorderStart; i<postorderEnd; i++) {
            if(!leftSet[postorder[i]]) {
                postorderLeftEnd = i;
                break;
            }
        }

        Node left = func(inorderStart, inorderLeftEnd, postorderStart, postorderLeftEnd);
        Node right = func(inorderLeftEnd+1, inorderEnd, postorderLeftEnd, postorderEnd-1);
        curNode.left = left;
        curNode.right = right;
        return curNode;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inorder = new int[n];
        postorder = new int[n];
        leftSet = new boolean[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }
        Node tree = func(0, inorder.length, 0, postorder.length);

        StringBuffer sb = new StringBuffer();
        tree.printPreorder(sb);
        System.out.println(sb);
    }
}
