/**
 * Project Looking Glass
 *
 * $RCSfile: GeometryArrayRemote.java,v $
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
 * $Date: 2004-06-23 18:51:09 $
 * $State: Exp $
 */
package org.jdesktop.lg3d.sg.internal.rmi.rmiserver;

import javax.vecmath.*;


/**
 * The GeometryArray object contains separate arrays of positional
 * coordinates, colors, normals, and texture coordinates that
 * describe point, line, or polygon geometry.  This class is extended
 * to create the various primitive types (such as lines,
 * triangle strips, etc.).
 * Vertex data may be passed to this geometry array in one of two
 * ways: by copying the data into the array using the existing
 * methods, or by passing a reference to the data.
 * <p>
 * <ul>
 * <li>
 * <b>By Copying:</b>
 * The existing methods for setting positional coordinates, colors,
 * normals, and texture coordinates (such as <code>setCoordinate</code>,
 * <code>setColors</code>, etc.)  copy the data into this
 * GeometryArray.  This is appropriate for many applications and
 * offers an application much flexibility in organizing its data.
 * This is the default mode.
 * </li>
 * <li><b>By Reference:</b>
 * A new set of methods in Java 3D version 1.2 allows data to be
 * accessed by reference, directly from the user's arrays.  To use
 * this feature, set the <code>BY_REFERENCE</code> bit in the
 * <code>vertexFormat</code> field of the constructor for this
 * GeometryArray.  In this mode, the various set methods for
 * coordinates, normals, colors, and texture coordinates are not used.
 * Instead, new methods are used to set a reference to user-supplied
 * coordinate, color, normal, and texture coordinate arrays (such as
 * <code>setCoordRefFloat</code>, <code>setColorRefFloat</code>,
 * etc.).  Data in any array that is referenced by a live or compiled
 * GeometryArray object may only be modified via the
 * <code>updateData</code> method (subject to the
 * <code>ALLOW_REF_DATA_WRITE</code> capability bit).  Applications
 * must exercise care not to violate this rule.  If any referenced
 * geometry data is modified outside of the <code>updateData</code>
 * method, the results are undefined.
 * </li>
 * </ul>
 * <p>
 * All colors used in the geometry array object must be in the range [0.0,1.0].
 * Values outside this range will cause undefined results.
 * All normals used in the geometry array object must be unit length
 * vectors.  That is their geometric length must be 1.0.  Normals that
 * are not unit length vectors will cause undefined results.
 * <p>
 * Note that the term <i>coordinate</i>, as used in the method names
 * and method descriptions, actually refers to a set of <i>x</i>,
 * <i>y</i>, and <i>z</i> coordinates representing the position of a
 * single vertex.  The term <i>coordinates</i> (plural) is used to
 * indicate sets of <i>x</i>, <i>y</i>, and <i>z</i> coordinates for
 * multiple vertices.  This is somewhat at odds with the mathematical
 * definition of a coordinate, but is used as a convenient shorthand.
 * Similarly, the term <i>texture coordinate</i> is used to indicate a
 * set of texture coordinates for a single vertex, while the term
 * <i>texture coordinates</i> (plural) is used to indicate sets of
 * texture coordinates for multiple vertices.
 */

public interface GeometryArrayRemote extends GeometryRemote {


    /*
     *------------------------------------------------------------------
     * Common methods
     *------------------------------------------------------------------
     */


    /**
     * Retrieves the number of vertices in this GeometryArray
     * @return number of vertices in this GeometryArray
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     */
    public int getVertexCount() throws java.rmi.RemoteException;

    /**
     * Retrieves the vertexFormat of this GeometryArray
     * @return format of vertices in this GeometryArray
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     */
    public int getVertexFormat() throws java.rmi.RemoteException;


    /**
     * Retrieves the number of texture coordinate sets in this
     * GeometryArray object.
     *
     * @return the number of texture coordinate sets
     * in this GeometryArray object
     *
     * @since Java 3D 1.2
     */
    public int getTexCoordSetCount() throws java.rmi.RemoteException;


    /**
     * Retrieves the length of the texture coordinate set mapping
     * array of this GeometryArray object.
     *
     * @return the length of the texture coordinate set mapping
     * array of this GeometryArray object
     *
     * @since Java 3D 1.2
     */
    public int getTexCoordSetMapLength() throws java.rmi.RemoteException;


    /**
     * Retrieves the texture coordinate set mapping
     * array from this GeometryArray object.
     *
     * @param texCoordSetMap an array that will receive a copy of the
     * texture coordinate set mapping array.  The array must be large
     * enough to hold all entries of the texture coordinate set
     * mapping array.
     *
     * @since Java 3D 1.2
     */
    public void getTexCoordSetMap(int[] texCoordSetMap) throws java.rmi.RemoteException;


    /**
     * Updates geometry array data that is accessed by reference.
     * This method calls the updateData method of the specified
     * GeometryUpdater object to synchronize updates to vertex
     * data that is referenced by this GeometryArray object.
     * Applications that wish to modify such data must perform all
     * updates via this method.
     * <p>
     * This method may also be used to atomically set multiple
     * references (for example, to coordinate and color arrays)
     * or to atomically
     * change multiple data values through the geometry data copying
     * methods.
     *
     * @param updater object whose updateData callback method will be
     * called to update the data referenced by this GeometryArray.
     * @exception CapabilityNotSetException if the appropriate capability
     * is not set, the vertex data mode is <code>BY_REFERENCE</code>, and this
     * object is part of a live or compiled scene graph
     *
     * @since Java 3D 1.2
     */
//    public void updateData(GeometryUpdater updater) {
//	throw new RuntimeException("Not implemented");
//    }


