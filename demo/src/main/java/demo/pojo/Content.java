package demo.pojo;

import java.util.Date;

public class Content {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column content.contentId
     *
     * @mbggenerated
     */
    private String contentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column content.contendDate
     *
     * @mbggenerated
     */
    private Date contenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column content.submitterId
     *
     * @mbggenerated
     */
    private String submitterid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column content.contentId
     *
     * @return the value of content.contentId
     *
     * @mbggenerated
     */
    public String getContentid() {
        return contentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column content.contentId
     *
     * @param contentid the value for content.contentId
     *
     * @mbggenerated
     */
    public void setContentid(String contentid) {
        this.contentid = contentid == null ? null : contentid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column content.contendDate
     *
     * @return the value of content.contendDate
     *
     * @mbggenerated
     */
    public Date getContenddate() {
        return contenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column content.contendDate
     *
     * @param contenddate the value for content.contendDate
     *
     * @mbggenerated
     */
    public void setContenddate(Date contenddate) {
        this.contenddate = contenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column content.submitterId
     *
     * @return the value of content.submitterId
     *
     * @mbggenerated
     */
    public String getSubmitterid() {
        return submitterid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column content.submitterId
     *
     * @param submitterid the value for content.submitterId
     *
     * @mbggenerated
     */
    public void setSubmitterid(String submitterid) {
        this.submitterid = submitterid == null ? null : submitterid.trim();
    }
}