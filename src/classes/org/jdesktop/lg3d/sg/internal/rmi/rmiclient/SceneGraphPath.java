/**
 * Project Looking Glass
 *
 * $RCSfile: SceneGraphPath.java,v $
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
 * $Date: 2004-06-23 18:50:58 $
 * $State: Exp $
 */
package org.jdesktop.lg3d.sg.internal.rmi.rmiclient;

import  javax.vecmath.Point3d;
import  javax.vecmath.Point4d;

import org.jdesktop.lg3d.sg.internal.rmi.rmiserver.SceneGraphPathRemote;
import org.jdesktop.lg3d.sg.internal.wrapper.*;

/**
 * A SceneGraphPath object represents the path from a Locale to a
 * terminal node in the scene graph.  This path consists of a Locale, a
 * terminal node, and an array of internal nodes that are in the path
 * from the Locale to the terminal node.  The terminal node may be
 * either a Leaf node or a Group node.  A valid SceneGraphPath must
 * uniquely identify a specific instance of the terminal node.  For
 * nodes that are not under a SharedGroup, the minimal SceneGraphPath
 * consists of the Locale and the terminal node itself.  For nodes that
 * are under a SharedGroup, the minimal SceneGraphPath consists of the
 * Locale, the terminal node, and a list of all Link nodes in the path
 * from the Locale to the terminal node.  A SceneGraphPath may optionally
 * contain other interior nodes that are in the path.
 * A SceneGraphPath is verified for correctness and uniqueness when
 * it is sent as an argument to other methods of Java 3D.
 * <p>
 * In the array of internal nodes, the node at index 0 is the node
 * closest to the Locale.  The indices increase along the path to the
 * terminal node, with the node at index length-1 being the node closest
 * to the terminal node.  The array of nodes does not contain either the
 * Locale (which is not a node) or the terminal node.
 * <p>
 * When a SceneGraphPath is returned from the picking or collision
 * methods of Java 3D, it will also contain the value of the
 * LocalToVworld transform of the terminal node that was in effect at
 * the time the pick or collision occurred.
 * Note that ENABLE_PICK_REPORTING and ENABLE_COLLISION_REPORTING are
 * disabled by default.  This means that the picking and collision
 * methods will return the minimal SceneGraphPath by default.
 *
 * @see Node#ENABLE_PICK_REPORTING
 * @see Node#ENABLE_COLLISION_REPORTING
 * @see BranchGroup#pickAll
 * @see BranchGroup#pickAllSorted
 * @see BranchGroup#pickClosest
 * @see BranchGroup#pickAny
 */

public class SceneGraphPath implements SceneGraphPathWrapper {

    SceneGraphPathRemote remote;
    
    Object root = null;
    Node[] interior = null;
    Node item = null;
    Transform3D transform = new Transform3D();

    // Intersect Point for item when picked
    Point3d intersectPoint = new Point3d();

    double pickDistance;                    // distance to pick location

    /**
     * Constructs a SceneGraphPath object with default parameters.
     * The default values are as follows:
     * <ul>
     * root : null<br>
     * object : null<br>
     * list of (interior) nodes : null<br>
     * transform : identity<br>
     * </ul>
     */
    public SceneGraphPath() {
	// Just use defaults
    }

    /**
     * Constructs a new SceneGraphPath object.
     * @param root the Locale object of this path
     * @param object the terminal node of this path
     */
    public SceneGraphPath(Object root, Node  object) {

        this.item = object;
	//this.root = root;
    }

    /**
     * Constructs a new SceneGraphPath object.
     * @param root the Locale object of this path
     * @param nodes an array of node objects in the path from
     * the Locale to the terminal node
     * @param object the terminal node of this path
     */
    public SceneGraphPath(Object root, Node nodes[], Node  object) {

        this.item = object;
	//this.root = root;
	this.interior = new Node[nodes.length];
	for (int i = 0; i < nodes.length; i++)
	    this.interior[i] = nodes[i];
    }