    /**
     * Sets the valid vertex count for this GeometryArray object.
     * This count specifies the number of vertices actually used in
     * rendering or other operations such as picking and collision.
     * This attribute is initialized to <code>vertexCount</code>.
     *
     * @param validVertexCount the new valid vertex count.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * <p>
     * @exception IllegalArgumentException if any of the following are
     * true:
     * <ul>
     * <code>validVertexCount < 0</code>,<br>
     * <code>initialVertexIndex + validVertexCount > vertexCount</code>,<br>
     * <code>initialCoordIndex + validVertexCount > vertexCount</code>,<br>
     * <code>initialColorIndex + validVertexCount > vertexCount</code>,<br>
     * <code>initialNormalIndex + validVertexCount > vertexCount</code>,<br>
     * <code>initialTexCoordIndex + validVertexCount > vertexCount</code>
     * </ul>
     * <p>
     * @exception ArrayIndexOutOfBoundsException if the geometry data format
     * is <code>BY_REFERENCE</code> and any the following
     * are true for non-null array references:
     * <ul>
     * <code>CoordRef.length</code> < <i>num_words</i> *
     * (<code>initialCoordIndex + validVertexCount</code>),<br>
     * <code>ColorRef.length</code> < <i>num_words</i> *
     * (<code>initialColorIndex + validVertexCount</code>),<br>
     * <code>NormalRef.length</code> < <i>num_words</i> *
     * (<code>initialNormalIndex + validVertexCount</code>),<br>
     * <code>TexCoordRef.length</code> < <i>num_words</i> *
     * (<code>initialTexCoordIndex + validVertexCount</code>),<br>
     * <code>InterleavedVertices.length</code> < <i>words_per_vertex</i> *
     * (<code>initialVertexIndex + validVertexCount</code>)<br>
     * </ul>
     * where <i>num_words</i> depends on which variant of
     * <code>set</code><i>Array</i><code>Ref</code> is used, and
     * <i>words_per_vertex</i> depends on which vertex formats are enabled
     * for interleaved arrays.
     *
     * @since Java 3D 1.2
     */
    public void setValidVertexCount(int validVertexCount) throws java.rmi.RemoteException;


    /**
     * Gets the valid vertex count for this GeometryArray object.
     * For geometry strip primitives (subclasses of GeometryStripArray),
     * the valid vertex count is defined to be the sum of the
     * stripVertexCounts array.
     * @return the current valid vertex count
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @since Java 3D 1.2
     */
    public int getValidVertexCount() throws java.rmi.RemoteException;

    /*
     *------------------------------------------------------------------
     * By-copying methods
     *------------------------------------------------------------------
     */


    /**
     * Sets the initial vertex index for this GeometryArray object.
     * This index specifies the first vertex within this geometry
     * array that is actually used in rendering or other operations
     * such as picking and collision.  This attribute is initialized
     * to 0.
     * This attribute is only used when the data mode for this
     * geometry array object is not <code>BY_REFERENCE</code>
     * or when the data mode is <code>INTERLEAVED</code>.
     *
     * @param initialVertexIndex the new initial vertex index.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalArgumentException if either of the following are
     * true:
     * <ul>
     * <code>initialVertexIndex < 0</code> or<br>
     * <code>initialVertexIndex + validVertexCount > vertexCount</code><br>
     * </ul>
     *
     * @exception ArrayIndexOutOfBoundsException if the geometry data format
     * is <code>INTERLEAVED</code>, the InterleavedVertices array is
     * non-null, and:
     * <ul>
     * <code>InterleavedVertices.length</code> < <i>num_words</i> *
     * (<code>initialVertexIndex + validVertexCount</code>)<br>
     * </ul>
     * where <i>num_words</i> depends on which vertex formats are enabled.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code> and is <i>not</i>
     * <code>INTERLEAVED</code>.
     *
     * @since Java 3D 1.2
     */
    public void setInitialVertexIndex(int initialVertexIndex) throws java.rmi.RemoteException;


    /**
     * Gets the initial vertex index for this GeometryArray object.
     * This attribute is only used when the data mode for this
     * geometry array object is <i>not</i> <code>BY_REFERENCE</code>
     * or when the data mode is <code>INTERLEAVED</code>.
     * @return the current initial vertex index for this GeometryArray object.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @since Java 3D 1.2
     */
    public int getInitialVertexIndex() throws java.rmi.RemoteException;


  /**
   * Sets the coordinate associated with the vertex at
   * the specified index for this object.
   * @param index destination vertex index in this geometry array
   * @param coordinate source array of 3 values containing the new coordinate
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setCoordinate(int index, float coordinate[]) throws java.rmi.RemoteException;

  /**
   * Sets the coordinate associated with the vertex at
   * the specified index for this object.
   * @param index destination vertex index in this geometry array
   * @param coordinate a point containing the new coordinate
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setCoordinate(int index, Point3f coordinate) throws java.rmi.RemoteException;

  /**
   * Sets the coordinates associated with the vertices starting at
   * the specified index for this object.  The entire source array is
   * copied to this geometry array.
   * @param index starting destination vertex index in this geometry array
   * @param coordinates source array of 3*n values containing n new coordinates
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setCoordinates(int index, float coordinates[]) throws java.rmi.RemoteException;

  /**
   * Sets the coordinates associated with the vertices starting at
   * the specified index for this object.  The entire source array is
   * copied to this geometry array.
   * @param index starting destination vertex index in this geometry array
   * @param coordinates source array of points containing new coordinates
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setCoordinates(int index, Point3f coordinates[]) throws java.rmi.RemoteException;

  /**
   * Sets the coordinates associated with the vertices starting at
   * the specified index for this object using coordinate data starting
   * from vertex index <code>start</code> for <code>length</code> vertices.
   * @param index starting destination vertex index in this geometry array
   * @param coordinates source array of 3*n values containing n new coordinates
   * @param start starting source vertex index in <code>coordinates</code> array.
   * @param length number of vertices to be copied.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setCoordinates(int index, float coordinates[],
				   int start, int length) throws java.rmi.RemoteException;

  /**
   * Sets the coordinates associated with the vertices starting at
   * the specified index for this object using coordinate data starting
   * from vertex index <code>start</code> for <code>length</code> vertices.
   * @param index starting destination vertex index in this geometry array
   * @param coordinates source array of points containing new coordinates
   * @param start starting source vertex index in <code>coordinates</code> array.
   * @param length number of vertices to be copied.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setCoordinates(int index, Point3f coordinates[],
				   int start, int length) throws java.rmi.RemoteException;

  /**
   * Sets the color associated with the vertex at
   * the specified index for this object.
   * @param index destination vertex index in this geometry array
   * @param color source array of 3 or 4 values containing the new color
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if COLOR bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   */
  public void setColor(int index, float color[]) throws java.rmi.RemoteException;

