package enums;

import randomizer.DataRandom;

public enum Kind {
    GLOBAL_PASS("Global Mobile Pass"),
    AUSTRIA("Austria Mobile Pass"),
    BENELUX("Benelux Mobile Pass"),
    BULGARIA("Bulgaria Mobile Pass"),
    CROATIA("Croatia Mobile Pass"),
    CZECH_REPUBLIC("Czech Republic Mobile Pass"),
    DENMARK("Denmark Mobile Pass"),
    FINLAND("Finland Mobile Pass"),
    FRANCE("France Mobile Pass"),
    GREAT_BRITAN("Great-Britain Mobile Pass"),
    GREEK("Greek Islands Mobile Pass"),
    GREECE("Greece Mobile Pass"),
    HUNGARY("Hungary Mobile Pass"),
    IRELAND("Ireland Mobile Pass"),
    ITALY("Italy Mobile Pass"),
    LITHUANIA("Lithuania Mobile Pass"),
    MACEDONIA("Macedonia Mobile Pass"),
    NORWAY("Norway Mobile Pass"),
    POLAND("Poland Mobile Pass"),
    PORTUGAL("Portugal Mobile Pass"),
    ROMANIA("Romania Mobile Pass"),
    SCANDINAVIA("Scandinavia Mobile Pass"),
    SERBIA("Serbia Mobile Pass"),
    SLOVAKIA("Slovakia Mobile Pass"),
    SLOVENIA("Slovenia Mobile Pass"),
    SPAIN("Spain Mobile Pass"),
    SWEDEN("Sweden Mobile Pass"),
    TURKEY("Turkey Mobile Pass");

    private String kind;

    Kind(String kin) {
        kind = kin;
    }

    public String getKind() {
        return kind;
    }

    public String randomKind() {
        Kind[] list = values();
        return list[DataRandom.generator.nextInt(list.length)].getKind();
    }
}
