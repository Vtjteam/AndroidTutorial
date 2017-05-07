package ir.vtj.lastmusic;

/**
 * Created by hassan on 5/7/17.
 */

public class songArrayList {

    public songArrayList(long thisId, long thisalbumId, String thisTitle, String thisArtist, String thisdata) {
        this.thisId = thisId;
        this.thisalbumId = thisalbumId;
        this.thisTitle = thisTitle;
        this.thisArtist = thisArtist;
        this.thisdata = thisdata;
    }

    public long getThisId() {
        return thisId;
    }

    public void setThisId(long thisId) {
        this.thisId = thisId;
    }

    public long getThisalbumId() {
        return thisalbumId;
    }

    public void setThisalbumId(long thisalbumId) {
        this.thisalbumId = thisalbumId;
    }

    public String getThisTitle() {
        return thisTitle;
    }

    public void setThisTitle(String thisTitle) {
        this.thisTitle = thisTitle;
    }

    public String getThisArtist() {
        return thisArtist;
    }

    public void setThisArtist(String thisArtist) {
        this.thisArtist = thisArtist;
    }

    long thisId;
    long thisalbumId;
    String thisTitle;
    String thisArtist;

    public String getThisdata() {
        return thisdata;
    }

    public void setThisdata(String thisdata) {
        this.thisdata = thisdata;
    }

    String thisdata;
}
