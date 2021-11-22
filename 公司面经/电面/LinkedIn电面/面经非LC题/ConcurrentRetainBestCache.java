import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/* Gets some data. If possible, retrieves it from cache to be fast. If the data is not cached,
 * retrieves it from the data source. If the cache is full, attempt to cache the returned data,
 * evicting the T with lowest rank among the ones that it has available
 * If there is a tie, the cache may choose any T with lowest rank to evict.
 */

// What if rank is defined as number of reads of element in cache?
// LRU
// Let's assume that rank‍‌‌‌‌‌‌‍‌‌‌‍‍‌‍‌‍‌‌‌ can change dynamicly. It is not immutable, but it is not LRU. We do not know how it is changed如定义


// How to consider thread safe? ‍‌‌‌‌‌‌‍‌‌‌‍‍‌‍‌‍‌‌‌这个重点讨论不同的锁
//1。  Use an existing caching solution like EHcache： open source Java distributed cache
//2。 Use the Spring framework which got an easy way to cache results of a method with a simple @Cacheable annotation
//3。 Use one of the synchronized maps like ConcurrentHashMap
// 4。 laze cache? 不先写入数据库，我的理解是可能之后aschronize去跑？
public class ConcurrentRetainBestCache<K, T extends ConcurrentRetainBestCache.Rankable> {
    /* Constructor with a data source (assumed to be slow) and a cache size */
    int entriesToRetain;
    ConcurrentHashMap<K, T> cache;
    PriorityQueue<Wrapper> pq;
    DataSource<K, T> ds;
    ReentrantLock lock;
    public ConcurrentRetainBestCache(DataSource<K, T> ds, int entriesToRetain) {
        this.entriesToRetain = entriesToRetain;
        this.ds = ds;
        this.cache = new ConcurrentHashMap<>();
        this.pq = new PriorityQueue<>((a, b) -> Long.compare(a.value.getRank(), b.value.getRank()));
        this.lock = new ReentrantLock();
    }

    public T get(K key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        T valueFromDs = ds.get(key);
        this.lock.lock();

        putCache(key, valueFromDs);
        this.lock.unlock();

        return valueFromDs;
    }

    public void putCache(K key, T value) {
        if (cache.size() == entriesToRetain) {
            evict();
        }

        cache.put(key, value);
        pq.offer(new Wrapper(key, value));
        entriesToRetain++;
    }

    public void evict() {
        Wrapper evictItem = pq.poll();
        cache.remove(evictItem.key);
        entriesToRetain--;
    }

    class Wrapper {
        K key;
        T value;

        public Wrapper(K key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    /*
     * For reference, he‍‌‌‌‌‌‌‍‌‌‌‍‍‌‍‌‍‌‌‌re are the Rankable and DataSource interfaces.
     * You do not need to implement them, and should not make assumptions
     * about their implementations.
     */
    public interface Rankable {
        /**
         * Returns the Rank of this object, using some algorithm and potentially
         * the internal state of the Rankable.
         */
        long getRank();
    }
    public interface DataSource<K, T extends Rankable> {
        T get(K key);
    }
}


