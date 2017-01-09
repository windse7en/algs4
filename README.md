In java.
会搜索当前目录。
## Install
1. Downlaod code from [algs4 site](http://algs4.cs.princeton.edu/home/)
2. Install java 6, config all the settings (findbugs, checkstyle, java-algs4)
3. Testing.

## Shortcut
j4:   java-algs4
jc4:  javac-algs4
c4:   checkstyle-algs4
f4:   findbugs-algs4

## Percolation:
http://coursera.cs.princeton.edu/algs4/checklists/percolation.html

## Sorting

### Selection sort
N time exchange, find the final position only one time, But, n^2 comparition always

### Insertion sort
Inversion: an inversion is a pair of keys that are out of order.
A E E L M O T R X P S
T-R T-P T-S R-P X-P X-S (6 inversions)

An array is partially sorted if the number of inversions is less or equal than cN.

Insertion sort is good at partial sorted array

### Shellsort
h-sorting array, each h=4, 每隔h个，已经sorted了。
就是insertion sort，不过每次是h个间隔，不是1个。
1. Big increments => small subarray, insertion sort good
2. Small increments => nearly sorted, insertion sort is good

Sequence: 1, 5, 19, 41. (9 x 4^i) - (9 x 2^i) + 1, or 3x + 1

### Knuth shuffle
random pick between 0 and i at random and switch.

### Convex Hull
Smallest convex set containing all points

Robot moving plan
farthest point
geometric properties
Graham scan demo.

Clockwise, keep clockwise always.

Dutch national flag problem
http://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
