//package main;


//public class main {
//    public static void main(String[] args) throws CloneNotSupportedException {
//
//        while (true) {
//            Scanner s = new Scanner(System.in);
//            System.out.print("chose level from 1 to 16: ");
//            int i = s.nextInt();
//            if(i > 16){
//                System.out.println("invalid input");
//                continue;
//            }
//            i -= 1;
//            System.out.println("1.User Play\n" +
//                    "2.BFS\n" +
//                    "3.UCS\n" +
//                    "4.Hill Climbing\n" +
//                    "5.A*\n");
//            int j = s.nextInt();
//
//            long current = System.currentTimeMillis();
//            switch (j) {
//                case 1 -> new UserPlay(GameLevels.levels.get(i));
//                case 2 -> new BFS(GameLevels.levels.get(i));
//                case 3 -> new UCS(GameLevels.levels.get(i));
//                case 4 -> new HillClimbing(GameLevels.levels.get(i));
//                case 5 -> new A8(GameLevels.levels.get(i));
//                default -> {
//                    System.out.println("invalid input");
//                    continue;
//                }
//            }
//            long now = System.currentTimeMillis();
//            System.out.println("total time: " + (double) (now - current) / 1000 + "s\n");
//        }
//    }
//}

//======================<<< Level 1 >>>===============================
/* Bfs
Visited States Count: 7
Node Depth: 1
total time: 0.047s
 */

/* UCS
Visited States Count: 10
Node Depth: 1
total time: 0.047s
 */

//======================<<< Level 2 >>>===============================
/* Bfs
Visited States Count: 12
Node Depth: 1
total time: 0.047s
 */

/* UCS
Visited States Count: 14
Node Depth: 1
total time: 0.06s
 */

//======================<<< Level 6 >>>===============================
/* BFS
Visited States Count: 68
Node Depth: 2
total time: 0.047s
 */


/* UCS
Visited States Count: 110
Node Depth: 2
total time: 0.078s
 */

//======================<<< Level 10 >>>===============================

/* UCS
Visited States Count: 81
Node Depth: 2
total time: 0.076s
 */

/* A*
Visited States Count: 4253
Node Depth: 2
total time: 3.052s
 */


//======================<<< Level 16 >>>===============================
/* BFS
Visited States Count: 31903
Node Depth: 3
total time: 1458.982s
 */
/* UCS cost 1 for R and A
Visited States Count: 2394
Node Depth: 3
total time: 4.744s
 */
/* UCS with cost 1 for R and cost 2 for A
Visited States Count: 19426
Node Depth: 3
total time: 468.563s
 */
/* Hill Climbing
Visited States Count: 1937
Node Depth: 19
total time: 3.117s
 */
/* A*
Visited States Count: 9
Node Depth: 8
total time: 0.055s
 */