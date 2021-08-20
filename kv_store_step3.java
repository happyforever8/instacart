import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
/*
Feature: key value -> map
Data Structure: HashMap<String, String> map
APIs:
- set: map.set(k, v) returns nothing
    - assumption: always succeeds; K and V are always strings
    - a newer V can overwrite an old V for the same K
- get: map.get(k) returns value
    - K is always a string
    - returns null when K is not in map
*/
 
public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        MyMap kv = new MyMap();
        print(kv.get("foo")); // null
        Long t1 = kv.set("foo", "bar"); 
        Thread.sleep(3000);
        Long t2 = kv.set("foo", "baz"); 
        print(t1);
        print(t2);
 
        print(kv.get("foo", t1-999)); // null
        print(kv.get("foo", t1+2)); // bar 
 
        print(kv.get("foo", t2)); // baz
        print(kv.get("foo", t2+99999999)); // baz
 
        print(kv.get("foo")); // baz
    }
     
    private static void print(String val) {
        System.out.println(val);
    }
     
    private static void print(Long val) {
        System.out.println(val);
    }
}
 
// 1 - a
// 2 - b
// 4 - d
// get(k, 2) -> b
// get(k, 3) -> null
// get(k) -> d
 
class MyMap {
    private Map<String, List<Node>> map;
    public MyMap() {
        map = new HashMap<>();
    }
 
    public Long set(String key, String value) {
        List<Node> nodes = map.get(key);
        if (nodes == null) {
            nodes = new ArrayList<Node>();
            map.put(key, nodes);
        }
        Long t = System.currentTimeMillis();
        nodes.add(new Node(value, t));
        return t;
    }
     
    public String get(String key, Long timestamp) {
        List<Node> nodes = map.get(key);
        if (nodes == null || nodes.isEmpty()) {
            return null;
        } else {
            return search(nodes, timestamp);
        }
    }
     
    public String get(String key) {
        List<Node> nodes = map.get(key);
        if (nodes == null || nodes.isEmpty()) {
            return null;
        } else {
            return nodes.get(nodes.size() - 1).value;
        }
    }
     
    private String search(List<Node> nodes, Long timestamp) {
        int left = 0;
        int right = nodes.size() - 1;
        while (left < right - 1) { 
            int mid = ‍‍‍‍‍‍‍‍‌‍‌‌‍‌‌‌‌‌left + (right - left) / 2;
            Node midNode = nodes.get(mid);
            if (midNode.timestamp == timestamp) {
                return midNode.value;
            } else if (midNode.timestamp < timestamp) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
         
        Node rightNode = nodes.get(right);
        if (rightNode.timestamp <= timestamp) {
            return rightNode.value;
        }
        Node leftNode = nodes.get(left);
        return leftNode != null && leftNode.timestamp <= timestamp ? leftNode.value: null;
    }
}
 
class Node {
    Long timestamp;
    String value;
     
    public Node(String v, Long t) {
        timestamp = t;
        value = v;
    }
}