  /**
   * Sets the color associated with the vertex at
   * the specified index for this object.
   * @param index destination vertex index in this geometry array
   * @param color source array of 3 or 4 values containing the new color
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if COLOR bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   */
  public void setColor(int index, byte color[]) throws java.rmi.RemoteException;

  /**
   * Sets the color associated with the vertex at
   * the specified index for this object.
   * @param index destination vertex index in this geometry array
   * @param color a Color3f containing the new color
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if COLOR bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   * @exception IllegalStateException if COLOR_4 is specified in the vertex 
   * format
   */
  public void setColor(int index, Color3f color) throws java.rmi.RemoteException;

  /**
   * Sets the color associated with the vertex at
   * the specified index for this object.
   * @param index destination vertex index in this geometry array
   * @param color a Color4f containing the new color
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if COLOR bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   * @exception IllegalStateException if COLOR_3 is specified in the vertex 
   * format
   */
  public void setColor(int index, Color4f color) throws java.rmi.RemoteException;

  /**
   * Sets the colors associated with the vertices starting at
   * the specified index for this object.  The entire source array is
   * copied to this geometry array.
   * @param index starting destination vertex index in this geometry array
   * @param colors source array of 3*n or 4*n values containing n new colors
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if COLOR bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   */
  public void setColors(int index, float colors[]) throws java.rmi.RemoteException;

  /**
   * Sets the colors associated with the vertices starting at
   * the specified index for this object.  The entire source array is
   * copied to this geometry array.
   * @param index starting destination vertex index in this geometry array
   * @param colors source array of Color3f objects containing new colors
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if COLOR bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   * @exception IllegalStateException if COLOR_4 is specified in vertex format
   */
  public void setColors(int index, Color3f colors[]) throws java.rmi.RemoteException;

  /**
   * Sets the colors associated with the vertices starting at
   * the specified index for this object.  The entire source array is
   * copied to this geometry array.
   * @param index starting destination vertex index in this geometry array
   * @param colors source array of Color4f objects containing new colors
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if COLOR bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   * @exception IllegalStateException if COLOR_3 is specified in vertex format
   */
  public void setColors(int index, Color4f colors[]) throws java.rmi.RemoteException;

  /**
   * Sets the colors associated with the vertices starting at
   * the specified index for this object using data in <code>colors</code>
   * starting at index <code>start</code> for <code>length</code> colors.
   * @param index starting destination vertex index in this geometry array
   * @param colors source array of 3*n or 4*n values containing n new colors
   * @param start starting source vertex index in <code>colors</code> array.
   * @param length number of colors to be copied.
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if COLOR bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setColors(int index, float colors[],
				   int start, int length) throws java.rmi.RemoteException;

  /**
   * Sets the colors associated with the vertices starting at
   * the specified index for this object using data in <code>colors</code>
   * starting at index <code>start</code> for <code>length</code> colors.
   * @param index starting destination vertex index in this geometry array
   * @param colors source array of Color3f objects containing new colors
   * @param start starting source vertex index in <code>colors</code> array.
   * @param length number of colors to be copied.
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if COLOR bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   * @exception IllegalStateException if COLOR_4 is specified in vertex format
   */
  public void setColors(int index, Color3f colors[],
				   int start, int length) throws java.rmi.RemoteException;

  /**
   * Sets the colors associated with the vertices starting at
   * the specified index for this object using data in <code>colors</code>
   * starting at index <code>start</code> for <code>length</code> colors.
   * @param index starting destination vertex index in this geometry array
   * @param colors source array of Color4f objects containing new colors
   * @param start starting source vertex index in <code>colors</code> array.
   * @param length number of colors to be copied.
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if COLOR bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   * @exception IllegalStateException if COLOR_3 is specified in vertex format
   */
  public void setColors(int index, Color4f colors[],
				   int start, int length) throws java.rmi.RemoteException;

  /**
   * Sets the normal associated with the vertex at
   * the specified index for this object.
   * @param index destination vertex index in this geometry array
   * @param normal source array of 3 values containing the new normal
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if NORMALS bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setNormal(int index, float normal[]) throws java.rmi.RemoteException;

  /**
   * Sets the normal associated with the vertex at
   * the specified index for this object.
   * @param index destination vertex index in this geometry array
   * @param normal the vector containing the new normal
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if NORMALS bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setNormal(int index, Vector3f normal) throws java.rmi.RemoteException;

  /**
   * Sets the normals associated with the vertices starting at
   * the specified index for this object.  The entire source array is
   * copied to this geometry array.
   * @param index starting destination vertex index in this geometry array
   * @param normals source array of 3*n values containing n new normals
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if NORMALS bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setNormals(int index, float normals[]) throws java.rmi.RemoteException;

  /**
   * Sets the normals associated with the vertices starting at
   * the specified index for this object.  The entire source array is
   * copied to this geometry array.
   * @param index starting destination vertex index in this geometry array
   * @param normals source array of vectors containing new normals
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if NORMALS bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setNormals(int index, Vector3f normals[]) throws java.rmi.RemoteException;

  /**
   * Sets the normals associated with the vertices starting at
   * the specified index for this object using data in <code>normals</code>
   * starting at index <code>start</code> and  ending at index <code>start+length</code>.
   * @param index starting destination vertex index in this geometry array
   * @param normals source array of 3*n values containing n new normals
   * @param start starting source vertex index in <code>normals</code> array.
   * @param length number of normals to be copied.
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if NORMALS bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setNormals(int index, float normals[],
				   int start, int length) throws java.rmi.RemoteException;

  /**
   * Sets the normals associated with the vertices starting at
   * the specified index for this object using data in <code>normals</code>
   * starting at index <code>start</code> and  ending at index <code>start+length</code>.
   * @param index starting destination vertex index in this geometry array
   * @param normals source array of vectors containing new normals
   * @param start starting source vertex index in <code>normals</code> array.
   * @param length number of normals to be copied.
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception ArrayIndexOutOfBoundsException if NORMALS bit NOT set in
   * constructor <code>vertexFormat</code> or array index for element is out of bounds.
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void setNormals(int index, Vector3f normals[],
				   int start, int length) throws java.rmi.RemoteException;


    /**
     * @deprecated As of Java 3D version 1.2, replaced by
     * <code>setTextureCoordinate(int texCoordSet,  ...)</code>
     */
    public void setTextureCoordinate(int index, float texCoord[]) throws java.rmi.RemoteException;

