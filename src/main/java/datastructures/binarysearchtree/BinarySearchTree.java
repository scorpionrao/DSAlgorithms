package datastructures.binarysearchtree;

public class BinarySearchTree {

    /*
        Local Search Problems:
            - Word Search   : Find a word in DictionaryIter
            - Date IndexRange    : all emails in a given period
            - Closest       : closest person in height

        General: RangeSearch, NearestNeighbors

        DataStructure: Store keys in ordered set

        Dynamic Data Structure: Search + INSERT and DELETE
                        RangeSearch     NearestNeighbors    Insert      Delete
        HashTable       XXX             XXX                 O(1)        O(1)
        Array(Dynamic)  O(N)            O(N)                O(1)        O(1)
        Sorted Array    O(log N)        O(log N)            O(N)        O(N)
        Linked List     O(N)            O(N)                O(1)        O(1)
        Search Tree     O(log N)        O(log N)            O(log N)    O(log N)

        Find/Search:

        Find(R, k) {
            if(R.key == k) {
                return R
            } else if(R.key < k) {
                return Find(R.right, k)
            } else if(R.key > k) {
                if(R.left != null) {
                    return Find(R.left, k)
                }
                return R;
            }
        }

        Next(N) {
            if (N.right != null) {
                return LeftDescendant(N.right)
            } else {
                return RightAncestor(N)
            }
        }

        LeftDescendant(N) {
            if N.left == null {
                return N
            } else {
                // left most leaf
                return LeftDescendant(N)
            }
        }

        RightAncestor(N) {
            if(N == null) {
                return null
            // key is on the left of parent
            } else if(N.key < N.parent.key) {
                return N.parent
            // key is on the right of parent
            } else {
                return RightAncestor(N.parent)
            }
        }

        RangeSearch(x,y,R) {
            List = {}
            N = Find(x, R);
            while (N.key <= y) {
                if(N.key >= x) {
                    List.append(N)
                }
                N = Next(N)
            }
            return List
        }

        Insert(k,R) {
            P = Find(k,R)
            if(k < P.key) {
                P.leftchild = new Node(k)
            } else {
                P.rightchild = new Node(k)
            }
        }

        Delete(N) {
            if(N.right == null) {
                remove N, N.parent.left = N.left
            } else {
                X = Next(N)
                // X.left = null GUARANTEED
                N = X;
                X.parent.left = X.right
            }
        }

        // Balance Tree
        RotateRight(X) {
            P = X.parent
            Y = X.left
            B = Y.right
            Y.parent = P
            P.appropriateChild = Y
            X.parent = Y, Y.right = X
            B.parent = X, X.left = B
        }

        AVLInsert(k, R) {
            Insert(k, R)
            N = Find(k, R)
            Rebalance(N)
        }

        AVLDelete(k, R) {
            Delete(k)
            M = Parent of node replacing N
            Rebalance(M)
        }

        Rebalance(N) {
            P = N.parent;
            if(N.left.height > N.right.height + 1 {
                RebalanceRight(N)
            }
            if(N.right.height > N.left.height + 1 {
                RebalanceLeft(N)
            }
            AdjustHeight(N)
            if P != null {
                Rebalance(P)
            }
        }

        AdjustHeight(N) {
            N.height = 1 + max(N.left.height, N.right.height);
        }


     */
}