    /**
     * Constructs a new SceneGraphPath object 
     * @param sgp  the SceneGraphPath to copy from
     */
    SceneGraphPath(SceneGraphPath sgp) {
	set(sgp);
    }
    
    SceneGraphPath( javax.media.j3d.SceneGraphPath sgp ) {
//        this.item = (Node)sgp.getObject().getUserData();
//        
//        interior = new Node[ sgp.nodeCount() ];
//        for(int i=0; i<interior.length; i++)
//            interior[i] = (Node)sgp.getNode(i).getUserData();
//        
//        javax.media.j3d.Transform3D j3dT3D = sgp.getTransform();
//        if (j3dT3D!=null)
//            transform.wrapped = j3dT3D;
//        
//        wrapped = sgp;
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Sets this path's values to that of the specified path.
     * @param newPath the SceneGraphPath to copy
     */
    public final void set(SceneGraphPathWrapper newPath) {
//	this.root = newPath.root;
//	this.item = newPath.item;
//	this.transform.set(newPath.transform);
//	if(newPath.interior != null && newPath.interior.length > 0) {
//	    interior = new Node[newPath.interior.length];
//	    for (int i = 0; i < interior.length; i++)
//		this.interior[i] = newPath.interior[i];
//	}
//	else
//	    interior = null;
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Sets this path's Locale to the specified Locale.
     * @param newLocale The new Locale
     */
    public final void setLocale(Object newLocale) {
	//root = newLocale;
    }

    /**
     * Sets this path's terminal node to the specified node object.
     * @param object the new terminal node
     */
    public final void setObject(NodeWrapper  object) {
	    this.item = (Node)object;
    }

    /**
     * Sets this path's node objects to the specified node objects.
     * @param nodes an array of node objects in the path from
     * the Locale to the terminal node
     */
    public final void setNodes(NodeWrapper nodes[]) {
        
	if(nodes != null && nodes.length > 0) {
	    interior = new Node[nodes.length];
	    for (int i = 0; i < nodes.length; i++)
		this.interior[i] = (Node)nodes[i];
	}
	else
	    interior = null;
    }

    /**
     * Replaces the node at the specified index with newNode.
     * @param index the index of the node to replace
     * @param newNode the new node
     * @exception NullPointerException if the node array pointer is null.
     *
     */
    public final void setNode(int index, NodeWrapper newNode) {
      if(interior == null)
	throw new NullPointerException(J3dI18N.getString("SceneGraphPath0"));

      interior[index] = (Node)newNode;
    }

    /**
     * Sets the transform component of this SceneGraphPath to the value of
     * the passed transform.
     * @param trans the transform to be copied. trans should be the 
     * localToVworld matrix of this SceneGraphPath object.
     */
     public final void setTransform(Transform3DWrapper trans) {
	 transform.set((Transform3D)trans);
     }

    /**
      *  Returns a copy of the transform associated with this SceneGraphPath;
      *  returns null if there is no transform associated.
      *  If this SceneGraphPath was returned by a Java 3D picking or
      *  collision method, the local coordinate to virtual world
      *  coordinate transform for this scene graph object at the
      *  time of the pick or collision is recorded.
      *  @return the local to VWorld transform
      */ 
    public final Transform3DWrapper getTransform() {
	return new Transform3D(transform);
    }

    /**
     * Retrieves the path's Locale
     * @return this path's Locale
     */
    public final Object getLocale() {
	return null;
    }

    /**
     * Retrieves the path's terminal node object.
     * @return the terminal node
     */
    public final NodeWrapper getObject() {
	return this.item;
    }

    /**
     * Retrieves the number of nodes in this path.  The number of nodes
     * does not include the Locale or the terminal node object itself.
     * @return a count of the number of nodes in this path
     */
    public final int nodeCount() {
        if(interior == null)
	  return 0;
	return interior.length;
    }

    /**
     * Retrieves the node at the specified index.
     * @param index the index specifying which node to retrieve
     * @return the specified node
     */
    public final NodeWrapper getNode(int index) {
      if(interior == null)
	throw new 
          ArrayIndexOutOfBoundsException(J3dI18N.getString("SceneGraphPath1"));
	return interior[index];
    }

    /**
     * Returns true if all of the data members of path testPath are
     * equal to the corresponding data members in this SceneGraphPath and
     * if the values of the transforms is equal.
     * @param testPath the path we will compare this object's path against.
     * @return  true or false
     */
    public boolean equals(SceneGraphPathWrapper testPath) {
//	boolean result = true;
//        try {
//	  
//	  if(testPath == null || root != testPath.root || item != testPath.item)
//	    return false;
//	  
//	  result = transform.equals(testPath.transform);
//	  
//	  if(result == false)
//	    return false;
//
//	  if(interior == null || testPath.interior == null) {
//	    if(interior != testPath.interior) 
//	      return false;
//	    else 
//	      result = (root == testPath.root && item == testPath.item);
//	    
//	  } else  {
//	    if (interior.length == testPath.interior.length) {
//	      for (int i = 0; i < interior.length; i++)
//		if (interior[i] !=  testPath.interior[i]) {
//		    return false;
//		}
//	    }
//	    else
//		return false;
//	  }
//	  
//        }
//        catch (NullPointerException e2) {return false;}
//	
//        return result;
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Returns true if the Object o1 is of type SceneGraphPath and all of the
     * data members of o1 are equal to the corresponding data members in
     * this SceneGraphPath  and if the values of the transforms is equal.
     * @param o1 the object we will compare this SceneGraphPath's path against.
     * @return  true or false
     */
    public boolean equals(Object o1) {
	boolean result = true;

        try {
	   SceneGraphPath testPath = (SceneGraphPath)o1;
           if(testPath == null || root != testPath.root || item != testPath.item)
	     return false;
	   
	   result = transform.equals(testPath.transform);
	   
	   if(result == false)
	     return false;
	   
	   if(interior == null || testPath.interior == null) {
             if(interior != testPath.interior) 
	       return false;
             else 
	       result = (root == testPath.root && item == testPath.item);
	     
           } else  {
	     if (interior.length == testPath.interior.length) {
	       for (int i = 0; i < interior.length; i++)
		 if (interior[i] !=  testPath.interior[i]) {
		     return false;
		 }
	     }
	     else
		 return false;
           }
	   
 	   return result;
        }
        catch (NullPointerException e2) {return false;}
        catch (ClassCastException   e1) {return false;}
    }
  
  
    /**
     * Returns a hash number based on the data values in this
     * object. Two different SceneGraphPath objects with identical data
     * values (ie, returns true for trans.equals(SceneGraphPath) ) will
     * return the same hash number.  Two Paths with different data members
     * may return the same hash value, although this is not likely.
     * @return the integer hash value
     */
    public int hashCode() {
       //return wrapped.hashCode();
        throw new RuntimeException("Not Implemented");
//        try {
//            return remote.hashCode();
//        } catch( java.rmi.RemoteException rex ) {
//            throw new RuntimeException(rex);
//        }
    }

   /**
     * Determines whether two SceneGraphPath objects represent the same
     * path in the scene graph; either object might include a different 
     * subset of internal nodes; only the internal link nodes, the Locale, 
     * and the Node itself are compared.  The paths are not validated for
     * correctness or uniqueness.
     * @param path  the SceneGraphPath to be compared to this SceneGraphPath
     * @return true or false
     */
    public final boolean isSamePath(SceneGraphPathWrapper testPath) {
         //return wrapped.isSamePath( testPath.wrapped );
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Returns a string representation of this object;
     * the string contains the class names of all Nodes in the SceneGraphPath,
     * the toString() method of any associated user data provided by 
     * SceneGraphObject.getUserData(), and also prints out the transform,
     * if it is not null.
     *  @return String representation of this object
     */
    public String toString() {
        //return wrapped.toString();
        return super.toString();
    }
    
}
