class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int[] result = {0,0};
        for(String input : keyinput) {
            if(input.equals("left")) result[0]--;
            if(input.equals("right")) result[0]++;
            if(input.equals("up")) result[1]++;
            if(input.equals("down")) result[1]--;
            
            if(result[0] < -1 * (board[0]-1)/2) result[0] = -1 * (board[0]-1)/2;
            if(result[1] < -1 * (board[1]-1)/2) result[1] = -1 * (board[1]-1)/2;
            if(result[0] > (board[0]-1)/2) result[0] = (board[0]-1)/2;
            if(result[1] > (board[1]-1)/2) result[1] = (board[1]-1)/2;
        }
        return result;
    }
}