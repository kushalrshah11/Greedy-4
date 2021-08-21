// time - O(mn)log(mn)
// space - O(mn)



class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {

        if(workers == null || workers.length  == 0 || bikes == null  || bikes.length  == 0) {
            return new int[] {};
        }
        TreeMap<Integer, List<int []>> map = new TreeMap<>();
        int wl = workers.length; int bl = bikes.length;
        int [] result = new int[wl];

        for(int i = 0; i < wl; i++) {
            for(int j = 0; j < bl; j++) {

                int dist  = calculateDistance(workers[i],bikes[j]);
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});
            }
        }

        boolean []  workersAllocated = new boolean[wl];
        boolean [] occupiedBikes = new boolean[bl];

        for(int dist: map.keySet()) {

            List<int []> li = map.get(dist);

            for(int [] wb: li) {
                int worker = wb[0];
                int  bike = wb[1];
                if(!workersAllocated[worker] && !occupiedBikes[bike]) {
                    workersAllocated[worker] = true;
                    occupiedBikes[bike] = true;
                    result[worker] = bike;
                }
            }

        }

        return result;
    }

    private int calculateDistance(int [] worker, int []  bike) {
        return Math.abs(worker[0]  - bike[0]) + Math.abs(worker[1] -  bike[1]);
    }
}