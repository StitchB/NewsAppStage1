package com.example.android.newsappstage1;

/**
 * An {@link News} object contains information related to a single news.
 */
public class News {

    /** Title of the news */
    private String newsTitle;

    /** Section name of the news */
    private String newsSectionName;

    /** Publication date of the news */
    private String newsPublicationDate;

    /** Website URL of the news */
    private String newsUrl;

    /** Image for the news */
    private String newsImage;

    /**
     * Constructs a new {@link News} object.
     *
     * @param title is the title of the news
     * @param section is the section where the news happened
     * @param publicationDate is the news publication date
     * @param url is the website URL to find more details about the news
     * @param image is the image for the news
     */
    public News(String title, String section, String publicationDate, String url, String image) {
        newsTitle = title;
        newsSectionName = section;
        newsPublicationDate = publicationDate;
        newsUrl = url;
        newsImage = image;
    }

    /**
     * Returns the magnitude of the news.
     */
    public String getTitle() { return newsTitle; }

    /**
     * Returns the section name of the news.
     */
    public String getSectionName() {
        return newsSectionName;
    }

    /**
     * Returns the publication date of the news.
     */
    public String getPublicationDate() {
        return newsPublicationDate;
    }

    /**
     * Returns the website URL to find more information about the news.
     */
    public String getUrl() {
        return newsUrl;
    }

    /**
     * Returns the image for the news.
     */
    public String getImage() {
        return newsImage;
    }
}