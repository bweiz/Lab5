import java.util.Iterator;

public class heapMinPQ<Key> {
	private Key[] pq;
	private int n;
	
	public heapMinPQ(int initCap) {
		pq = (Key[]) new Object[initCap + 1];
		n = 0;
	}
	public heapMinPQ() {
		this(4);
	}
	
	public void insert(Key x) {
		if (x == null) return;

		
		pq[++n] = x;
		swim(n);
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	public int size() {
		return n;
	}
	
	public Key delMin() {
		Key min = pq[1];

		exch(1, n--);
		sink(1);

		

		return min;
	}
	
	private void swim(int k) {
		while (k > 1 && greater(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}
	
	private void sink(int k) {
		while (2*k <= n) {
			int j = 2*k;
			if (j < n && greater(j, j+1)) j++;
			if(!greater(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
	private boolean greater(int i, int j) {
		return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
	}
	
	private boolean less(int i, int j) {
		return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
	}
	private void exch(int i, int j) {
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}
	
    private void resize(int capacity) {
        heapMinPQ<Key> temp = new heapMinPQ<Key>(capacity);
        for (int i = 0; i < n; i++) {
            if (pq[i] != null) {
                temp.insert(pq[i]);
            }
        }
        pq = temp.pq;
    }
    

    
}
