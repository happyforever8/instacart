import java.util.*;
public class HelloWorld{

     public static void main(String []args){
        System.out.println("Hello World");
        TimeMap1 timeMap1 = new TimeMap1();
        timeMap1.set("1", "2");
        timeMap1.set("", "3");
        timeMap1.set("1", "4");
        System.out.println(timeMap1.get("1") + "   timeMap1");
        
         System.out.println("=============================");
        
        TimeMap2 timeMap2 = new TimeMap2();
        timeMap2.set("1", "2", 1);
        timeMap2.set("", "3", 2);
        timeMap2.set("1", "4", 3);
        System.out.println(timeMap2.get("1") + "   without time");
        System.out.println(timeMap2.get("1", 1) + "   with time");
        
        
          TimeMap22 timeMap22 = new TimeMap22();
        timeMap22.set("1", "2", 1);
        timeMap22.set("", "3", 2);
        timeMap22.set("1", "4", 3);
        System.out.println(timeMap22.get("1") + "   without time");
        System.out.println(timeMap22.get("1", 1) + "   with time");
        
     }
     
     
     
    // question here
    // if same key, value will override????
    // if there is no key, return null string???
  static class TimeMap1 {
    Map<String, String> map;
    /** Initialize your data structure here. */
    public TimeMap1() {
       map = new HashMap<>();
    }
    
    public void set(String key, String value) {
      map.put(key, value);
    }
    
    public String get(String key) {
        if (!map.containsKey(key)){
            return "";
        }
        return map.get(key);
    }
  }
//=====================question two =====================================================
     // question here
     // what is the timeStamp type, string or integer
     // what able the same key, return latest value????
     
     // get(key) time is o(n)
     // get(key, timestamp) time is o(1)
  static class TimeMap2 {
    
    Map<String, HashMap<Long, String>> map;
    
    /** Initialize your data structure here. */
    public TimeMap2() {
       map = new HashMap<>();
    }
    
    public void set(String key, String value) {
        // timeStamp is system timeStamp
         
        Long timestamp = System.currentTimeMillis();
        map.putIfAbsent(key, new HashMap<>());
        map.get(key).put(timestamp, value);
    }
    
    public String get(String key) {
        if (!map.containsKey(key)){
            return "";
        }
         String result = "";
         HashMap<Long, String> currMap = map.get(key);
         for(Map.Entry m : currMap.entrySet()){    
             result = (String)m.getValue();
          }  
         
         return result;
    }
    public String get(String key, long timestamp) {
        if (!map.containsKey(key)){
            return "";
        }
        Map<Long, String> currMap = map.get(key);
        
        if (currMap.containsKey(timestamp)){
           return  currMap.get(timestamp);
        }
          
        return  "";
    }
  }
  
   // ***************solution two get(key) is o(1)***************
  
  static class TimeMap22 {
      static class Node {
        String key;
        String value;
        long timeStamp;
        Node (String key, String value, long timeStamp) {
            this.key = key;
            this.value = value;
            this.timeStamp = timeStamp;
        }
    }
    
    Map<String, List<Node>> map = new HashMap<>();
    Map<String, HashMap<Long, String>> timeToValueMap = new HashMap<>();
    
   
   
    public  void set(String key, String value) {
         // using sytem time stamp
          Long timeStamp = System.currentTimeMillis();
        List<Node> list = map.getOrDefault(key, new ArrayList<>());
        list.add(new Node(key, value, timeStamp));
        map.put(key, list);
        
        timeToValueMap.putIfAbsent(key, new HashMap<>());
        timeToValueMap.get(key).put(timeStamp, value);
    }
    
    public  String get(String key, int timeStamp) {
        // List<Node> list = map.getOrDefault(key, new ArrayList<>());
        // if (list.isEmpty()) return "";
        
        if (!timeToValueMap.containsKey(key)){
            return "";
        }
        Map<Integer, String> currMap = timeToValueMap.get(key);
        
        if (currMap.containsKey(timeStamp)){
           return  currMap.get(timeStamp);
        }
          
        return  "";
                     
    }
    public  String get(String key) {
        List<Node> list = map.getOrDefault(key, new ArrayList<>());
        if (list.isEmpty()) return "";
        
        return list.get(list.size() - 1).value; 
                     
    }
      
  }
  
  
  
  //==============================question three==================================
     
     
    class TimeMap {
    
    // treemap search is O(logn)
    // treemap insert is o(nlogn)
    Map<String, TreeMap<Integer, String>> map;
    /** Initialize your data structure here. */
    public TimeMap() {
       map = new HashMap<>();
    }
    // treemap set is O(logn )
    
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)){
            return "";
        }
        TreeMap<Integer, String> treeMap = map.get(key);
        
        
        // using Integer to avoid NPE
        Integer target = treeMap.floorKey(timestamp); 
        return target == null ? "" : treeMap.get(target);
    }
  }
  
 // ***************solution two without treemap***************
// 时间复杂度：set 操作的复杂度为 O(1)O(1)；get 操作的复杂度为 O(\log{n})O(logn)
// 空间复杂度：O(n)O(n)
   class Node {
        String key;
        String value;
        int timeStamp;
        Node (String key, String value, int timeStamp) {
            this.key = key;
            this.value = value;
            this.timeStamp = timeStamp;
        }
    }
    
    Map<String, List<Node>> map = new HashMap<>();
    public void set(String key, String value, int timeStamp) {
        List<Node> list = map.getOrDefault(key, new ArrayList<>());
        list.add(new Node(key, value, timeStamp));
        map.put(key, list);
    }
    
    public String get(String key, int timeStamp) {
        List<Node> list = map.getOrDefault(key, new ArrayList<>());
        if (list.isEmpty()) return "";
        int low =0;
         int high = list.size() -1;

        // Binary search through
         while(low + 1 < high){
             int mid = (high + low)/2;
             Node midTerm = list.get(mid);
             if(midTerm.timeStamp == timeStamp){
                 return midTerm.value;
             } else if( midTerm.timeStamp < timeStamp){
                 low = mid;
             }else{
                 high = mid;
             }
         }
         // Now left pointer is less than right pointer
         // Check right pointer <= timerstamp as we want to get the greatest time smaller than the timestamp
        if(list.get(high).timeStamp <= timeStamp){
            return list.get(high).value;
        //  Check left
        }else if(list.get(low).timeStamp <= timeStamp){
            return list.get(low).value;
        }
         // Must be lower than the lowest possible value
            return "";                
    }

}
