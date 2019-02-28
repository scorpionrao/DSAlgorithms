package codecamp;

import java.util.*;

public class DuplicateFiles {

    private static List<List<String>> findDuplicateFiles(String[] fileInput) {
        Map<String, List<String>> map = new HashMap<>();
        for(String path : fileInput) {
            String[] files = path.split(" ");
            for(int i = 1; i < files.length; i++) {
                int delimiter = files[i].indexOf('(');
                String fileName = files[i].substring(0, delimiter);
                String content = files[i].substring(delimiter+1, files[i].length()-1);
                List<String> duplicates = map.getOrDefault(content, new ArrayList<>());
                duplicates.add(files[0] + "/" + fileName);
                map.put(content, duplicates);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for(String content : map.keySet()) {
            if(map.get(content).size() > 1) {
                result.add(map.get(content));
            }
        }
        return result;
    }

    public static void evaluate(String[] fileInput) {
        System.out.println(Arrays.toString(fileInput));
        List<List<String>> duplicateFiles = findDuplicateFiles(fileInput);
        System.out.println("Duplicate Files : ");
        System.out.println(duplicateFiles.toString());
    }

    public static void main(String[] args) {
        String[] fileInput = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        evaluate(fileInput);
    }
}
