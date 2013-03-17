package de.wsdevel.elsbeth.content;

import java.io.File;

/**
 * Created on 21.11.2010 for project: Elsbeth. (c) 2010, Sebastian A. Weiss -
 * All rights reserved.
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss -
 *         scenejo.org</a>
 */
public class Source {

    /**
     * Default constructor.
     */
    public Source() {
    }

    // SCENEJO
    // /**
    // * Default constructor.
    // */
    // public Source(final String fileNameVal, final String sreIdVal) {
    // setFileName(fileNameVal);
    // setSreId(sreIdVal);
    // }

    /**
     * Default constructor.
     */
    public Source(final String fileNameVal) {
	setFileName(fileNameVal);
    }

    /**
     * {@link File} COMMENT.
     */
    private String fileName;

    // SCENEJO
    // /**
    // * {@link String} COMMENT.
    // */
    // private String sreId;

    /**
     * @param obj
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Source other = (Source) obj;
	if (this.fileName == null) {
	    if (other.fileName != null) {
		return false;
	    }
	} else if (!this.fileName.equals(other.fileName)) {
	    return false;
	}
	// SCENEJO
	// if (this.sreId == null) {
	// if (other.sreId != null) {
	// return false;
	// }
	// } else if (!this.sreId.equals(other.sreId)) {
	// return false;
	// }
	return true;
    }

    /**
     * @return {@link String} the fileName.
     */
    public final String getFileName() {
	return this.fileName;
    }

    // SCENEJO
    // /**
    // * @return {@link String} the sreId.
    // */
    // public final String getSreId() {
    // return this.sreId;
    // }

    /**
     * @return
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ (this.fileName == null ? 0 : this.fileName.hashCode());
	// SCENEJO
	// result = prime * result
	// + (this.sreId == null ? 0 : this.sreId.hashCode());
	return result;
    }

    /**
     * @param fileNameVal
     *            {@link String} the fileName to set.
     */
    public final void setFileName(final String fileNameVal) {
	this.fileName = fileNameVal;
    }

    // SCENEJO
    // /**
    // * @param sreIdVal
    // * {@link String} the sreId to set.
    // */
    // public final void setSreId(final String sreIdVal) {
    // this.sreId = sreIdVal;
    // }

}
//
// $Log: $
//
