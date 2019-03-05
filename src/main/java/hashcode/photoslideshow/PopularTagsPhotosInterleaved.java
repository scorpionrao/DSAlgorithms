package hashcode.photoslideshow;

import java.util.*;

public class PopularTagsPhotosInterleaved implements SlideShow {

    public static class PhotoRefined {
        Photo photo;
        int photoScore;

        PhotoRefined(Photo photo, int photoScore) {
            this.photo = photo;
            this.photoScore = photoScore;
        }
    }

    Map<String, Integer> tagScore = new HashMap<>();
    Map<Photo, Integer> photoScore = new HashMap<>();
    List<PhotoRefined> photoRefinedList = new ArrayList<>();

    @Override
    public String getDescription() {
        return "PhotoSort: Popular Tags -> Popular Photos. SlideSort: Interleaved";
    }

    @Override
    public List<Slide> createSlideShow(List<List<Photo>> photos) {

        updateTagScore(photos.get(0));
        updateTagScore(photos.get(1));

        updatePhotoScore(photos.get(0));
        updatePhotoScore(photos.get(1));

        Collections.sort(photoRefinedList, new Comparator<PhotoRefined>() {
            @Override
            public int compare(PhotoRefined pr1, PhotoRefined pr2) {
                return pr1.photoScore - pr2.photoScore;
            }
        });

        List<Slide> slides = new ArrayList<>();

        int rightMostVertical = photoRefinedList.size() - 1;
        for(int i = 0; i < photoRefinedList.size(); i++) {
            if(photoRefinedList.get(i).photo.isHorizontal) {
                slides.add(new Slide(photoRefinedList.get(i).photo));
            } else {
                while(i < rightMostVertical && photoRefinedList.get(rightMostVertical).photo.isHorizontal) {
                    rightMostVertical--;
                }
                slides.add(new Slide(photoRefinedList.get(i).photo, photoRefinedList.get(rightMostVertical).photo));
                rightMostVertical--;
            }
        }
        return slides;
    }

    private void updateTagScore(List<Photo> photos) {
        for(Photo photo : photos) {
            for(String tag : photo.tags) {
                int frequency = tagScore.getOrDefault(tag, 0);
                tagScore.put(tag, frequency++);
            }
        }
    }

    private void updatePhotoScore(List<Photo> photos) {
        for(Photo photo : photos) {
            for(String tag : photo.tags) {
                int currentValue = photoScore.getOrDefault(photo, 0);
                photoScore.put(photo, currentValue + tagScore.get(tag));
            }
            photoRefinedList.add(new PhotoRefined(photo, photoScore.get(photo)));
        }
    }
}
