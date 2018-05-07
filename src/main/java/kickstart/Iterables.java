package kickstart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

public class Iterables {

    /*
        Implementing Iterable allows iterating through the class
        because an iterator is available
     */
    public static class UrlLibrary implements Iterable<String> {

        LinkedList<String> linkedList = new LinkedList<>();

        public UrlLibrary() {
            linkedList.add("https://www.twitter.com");
            linkedList.add("https://www.youtube.com");
            linkedList.add("https://www.google.com");
        }

        public class InnerClass implements Iterator<String> {

            private int indexOfUrlForIterator = 0;

            @Override
            public boolean hasNext() {
                return indexOfUrlForIterator < linkedList.size();
            }

            @Override
            public String next() {
                StringBuilder sb = new StringBuilder();
                try {
                    URL url = new URL(linkedList.get(indexOfUrlForIterator));
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                    sb = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    bufferedReader.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                indexOfUrlForIterator++;
                return sb.toString();
            }

            @Override
            public void remove() {
                linkedList.remove(indexOfUrlForIterator);
            }
        }

        // CREATE OWN ITERATOR
        @Override
        public Iterator<String> iterator() {
            return new InnerClass();
            // return linkedList.iterator();
        }
    }
    public static void main(String[] args) {
        UrlLibrary library = new UrlLibrary();
        for(String html : library) {
            System.out.println(html.length());
            System.out.println(html);
        }
    }
}
