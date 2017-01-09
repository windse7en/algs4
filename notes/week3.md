## Merge Sort
Divide and conquer, merge sort improvement:
1. If there is only a small size(cutoff), use insertsort instead of MS, as it costs too much memory for stack.
2. If two parts are already sorted, just returns.
3. swap original array and temp array, as it will reduce the time to allocate memory.
Compare times and array access is O(n lg n)

Merge Sort 是stable的sort，还有insertion sort也是，遇到equal case，可以保证相等的顺序。

Quick Selection, best at O(n).

## Duplicate Keys:
For duplicate keys, merge sort is from 1/2 n lg n to n lg n, quick sort is quadratic time.

3-way partitioning

Java Array sort: use quicksort for primitive type of data, use mergesort for objects.

### Internal Sort
### External Sort
### String/Redix Sort
* Stable?
* Parallel?
* Deterministic?
* Keys all distinct?
* Multiple key types?
* linked list or arrays?
* randomly ordered?
* guaranteed performance?
