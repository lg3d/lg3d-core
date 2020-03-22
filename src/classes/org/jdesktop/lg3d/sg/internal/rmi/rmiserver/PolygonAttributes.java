/**
 * Project Looking Glass
 *
 * $RCSfile: PolygonAttributes.java,v $
 *
 * Copyright (c) 2004, Sun Microsystems, Inc., All Rights Reserved
 *
 * Redistributions in source code form must reproduce the above
 * copyright and this condition.
 *
 * The contents of this file are subject to the GNU General Public
 * License, Version 2 (the "License"); you may not use this file
 * except in compliance with the License. A copy of the License is
 * available at http://www.opensource.org/licenses/gpl-license.php.
 *
 * $Revision: 1.2 $
 * $Date: 2004-06-23 18:51:19 $
 * $State: Exp $
 */
package org.jdesktop.lg3d.sg.internal.rmi.rmiserver;


/**
 * The PolygonAttributes object defines attributes for rendering polygon
 * primitives.
 * Polygon primitives include triangles, triangle strips, triangle fans,
 * and quads.
 * The polygon attributes that can be defined are:</li>
 * <p><ul>
 * <li>Rasterization mode - defines how the polygon is drawn: as points,
 * outlines, or filled.<p>
 * <ul>
 * <li>POLYGON_POINT - the polygon is rendered as points
 * drawn at the vertices.</li><p>
 * <li>POLYGON_LINE - the polygon is rendered as lines
 * drawn between consecutive vertices.</li><p>
 * <li>POLYGON_FILL - the polygon is rendered by filling the interior
 * between the vertices. The default mode.</li>
 * <p></ul>
 * <li>Face culling - defines which polygons are culled (discarded)
 * before they are converted to screen coordinates.<p>
 * <ul>
 * <li>CULL_NONE - disables face culling.</li>
 * <li>CULL_BACK - culls all back-facing polygons. The default.</li>
 * <li>CULL_FRONT - culls all front-facing polygons.</li>
 * <p></ul>
 * <li>Back-face normal flip - specifies whether vertex normals of
 * back-facing polygons are flipped (negated) prior to lighting. The
 * setting is either true, meaning to flip back-facing normals, or 
 * false. The default is false.</li>
 * <p>
 * <li>Offset - the depth values of all pixels generated by polygon
 * rasterization can be offset by a value that is computed for that 
 * polygon. Two values are used to specify the offset:</li><p>
 * <ul>
 * <li>Offset bias - the constant polygon offset that is added to 
 * the final device coordinate Z value of polygon primitives.</li>
 * <p>
 * <li>Offset factor - the factor to be multiplied by the
 * slope of the polygon and then added to the final, device coordinate
 * Z value of the polygon primitives.</li><p>
 * </ul>
 * These values can be either positive or negative. The default
 * for both of these values is 0.0.<p>
 * </ul>
 * 
 * @see Appearance
 */
public class PolygonAttributes extends NodeComponent implements PolygonAttributesRemote {

    /**
     * Constructs a PolygonAttributes object with default parameters.
     * The default values are as follows:
     * <ul>
     * cull face : CULL_BACK<br>
     * back face normal flip : false<br>
     * polygon mode : POLYGON_FILL<br>
     * polygon offset : 0.0<br>
     * polygon offset factor : 0.0<br>
     * </ul>
     */
    public PolygonAttributes() throws java.rmi.RemoteException {
    }
    
    protected void createWrapped() {
	wrapped = new org.jdesktop.lg3d.sg.PolygonAttributes();
        wrapped.setUserData( this );
    }

    /**
     * Constructs a PolygonAttributes object with specified values.
     * @param polygonMode polygon rasterization mode; one of POLYGON_POINT,
     * POLYGON_LINE, or POLYGON_FILL
     * @param cullFace polygon culling mode; one of CULL_NONE,
     * CULL_BACK, or CULL_FRONT
     * @param polygonOffset constant polygon offset
     */
    public PolygonAttributes(int polygonMode,
			     int cullFace,
			     float polygonOffset) throws java.rmi.RemoteException {
	this(polygonMode, cullFace, polygonOffset, false, 0.0f);
     }

    /**
     * Constructs PolygonAttributes object with specified values.
     * @param polygonMode polygon rasterization mode; one of POLYGON_POINT,
     * POLYGON_LINE, or POLYGON_FILL
     * @param cullFace polygon culling mode; one of CULL_NONE,
     * CULL_BACK, or CULL_FRONT
     * @param polygonOffset constant polygon offset
     * @param backFaceNormalFlip back face normal flip flag; true or false
     */
    public PolygonAttributes(int polygonMode,
			     int cullFace,
			     float polygonOffset,
			     boolean backFaceNormalFlip) throws java.rmi.RemoteException {
	this(polygonMode, cullFace, polygonOffset, backFaceNormalFlip, 0.0f);
     }

