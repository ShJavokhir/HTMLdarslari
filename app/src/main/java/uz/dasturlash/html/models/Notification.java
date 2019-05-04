package uz.dasturlash.html.models;

public class Notification {
    private String id;
    private String title;
    private String text;
    private String image_url;
    private String date_created;
    private String is_active;
    private String view;
    private String max_view;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getIs_active() {
        return is_active;
    }

    public String getView() {
        return view;
    }

    public String getMax_view() {
        return max_view;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public void setView(String view) {
        this.view = view;
    }

    public void setMax_view(String max_view) {
        this.max_view = max_view;
    }
}
