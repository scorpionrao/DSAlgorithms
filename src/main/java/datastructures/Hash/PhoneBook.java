package datastructures.Hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class PhoneBook {

    public static void main(String[] args) throws IOException {
        new PhoneBook().processQueries();
    }

    FastScanner scanner = new FastScanner();
    private List<Contact> contacts = new ArrayList<>();

    // Hash table
    private List<Contact>[] hashtable;
    // ? WTH
    private int m = 101;
    private int a = -1;
    private int b = -1;

    private Random random = new Random();

    // for comparison in future
    private List<String> resNaive = new ArrayList<>();
    private List<String> resFast = new ArrayList<>();

    public PhoneBook() {
        hashtable = new List[m];
        for(int i = 0; i < m; i++) {
            hashtable[i] = new ArrayList<>();
        }
    }

    private int nextPrime(int n) {
        if(n % 2 == 0) {
            n = n + 1;
        }
        while(!isPrime(n)) {
            n = n + 2;
        }
        return n;
    }

    private boolean isPrime(int n) {
        if(n % 2 == 0) {
            return false;
        }
        for(int i = 3; i * i <= n; i = i + 2) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int hash(int number) {
        int p = nextPrime(10000000);
        int bound = p - 1;
        if(a == -1) {
            a = (random.nextInt(bound) + 1) % p;
        }
        if(b == -1) {
            b = (random.nextInt(bound) + 1) % p;
        }
        int hashval = (((a * (number % p)) % p + (b % p)) % p) % m;
        if(hashval < 0) {
            hashval = hashval + m;
        }
        return hashval;
    }

    public void insertNaive(Query query) {
        boolean wasFound = false;
        for(Contact contact : contacts) {
            if(contact.number == query.number) {
                contact.name = query.name;
                wasFound = true;
                break;
            }
        }
        if(!wasFound) {
            contacts.add(new Contact(query.name, query.number));
        }
    }

    public void insertFast(Query query) {
        int index = hash(query.number);
        for(Contact contact : hashtable[index]) {
            if(contact.number == query.number) {
                contact.name = query.name;
                return;
            }
        }
        hashtable[index].add(new Contact(query.name, query.number));
    }

    public void deleteNaive(Query query) {
        for(Iterator<Contact> it = contacts.iterator(); it.hasNext();) {
            if(it.next().number == query.number) {
                it.remove();
                break;
            }
        }
    }

    public void deleteFast(Query query) {
        int index = hash(query.number);
        Iterator<Contact> it = hashtable[index].iterator();
        while(it.hasNext()) {
            Contact contact = it.next();
            if(contact.number == query.number) {
                it.remove();
                return;
            }
        }
    }

    public void findNaive(Query query) {
        String response = "not found";
        for(Contact contact : contacts) {
            if(contact.number == query.number) {
                response = contact.name;
                break;
            }
        }
        writeResponse(response);
        resNaive.add(response);
    }

    public void findFast(Query query) {
        int index = hash(query.number);
        String response = "not found";
        for(Contact contact : hashtable[index]) {
            if(contact.number == query.number) {
                response = contact.name;
                break;
            }
        }
        writeResponse(response);
        resFast.add(response);
    }

    public void processQuery(Query query) {
        if(query.type.equals("add")) {
            //insertNaive(query);
            insertFast(query);
        } else if (query.type.equals("del")) {
            //deleteNaive(query);
            deleteFast(query);
        } else {
            // findNaive(query);
            findFast(query);
        }
    }

    public void processQueries() throws IOException {
        int queryCount = scanner.nextInt();
        for(int i = 0; i < queryCount; i++) {
            Query query = readQuery();
            processQuery(query);
        }
    }

    public Query readQuery() throws IOException {
        String type = scanner.next();
        int number = scanner.nextInt();
        if(type.equals("add")) {
            String name = scanner.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    public void writeResponse(String response) {
        System.out.println(response);
    }

    class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    class Query {
        String type;
        String name;
        int number;

        // for add and update payloads
        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        // for delete and find payloads
        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