    /**
     * Sets the texture coordinate associated with the vertex at the
     * specified index in the specified texture coordinate set for
     * this object.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index destination vertex index in this geometry array
     * @param texCoord source array of 2, 3 or 4 values containing the new
     * texture coordinate
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
     *
     * @since Java 3D 1.2
     */
    public void setTextureCoordinate(int texCoordSet,
				     int index, float texCoord[]) throws java.rmi.RemoteException;

    /**
     * @deprecated As of Java 3D version 1.2, replaced by
     * <code>setTextureCoordinate(int texCoordSet, TexCoord2f texCoord)</code>
     */
    public void setTextureCoordinate(int index, Point2f texCoord) throws java.rmi.RemoteException;

    /**
     * Sets the texture coordinate associated with the vertex at
     * the specified index in the specified texture coordinate set
     * for this object.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index destination vertex index in this geometry array
     * @param texCoord the TexCoord2f containing the new texture coordinate
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
     *
     * @exception IllegalStateException if TEXTURE_COORDINATE_3 or
     * TEXTURE_COORDINATE_4 is specified in vertex format
     *
     * @since Java 3D 1.2
     */
    public void setTextureCoordinate(int texCoordSet,
				     int index, TexCoord2f texCoord) throws java.rmi.RemoteException;

    /**
     * Sets the texture coordinate associated with the vertex at
     * the specified index in the specified texture coordinate set
     * for this object.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index destination vertex index in this geometry array
     * @param texCoord the TexCoord3f containing the new texture coordinate
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
     *
     * @exception IllegalStateException if TEXTURE_COORDINATE_2 or
     * TEXTURE_COORDINATE_4 is specified in vertex format
     *
     * @since Java 3D 1.2
     */
    public void setTextureCoordinate(int texCoordSet,
				     int index, TexCoord3f texCoord) throws java.rmi.RemoteException;

    /**
     * Sets the texture coordinates associated with the vertices starting at
     * the specified index in the specified texture coordinate set
     * for this object.  The entire source array is
     * copied to this geometry array.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index starting destination vertex index in this geometry array
     * @param texCoords source array of 2*n, 3*n or 4*n values containing n new
     * texture coordinates
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
     *
     * @since Java 3D 1.2
     */
    public void setTextureCoordinates(int texCoordSet,
				      int index, float texCoords[]) throws java.rmi.RemoteException;

    /**
     * @deprecated As of Java 3D version 1.2, replaced by
     * <code>setTextureCoordinates(int texCoordSet, TexCoord2f texCoords[])</code>
     */
    public void setTextureCoordinates(int index, Point2f texCoords[]) throws java.rmi.RemoteException;

    /**
     * Sets the texture coordinates associated with the vertices starting at
     * the specified index in the specified texture coordinate set
     * for this object.  The entire source array is
     * copied to this geometry array.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index starting destination vertex index in this geometry array
     * @param texCoords source array of TexCoord2f objects containing new
     * texture coordinates
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
     *
     * @exception IllegalStateException if TEXTURE_COORDINATE_3 or
     * TEXTURE_COORDINATE_4 is specified in vertex format
     *
     * @since Java 3D 1.2
     */
    public void setTextureCoordinates(int texCoordSet,
				      int index, TexCoord2f texCoords[]) throws java.rmi.RemoteException;

    /**
     * Sets the texture coordinates associated with the vertices starting at
     * the specified index in the specified texture coordinate set
     * for this object.  The entire source array is
     * copied to this geometry array.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index starting destination vertex index in this geometry array
     * @param texCoords source array of TexCoord3f objects containing new
     * texture coordinates
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
     *
     * @exception IllegalStateException if TEXTURE_COORDINATE_2 or
     * TEXTURE_COORDINATE_4 is specified in vertex format
     *
     * @since Java 3D 1.2
     */
    public void setTextureCoordinates(int texCoordSet,
				      int index, TexCoord3f texCoords[]) throws java.rmi.RemoteException;

    /**
     * Sets the texture coordinates associated with the vertices
     * starting at the specified index in the specified texture
     * coordinate set for this object using data in
     * <code>texCoords</code> starting at index <code>start</code> and
     * ending at index <code>start+length</code>.
     *
     * @param index starting destination vertex index in this geometry array
     * @param texCoords source array of 2*n , 3*n or 4*n values containing 
     * n new * texture coordinates
     * @param start starting source vertex index in <code>texCoords</code>
     * array.
     * @param length number of texture Coordinates to be copied.
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
     *
     * @since Java 3D 1.2
     */
    public void setTextureCoordinates(int texCoordSet,
				      int index, float texCoords[],
				      int start, int length) throws java.rmi.RemoteException;

    /**
     * Sets the texture coordinates associated with the vertices
     * starting at the specified index in the specified texture
     * coordinate set for this object using data in
     * <code>texCoords</code> starting at index <code>start</code> and
     * ending at index <code>start+length</code>.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index starting destination vertex index in this geometry array
     * @param texCoords source array of TexCoord2f objects containing new
     * texture coordinates
     * @param start starting source vertex index in <code>texCoords</code>
     * array.
     * @param length number of texture Coordinates to be copied.
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
     *
     * @exception IllegalStateException if TEXTURE_COORDINATE_3 or 
     * TEXTURE_COORDINATE_4 is specified in vertex format
     *
     * @since Java 3D 1.2
     */
    public void setTextureCoordinates(int texCoordSet,
				      int index, TexCoord2f texCoords[],
				      int start, int length) throws java.rmi.RemoteException;

