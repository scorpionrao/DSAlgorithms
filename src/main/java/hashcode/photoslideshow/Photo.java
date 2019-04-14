package hashcode.photoslideshow;

import java.util.LinkedHashSet;
import java.util.Set;

class Photo {
    int id;
    boolean isHorizontal;
    Set<String> tags;

    Photo(int id, String layout) {
        this.id = id;
        this.isHorizontal = "H".equals(layout);
        this.tags = new LinkedHashSet<>();
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", layout: " + (isHorizontal ? "H" : "V") + ", tags: " + tags.toString();
    }

    @Override
    public boolean equals(Object other) {
        return this.id == ((Photo) other).id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
