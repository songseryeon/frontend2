package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookApiResponse {

    @SerializedName("documents")
    public ArrayList<Document> documents;
    @SerializedName("meta")
    public Meta metas;

    public static class Document implements Serializable {
        @SerializedName("authors")
        private List<String> authors;

        @SerializedName("contents")
        private String contents;

        @SerializedName("publisher")
        private String publisher;

        @SerializedName("thumbnail")
        private String thumbnail;

        @SerializedName("title")
        private String title;

        @SerializedName("isbn")
        private String isbn;

        public List<String> getAuthors() {
            return authors;
        }

        public String getContents() {
            return contents;
        }

        public String getPublisher() {
            return publisher;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public String getIsbn() { return isbn; }
    }

    public static class Meta implements Serializable {
        @SerializedName("is_end")
        private boolean is_end;

        @SerializedName("pageable_count")
        private int pageable_count;

        @SerializedName("total_count")
        private int total_count;

        public boolean isIs_end() {
            return is_end;
        }

        public int getPageable_count() {
            return pageable_count;
        }

        public int getTotal_count() {
            return total_count;
        }
    }
}