  /**
   * Gets the coordinate associated with the vertex at
   * the specified index for this object using data in <code>texCoords</code>
   * @param index source vertex index in this geometry array
   * @param coordinate destination array of 3 values that will receive the coordinate
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void getCoordinate(int index, float coordinate[]) throws java.rmi.RemoteException;

  /**
   * Gets the coordinate associated with the vertex at
   * the specified index for this object.
   * @param index source vertex index in this geometry array
   * @param coordinate a vector that will receive the coordinate
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void getCoordinate(int index, Point3f coordinate) throws java.rmi.RemoteException;

  /**
   * Gets the coordinates associated with the vertices starting at
   * the specified index for this object.  The length of the destination
   * array determines the number of vertices copied.
   * A maximum of <code>vertexCount-index</code> coordinates
   * are copied.  If the destination array is larger than is needed
   * to hold the coordinates, the excess locations in the
   * array are not modified.  If the destination array is smaller
   * than is needed to hold the coordinates, only as
   * many coordinates as the array will hold are copied.
   *
   * @param index starting source vertex index in this geometry array
   * @param coordinates destination array of 3*n values that will receive new coordinates
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void getCoordinates(int index, float coordinates[]) throws java.rmi.RemoteException;

  /**
   * Gets the coordinates associated with the vertices starting at
   * the specified index for this object.  The length of the destination
   * array determines the number of vertices copied.
   * A maximum of <code>vertexCount-index</code> coordinates
   * are copied.  If the destination array is larger than is needed
   * to hold the coordinates, the excess locations in the
   * array are not modified.  If the destination array is smaller
   * than is needed to hold the coordinates, only as
   * many coordinates as the array will hold are copied.
   *
   * @param index starting source vertex index in this geometry array
   * @param coordinates destination array of points that will receive new coordinates
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void getCoordinates(int index, Point3f coordinates[]) throws java.rmi.RemoteException;

  /**
   * Gets the color associated with the vertex at
   * the specified index for this object. The color is copied into the
   * specified array. The array must be large enough to hold all 
   * of the colors.
   * @param index source vertex index in this geometry array
   * @param color destination array of 3 or 4 values that will receive the color
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void getColor(int index, float color[]) throws java.rmi.RemoteException;

  /**
   * Gets the color associated with the vertex at
   * the specified index for this object.
   * @param index source vertex index in this geometry array
   * @param color a vector that will receive the color
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   * @exception IllegalStateException if COLOR_4 is specified in the vertex
   * format
   */
  public void getColor(int index, Color3f color) throws java.rmi.RemoteException;

  /**
   * Gets the color associated with the vertex at
   * the specified index for this object.
   * @param index source vertex index in this geometry array
   * @param color a vector that will receive the color
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   * @exception IllegalStateException if COLOR_3 is specified in the vertex
   * format
   */
  public void getColor(int index, Color4f color) throws java.rmi.RemoteException;

  /**
   * Gets the colors associated with the vertices starting at
   * the specified index for this object.  The color is copied into the
   * specified array. The length of the destination
   * array determines the number of colors copied.
   * A maximum of <code>vertexCount-index</code> colors
   * are copied.  If the destination array is larger than is needed
   * to hold the colors, the excess locations in the
   * array are not modified.  If the destination array is smaller
   * than is needed to hold the colors, only as
   * many colors as the array will hold are copied.
   *
   * @param index starting source vertex index in this geometry array
   * @param colors destination array of 3*n or 4*n values that will 
   * receive n new colors
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void getColors(int index, float colors[]) throws java.rmi.RemoteException;

  /**
   * Gets the colors associated with the vertices starting at
   * the specified index for this object.  The color is copied into the
   * specified array. The length of the destination
   * array determines the number of colors copied.
   * A maximum of <code>vertexCount-index</code> colors
   * are copied.  If the destination array is larger than is needed
   * to hold the colors, the excess locations in the
   * array are not modified.  If the destination array is smaller
   * than is needed to hold the colors, only as
   * many colors as the array will hold are copied.
   *
   * @param index starting source vertex index in this geometry array
   * @param colors destination array of Color3f objects that will receive new colors
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   * @exception IllegalStateException if COLOR_4 is specified in the vertex
   * format
   */
  public void getColors(int index, Color3f colors[]) throws java.rmi.RemoteException;

  /**
   * Gets the colors associated with the vertices starting at
   * the specified index for this object.  The color is copied into the
   * specified array. The length of the destination
   * array determines the number of colors copied.
   * A maximum of <code>vertexCount-index</code> colors
   * are copied.  If the destination array is larger than is needed
   * to hold the colors, the excess locations in the
   * array are not modified.  If the destination array is smaller
   * than is needed to hold the colors, only as
   * many colors as the array will hold are copied.
   *
   * @param index starting source vertex index in this geometry array
   * @param colors destination array of Color4f objects that will receive new colors
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
   * @exception IllegalStateException if the data mode for this geometry
   * array object is <code>BY_REFERENCE</code>.
   * @exception IllegalStateException if COLOR_3 is specified in the vertex
   * format
   */
  public void getColors(int index, Color4f colors[]) throws java.rmi.RemoteException;

  /**
   * Gets the normal associated with the vertex at
   * the specified index for this object. The normal is copied into
   * the specified array.
   * @param index source vertex index in this geometry array
   * @param normal destination array of 3 values that will receive the normal
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void getNormal(int index, float normal[]) throws java.rmi.RemoteException;

  /**
   * Gets the normal associated with the vertex at
   * the specified index for this object.
   * @param index source vertex index in this geometry array
   * @param normal the vector that will receive the normal
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void getNormal(int index, Vector3f normal) throws java.rmi.RemoteException;

  /**
   * Gets the normals associated with the vertices starting at
   * the specified index for this object.  The length of the destination
   * array determines the number of normals copied.
   * A maximum of <code>vertexCount-index</code> normals
   * are copied.  If the destination array is larger than is needed
   * to hold the normals, the excess locations in the
   * array are not modified.  If the destination array is smaller
   * than is needed to hold the normals, only as
   * many normals as the array will hold are copied.
   *
   * @param index starting source vertex index in this geometry array
   * @param normal destination array of 3*n values that will receive the normal
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void getNormals(int index, float normals[]) throws java.rmi.RemoteException;

  /**
   * Gets the normals associated with the vertices starting at
   * the specified index for this object.  The length of the destination
   * array determines the number of normals copied.
   * A maximum of <code>vertexCount-index</code> normals
   * are copied.  If the destination array is larger than is needed
   * to hold the normals, the excess locations in the
   * array are not modified.  If the destination array is smaller
   * than is needed to hold the normals, only as
   * many normals as the array will hold are copied.
   *
   * @param index starting source vertex index in this geometry array
   * @param normals destination array of vectors that will receive the normals
   * @exception CapabilityNotSetException if the appropriate capability is
   * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
   */
  public void getNormals(int index, Vector3f normals[]) throws java.rmi.RemoteException;

