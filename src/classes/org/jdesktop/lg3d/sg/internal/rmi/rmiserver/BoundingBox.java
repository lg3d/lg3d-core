/**
 * Project Looking Glass
 *
 * $RCSfile: BoundingBox.java,v $
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
 * $Date: 2004-06-23 18:51:02 $
 * $State: Exp $
 */
package org.jdesktop.lg3d.sg.internal.rmi.rmiserver;

import javax.vecmath.*;

/**
 *  This class defines an axis aligned bounding box which is used for
 *  bounding regions.
 *
 */

public class BoundingBox extends Bounds implements BoundingBoxRemote {

    /**
     * Constructs and initializes a BoundingBox given min,max in x,y,z.
     * @param lower the "small" corner
     * @param upper the "large" corner
     */
    public BoundingBox(Point3f lower, Point3f upper) throws java.rmi.RemoteException {
        setLower( lower.x, lower.y, lower.z );
        setUpper( upper.x, upper.y, upper.z );
    }
    
    /**
     * Constructs and initializes a 2X bounding box about the
     * origin. The lower corner is initialized to (-1.0d, -1.0d, -1.0d)
     * and the opper corner is initialized to (1.0d, 1.0d, 1.0d).
     */
    public BoundingBox() throws java.rmi.RemoteException {
    }
    
    /**
     * Constructs a BoundingBox from a  bounding object. 
     * @param boundsObject  a bounds object 
     */
    public BoundingBox(Bounds boundsObject) throws java.rmi.RemoteException {
        set( (BoundsRemote)boundsObject );
    }

    /**
     * Constructs a BoundingBox from an array of bounding objects. 
     * @param bounds an array of bounding objects
     */
    public BoundingBox(Bounds[] bounds) throws java.rmi.RemoteException {
        //set( (BoundsRemote)boundsObject );
        throw new RuntimeException("Not implemented");
    }

    protected void createWrapped() {
	wrapped = new org.jdesktop.lg3d.sg.BoundingBox();
    }
    
