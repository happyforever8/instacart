Question: Transform from start to end and return its shortest path.

Start = [L,_,_,R,_,R]
End = [_,R,R,L,_,_]
Return its path

[_,L,_,R,_R]
[_,_,L,R,_R]
[_,R,L,_,_R]
[_,R,_,L,_R]
[_,R,_,L,R_]
[_,R,R,L,_,_]
Rules:

L can only move to the right
R can only move to the left
When L and R are next to one another, they can jump over. ie: _, L, R, _ => R, L, _, _, or _, _, R, L
Jumping more than 2 units is not allowed. ie: ,L,L,R,, cannot become R,L,L, _ ,_


public int solve(List<Character> from, List<Character> to) {
        Set<List<Character>> seen = new HashSet<>();
        Queue<List<Character>> q = new ArrayDeque<>();
        q.add(from);
        
        int level = 0;
        while(!q.isEmpty()) {
            for (int i = 0, sz = q.size(); i < sz ;++i) {
                var current = q.poll();
                if (!seen.add(current)) continue;
                if (to.equals(current)) {
                    return level;
                }
                for(var next : getNexts(current)) {
                    if(seen.contains(next)) continue;
                    q.offer(next);
                }
            }
            ++level;
        }
        return -1;
    }

    private List<List<Character>> getNexts(List<Character> list) {
        var results = new ArrayList<List<Character>>();
        for (int i = 0; i < list.size(); ++i) {
            var prev = i > 0 ? list.get(i - 1) : null;
            var current = list.get(i);
            var next = i < list.size() - 1 ? list.get(i + 1) : null;

            if (next != null && (current == 'L' && next == '_' || current == '_' && next == 'R')) {
                // can jump
                var clone = new ArrayList<>(list);
                Collections.swap(clone, i, i + 1);
                results.add(clone);
            } else if (prev != null && next != null && (prev == '_' && current == 'L' && next == 'R' || prev == 'L' && current == 'R' && next == '_')) {
                // R can jump
                var clone = new ArrayList<>(list);
                Collections.swap(clone, i - 1, i + 1);
                results.add(clone);
            }
        }
        return results;
    }