    /**
     * @deprecated As of Java 3D version 1.2, replaced by
     * <code>getTextureCoordinate(int texCoordSet, ...)</code>
     */
    public void getTextureCoordinate(int index, float texCoord[]) throws java.rmi.RemoteException;

    /**
     * Gets the texture coordinate associated with the vertex at
     * the specified index in the specified texture coordinate set
     * for this object.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index source vertex index in this geometry array
     * @param texCoord array of 2, 3 or 4 values that will receive the
     * texture coordinate
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
     *
     * @since Java 3D 1.2
     */
    public void getTextureCoordinate(int texCoordSet,
				     int index, float texCoord[]) throws java.rmi.RemoteException;

    /**
     * Gets the texture coordinate associated with the vertex at
     * the specified index in the specified texture coordinate set
     * for this object.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index source vertex index in this geometry array
     * @param texCoord the vector that will receive the texture coordinates
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
     *
     * @exception IllegalStateException if TEXTURE_COORDINATE_3 or
     * TEXTURE_COORDINATE_4 is specified in vertex format
     *
     * @since Java 3D 1.2
     */
    public void getTextureCoordinate(int texCoordSet,
				     int index, TexCoord2f texCoord) throws java.rmi.RemoteException;

    /**
     * Gets the texture coordinates associated with the vertices starting at
     * the specified index in the specified texture coordinate set
     * for this object.  The length of the destination
     * array determines the number of texture coordinates copied.
     * A maximum of <code>vertexCount-index</code> texture coordinates
     * are copied.  If the destination array is larger than is needed
     * to hold the texture coordinates, the excess locations in the
     * array are not modified.  If the destination array is smaller
     * than is needed to hold the texture coordinates, only as
     * many texture coordinates as the array will hold are copied.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index starting source vertex index in this geometry array
     * @param texCoords destination array of 2*n , 3*n or 4*n values that
     * will receive n new texture coordinates
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
     *
     * @since Java 3D 1.2
     */
    public void getTextureCoordinates(int texCoordSet,
				      int index, float texCoords[]) throws java.rmi.RemoteException;
 
    /**
     * Gets the texture coordinates associated with the vertices starting at
     * the specified index in the specified texture coordinate set
     * for this object.  The length of the destination
     * array determines the number of texture coordinates copied.
     * A maximum of <code>vertexCount-index</code> texture coordinates
     * are copied.  If the destination array is larger than is needed
     * to hold the texture coordinates, the excess locations in the
     * array are not modified.  If the destination array is smaller
     * than is needed to hold the texture coordinates, only as
     * many texture coordinates as the array will hold are copied.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index starting source vertex index in this geometry array
     * @param texCoords destination array of TexCoord2f objects that will
     * receive the texture coordinates
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is <code>BY_REFERENCE</code>.
     *
     * @exception IllegalStateException if TEXTURE_COORDINATE_3 or
     * TEXTURE_COORDINATE_4 is specified in vertex format
     *
     * @since Java 3D 1.2
     */
    public void getTextureCoordinates(int texCoordSet,
				      int index, TexCoord2f texCoords[]) throws java.rmi.RemoteException;

    /**
     * Gets the initial coordinate index for this GeometryArray object.
     * This attribute is only used when the data mode for this
     * geometry array object is <code>BY_REFERENCE</code>
     * and is <i>not</i> </code>INTERLEAVED</code>.
     * @return the current initial coordinate index for this
     * GeometryArray object.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @since Java 3D 1.2
     */
    public int getInitialCoordIndex() throws java.rmi.RemoteException;

    /**
     * Gets the initial color index for this GeometryArray object.
     * This attribute is only used when the data mode for this
     * geometry array object is <code>BY_REFERENCE</code>
     * and is <i>not</i> </code>INTERLEAVED</code>.
     * @return the current initial color index for this
     * GeometryArray object.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @since Java 3D 1.2
     */
    public int getInitialColorIndex() throws java.rmi.RemoteException;

    /**
     * Gets the initial normal index for this GeometryArray object.
     * This attribute is only used when the data mode for this
     * geometry array object is <code>BY_REFERENCE</code>
     * and is <i>not</i> </code>INTERLEAVED</code>.
     * @return the current initial normal index for this
     * GeometryArray object.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @since Java 3D 1.2
     */
    public int getInitialNormalIndex() throws java.rmi.RemoteException;


    /**
     * Gets the initial texture coordinate index for the specified
     * texture coordinate set for this GeometryArray object.
     * This attribute is only used when the data mode for this
     * geometry array object is <code>BY_REFERENCE</code>
     * and is <i>not</i> </code>INTERLEAVED</code>.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     *
     * @return the current initial texture coordinate index for the specified
     * texture coordinate set
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if texCoordSet is out of range.
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @since Java 3D 1.2
     */
    public int getInitialTexCoordIndex(int texCoordSet) throws java.rmi.RemoteException;

    /**
     * Sets the float coordinate array reference to the specified
     * array.  The array contains floating-point <i>x</i>, <i>y</i>,
     * and <i>z</i> values for each vertex (for a total of 3*<i>n</i>
     * values, where <i>n</i> is the number of vertices).  Only one of
     * <code>coordRefFloat</code>, <code>coordRefDouble</code>,
     * <code>coordRef3f</code>, or <code>coordRef3d</code> may be
     * non-null (or they may all be null).  An attempt to set more
     * than one of these attributes to a non-null reference will
     * result in an exception being thrown.  If all coordinate array
     * references are null, the entire geometry array object is
     * treated as if it were null--any Shape3D or Morph node that uses
     * this geometry array will not be drawn.
     *
     * @param coords an array of 3*<i>n</i> values to which a
     * reference will be set.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is not <code>BY_REFERENCE</code>,
     * is <code>USE_NIO_BUFFER</code>, or is <code>INTERLEAVED</code>.
     * @exception IllegalArgumentException if the specified array is
     * non-null and any other coordinate reference is also non-null.
     * @exception ArrayIndexOutOfBoundsException if
     * <code>coords.length < 3 * (initialCoordIndex + validVertexCount)</code>.
     *
     * @exception ArrayIndexOutOfBoundsException if this GeometryArray
     * object is a subclass of IndexedGeometryArray, and any element
     * in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * in the coordinate index array is greater than or equal to the
     * number of vertices defined by the coords array,
     * <code>coords.length / 3</code>.
     *
     * @since Java 3D 1.2
     */
    public void setCoordRefFloat(float[] coords) throws java.rmi.RemoteException;


