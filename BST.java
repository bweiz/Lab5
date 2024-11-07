
public class BST<Key extends Comparable<Key>, Value> {
	private Node root;
	
	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int n;
		
		public Node(Key key, Value val, int n) {
			this.key = key;
			this.val = val;
			this.n = n;
		}
	}
		
	public BST() {
		root = null;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node x) {
		if (x == null) return 0;
		else return x.n;
	}
	
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }
	
	public void put(Key key, Value val) {
		if (key == null) return;
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
		
	}
	private Node put(Node x, Key key, Value val) {
		
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0) x.left = put(x.left, key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		x.n = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	   public void delete(Key key) {
	        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
	        root = delete(root, key);
	    }

	    private Node delete(Node x, Key key) {
	        if (x == null) return null;

	        int cmp = key.compareTo(x.key);
	        if      (cmp < 0) x.left  = delete(x.left,  key);
	        else if (cmp > 0) x.right = delete(x.right, key);
	        else { 
	            if (x.right == null) return x.left;
	            if (x.left  == null) return x.right;
	            Node t = x;
	            x = min(t.right);
	            x.right = deleteMin(t.right);
	            x.left = t.left;
	        } 
	        x.n = size(x.left) + size(x.right) + 1;
	        return x;
	    } 
	    public void deleteMin() {
	        root = deleteMin(root);
	    }

	    private Node deleteMin(Node x) {
	        if (x.left == null) return x.right;
	        x.left = deleteMin(x.left);
	        x.n = size(x.left) + size(x.right) + 1;
	        return x;
	    }
	
	private Key min() {
		Node x = min(root);
		return x.key;
	}
	private Node min(Node x) {
		
		if (x.left == null) return x;
		return min(x.left);
	}
	
	private Key max() {
		Node x = max(root);
		return x.key;
	}
	private Node max(Node x) {
		
		if (x.right == null) return x;
		return min(x.right);
	}
	
	public Iterable<Key> keys() {
		return keys(min(), max());
	}
	
	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, hi, lo);
		return queue;
		
	}
	
	
	public Iterable<Key> OrderedTraversal(String type) {
		Queue<Key> queue = new Queue<Key>();
		if (type.equals("In")) {
			InOrder(root, queue);
		} 
		return queue;
	}
	
	private void InOrder(Node x, Queue<Key> q) {
		if (x == null) return;
		
		InOrder(x.left, q);
		q.enqueue(x.key);
		InOrder(x.right, q);
	}
	private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
		if (x == null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0) keys(x.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
		if (cmphi > 0) keys(x.right, queue, lo, hi);
	}
	
}
