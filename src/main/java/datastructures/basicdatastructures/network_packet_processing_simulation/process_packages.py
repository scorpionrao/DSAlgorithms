# python3

class datastructures.basicdatastructures.Request:
    def __init__(self, arrival_time, process_time):
        self.arrival_time = arrival_time
        self.process_time = process_time

class datastructures.basicdatastructures.Response:
    def __init__(self, dropped, start_time):
        self.dropped = dropped
        self.start_time = start_time

class datastructures.basicdatastructures.Buffer:
    def __init__(self, size):
        self.size = size
        self.finish_time_ = []

    def Process(self, request1):
        # write your code here
        return datastructures.basicdatastructures.Response(False, -1)

def ReadRequests(count):
    request1s = []
    for i in range(count):
        arrival_time, process_time = map(int, input().strip().split())
        request1s.append(datastructures.basicdatastructures.Request(arrival_time, process_time))
    return request1s

def ProcessRequests(request1s, buffer1):
    response1s = []
    for request1 in request1s:
        response1s.append(buffer1.Process(request1))
    return response1s

def PrintResponses(response1s):
    for response1 in response1s:
        print(response1.start_time if not response1.dropped else -1)

if __name__ == "__main__":
    size, count = map(int, input().strip().split())
    request1s = ReadRequests(count)

    buffer1 = datastructures.basicdatastructures.Buffer(size)
    response1s = ProcessRequests(request1s, buffer1)

    PrintResponses(response1s)