    /**
     * Gets the float coordinate array reference.
     * @return the current float coordinate array reference.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is not <code>BY_REFERENCE</code>,
     * is <code>USE_NIO_BUFFER</code>, or is <code>INTERLEAVED</code>.
     *
     * @since Java 3D 1.2
     */
    public float[] getCoordRefFloat() throws java.rmi.RemoteException;

    /**
     * @deprecated As of Java 3D version 1.3, use geometry by-copy
     * for Point3f arrays
     *
     * @since Java 3D 1.2
     */
    public Point3f[] getCoordRef3f() throws java.rmi.RemoteException;


    /**
     * Sets the float color array reference to the specified array.
     * The array contains floating-point <i>red</i>, <i>green</i>,
     * <i>blue</i>, and, optionally, <i>alpha</i> values for each
     * vertex (for a total of 3*<i>n</i> or 4*<i>n</i> values, where
     * <i>n</i> is the number of vertices).  Only one of
     * <code>colorRefFloat</code>, <code>colorRefByte</code>,
     * <code>colorRef3f</code>, <code>colorRef4f</code>,
     * <code>colorRef3b</code>, or <code>colorRef4b</code> may be
     * non-null (or they may all be null).  An attempt to set more
     * than one of these attributes to a non-null reference will
     * result in an exception being thrown.  If all color array
     * references are null and colors are enabled (that is, the
     * vertexFormat includes either <code>COLOR_3</code> or
     * <code>COLOR_4</code>), the entire geometry array object is
     * treated as if it were null--any Shape3D or Morph node that uses
     * this geometry array will not be drawn.
     *
     * @param colors an array of 3*<i>n</i> or 4*<i>n</i> values to which a
     * reference will be set.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is not <code>BY_REFERENCE</code>,
     * is <code>USE_NIO_BUFFER</code>, or is <code>INTERLEAVED</code>.
     * @exception IllegalArgumentException if the specified array is
     * non-null and any other color reference is also non-null.
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>COLOR</code> bits are set in the
     * <code>vertexFormat</code>, or if
     * <code>colors.length < </code> <i>num_words</i> <code> *
     * (initialColorIndex + validVertexCount)</code>,
     * where <i>num_words</i> is 3 or 4 depending on the vertex color format.
     *
     * @exception ArrayIndexOutOfBoundsException if this GeometryArray
     * object is a subclass of IndexedGeometryArray, and any element
     * in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * in the color index array is greater than or equal to the
     * number of vertices defined by the colors array,
     * <code>colors.length / </code> <i>num_words</i>.
     *
     * @since Java 3D 1.2
     */
    public void setColorRefFloat(float[] colors) throws java.rmi.RemoteException;

    /**
     * Gets the float color array reference.
     * @return the current float color array reference.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is not <code>BY_REFERENCE</code>,
     * is <code>USE_NIO_BUFFER</code>, or is <code>INTERLEAVED</code>.
     *
     * @since Java 3D 1.2
     */
    public float[] getColorRefFloat() throws java.rmi.RemoteException;

    /**
     * @deprecated As of Java 3D version 1.3, use geometry by-copy
     * for Color3f arrays
     *
     * @since Java 3D 1.2
     */
    public void setColorRef3f(Color3f[] colors) throws java.rmi.RemoteException;


    /**
     * @deprecated As of Java 3D version 1.3, use geometry by-copy
     * for Color3f arrays
     *
     * @since Java 3D 1.2
     */
    public Color3f[] getColorRef3f() throws java.rmi.RemoteException;
    /**
     * Sets the float normal array reference to the specified
     * array.  The array contains floating-point <i>nx</i>, <i>ny</i>,
     * and <i>nz</i> values for each vertex (for a total of 3*<i>n</i>
     * values, where <i>n</i> is the number of vertices).  Only one of
     * <code>normalRefFloat</code> or <code>normalRef3f</code> may be
     * non-null (or they may all be null).  An attempt to set more
     * than one of these attributes to a non-null reference will
     * result in an exception being thrown.  If all normal array
     * references are null and normals are enabled (that is, the
     * vertexFormat includes
     * <code>NORMAL</code>), the entire geometry array object is
     * treated as if it were null--any Shape3D or Morph node that uses
     * this geometry array will not be drawn.
     *
     * @param normals an array of 3*<i>n</i> values to which a
     * reference will be set.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is not <code>BY_REFERENCE</code>,
     * is <code>USE_NIO_BUFFER</code>, or is <code>INTERLEAVED</code>.
     * @exception IllegalArgumentException if the specified array is
     * non-null and any other normal reference is also non-null.
     * @exception ArrayIndexOutOfBoundsException if
     * <code>NORMALS</code> bit is not set in the
     * <code>vertexFormat</code>, or if
     * <code>normals.length < 3 * (initialNormalIndex + validVertexCount)</code>.
     *
     * @exception ArrayIndexOutOfBoundsException if this GeometryArray
     * object is a subclass of IndexedGeometryArray, and any element
     * in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * in the normal index array is greater than or equal to the
     * number of vertices defined by the normals array,
     * <code>normals.length / 3</code>.
     *
     * @since Java 3D 1.2
     */
    public void setNormalRefFloat(float[] normals) throws java.rmi.RemoteException;


    /**
     * Gets the float normal array reference.
     * @return the current float normal array reference.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is not <code>BY_REFERENCE</code>,
     * is <code>USE_NIO_BUFFER</code>, or is <code>INTERLEAVED</code>.
     *
     * @since Java 3D 1.2
     */
    public float[] getNormalRefFloat() throws java.rmi.RemoteException;

    /**
     * @deprecated As of Java 3D version 1.3, use geometry by-copy
     * for Vector3f arrays
     *
     * @since Java 3D 1.2
     */
    public Vector3f[] getNormalRef3f() throws java.rmi.RemoteException;

