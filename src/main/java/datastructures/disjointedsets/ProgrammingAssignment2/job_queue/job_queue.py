# python3

class JobQueue1:
    def read_data(self):
        self.num_workers, m = map(int, input().split())
        self.jobs = list(map(int, input().split()))
        assert m == len(self.jobs)

    def write_response(self):
        for row in range(len(self.jobs)):
          print(self.assigned_workers[row], self.start_times[row])

    def assign_jobs(self):
        # TODO: replace this code with a faster algorithm.
        self.assigned_workers = [None] * len(self.jobs)
        self.start_times = [None] * len(self.jobs)
        next_free_time = [0] * self.num_workers
        for row in range(len(self.jobs)):
          next_worker = 0
          for col in range(self.num_workers):
            if next_free_time[col] < next_free_time[next_worker]:
              next_worker = col
          self.assigned_workers[row] = next_worker
          self.start_times[row] = next_free_time[next_worker]
          next_free_time[next_worker] += self.jobs[row]

    def solve(self):
        self.read_data()
        self.assign_jobs()
        self.write_response()

if __name__ == '__main__':
    job_queue = JobQueue1()
    job_queue.solve()

