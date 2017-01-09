BST

## 2-3 trees, all the length of path from leaf to root are same.
If there are 2 keys, there must be 3 children
If there are 1 keys, there must be 2 children
If it's full, only split the root and increase the height by 1.

Perfect balance, all the time.
Guarantee log performance for search, insert, delete.

## Red black tree is a special 2-3 tree

## B-Tree: M - 1 keys, B+tree, B* tree, B# tree.

root at least 2 key, other node at least M/2

Like 2-3, but more keys

insertion and search just like Red black tree.

File system

Symbol table: TreeMap TreeSet reb-black tree

# Geometric data

### 1d Range search. : actions:
1. insert
2. delete
3. search
4. search in Range
5. count in Range

### line segment intersection: verical and horizontal lines. find all intersection
scan from left to right, if there is a vertical line, do the range search in y coorinator. P13

### Kd trees: extension of BST.
1. insert 2D keys
2. delete 2D keys
3. search 2D keys
4. search in Range
5. count in range

#### gird implementation: for equal distributed

space-partition trees  P24, vertical divided and horizontal divided

nearest neighbor search. , k-d tree, recursive 1 dimention once  Jon Bentley

## 1d interval search P42
left endpoint as the BST key, store max endpoint of right endpoint in subtree

## Rectangle intersection
sweep-line analysis
1 d interval search instead of 1 d range search
P56 summary
