package net.bryansaunders.jee6divelog.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base Entity Element for all DiveLog Entities.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@MappedSuperclass
public class DiveLogEntity {

    /**
     * Id.
     */
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    /**
     * Version.
     */
    @Version
    private Integer version;

    /**
     * Get the id.
     * 
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Set the id.
     * 
     * @param newId
     *            the id to set
     */
    public void setId(final Integer newId) {
        this.id = newId;
    }

    /**
     * Get the version.
     * 
     * @return the version
     */
    public Integer getVersion() {
        return this.version;
    }

    /**
     * Set the version.
     * 
     * @param newVersion
     *            the version to set
     */
    public void setVersion(Integer newVersion) {
        this.version = newVersion;
    }

}
