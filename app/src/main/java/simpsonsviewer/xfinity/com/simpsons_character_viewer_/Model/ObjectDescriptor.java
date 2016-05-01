package simpsonsviewer.xfinity.com.simpsons_character_viewer_.Model;

/**
 * Created by m14x on 05/01/2016.
 */
public class ObjectDescriptor {

    private String characterName = "";
    private String characterDetail = "";
    private String imageUrl;
    private String detailUrl;


    public ObjectDescriptor(){

    }


    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterDetail() {
        return characterDetail;
    }

    public void setCharacterDetail(String characterDetail) {
        this.characterDetail = characterDetail;
    }






}
