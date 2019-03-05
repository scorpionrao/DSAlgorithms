package hashcode.photoslideshow;

import java.util.LinkedHashSet;
import java.util.Set;

class Slide {

    Photo photo1;
    Photo photo2;
    Set<String> tags;

    Slide(Photo photo1, Photo photo2) {
        this.photo1 = photo1;
        this.photo2 = photo2;
        tags = new LinkedHashSet<>();
        tags.addAll(photo1.tags);
        tags.addAll(photo2.tags);
    }

    Slide(Photo photo1) {
        this.photo1 = photo1;
        this.photo2 = null;
        tags = new LinkedHashSet<>();
        tags.addAll(photo1.tags);
    }

    @Override
    public String toString() {
        return "Photo1: " + this.photo1.id + ", Photo2: " + this.photo2.id;
    }
}