    /**
     * Constructs PolygonAttributes object with specified values.
     * @param polygonMode polygon rasterization mode; one of POLYGON_POINT,
     * POLYGON_LINE, or POLYGON_FILL
     * @param cullFace polygon culling mode; one of CULL_NONE,
     * CULL_BACK, or CULL_FRONT
     * @param polygonOffset constant polygon offset
     * @param backFaceNormalFlip back face normal flip flag; true or false
     * @param polygonOffset polygon offset factor for slope-based polygon
     * offset
     *
     * @since Java 3D 1.2
     */
    public PolygonAttributes(int polygonMode,
			     int cullFace,
			     float polygonOffset,
			     boolean backFaceNormalFlip,
			     float polygonOffsetFactor ) throws java.rmi.RemoteException {

	setPolygonMode(polygonMode);
        setCullFace(cullFace);
        setPolygonOffset(polygonOffset);
        setBackFaceNormalFlip(backFaceNormalFlip);
        setPolygonOffsetFactor(polygonOffsetFactor);
     }

    /**
     * Sets the face culling for this
     * appearance component object.
     * @param cullFace the face to be culled, one of:
     * CULL_NONE, CULL_FRONT, or CULL_BACK
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     */
    public void setCullFace(int cullFace) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.PolygonAttributes)wrapped).setCullFace(cullFace);
    }

    /**
     * Gets the face culling for this
     * appearance component object.
     * @return the face to be culled
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     */
    public int getCullFace() throws java.rmi.RemoteException {
        return ((org.jdesktop.lg3d.sg.PolygonAttributes)wrapped).getCullFace();
    }

    /**
     * Sets the back face normal flip flag to the specified value.
     * This flag indicates whether vertex normals of back facing polygons
     * should be flipped (negated) prior to lighting.  When this flag
     * is set to true and back face culling is disabled, polygons are
     * rendered as if the polygon had two sides with opposing normals.
     * This feature is disabled by default.
     * @param backFaceNormalFlip the back face normal flip flag
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     */
    public void setBackFaceNormalFlip(boolean backFaceNormalFlip) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.PolygonAttributes)wrapped).setBackFaceNormalFlip(backFaceNormalFlip);
    }

    /**
     * Gets the back face normal flip flag.
     * @return the back face normal flip flag
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     */
    public boolean getBackFaceNormalFlip() throws java.rmi.RemoteException  {
        return ((org.jdesktop.lg3d.sg.PolygonAttributes)wrapped).getBackFaceNormalFlip();
    }

    /**
     * Sets the polygon rasterization mode for this
     * appearance component object.
     * @param polygonMode the polygon rasterization mode to be used; one of
     * POLYGON_FILL, POLYGON_LINE, or POLYGON_POINT
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     */
    public void setPolygonMode(int polygonMode) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.PolygonAttributes)wrapped).setPolygonMode(polygonMode);
    }

    /**
     * Gets the polygon rasterization mode for this
     * appearance component object.
     * @return the polygon rasterization mode
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     */
    public int getPolygonMode() throws java.rmi.RemoteException {
        return ((org.jdesktop.lg3d.sg.PolygonAttributes)wrapped).getPolygonMode();
    }

    /**
     * Sets the constant polygon offset to the specified value.
     * This screen space
     * offset is added to the final, device coordinate Z value of polygon
     * primitives.
     * @param polygonOffset the constant polygon offset
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     */
    public void setPolygonOffset(float polygonOffset) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.PolygonAttributes)wrapped).setPolygonOffset(polygonOffset);
    }

    /**
     * Gets the constant polygon offset.
     * @return the constant polygon offset
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     */
    public float getPolygonOffset() throws java.rmi.RemoteException {
        return ((org.jdesktop.lg3d.sg.PolygonAttributes)wrapped).getPolygonOffset();
    }

    /**
     * Sets the polygon offset factor to the specified value.
     * This factor is multiplied by the slope of the polygon, and
     * then added to the final, device coordinate Z value of polygon
     * primitives.
     * @param polygonOffsetFactor the polygon offset factor
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     *
     * @since Java 3D 1.2
     */
    public void setPolygonOffsetFactor(float polygonOffsetFactor) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.PolygonAttributes)wrapped).setPolygonOffsetFactor(polygonOffsetFactor);
    }

    /**
     * Gets the polygon offset factor.
     * @return the polygon offset factor.
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     *
     * @since Java 3D 1.2
     */
    public float getPolygonOffsetFactor() throws java.rmi.RemoteException {
        return ((org.jdesktop.lg3d.sg.PolygonAttributes)wrapped).getPolygonOffsetFactor();
    }

}