    /**
     * Gets the lower corner of this bounding box.
     * @param p1 a Point to receive the lower corner of the bounding box
     */
    public void getLower(Point3f p1) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).getLower( p1 );
    }
    
     /**
     * Gets the upper corner of this bounding box.
     * @param p1 a Point to receive the upper corner of the bounding box
     */
    public void getUpper(Point3f p1) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).getUpper( p1 );
    }

    /**
     * Sets the lower corner of this bounding box.
     * @param xmin minimum x value of boundining box
     * @param ymin minimum y value of boundining box
     * @param zmin minimum z value of boundining box
     */
    public void setLower(float xmin, float ymin, float zmin) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).setLower( xmin, ymin, zmin );
    }
    

    /**
     * Sets the upper corner of this bounding box.
     * @param xmax max x value of boundining box
     * @param ymax max y value of boundining box
     * @param zmax max z value of boundining box
     */
    public void setUpper(float xmax, float ymax, float zmax) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).setUpper( xmax, ymax, zmax );
    }


    /**
     * Sets the the value of this BoundingBox
     * @param boundsObject another bounds object
     */
    public void set(BoundsRemote  boundsObject) throws java.rmi.RemoteException {
      ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).set( SGObjectFactoryImpl.getFactoryImpl().getLocal(boundsObject).wrapped );
    }


    /** 
     * Combines this bounding box with a bounding object   so that the
     * resulting bounding box encloses the original bounding box and the
     * specified bounds object.
     * @param boundsObject another bounds object
     */
    public void combine(BoundsRemote boundsObject) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).combine( SGObjectFactoryImpl.getFactoryImpl().getLocal(boundsObject).wrapped );
    }

    /** 
     * Combines this bounding box with an array of bounding objects  
     * so that the resulting bounding box encloses the original bounding 
     * box and the array of bounding objects.
     * @param boundsObjects an array of bounds objects
     */
    public void combine(BoundsRemote[] bounds) throws java.rmi.RemoteException {
        throw new RuntimeException( "Not implented" );
       //((org.jdesktop.lg3d.sg.BoundingBox)wrapped).combine( 
    }

    /**
     * Modifies the bounding box so that it bounds the volume
     * generated by transforming the given bounding object.
     * @param boundsObject the bounding object to be transformed 
     * @param matrix a transformation matrix
     */
    public void transform( BoundsRemote boundsObject, Transform3DRemote matrix) throws java.rmi.RemoteException {
	((org.jdesktop.lg3d.sg.BoundingBox)wrapped).transform( SGObjectFactoryImpl.getFactoryImpl().getLocal(boundsObject).wrapped, SGObjectFactoryImpl.getFactoryImpl().getLocal(matrix).wrapped );
    }

    /** 
     * Transforms this bounding box by the given matrix.
     * @param matrix a transformation matrix
     */
    public void transform(Transform3DRemote matrix) throws java.rmi.RemoteException {
        ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).transform( SGObjectFactoryImpl.getFactoryImpl().getLocal(matrix).wrapped );
    }
    
    /** 
     * Test for intersection with a ray.
     * @param origin the starting point of the ray   
     * @param direction the direction of the ray
     * @return true or false indicating if an intersection occured 
     */
    public boolean intersect(Point3d origin, Vector3d direction ) throws java.rmi.RemoteException {
	
        return ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).intersect( origin, direction );
    }
    
    /** 
     * Test for intersection with a point.
     * @param point a point defining a position in 3-space 
     * @return true or false indicating if an intersection occured 
     */
    public boolean intersect(Point3d point ) throws java.rmi.RemoteException {

        return ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).intersect( point );
    }

    /** 
     * Test for intersection with a point.
     * @param point a point defining a position in 3-space 
     * @return true or false indicating if an intersection occured 
     */
    public boolean intersect(Point3f point ) throws java.rmi.RemoteException {
        
        return ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).intersect( new Point3d(point) );
    }

    /**
     * Tests whether the bounding box is empty.  A bounding box is
     * empty if it is null (either by construction or as the result of
     * a null intersection) or if its volume is negative.  A bounding box
     * with a volume of zero is <i>not</i> empty.
     * @return true if the bounding box is empty; otherwise, it returns false
     */
    public boolean isEmpty() throws java.rmi.RemoteException {

	 return ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).isEmpty();
    }

    /** 
     * Test for intersection with another bounds object. 
     * @param boundsObject another bounds object 
     * @return true or false indicating if an intersection occured 
     */
    public boolean intersect(BoundsRemote boundsObject) throws java.rmi.RemoteException {
            
	return ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).intersect( SGObjectFactoryImpl.getFactoryImpl().getLocal(boundsObject).wrapped );
    }

    /** 
     * Test for intersection with an array of bounds objects.
     * @param boundsObjects an array of bounding objects 
     * @return true or false indicating if an intersection occured 
     */
    public boolean intersect(BoundsRemote[] boundsObjects) throws java.rmi.RemoteException {

	throw new RuntimeException("Not implemented");
    }
    
    /** 
     * Test for intersection with another bounding box.
     * @param boundsObject another bounding object 
     * @param newBoundBox the new bounding box which is the intersection of
     *        the boundsObject and this BoundingBox
     * @return true or false indicating if an intersection occured 
     */
    public boolean intersect(BoundsRemote boundsObject, BoundingBox newBoundBox) throws java.rmi.RemoteException {

	return ((org.jdesktop.lg3d.sg.BoundingBox)wrapped).intersect( SGObjectFactoryImpl.getFactoryImpl().getLocal(boundsObject).wrapped, 
                            (org.jdesktop.lg3d.sg.BoundingBox)newBoundBox.wrapped );
    }

    /** 
     * Test for intersection with an array of  bounds objects.
     * @param boundsObjects an array of  bounds objects 
     * @param newBoundBox the new bounding box which is the intersection of
     *	      the boundsObject and this BoundingBox
     * @return true or false indicating if an intersection occured 
     */
    public boolean intersect(BoundsRemote[] boundsObjects, BoundingBox newBoundBox) throws java.rmi.RemoteException {

       throw new RuntimeException("Not Implemented");
    }


    /** 
     * Finds closest bounding object that intersects this bounding box.
     * @param boundsObjects an array of bounds objects 
     * @return closest bounding object 
     */
    public BoundsRemote closestIntersection( BoundsRemote[] boundsObjects) throws java.rmi.RemoteException {

	throw new RuntimeException("Not Implemented");
    }
    
}      

