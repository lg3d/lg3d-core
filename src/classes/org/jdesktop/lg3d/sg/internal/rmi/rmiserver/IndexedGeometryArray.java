/**
 * Project Looking Glass
 *
 * $RCSfile: IndexedGeometryArray.java,v $
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
 * $Date: 2004-06-23 18:51:14 $
 * $State: Exp $
 */
package org.jdesktop.lg3d.sg.internal.rmi.rmiserver;

import javax.vecmath.*;

/**
 * The IndexedGeometryArray object contains separate integer arrays
 * that index into the arrays of positional coordinates, colors,
 * normals, and texture coordinates.  These index arrays specify how
 * vertices are connected to form geometry primitives.  This class is
 * extended to create the various indexed primitive types (e.g.,
 * lines, triangle strips, etc.).
 */

public abstract class IndexedGeometryArray extends GeometryArray implements IndexedGeometryArrayRemote {

    // non-public, no parameter constructor
    IndexedGeometryArray() throws java.rmi.RemoteException {}
    
    /**
     * Constructs an empty IndexedGeometryArray object with the specified
     * number of vertices, vertex format, and number of indices.
     * Defaults are used for all other parameters.  The default values
     * are as follows:
     *
     * <ul>
     * validIndexCount : indexCount<br>
     * initialIndexIndex : 0<br>
     * all index array values : 0<br>
     * </ul>
     *
     * @param vertexCount the number of vertex elements in this
     * IndexedGeometryArray
     * @param vertexFormat a mask indicating which components are
     * present in each vertex.  This is specified as one or more
     * individual flags that are bitwise "OR"ed together to describe
     * the per-vertex data.
     * The flags include: COORDINATES, to signal the inclusion of
     * vertex positions--always present; NORMALS, to signal 
     * the inclusion of per vertex normals; one of COLOR_3,
     * COLOR_4, to signal the inclusion of per vertex
     * colors (without or with color information); one of 
     * TEXTURE_COORDINATE_2, TEXTURE_COORDINATE_3 or
     * TEXTURE_COORDINATE_4, to signal the
     * inclusion of per-vertex texture coordinates 2D, 3D or 4D.
     * @param indexCount the number of indices in this object.  This
     * count is the maximum number of vertices that will be rendered.
     */
    public IndexedGeometryArray(int vertexCount,
				int vertexFormat,
				int indexCount) throws java.rmi.RemoteException {
    }

    /**
     * Constructs an empty IndexedGeometryArray object with the specified
     * number of vertices, vertex format, number of texture coordinate
     * sets, texture coordinate mapping array, and number of indices.
     * Defaults are used for all other parameters.
     *
     * @param vertexCount the number of vertex elements in this
     * IndexedGeometryArray<p>
     *
     * @param vertexFormat a mask indicating which components are
     * present in each vertex.  This is specified as one or more
     * individual flags that are bitwise "OR"ed together to describe
     * the per-vertex data.
     * The flags include: COORDINATES, to signal the inclusion of
     * vertex positions--always present; NORMALS, to signal 
     * the inclusion of per vertex normals; one of COLOR_3,
     * COLOR_4, to signal the inclusion of per vertex
     * colors (without or with color information); one of 
     * TEXTURE_COORDINATE_2, TEXTURE_COORDINATE_3 or TEXTURE_COORDINATE_4,
     * to signal the
     * inclusion of per-vertex texture coordinates 2D , 3D or 4D.<p>
     *
     * @param texCoordSetCount the number of texture coordinate sets
     * in this GeometryArray object.  If <code>vertexFormat</code>
     * does not include one of <code>TEXTURE_COORDINATE_2</code>,
     * <code>TEXTURE_COORDINATE_3</code> or
     * <code>TEXTURE_COORDINATE_4</code>, the
     * <code>texCoordSetCount</code> parameter is not used.<p>
     *
     * @param texCoordSetMap an array that maps texture coordinate
     * sets to texture units.  The array is indexed by texture unit
     * number for each texture unit in the associated Appearance
     * object.  The values in the array specify the texture coordinate
     * set within this GeometryArray object that maps to the
     * corresponding texture
     * unit.  All elements within the array must be less than
     * <code>texCoordSetCount</code>.  A negative value specifies that
     * no texture coordinate set maps to the texture unit
     * corresponding to the index.  If there are more texture units in
     * any associated Appearance object than elements in the mapping
     * array, the extra elements are assumed to be -1.  The same
     * texture coordinate set may be used for more than one texture
     * unit.  Each texture unit in every associated Appearance must
     * have a valid source of texture coordinates: either a
     * non-negative texture coordinate set must be specified in the
     * mapping array or texture coordinate generation must be enabled.
     * Texture coordinate generation will take precedence for those
     * texture units for which a texture coordinate set is specified
     * and texture coordinate generation is enabled.  If
     * <code>vertexFormat</code> does not include one of
     * <code>TEXTURE_COORDINATE_2</code>,
     * <code>TEXTURE_COORDINATE_3</code> or
     * <code>TEXTURE_COORDINATE_4</code>, the
     * <code>texCoordSetMap</code> array is not used.<p>
     *
     * @param indexCount the number of indices in this object.  This
     * count is the maximum number of vertices that will be rendered.
     *
     * @since Java 3D 1.2
     */
    public IndexedGeometryArray(int vertexCount,
				int vertexFormat,
				int texCoordSetCount,
				int[] texCoordSetMap,
				int indexCount) throws java.rmi.RemoteException {
    }

