# python3

import sys, threading
sys.setrecursionlimit(10**7) # max depth of recursion
threading.stack_size(2**27)  # new thread will get stack of such size

class datastructures.basicdatastructures.tree_height.tree_height:
        def read(self):
                self.n = int(sys.stdin.readline())
                self.parent = list(map(int, sys.stdin.readline().split()))

        def compute_height(self):
                # Replace this code with a faster implementation
                maxHeight = 0
                for vertex in range(self.n):
                        height = 0
                        row = vertex
                        while row != -1:
                                height += 1
                                row = self.parent[row]
                        maxHeight = max(maxHeight, height);
                return maxHeight;

def main():
  tree = datastructures.basicdatastructures.tree_height.tree_height()
  tree.read()
  print(tree.compute_height())

threading.Thread(target=main).start()
