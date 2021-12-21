class SnakeAndLadderCorrector{
    private HashMap<Integer,Integer> SnakeMap;
    private HashMap<Integer,Integer> LadderMap;
    public void SnakeAndLadderCorrector(HashMap<Integer,Integer> SnakeMap,HashMap<Integer,Integer> LadderMap){
        this.SnakeMap=SnakeMap;
        this.LadderMap=LadderMap;
    }
    public void corrector{
        for(Integer start:SnakeMap){
            if(LadderMap.containsKey(SnakeMap.get(start))){
                if(start<LadderMap.get(SnakeMap.get(start))){
                    LadderMap.put(start,LadderMap.get(SnakeMap.get(start)));
                    SnakeMap.remove(start);
                }
                else{
                    SnakeMap.put(start,LadderMap.get(SnakeMap.get(start)));
                    SnakeMap.remove(start);
                }
            }
        }
        for(Integer start:LadderMap){
            if(SankeMap.containsKey(LadderMap.get(start))){
                if(start<SnakeMap.get(LadderMap.get(start))){
                    LadderMap.put(start,SnakeMap.get(LadderMap.get(start)));
                    LadderMap.remove(start);
                }
                else{
                    SnakeMap.put(start,LadderMap.get(LadderMap.get(start)));
                    LadderMap.remove(start);
                }
            }
        }
    }
    
}