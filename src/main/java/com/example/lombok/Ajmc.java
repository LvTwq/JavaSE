package com.example.lombok;

/**
 * @author 吕茂陈
 * @date 2021/09/26 17:30
 */
public class Ajmc {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ajmc(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Ajmc.AjmcBuilder builder() {
        return new Ajmc.AjmcBuilder();
    }

    public static class AjmcBuilder {
        private String id;
        private String name;

        public Ajmc.AjmcBuilder id(String id) {
            this.id = id;
            return this;
        }

        public Ajmc.AjmcBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Ajmc build() {
            return new Ajmc(this.id, this.name);
        }

        @Override
        public String toString() {
            return "AjmcBuilder{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Ajmc{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
