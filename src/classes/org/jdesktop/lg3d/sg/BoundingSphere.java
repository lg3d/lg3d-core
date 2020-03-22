/**
 * Project Looking Glass
 *
 * $RCSfile: BoundingSphere.java,v $
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
 * $Revision: 1.3 $
 * $Date: 2006-06-30 17:38:31 $
 * $State: Exp $
 */
package org.jdesktop.lg3d.sg;

import javax.vecmath.Point3f;
import javax.vecmath.Point3d;

import org.jdesktop.lg3d.sg.internal.wrapper.BoundingSphereWrapper;

/**
 * This class defines a spherical bounding region which is defined by a
 * center point and a radius.
 */

public class BoundingSphere extends Bounds
{
	
    /**
     * Constructs and initializes a BoundingSphere from a center and radius.
     * @param center the center of the bounding sphere
     * @param radius the radius of the bounding sphere
     */
    public BoundingSphere(Point3f center, float radius) {
        setCenter( center );
        setRadius( radius );
    }
    
    public BoundingSphere() {
    }
    
    protected void createWrapped() {
        wrapped = (BoundingSphereWrapper)instantiate( SceneGraphSetup.getWrapperPackage()+"BoundingSphere" );
    }
    
    /**
     * Set the radius of this bounding sphere as a float.
     */
    public void setRadius( float radius ) {
	((BoundingSphereWrapper)wrapped).setRadius( radius );
    }
 
    /**
     * Returns the radius of this bounding sphere as a double.
     * @return the radius of the bounding sphere
     */
    public float getRadius() {
	return (float)((BoundingSphereWrapper)wrapped).getRadius();
    }

    /**
     * Set the position of this bounding sphere as a point.
     * @param center the center Point
     */
    public void setCenter(Point3f center) {
	((BoundingSphereWrapper)wrapped).setCenter( center );
    }

    /**
     * Returns the position of this bounding sphere as a point.
     * @param center a Point to receive the center of the bounding sphere
     */
    public void getCenter(Point3f center) {
	((BoundingSphereWrapper)wrapped).getCenter( center );
    }
    
    /**
     * Sets the value of this BoundingSphere.
     * @param boundsObject another bounds object
     */
    public void set(Bounds  boundsObject){
	((BoundingSphereWrapper)wrapped).set( boundsObject.wrapped );
    }
    
    /**
     * Creates a copy of the bounding sphere.
     * @return a BoundingSphere 
     */
    public Object clone() {
        throw new RuntimeException("Not Implemented");
	//return new BoundingSphere(this.center, this.radius);
    }


    /**
     * Indicates whether the specified <code>bounds</code> object is
     * equal to this BoundingSphere object.  They are equal if the
     * specified <code>bounds</code> object is an instance of
     * BoundingSphere and all of the data
     * members of <code>bounds</code> are equal to the corresponding
     * data members in this BoundingSphere.
     * @param bounds the object with which the comparison is made.
     * @return true if this BoundingSphere is equal to <code>bounds</code>;
     * otherwise false
     *
     * @since Java 3D 1.2
     */
    public boolean equals(Object bounds) {
	return ((BoundingSphereWrapper)wrapped).equals( bounds );
    }


    /**
     * Returns a hash code value for this BoundingSphere object
     * based on the data values in this object.  Two different
     * BoundingSphere objects with identical data values (i.e.,
     * BoundingSphere.equals returns true) will return the same hash
     * code value.  Two BoundingSphere objects with different data
     * members may return the same hash code value, although this is
     * not likely.
     * @return a hash code value for this BoundingSphere object.
     *
     * @since Java 3D 1.2
     */
    public int hashCode() {
	return ((BoundingSphereWrapper)wrapped).hashCode();
    }


    /** 
     * Combines this bounding sphere with a bounding object so that the
     * resulting bounding sphere encloses the original bounding sphere and the
     * given bounds object.
     * @param boundsObject another bounds object
     */
    public void combine(Bounds boundsObject) {
        ((BoundingSphereWrapper)wrapped).combine( boundsObject.wrapped );
    }
    
    /** 
     * Combines this bounding sphere with an array of bounding objects so that the
     * resulting bounding sphere encloses the original bounding sphere and the
     * given array of bounds object.
     * @param boundsObjects an array of bounds objects
     */
    public void combine(Bounds[] boundsObjects) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Modifies the bounding sphere so that it bounds the volume
     * generated by transforming the given bounding object.
     * @param boundsObject the bounding object to be transformed 
     * @param matrix a transformation matrix
     */
    public void transform( Bounds boundsObject, Transform3D matrix) {
	((BoundingSphereWrapper)wrapped).transform( boundsObject.wrapped, matrix.wrapped );
    }

    /** 
     * Transforms this bounding sphere by the given matrix.
     */
    public void transform( Transform3D trans) {
	((BoundingSphereWrapper)wrapped).transform( trans.wrapped );
    }


    /** 
     * Test for intersection with a ray.
     * @param origin the starting point of the ray
     * @param direction the direction of the ray
     * @return true or false indicating if an intersection occured
     */ 
//    public boolean intersect(Point3d origin, Vector3d direction ) {
//
//	return ((javax.media.j3d.BoundingSphere)wrapped).intersect( origin, direction );
//    }

    
    /**
     * Test for intersection with a point.
     * @param point a point defining a position in 3-space
     * @return true or false indicating if an intersection occured
     */ 
//    public boolean intersect(Point3d point ) {
//	return ((javax.media.j3d.BoundingSphere)wrapped).intersect( point );
//    }

    /**
     * Test for intersection with a point.
     * @param point a point defining a position in 3-space
     * @return true or false indicating if an intersection occured
     */ 
    public boolean intersect(Point3f point ) {
	return ((BoundingSphereWrapper)wrapped).intersect( point );
      
    }

    /**
     * Tests whether the bounding sphere is empty.  A bounding sphere is
     * empty if it is null (either by construction or as the result of
     * a null intersection) or if its volume is negative.  A bounding sphere
     * with a volume of zero is <i>not</i> empty.
     * @return true if the bounding sphere is empty;
     * otherwise, it returns false
     */
    public boolean isEmpty() {
	return ((BoundingSphereWrapper)wrapped).isEmpty();
    }

    /**
     * Test for intersection with another bounds object.
     * @param boundsObject another bounds object
     * @return true or false indicating if an intersection occured
     */ 
    public boolean intersect(Bounds boundsObject) {
	return ((BoundingSphereWrapper)wrapped).intersect( boundsObject.wrapped );
    }

    /**
     * Test for intersection with another bounds object.
     * @param boundsObjects an array of bounding objects
     * @return true or false indicating if an intersection occured
     */ 
    public boolean intersect(Bounds[] boundsObjects) {
	throw new RuntimeException("Not Implemented");
    }    

    /** 
     * Finds closest bounding object that intersects this bounding sphere.
     * @param boundsObjects an array of  bounds objects 
     * @return closest bounding object 
     */
    public Bounds closestIntersection( Bounds[] boundsObjects) {

	throw new RuntimeException("Not Implemented");

    }



    /**
     * Returns a string representation of this class.
     */
    public String toString() {
	return ((BoundingSphereWrapper)wrapped).toString();
    
    }
}




