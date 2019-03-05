package hashcode.photoslideshow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MostTagsPhotosAndSlidesInterleaved implements SlideShow {

    @Override
    public String getDescription() {
        return "Most Tags Photos and Slides Interleaved";
    }

    @Override
    public List<Slide> createSlideShow(List<List<Photo>> photos) {
        List<Photo> allPhotos = new ArrayList<>(photos.get(0));
        allPhotos.addAll(new ArrayList<>(photos.get(1)));


        allPhotos.sort(new Comparator<Photo>() {
            @Override
            public int compare(Photo p1, Photo p2) {
                return new Integer(p1.tags.size()).compareTo(new Integer(p2.tags.size()));
            }
        });

        List<Slide> slides = new ArrayList<>();

        int rightMostVertical = allPhotos.size() - 1;
        for(int i = 0; i < allPhotos.size(); i++) {
            if(allPhotos.get(i).isHorizontal) {
                slides.add(new Slide(allPhotos.get(i)));
            } else {
                while(i < rightMostVertical && allPhotos.get(rightMostVertical).isHorizontal) {
                    rightMostVertical--;
                }
                slides.add(new Slide(allPhotos.get(i), allPhotos.get(rightMostVertical)));
                rightMostVertical--;
            }
        }
        return slides;
    }
}