    /**
    * Gets number of indices for this IndexedGeometryArray.
    * @return indexCount the number of indices
    */
    public int getIndexCount() throws java.rmi.RemoteException {
        return ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).getIndexCount();
    }

    /**
     * Sets the valid index count for this IndexedGeometryArray object.
     * This count specifies the number of indexed vertices actually used
     * in rendering or other operations such as picking and collision.
     * This attribute is initialized to <code>indexCount</code>.
     *
     * @param validIndexCount the new valid index count.
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @exception IllegalArgumentException if either of the following is true:
     * <ul>
     * <code>validIndexCount < 0</code>, or<br>
     * <code>initialIndexIndex + validIndexCount > indexCount</code><br>
     * </ul>
     *
     * @exception ArrayIndexOutOfBoundsException if any element in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * in the index array associated with any of the enabled vertex
     * components (coord, color, normal, texcoord) is out of range.
     * An element is out of range if it is less than 0 or is greater
     * than or equal to the number of vertices actually defined for
     * the particular component's array.
     *
     * @since Java 3D 1.3
     */
    public void setValidIndexCount(int validIndexCount) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).setValidIndexCount(validIndexCount);
    }

    /**
     * Gets the valid index count for this IndexedGeometryArray
     * object.  For geometry strip primitives (subclasses of
     * IndexedGeometryStripArray), the valid index count is defined
     * to be the sum of the stripIndexCounts array.
     *
     * @return the current valid index count
     *
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @since Java 3D 1.3
     */
    public int getValidIndexCount() throws java.rmi.RemoteException {
        return ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).getValidIndexCount();
    }

    /**
     * Sets the initial index index for this IndexedGeometryArray object.
     * This index specifies the first index within this indexed geometry
     * array that is actually used in rendering or other operations
     * such as picking and collision.  This attribute is initialized
     * to 0.
     *
     * @param initialIndexIndex the new initial index index.
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     * @exception IllegalArgumentException if either of the following is true:
     * <ul>
     * <code>initialIndexIndex < 0</code>, or<br>
     * <code>initialIndexIndex + validIndexCount > indexCount</code><br>
     * </ul>
     *
     * @exception ArrayIndexOutOfBoundsException if any element in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * in the index array associated with any of the enabled vertex
     * components (coord, color, normal, texcoord) is out of range.
     * An element is out of range if it is less than 0 or is greater
     * than or equal to the number of vertices actually defined for
     * the particular component's array.
     *
     * @since Java 3D 1.3
     */
    public void setInitialIndexIndex(int initialIndexIndex) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).setInitialIndexIndex(initialIndexIndex);
    }

    /**
     * Gets the initial index index for this IndexedGeometryArray object.
     * @return the current initial index index
     * @exception CapabilityNotSetException if the appropriate capability is
     * not set and this object is part of a live or compiled scene graph
     *
     * @since Java 3D 1.3
     */
    public int getInitialIndexIndex() throws java.rmi.RemoteException {
        return ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).getInitialIndexIndex();
    }

    /**
     * This method is not supported for indexed geometry arrays.
     * Indexed primitives use an array of indices to determine how
     * to access the vertex array.
     * The initialIndexIndex attribute can be used to set the starting
     * index within the index arrays.
     *
     * @exception UnsupportedOperationException this method is not supported
     *
     * @since Java 3D 1.3
     */
    public void setInitialVertexIndex(int initialVertexIndex) throws java.rmi.RemoteException {
	throw new UnsupportedOperationException();
    }

    /**
     * This method is not supported for indexed geometry arrays.
     * Indexed primitives use an array of indices to determine how
     * to access the vertex array.
     *
     * @exception UnsupportedOperationException this method is not supported
     *
     * @since Java 3D 1.3
     */
    public void setInitialCoordIndex(int initialCoordIndex) throws java.rmi.RemoteException {
	throw new UnsupportedOperationException();
    }

    /**
     * This method is not supported for indexed geometry arrays.
     * Indexed primitives use an array of indices to determine how
     * to access the vertex array.
     *
     * @exception UnsupportedOperationException this method is not supported
     *
     * @since Java 3D 1.3
     */
    public void setInitialColorIndex(int initialColorIndex) throws java.rmi.RemoteException {
	throw new UnsupportedOperationException();
    }

    /**
     * This method is not supported for indexed geometry arrays.
     * Indexed primitives use an array of indices to determine how
     * to access the vertex array.
     *
     * @exception UnsupportedOperationException this method is not supported
     *
     * @since Java 3D 1.3
     */
    public void setInitialNormalIndex(int initialNormalIndex) throws java.rmi.RemoteException {
	throw new UnsupportedOperationException();
    }

    /**
     * This method is not supported for indexed geometry arrays.
     * Indexed primitives use an array of indices to determine how
     * to access the vertex array.
     *
     * @exception UnsupportedOperationException this method is not supported
     *
     * @since Java 3D 1.3
     */
    public void setInitialTexCoordIndex(int texCoordSet,
					int initialTexCoordIndex) throws java.rmi.RemoteException {
	throw new UnsupportedOperationException();
    }

    /**
     * This method is not supported for indexed geometry arrays.
     * Indexed primitives use an array of indices to determine how
     * to access the vertex array.
     * The validIndexCount attribute can be used to set the number of
     * valid indexed vertices rendered.
     *
     * @exception UnsupportedOperationException this method is not supported
     *
     * @since Java 3D 1.3
     */
    public void setValidVertexCount(int validVertexCount) throws java.rmi.RemoteException {
	throw new UnsupportedOperationException();
    }


    /**
     * Sets the coordinate index associated with the vertex at
     * the specified index for this object.
     * @param index the vertex index
     * @param coordinateIndex the new coordinate index
     *
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if index is less than 0
     * or is greater than or equal to indexCount
     *
     * @exception ArrayIndexOutOfBoundsException if index is in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * and the specified coordinateIndex is out of range.  The
     * coordinateIndex is out of range if it is less than 0 or is
     * greater than or equal to the number of vertices actually
     * defined for the coordinate array.
     */
  public void setCoordinateIndex(int index, int coordinateIndex) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).setCoordinateIndex( index, coordinateIndex );
  }

    /**
     * Sets the coordinate indices associated with the vertices starting at
     * the specified index for this object.
     * @param index the vertex index
     * @param coordinateIndices an array of coordinate indices
     *
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if index is less than 0
     * or is greater than or equal to indexCount
     *
     * @exception ArrayIndexOutOfBoundsException if any element of the
     * coordinateIndices array whose destination position is in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * is out of range.  An element is out of range if it is less than 0
     * or is greater than or equal to the number of vertices actually
     * defined for the coordinate array.
     */
  public void setCoordinateIndices(int index, int coordinateIndices[]) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).setCoordinateIndices( index, coordinateIndices );
  }

    /**
     * Sets the color index associated with the vertex at
     * the specified index for this object.
     * @param index the vertex index
     * @param colorIndex the new color index
     *
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if index is less than 0
     * or is greater than or equal to indexCount
     *
     * @exception ArrayIndexOutOfBoundsException if index is in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * and the specified colorIndex is out of range.  The
     * colorIndex is out of range if it is less than 0 or is
     * greater than or equal to the number of vertices actually
     * defined for the color array.
     */
  public void setColorIndex(int index, int colorIndex) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).setColorIndex( index, colorIndex );
  }

    /**
     * Sets the color indices associated with the vertices starting at
     * the specified index for this object.
     * @param index the vertex index
     * @param colorIndices an array of color indices
     *
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if index is less than 0
     * or is greater than or equal to indexCount
     *
     * @exception ArrayIndexOutOfBoundsException if any element of the
     * colorIndices array whose destination position is in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * is out of range.  An element is out of range if it is less than 0
     * or is greater than or equal to the number of vertices actually
     * defined for the color array.
     */
  public void setColorIndices(int index, int colorIndices[]) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).setColorIndices( index, colorIndices );
  }

    /**
     * Sets the normal index associated with the vertex at
     * the specified index for this object.
     * @param index the vertex index
     * @param normalIndex the new normal index
     *
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if index is less than 0
     * or is greater than or equal to indexCount
     *
     * @exception ArrayIndexOutOfBoundsException if index is in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * and the specified normalIndex is out of range.  The
     * normalIndex is out of range if it is less than 0 or is
     * greater than or equal to the number of vertices actually
     * defined for the normal array.
     */
  public void setNormalIndex(int index, int normalIndex) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).setNormalIndex( index, normalIndex );
  }

    /**
     * Sets the normal indices associated with the vertices starting at
     * the specified index for this object.
     * @param index the vertex index
     * @param normalIndices an array of normal indices
     *
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if index is less than 0
     * or is greater than or equal to indexCount
     *
     * @exception ArrayIndexOutOfBoundsException if any element of the
     * normalIndices array whose destination position is in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * is out of range.  An element is out of range if it is less than 0
     * or is greater than or equal to the number of vertices actually
     * defined for the normal array.
     */
  public void setNormalIndices(int index, int normalIndices[]) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).setNormalIndices( index, normalIndices );
  }

    /**
     * @deprecated As of Java 3D version 1.2, replaced by
     * <code>setTextureCoordinateIndex(int texCoordSet, ...)</code>
     */
    public void setTextureCoordinateIndex(int index, int texCoordIndex) throws java.rmi.RemoteException {
	setTextureCoordinateIndex(0, index, texCoordIndex);
    }

    /**
     * Sets the texture coordinate index associated with the vertex at
     * the specified index in the specified texture coordinate set
     * for this object.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index the vertex index
     * @param texCoordIndex the new texture coordinate index
     *
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if neither of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception ArrayIndexOutOfBoundsException if index is in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * and the specified texCoordIndex is out of range.  The
     * texCoordIndex is out of range if it is less than 0 or is
     * greater than or equal to the number of vertices actually
     * defined for the texture coordinate array.
     *
     * @since Java 3D 1.2
     */
    public void setTextureCoordinateIndex(int texCoordSet,
					  int index,
					  int texCoordIndex) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).setTextureCoordinateIndex( texCoordSet,
					   index,
					   texCoordIndex );
    }

    /**
     * @deprecated As of Java 3D version 1.2, replaced by
     * <code>setTextureCoordinateIndices(int texCoordSet, ...)</code>
     */
    public void setTextureCoordinateIndices(int index, int texCoordIndices[]) throws java.rmi.RemoteException {
	setTextureCoordinateIndices(0, index, texCoordIndices);
    }

    /**
     * Sets the texture coordinate indices associated with the vertices
     * starting at the specified index in the specified texture coordinate set
     * for this object.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index the vertex index
     * @param texCoordIndices an array of texture coordinate indices
     *
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if neither of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @exception ArrayIndexOutOfBoundsException if any element of the
     * texCoordIndices array whose destination position is in the range
     * <code>[initialIndexIndex, initialIndexIndex+validIndexCount-1]</code>
     * is out of range.  An element is out of range if it is less than 0
     * or is greater than or equal to the number of vertices actually
     * defined for the texture coordinate array.
     *
     * @since Java 3D 1.2
     */
    public void setTextureCoordinateIndices(int texCoordSet,
					    int index,
					    int texCoordIndices[]) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).setTextureCoordinateIndices( texCoordSet,
					     index,
					     texCoordIndices );
    }

  /**
   * Retrieves the coordinate index associated with the vertex at
   * the specified index for this object.
   * @param index the vertex index
   * @return the coordinate index
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
   */
  public int getCoordinateIndex(int index) throws java.rmi.RemoteException {
        return ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).getCoordinateIndex( index );
  }

  /**
   * Retrieves the coordinate indices associated with the vertices starting at
   * the specified index for this object.
   * @param index the vertex index
   * @param coordinateIndices array that will receive the coordinate indices
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
   */
  public void getCoordinateIndices(int index, int coordinateIndices[]) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).getCoordinateIndices( index, coordinateIndices );
  }

  /**
   * Retrieves the color index associated with the vertex at
   * the specified index for this object.
   * @param index the vertex index
   * @return the color index
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
   */
  public int getColorIndex(int index) throws java.rmi.RemoteException {
        return ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).getColorIndex( index );
  }

  /**
   * Retrieves the color indices associated with the vertices starting at
   * the specified index for this object. The color indicies are
   * copied into the specified array. The array must be large enough
   * to hold all of the indices.
   * @param index the vertex index
   * @param colorIndices array that will receive the color indices
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
   */
  public void getColorIndices(int index, int colorIndices[]) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).getColorIndices( index, colorIndices );
  }

  /**
   * Retrieves the normal index associated with the vertex at
   * the specified index for this object.
   * @param index the vertex index
   * @return the normal index
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
   */
  public int getNormalIndex(int index) throws java.rmi.RemoteException {
        return ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).getNormalIndex( index );
  }

  /**
   * Retrieves the normal indices associated with the vertices starting at
   * the specified index for this object. The normal indicies are
   * copied into the specified array. The array must be large enough
   * to hold all of the normal indicies.
   * 
   * @param index the vertex index
   * @param normalIndices array that will receive the normal indices
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
   */
  public void getNormalIndices(int index, int normalIndices[]) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).getNormalIndices( index, normalIndices );
  }

    /**
     * @deprecated As of Java 3D version 1.2, replaced by
     * <code>getTextureCoordinateIndex(int texCoordSet, ...)</code>
     */
    public int getTextureCoordinateIndex(int index) throws java.rmi.RemoteException {
	return (getTextureCoordinateIndex(0, index));
    }

    /**
     * Retrieves the texture coordinate index associated with the vertex at
     * the specified index in the specified texture coordinate set
     * for this object.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index the vertex index
     *
     * @return the texture coordinate index
     *
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if neither of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @since Java 3D 1.2
     */
    public int getTextureCoordinateIndex(int texCoordSet, int index) throws java.rmi.RemoteException {
        return ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).getTextureCoordinateIndex( texCoordSet, index );
    }

    /**
     * @deprecated As of Java 3D version 1.2, replaced by
     * <code>getTextureCoordinateIndices(int texCoordSet, ...)</code>
     */
    public void getTextureCoordinateIndices(int index, int texCoordIndices[]) throws java.rmi.RemoteException {
	getTextureCoordinateIndices(0, index, texCoordIndices);
    }


    /**
     * Retrieves the texture coordinate indices associated with the vertices
     * starting at the specified index in the specified texture coordinate set
     * for this object. The texture
     * coordinate indices are copied into the specified array. The array
     * must be large enough to hold all of the indices.
     *
     * @param texCoordSet texture coordinate set in this geometry array
     * @param index the vertex index
     * @param texCoordIndices array that will receive the texture coordinate 
     * indices
     *
     * @exception CapabilityNotSetException if appropriate capability is
     * not set and this object is part of live or compiled scene graph
     *
     * @exception ArrayIndexOutOfBoundsException if neither of the
     * <code>TEXTURE_COORDINATE</code> bits are set in the
     * <code>vertexFormat</code> or if the index or
     * texCoordSet is out of range.
     *
     * @since Java 3D 1.2
     */
    public void getTextureCoordinateIndices(int texCoordSet,
					    int index,
					    int texCoordIndices[]) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.IndexedGeometryArray)wrapped).getTextureCoordinateIndices( texCoordSet,
					     index,
					     texCoordIndices );
    }

}