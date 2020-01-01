package com.example.buku_tetangga;

import com.google.gson.annotations.SerializedName;

public class ModalRakBuku {
        /*
        INSTANCE FIELDS
         */
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("propellant")
        private String propellant;
        @SerializedName("imageurl")
        private String imageURL;
        @SerializedName("technologyexists")
        private int technologyExists;

        public ModalRakBuku(int id, String name, String propellant, String imageURL, int technologyExists) {
            this.id = id;
            this.name = name;
            this.propellant = propellant;
            this.imageURL = imageURL;
            this.technologyExists = technologyExists;
        }

        /*
         *GETTERS AND SETTERS
         */
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getPropellant() {
            return propellant;
        }

        public String getImageURL() {
            return imageURL;
        }

        public int getTechnologyExists() {
            return technologyExists;
        }

        public String setStock(){
            if(getTechnologyExists() == 0){
                return "Tersedia";
            }
            return "Habis";
        }

        /*
        TOSTRING
         */
        @Override
        public String toString() {
            return name;
        }

}
