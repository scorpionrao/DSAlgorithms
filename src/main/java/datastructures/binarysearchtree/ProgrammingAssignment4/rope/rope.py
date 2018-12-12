# python3

import sys

class Rope:
	def __init__(self, s):
		self.s = s
	def result(self):
		return self.s
	def process(self, row, col, k):
                # Write your code here
                

rope = Rope(sys.stdin.readline().strip())
q = int(sys.stdin.readline())
for _ in range(q):
	row, col, k = map(int, sys.stdin.readline().strip().split())
	rope.process(row, col, k)
print(rope.result())
