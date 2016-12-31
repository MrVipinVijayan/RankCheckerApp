package alexa_ranks.mysmartshoppie.com.alexademo;

public class Rank {
    private String websiteUrl;
    private String globalRank;

    public String getCountryRank() {
        return countryRank;
    }

    public void setCountryRank(String countryRank) {
        this.countryRank = countryRank;
    }

    private String countryRank;

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getGlobalRank() {
        return globalRank;
    }

    public void setGlobalRank(String globalRank) {
        this.globalRank = globalRank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRankInCountry() {
        return rankInCountry;
    }

    public void setRankInCountry(String rankInCountry) {
        this.rankInCountry = rankInCountry;
    }

    private String country;
    private String rankInCountry;
}
