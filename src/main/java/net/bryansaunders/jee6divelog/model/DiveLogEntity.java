package net.bryansaunders.jee6divelog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
     * Creation Date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    /**
     * Updated Date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    private Date updated;

    /**
     * Pre-Persist Logic.
     */
    @PrePersist
    protected void onCreate() {
        this.created = new Date();
        this.updated = this.created;
    }

    /**
     * Pre-Update Logic.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updated = new Date();
    }

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
    public void setVersion(final Integer newVersion) {
        this.version = newVersion;
    }

    /**
     * Get the created.
     * 
     * @return the created
     */
    public Date getCreated() {
        return new Date(this.created.getTime());
    }

    /**
     * Set the created.
     * 
     * @param newCreated
     *            the newCreated to set
     */
    public void setCreated(final Date newCreated) {
        this.created = new Date(newCreated.getTime());
    }

    /**
     * Get the updated.
     * 
     * @return the updated
     */
    public Date getUpdated() {
        return new Date(this.updated.getTime());
    }

    /**
     * Set the updated.
     * 
     * @param newUpdated
     *            the updated to set
     */
    public void setUpdated(final Date newUpdated) {
        this.updated = new Date(newUpdated.getTime());
    }

}