    /**
     * Sets the float texture coordinate array reference for the specified
     * texture coordinate set to the
     * specified array.  The array contains floating-point <i>s</i>,
     * <i>t</i>, and, optionally, <i>r</i> and <i>q</i> values for each 
     * vertex (for
     * a total of 2*<i>n</i> , 3*<i>n</i> or 4*<i>n</i> values, 
     * where <i>n</i> is
     * the number of vertices).  Only one of
     * <code>texCoordRefFloat</code>, <code>texCoordRef2f</code>, or
     * <code>texCoordRef3f</code> may be non-null (or they may all be
     * null).  An attempt to set more than one of these attributes to
     * a non-null reference will result in an exception being thrown.
     * If all texCoord array references are null and texture
     * coordinates are enabled (that is, the vertexFormat includes
     * <code>TEXTURE_COORDINATE_2</code>,
     * <code>TEXTURE_COORDINATE_3</code>, or
     * <code>TEXTURE_COORDINATE_4</code>), the entire geometry
     * array object is treated as if it were null--any Shape3D or
     * Morph node that uses this geometry array will not be drawn.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param texCoords an array of 2*<i>n</i>, 3*<i>n</i> or
     * 4*<i>n</i> values to
     * which a reference will be set.
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is not <code>BY_REFERENCE</code>,
     * is <code>USE_NIO_BUFFER</code>, or is <code>INTERLEAVED</code>.
     * @exception IllegalArgumentException if the specified array is
     * non-null and any other texCoord reference is also non-null.
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code>, or if texCoordSet is out of range,
     * or if
     * <code>texCoords.length < </code> <i>num_words</i> <code> *
     * (initialTexCoordIndex + validVertexCount)</code>,
     * where <i>num_words</i> is 2, 3, or 4 depending on the vertex
     * texture coordinate format.
     *
     * @exception ArrayIndexOutOfBoundsException if this GeometryArray
     * object is a subclass of IndexedGeometryArray, and any element
     * in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * in the texture coordinate index array is greater than or equal to the
     * number of vertices defined by the texCoords array,
     * <code>texCoords.length / </code> <i>num_words</i>.
     *
     * @since Java 3D 1.2
     */
    public void setTexCoordRefFloat(int texCoordSet, float[] texCoords) throws java.rmi.RemoteException;


    /**
     * Gets the float texture coordinate array reference for the specified
     * texture coordinate set.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     *
     * @return the current float texture coordinate array reference
     * for the specified texture coordinate set
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception IllegalStateException if the data mode for this geometry
     * array object is not <code>BY_REFERENCE</code>,
     * is <code>USE_NIO_BUFFER</code>, or is <code>INTERLEAVED</code>.
     *
     * @exception ArrayIndexOutOfBoundsException if none of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or texCoordSet is out of range.
     *
     * @since Java 3D 1.2
     */
    public float[] getTexCoordRefFloat(int texCoordSet) throws java.rmi.RemoteException;
    /**
     * @deprecated As of Java 3D version 1.3, use geometry by-copy
     * for TexCoord2f arrays
     *
     * @since Java 3D 1.2
     */
    public TexCoord2f[] getTexCoordRef2f(int texCoordSet) throws java.rmi.RemoteException;
    /**
     * Sets the interleaved vertex array reference to the specified
     * array.  The vertex components must be stored in a predetermined
     * order in the array.  The order is: texture coordinates, colors,
     * normals, and positional coordinates.  In the case of texture
     * coordinates, the values for each texture coordinate set
     * are stored in order from 0 through texCoordSetCount-1.  Only those
     * components that are enabled appear in the vertex.  The number
     * of words per vertex depends on which vertex components are
     * enabled.  Texture coordinates, if enabled, use 2 words per
     * texture coordinate set per vertex for
     * <code>TEXTURE_COORDINATE_2</code>, 3 words per texture
     * coordinate set per vertex for
     * <code>TEXTURE_COORDINATE_3</code> or 4 words per texture
     * coordinate set per vertex for
     * <code>TEXTURE_COORDINATE_4</code>.  Colors, if enabled, use 3
     * words per vertex for <code>COLOR_3</code> or 4 words per vertex
     * for <code>COLOR_4</code>.  Normals, if enabled, use 3 words per
     * vertex.  Positional coordinates, which are always enabled, use
     * 3 words per vertex.  For example, the format of interleaved
     * data for a GeometryArray object whose vertexFormat includes
     * <code>COORDINATES</code>, <code>COLOR_3</code>, and
     * <code>NORMALS</code> would be: <i>red</i>, <i>green</i>,
     * <i>blue</i>, <i>Nx</i>, <i>Ny</i>, <i>Nz</i>, <i>x</i>,
     * <i>y</i>, <i>z</i>.  All components of a vertex are stored in
     * adjacent memory locations.  The first component of vertex 0 is
     * stored beginning at index 0 in the array.  The first component
     * of vertex 1 is stored beginning at index
     * <i>words_per_vertex</i> in the array.  The total number of
     * words needed to store <i>n</i> vertices is
     * <i>words_per_vertex</i>*<i>n</i>.
     *
     * @param vertexData an array of vertex values to which a
     * reference will be set.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is not <code>INTERLEAVED</code>
     * or is <code>USE_NIO_BUFFER</code>.
     *
     * @exception ArrayIndexOutOfBoundsException if
     * <code>vertexData.length</code> < <i>words_per_vertex</i> *
     * (<code>initialVertexIndex + validVertexCount</code>),
     * where <i>words_per_vertex</i> depends on which formats are enabled.
     *
     * @exception ArrayIndexOutOfBoundsException if this GeometryArray
     * object is a subclass of IndexedGeometryArray, and any element
     * in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * in the index array associated with any of the enabled vertex
     * components (coord, color, normal, texcoord) is greater than or
     * equal to the number of vertices defined by the vertexData
     * array,
     * <code>vertexData.length / </code> <i>words_per_vertex</i>.
     *
     * @since Java 3D 1.2
     */
    public void setInterleavedVertices(float[] vertexData) throws java.rmi.RemoteException;
    
    /**
     * Gets the interleaved vertices array reference.
     * @return the current interleaved vertices array reference.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalStateException if the data mode for this geometry
     * array object is not <code>INTERLEAVED</code>
     * or is <code>USE_NIO_BUFFER</code>.
     *
     * @since Java 3D 1.2
     */
    public float[] getInterleavedVertices() throws java.rmi.RemoteException;
}
