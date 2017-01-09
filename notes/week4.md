# Priority Queue
Stack
Queue
RandomizedQueue
PriorityQueue

binary heap
Heap-ordered binary tree.
Tree, array representation.
Tree is just used for understanding. Array is the implementation.
1. root is 1
2. parent is at k / 2
3. children is at 2k and 2k + 1

Insertion:
1. Add node at end
2. then swim it up

Demotion:
1. If the parent's key is smaller
2. exchange parent's key with larger children key

Delete:
1. exchange root with the end one.
2. sink the new root.

Heapsort is in-place sorting and nlgn worst case
1. inner poor
2. makes poor of cache memory
3. not stable

Event-driven simulation:
1. Decision on dt

A* search Algorithms, 有权值得traverse method
