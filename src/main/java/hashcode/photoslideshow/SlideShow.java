package hashcode.photoslideshow;

import java.util.List;

public interface SlideShow {

    String getDescription();

    List<Slide> createSlideShow(List<List<Photo>> photos);
}

