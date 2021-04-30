package com.book.spring.chapter4.impl;

import com.book.spring.chapter4.ifs.CompactDisc;
import java.util.List;

/**
 * @author zhangyoubao
 * @version 2021/4/9
 */
public class BlankDisc implements CompactDisc {

    private String title;
    private String artist;
    private List<String> tracks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    @Override
    public void playTrack(int number) {
        System.out.println("playing track " + tracks.get(number - 1));
    }
}
