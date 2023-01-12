import java.util.*;

public class GaleShapley {
    public static void main (String[] args) {
        int[][]mp = {{2, 1, 3, 0},
                     {0, 1, 3, 2},
                     {0, 1, 2, 3},
                     {0, 1, 2, 3}};
                     
        int[][]wp = {{0, 2, 1, 3},
                     {2, 0, 3, 1},
                     {3, 2, 1, 0},
                     {2, 3, 1, 0}};
        
        System.out.println(matching(mp, wp));
                     
    }
    public static Map<Integer,Integer> matching (int[][]mp, int[][]wp) {
        Map<Integer, Integer> matches = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        int index = 0;
        int counter = 0;
        for(int i = 0; i < mp.length; i++) {
            q.add(i);
        }
        while(q.size() > 0) {
            int personM = q.remove();
            if(counter == mp.length) {
                index++;
                counter = 0;
            }
            int pontenitalW = mp[personM][index];
            if(matches.containsValue(pontenitalW)) {
                wPreference(pontenitalW, personM, wp, matches, q);
            } else {
                matches.put(personM, pontenitalW);
            }
            counter++;
        }
        return matches;
    }

    private static void wPreference(int pontenitalW, int currentM, int[][]wp, 
                                        Map<Integer, Integer> matches, Queue<Integer> q) {
        int assignedM = getKey(matches, pontenitalW);
        for(int i = 0; i < wp[pontenitalW].length; i++) {
            if(wp[pontenitalW][i] == assignedM) {
                q.add(currentM);
                break;
            } else if (wp[pontenitalW][i] == currentM) {
                matches.remove(assignedM);
                matches.put(currentM, pontenitalW);
                q.add(assignedM);
                break;
            }
        }
    }

    public static int getKey (Map<Integer, Integer> matches, int value) {
        for (Map.Entry<Integer, Integer> entry : matches.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
