package com.example.vinhtruong.new_week1.dto;

import java.util.List;

public class TrailerListingDTO {
    private List<TrailerListDTO> youtube;

    public List<TrailerListDTO> getYoutube() {
        return youtube;
    }

    public static class TrailerListDTO{
        private String name;
        private String size;
        private String source;
        private String type;

        public String getName() {
            return name;
        }

        public String getSize() {
            return size;
        }

        public String getSource() {
            return source;
        }

        public String getType() {
            return type;
        }
    }
}
