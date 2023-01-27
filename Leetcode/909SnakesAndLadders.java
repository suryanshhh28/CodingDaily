class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int steps = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        q.add(1);
        visited[n-1][0] = true;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i<size; i++) {
                int x = q.poll();
                if(x == n*n) {
                    return steps;
                }
                for(int j = 1; j<=6; j++) {
                    if(j+x > n*n) {
                        break;
                    }
                    int[] position = findCoordinates(j+x, n);
                    int r = position[0];
                    int c = position[1];
                    if(visited[r][c] == true) {
                        continue;
                    }
                    visited[r][c] = true;
                    if(board[r][c] == -1) {
                        q.add(j+x);
                    } else {
                        q.add(board[r][c]);
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    private int[] findCoordinates(int curr, int n) {
        int r = n - (curr - 1) / n - 1;
        int c = (curr - 1) % n;
        if(r % 2 == n % 2) {
            return new int[]{r, n-1-c};
        } else {
            return new int[]{r,c};
        } 
    }
}


// take board length n and store that into some variable
// it is an n*n board thus length of rows = length of columns
// initialize no. of steps = 0
// make a queue and a 2D boolean visited array
// add '1' in queue, as you will always start from 1
// mark 1st position as true in array by [n-1][0] = true, this is because if we look at our matrix
// we see that we start from last row's first column as it is = 1

// iterate till queue is not empty
// take the size of the queue
// iterate till it's size (for loop), as it would iterate all the numbers present in the queue right now
// remove one element from the queue
// check it removed element = n*n, if yes this means we are at our destination only, return number of steps
// take a for loop from j=1 to j=6 (numbers present on the dice)
// check if 'element that you removed from queue' + j > n*n, this means that you have exceeded the board's final index, thus break the for loop
// find out coordinates of the index at which you are at, i.e. it's row and column (by a seperate function)
// check if you have already visited that index, if yes continue the loop
// mark that position in visited array as true
// check if that index is -1
// if yes, in the queue add that index by j + x
// if no, means you are at a ladder or at a snake, thus add the index in the queue of that position, board[r][c]
// come out of both the for loops
// increase the steps by 1

// if the while loop above ends, return -1

// function to find coordinates of the board:
// pass the current index number in the function and the size of the board as parameters
// calculate row by formula -> int r = n - (curr - 1) / n - 1;
// calculare column by formula -> int c = (curr - 1) % n;
// check if row and column, both are even or both are odd by r%2==0 and n%2==0 (n here not c)
// if yes, return {r, n-1-c} this array
// if no, it means one is odd and one is even, thus return {r,c} array
