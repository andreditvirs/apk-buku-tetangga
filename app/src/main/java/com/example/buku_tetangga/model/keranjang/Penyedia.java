package com.example.buku_tetangga.model.keranjang;

import java.util.List;

public class Penyedia {
    private String itemTitle;
    private List<BukuInPenyedia> subItemList;

    public Penyedia(String itemTitle, List<BukuInPenyedia> subItemList) {
        this.itemTitle = itemTitle;
        this.subItemList = subItemList;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public List<BukuInPenyedia> getSubItemList() {
        return subItemList;
    }

    public void setSubItemList(List<BukuInPenyedia> subItemList) {
        this.subItemList = subItemList;
    }
}
