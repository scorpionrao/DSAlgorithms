package hashcode.photoslideshow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PhotoSlideshow {

    String[] paths = {
            "src/main/java/hashcode/photoslideshow/a_example.txt",
            "src/main/java/hashcode/photoslideshow/b_lovely_landscapes.txt",
            "src/main/java/hashcode/photoslideshow/c_memorable_moments.txt",
            "src/main/java/hashcode/photoslideshow/d_pet_pictures.txt",
            "src/main/java/hashcode/photoslideshow/e_shiny_selfies.txt"
    };

    private List<List<Photo>> readInput(String path) {
        List<List<Photo>> photos = new ArrayList<>();
        photos.add(new ArrayList<>());
        photos.add(new ArrayList<>());
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            int numOfPhotos = Integer.parseInt(reader.readLine());
            for (int i = 0; i < numOfPhotos; i++) {
                String[] details = reader.readLine().split(" ");
                Photo photo = new Photo(i, details[0]);
                for(int j = 0; j < Integer.valueOf(details[1]); j++) {
                    photo.addTag(details[j+2]);
                }
                if(photo.isHorizontal) {
                    photos.get(0).add(photo);
                } else {
                    photos.get(1).add(photo);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return photos;
    }



    private int calculateScore(List<Slide> slideShow) {
        int score = 0;
        for(int i = 0; i < slideShow.size() - 1; i++) {
            Set<String> slide1Tags = slideShow.get(i).tags;
            Set<String> slide2Tags = slideShow.get(i+1).tags;

            List<String> commonTags = new ArrayList<>(slide1Tags);
            commonTags.retainAll(new ArrayList<>(slide2Tags));
            int commonTagCount = commonTags.size();

            List<String> slide1OnlyTags = new ArrayList<>(slide1Tags);
            slide1OnlyTags.removeAll(commonTags);
            int slide1OnlyTagCount = slide1OnlyTags.size();

            List<String> slide2OnlyTags = new ArrayList<>(slide2Tags);
            slide2OnlyTags.removeAll(commonTags);
            int slide2OnlyTagCount = slide2OnlyTags.size();

            int interestFactor = Math.min(commonTagCount, Math.min(slide1OnlyTagCount, slide2OnlyTagCount));
            score = score + interestFactor;
        }
        return score;
    }

    private void printInput(List<List<Photo>> photos) {
        System.out.println("INPUT - HORIZONTAL");
        for(Photo photo : photos.get(0)) {
            System.out.println(photo.toString());
        }
        System.out.println("INPUT - VERTICAL");
        for(Photo photo : photos.get(1)) {
            System.out.println(photo.toString());
        }
    }

    private void printOutput(List<Slide> slideShow) {
        System.out.println("OUTPUT");
        System.out.println(slideShow.size());
        for(Slide slide : slideShow) {
            System.out.println("" + slide.photo1.id + (slide.photo2 == null ? "" : " " + slide.photo2.id));
        }
    }

    private void evaluate(SlideShow slideShow) {
        System.out.println("Approach - " + slideShow.getDescription());
        int totalScore = 0;
        for(String path : paths) {
            List<List<Photo>> photos = readInput(path);
            List<Slide> slides = slideShow.createSlideShow(photos);
            int score = calculateScore(slides);
            String fileName = path.substring(path.lastIndexOf("/")+1);
            System.out.println(fileName + " - " + score);
            totalScore = totalScore + score;
        }
        System.out.println("Total Score - " + totalScore);
    }

    public static void main(String[] args) {

        /* Best suited for - a_example.txt */
        PhotoSlideshow photoSlideshow = new PhotoSlideshow();
        photoSlideshow.evaluate(new InputOrderPhotosAndSimpleSlideSort());

        photoSlideshow.evaluate(new InputOrderPhotosAndReverseSlideSort());
        /* Best suited for - e_shiny_selfies.txt */
        /* Best suited for - c_memorable_moments.txt */
        /* Best suited for - b_lovely_landscapes.txt */
        photoSlideshow.evaluate(new PopularTagsPhotosInterleaved());
        /* Best suited for - d_pet_pictures.txt */
        photoSlideshow.evaluate(new MostTagsPhotosAndSlidesInterleaved());
    }
}
