package hashcode.photoslideshow;

import java.util.ArrayList;
import java.util.List;

public class InputOrderPhotosAndReverseSlideSort implements SlideShow {

    @Override
    public String getDescription() {
        return "PhotoSort: Input order. SlideSort: Vertical followed by horizontals.";
    }

    @Override
    public List<Slide> createSlideShow(List<List<Photo>> photos) {
        List<Slide> slideShow = new ArrayList<>();
        for(int i = 0; i < photos.get(1).size(); i = i + 2) {
            Photo photo1 = photos.get(1).get(i);
            Photo photo2 = photos.get(1).get(i+1);
            slideShow.add(new Slide(photo1, photo2));
        }
        for(Photo photo : photos.get(0)) {
            slideShow.add(new Slide(photo));
        }
        return slideShow;
    }

}
