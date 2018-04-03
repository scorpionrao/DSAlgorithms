package datastructures.Hash;

public class IP {

    /*
        log - array of lines(time, IP)
        C - map from IPs to counters
        i - first log line to process
        j - first line in current 1h window


        Every second {
            // UpdateAccessList(log, 0, 0, new HashMap());
            UpdateAccessList(log, i, j, C);
        }

        UpdateAccessList(log, i, j, C) {
            while(log[i].time < Now() {
                C[log[i].IP]++;
                i++;
            }
            while(log[j].time < Now() - 3600) {
                C[log[j].IP]--;
                j++;
            }
        }

        UpdateAccessList(log, i, j, C) {
            while(log[i].time < Now() {
                A[int(log[i].IP)] = A[int(log[i].IP)] + 1;
                i++;
            }
            while(log[j].time < Now() - 3600) {
                A[int(log[j].IP)] = A[int(log[j].IP)] - 1;
                j++;
            }
        }

        AccessedLastHour(IP, C) {
            return C[IP] > 0;
        }

        AccessedLastHour(IP, C) {
            return A[int(IP)] > 0;
        }

        // convert IP to Integer
        int(IP) {
            // move the first part of IP (8-bit format) to the left 24 places in (32-bit format)
            // move the second part of IP (8-bit format) to the left 16 places in (32-bit format)
            // move the third part of IP (8-bit format) to the left 8 places in (32-bit format)
            // move the fourth part of IP (8-bit format) nowhere in (32-bit format)

            return IP[1] * 2 ^ 24 + IP[2] * 2 ^ 16 + IP[3] * 2 ^ 8 + IP[4]
        }

        Asymptotics for Array:
        *) Update - O(1) per log line (array).
        *) Access - O(1) - because it is array.
        *) But needs 2 ^ 32 memory for even few IPs - 0.54 GB

        Asymptotics for List:
        *) n = active IPs, memory reduced to O(n) vvv
        *) Append, Top, Pop - O(1)
        *) Update - O(1) per log line <-->
        *) Access - FindByIP O(n), CountIP O(n) ^^^^
        *) Access - Last hour O(n), Count last hour O(n) ^^^^^

        Desirable Properties:
        *) hash function should be fast to computer - as used in every access request
        *) Different values for different objects
        *) Use direct addressing - O(m) memory
        *) Want small cardinality m
        *) Impossible to have all different values if number of objects |S| is more than m.

        CHAINING:
        *) Technique to store mappings from one type of object to another type of object.

        MAPPING:
        *) Technique to relate one type of object to another type of object.
        *) IPs to Integer
        *) Filename to File on disk
        *) ID to Name
        *) Name to Phone number

        A --> array of m lists (stores hashed value as reference)
        list --> array of maps
        map --> old key to value of the problem trying to solve.

        // c --> longest chain
        // n --> different keys of S
        // m --> different values of Hash function
        // Runtime - O(c + 1)
        // Space - O(n + m)

        // Chain of Maps (list)
        HasKey(O) {
            list = A[h(O)]
            for(o',v' : list) {
                   if( O == o') {
                        return true;
                   }
            }
            return false;
        }

        // Chain of objects (Set)
        Find(O) {
            list = A[h(O)]
            for(o' : list) {
                   if( O == o') {
                        return true;
                   }
            }
            return false;
        }

        // Runtime - O(c + 1)
        // Space - O(n + m)
        Get(O) {
            list = A[h(O)]
            for(o',v' : list) {
                   if( O == o') {
                        return v';
                   }
            }
            return n/a;
        }

        // Runtime - O(c + 1)
        // Space - O(n + m)
        // Chain of maps (List)
        Set(O)

        // Chain of objects (Set)
        Add(O) {
            list = A[h(O)]
            for(o' : list) {
                   if( O == o') {
                        return;
                   }
            }
            list.append(O);
        }

        // Chain of objects (Set)
        Remove(O) {
            if(!Find(O)) {
                return;
            }
            list = A[h(O)]
            list.erase(O);
        }

        HashTable - Implementation of a set or map using hashing is called a hash table.
        Method to implement HashTable - Chaining
        Java - HashSet, HashMap.
     */
}
