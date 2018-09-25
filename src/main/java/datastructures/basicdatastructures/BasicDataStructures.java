package datastructures.basicdatastructures;

public class BasicDataStructures {

    /*

        ARRAYS:

        Read operations:
        O(1) - Contiguous memory of equal size.

        Write operations:

                    Add        Remove
         End        O(1)        O(1)
         Arbitrary  O(N)        O(N)


         SINGLE LINKED LISTS -> DOUBLY LINKED LISTS:

                                no tail         with tail

         Add - PushFront(k)     O(1)
         Add - PushBack(k)      O(n)            O(1)
         Add - Before(n,k)      O(n) -> O(1)
         Add - After(n,k)       O(n)

         Remove - PopFront()    O(1)
         Remove - PopBack()     O(n) -> O(1)
         Remove - erase(k)      O(n)

         Read - TopFront        O(1)
         Read - TopBack         O(n)
         Read - Find(k)         O(n)
         Read - Empty()         O(1)

         PushFront(key) {

            node = new Node
            node.key = key
            node.next = head
            head = node
            if tail = null {
                tail = head
            }
         }

         PopFront() {
            if head == nill {
                ERROR: empty list
            }
            head = head.next
            if(head == nill) {
                tail = nill
            }
         }

         PushBack(key) {
            node = new Node
            node.key = key
            node.next = nill
            if(tail == nill) {
                head = tail = node
            } else {
                tail.next = node
                tail = node
            }
         }

         ABSTRACT STACKS:

         Using array - waste of unallocated memory, stack overflow is size increases

         Using Linked groupByProject - waste of additional pointer to each node

         ABSTRACT TREES:

         A tree is : a node with key, optional parent, list of child trees.

         Height(tree) {
            if(tree is nill) return 0
            return 1 + max(Height(tree.left) + Height(tree.right))
         }

         null - 0
         leaf - 1
         parent - 2
         root - 3

        Size(tree) {
            if(tree is nill) return 0
            return 1 + max(size(tree.left), size(tree.right))
        }

        Walking a tree:

            BFS1: Travel all nodes at one level before traversing child nodes
            DFS: Travel one sub-tree before invoking other.

        DFS - InOrder Traversal

        InOrderTraversal(tree) {
            if tree = null {
                return
            }
            InOrderTraversal(tree.left)
            printBoard(tree.key)
            InOrderTraversal(tree.right)
        }

        DFS - PreOrder Traversal

        PreOrderTraversal(tree) {
            if tree = null {
                return
            }
            printBoard(tree.key)
            PreOrderTraversal(tree.left)
            PreOrderTraversal(tree.right)
        }

        DFS - PostOrder Traversal

        PostOrderTraversal(tree) {
            if tree = null {
                return
            }
            PostOrderTraversal(tree.left)
            PostOrderTraversal(tree.right)
            printBoard(tree.key)
        }

        BFS1 - LevelTraversal

        LevelTraversal(tree) {
            if(tree == null) {return}
            queue = new Queue
            queue.enQueue(tree)

            while !queue.empty() {
                node = queue.deQueue()
                printBoard(node)
                if(node.left != null) {
                    queue.enQueue(node.left)
                }
                if(node.right != null) {
                    queue.enQueue(node.right)
                }
            }
        }

     */
}
