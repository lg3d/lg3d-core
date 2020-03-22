/**
 * Project Looking Glass
 *
 * $RCSfile: Sphere.java,v $
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
 * $Revision: 1.4 $
 * $Date: 2006-05-31 20:58:04 $
 * $State: Exp $
 */
package org.jdesktop.lg3d.utils.shape;

import org.jdesktop.lg3d.sg.*;
import javax.vecmath.*;

/**
 * Sphere is a geometry primitive created with a given radius and resolution.
 * It is centered at the origin.
 * <p>
 * When a texture is applied to a Sphere, it is mapped CCW from the back
 * of the sphere.
 * <p>
 * By default all primitives with the same parameters share their
 * geometry (e.g., you can have 50 shperes in your scene, but the
 * geometry is stored only once). A change to one primitive will
 * effect all shared nodes.  Another implication of this
 * implementation is that the capabilities of the geometry are shared,
 * and once one of the shared nodes is live, the capabilities cannot
 * be set.  Use the GEOMETRY_NOT_SHARED flag if you do not wish to
 * share geometry among primitives with the same parameters.
 */

public class Sphere extends Primitive {

  /**
   * Sphere shape identifier, used by <code>getShape</code>.
   *
   * @see Sphere#getShape
   */
  public static final int BODY = 0;

  static final int MID_REZ_DIV = 16;
  float  radius;
  int    divisions;
  
  /**
   *   Constructs a Sphere of a given radius. Normals are generated
   *   by default, texture coordinates are not. The resolution defaults to  
   *   15 divisions along sphere's axes. Appearance defaults to white.
   *   @param radius Radius
   */
  public Sphere (float radius) {
    this(radius,  GENERATE_NORMALS, MID_REZ_DIV);
  }

  /**  
   *   Constructs a default Sphere of radius of 1.0. Normals are generated
   *   by default, texture coordinates are not.
   *   Resolution defaults to 15 divisions. Appearance defaults to white.
   */
  public Sphere() {
    this(1.0f, GENERATE_NORMALS, MID_REZ_DIV);
  }

  /**  
   *   Constructs a Sphere of a given radius and appearance.
   *   Normals are generated by default, texture coordinates are not.
   *   @param radius Radius
   *   @param appearance Appearance
   */

  public Sphere (float radius, Appearance ap) {
    this(radius, GENERATE_NORMALS, MID_REZ_DIV, ap);
  }

  /**  
   *   Constructs a Sphere of a given radius and appearance with
   *   additional parameters specified by the Primitive flags.
   *   @param radius Radius
   *   @param flags 
   *   @param ap appearance
   */
  public Sphere(float radius, int primflags, Appearance ap) {
    this(radius, primflags, MID_REZ_DIV, ap);
  }

  /**  
   *   Constructs a Sphere of a given radius and number of divisions
   *   with additional parameters specified by the Primitive flags.
   *   Appearance defaults to white.
   *   @param radius Radius
   *   @param divisions Divisions
   *   @param primflags Primflags
   */
  public Sphere(float radius, int primflags, int divisions) {
    this(radius, primflags, divisions, null);
  }


  /**
   * Obtains Sphere's shape node that contains the geometry.
   * This allows users to modify the appearance or geometry.
   * @param partId The part to return (must be BODY for Spheres)
   * @return The Shape3D object associated with the partId.  If an
   * invalid partId is passed in, null is returned.
   */
  public Shape3D getShape(int partId) {
    if (partId != BODY) return null;
//     return (Shape3D)((Group)getChild(0)).getChild(BODY);
    return (Shape3D)getChild(BODY);
  }

  /** Obtains Sphere's shape node that contains the geometry.
   */
  public Shape3D getShape() {
//     return (Shape3D)((Group)getChild(0)).getChild(BODY);
      return (Shape3D)getChild(BODY);
  }

  /** Sets appearance of the Sphere.
   */
  public void setAppearance(Appearance ap) {
//     ((Shape3D)((Group)getChild(0)).getChild(BODY)).setAppearance(ap);
      ((Shape3D)getChild(BODY)).setAppearance(ap);
  }

    /**
     * Gets the appearance of the specified part of the sphere.
     *
     * @param partid identifier for a given subpart of the sphere
     *
     * @return The appearance object associated with the partID.  If an
     * invalid partId is passed in, null is returned.
     *
     * @since Java 3D 1.2.1
     */
    public Appearance getAppearance(int partId) {
	if (partId != BODY) return null;
	return getShape(partId).getAppearance();
    }


  /**  
   *   Constructs a customized Sphere of a given radius, 
   *   number of divisions, and appearance, with additional parameters
   *   specified by the Primitive flags.  The resolution is defined in
   *   terms of number of subdivisions along the sphere's axes. More
   *   divisions lead to more finely tesselated objects. 
   *   <p>
   *   If the appearance is null, the sphere defaults to a white appearance.
   */
  public Sphere(float radius, int primflags, int divisions, Appearance ap) {
    super();

    int sign;
    int n, nstep;

    this.radius = radius;
    this.divisions = divisions;

    /* 
     *     The sphere algorithm evaluates spherical angles along regular
     * units. For each spherical coordinate, (theta, rho), a (x,y,z) is
     * evaluated (along with the normals and texture coordinates).
     * 
     *       The spherical angles theta varies from 0 to 2pi and rho from 0
     * to pi. Sample points depends on the number of divisions.
     */

    flags = primflags;
    
    //Depending on whether normal inward bit is set.
    if ((flags & GENERATE_NORMALS_INWARD) != 0) {
	sign = -1;
    } else {
	sign = 1;
    }

    if (divisions < 4) {
	nstep = 1;
	n = 4;
    } else {
	int mod = divisions % 4;
	if (mod == 0) {
	    n = divisions;
	} else {
	    n = divisions + (4 - mod);
	}
	nstep = n/4;
    }
    

    GeomBuffer cache = getCachedGeometry(Primitive.SPHERE,
					 radius, 0.0f, 0.0f, 
					 divisions, 0, primflags);

    Shape3D shape;

    if (cache != null) {
	shape = new Shape3D(cache.getComputedGeometry());
        shape.setName("Sphere");
	numVerts += cache.getNumVerts();
	numTris += cache.getNumTris();
    } else {
	// buffer size = 8*(1 + 2E{i} + (nstep+1))
	//         where E{i} = sum of i = 2 ... nstep
	GeomBuffer gbuf = new GeomBuffer(8*nstep*(nstep+2));
	
	for (int i=0; i < 4; i++) {
	    buildQuadrant(gbuf, i*Math.PI/2, (i+1)*Math.PI/2, sign, nstep, n, true);
	    buildQuadrant(gbuf, i*Math.PI/2, (i+1)*Math.PI/2, sign, nstep, n, false);
	}

	shape = new Shape3D(gbuf.getGeom(flags));
        shape.setName("Sphere");
	numVerts = gbuf.getNumVerts();
	numTris = gbuf.getNumTris();
	if ((primflags & Primitive.GEOMETRY_NOT_SHARED) == 0) {
	    cacheGeometry(Primitive.SPHERE,
			  radius, 0.0f, 0.0f, 
			  divisions, 0, primflags, gbuf);
	}
    }

    if ((flags & ENABLE_APPEARANCE_MODIFY) != 0) {
	shape.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
	shape.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
    }

    if ((flags & ENABLE_GEOMETRY_PICKING) != 0) {
        shape.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
    }
    
    this.addChild(shape);

    if (ap == null) {
      setAppearance();
    }
    else setAppearance(ap);
  }

    /**
     * Returns the radius of the sphere
     *
     * @since Java 3D 1.2.1
     */
    public float getRadius() {
	return radius;
    }

    /**
     * Returns the number of divisions
     *
     * @since Java 3D 1.2.1
     */
    public int getDivisions() {
	return divisions;
    }

    void buildQuadrant(GeomBuffer gbuf, double startDelta, double endDelta,
		       int sign, int nstep, int n, boolean upperSphere)
    {
	
	double ds, dt, theta, delta;
	int i, j, index, i2;
	double h, r, vx, vz;
	Point3f pt;
	Vector3f norm;
	TexCoord2f texCoord;
	double starth;
	double t;
	boolean leftToRight;
        
        double tcOffset = yUp ? 1.0 : 0.0;  // Texture Coordinate Offset

	if (upperSphere) {
            dt = Math.PI/(2*nstep);
            theta = dt;
            starth = 1;
	    leftToRight = (sign > 0);
        } else {
            dt = -Math.PI/(2*nstep);
            theta = Math.PI + dt;
            starth = -1;
	    leftToRight = (sign < 0);
        }
	

	for (i = 1; i <= nstep; i++) {
	    h = Math.cos(theta);
	    r = Math.sin(theta);
	    if (sign > 0) {
		t = 1 - theta/Math.PI;
	    } else {
		t = theta/Math.PI;
	    }

	    i2 = i << 1;
	    // subdivision decreases towards the pole
	    ds =  (endDelta - startDelta) / i;

	    gbuf.begin(GeomBuffer.TRIANGLE_STRIP);	  

	    if (leftToRight) {
		// Build triangle strips from left to right
		delta =  startDelta;

		for (j=0; j < i; j++) {
		    vx = r*Math.cos(delta);
		    vz = r*Math.sin(delta);

		    gbuf.normal3d( vx*sign, h*sign, vz*sign );
		    gbuf.texCoord2d(0.75 - delta/(2*Math.PI), tcOffset-t);
		    gbuf.vertex3d( vx*radius, h*radius, vz*radius );
		    if (i > 1) {
			// get previous vertex from buffer
			index = gbuf.currVertCnt - i2;
			pt = gbuf.pts[index];
			norm = gbuf.normals[index];
			texCoord = gbuf.tcoords[index];
			// connect with correspondent vertices from previous row
			gbuf.normal3d(norm.x, norm.y, norm.z);
			gbuf.texCoord2d(texCoord.x, texCoord.y);
			gbuf.vertex3d(pt.x, pt.y, pt.z);
		    } else {
			gbuf.normal3d(0, sign*starth, 0);
			if (sign > 0) {
                            if (yUp)
                                gbuf.texCoord2d(0.75 - (startDelta + endDelta)/(4*Math.PI), 
                                                (theta - dt)/Math.PI);
                            else
                                gbuf.texCoord2d(0.75 - (startDelta + endDelta)/(4*Math.PI), 
                                                1.0 - (theta - dt)/Math.PI);
			} else {
                            if (yUp)
                                gbuf.texCoord2d(0.75 - (startDelta + endDelta)/(4*Math.PI), 
                                                1.0 - (theta - dt)/Math.PI);
                            else
                                gbuf.texCoord2d(0.75 - (startDelta + endDelta)/(4*Math.PI), 
                                                (theta - dt)/Math.PI);
			}
                        gbuf.vertex3d( 0, starth*radius, 0); 
			
		    }
		    delta += ds;
		}

		// Put the last vertex in that row,
		// for numerical accuracy we don't use delta
		// compute from above. 
		delta = endDelta;
		vx = r*Math.cos(delta);
		vz = r*Math.sin(delta);
		gbuf.normal3d( vx*sign, h*sign, vz*sign );
		gbuf.texCoord2d(0.75 - delta/(2*Math.PI), tcOffset - t);
		gbuf.vertex3d( vx*radius, h*radius, vz*radius);
	    } else {
		delta =  endDelta;
		// Build triangle strips from right to left
		for (j=i; j > 0; j--) {
		    vx = r*Math.cos(delta);
		    vz = r*Math.sin(delta);
		    
		    gbuf.normal3d( vx*sign, h*sign, vz*sign );
		    // Convert texture coordinate back to one
		    // set in previous version 
		    gbuf.texCoord2d(0.75 - delta/(2*Math.PI), tcOffset - t);
		    gbuf.vertex3d( vx*radius, h*radius, vz*radius );
		    if (i > 1) {
			// get previous vertex from buffer
			index = gbuf.currVertCnt - i2;
			pt = gbuf.pts[index];
			norm = gbuf.normals[index];
			texCoord = gbuf.tcoords[index];
			gbuf.normal3d(norm.x, norm.y, norm.z);
			gbuf.texCoord2d(texCoord.x, texCoord.y);
			gbuf.vertex3d(pt.x, pt.y, pt.z);
		    } else {
			gbuf.normal3d(0, sign*starth, 0);
			if (sign > 0) {
                            if (yUp)
                                gbuf.texCoord2d(0.75 - (startDelta + endDelta)/(4*Math.PI), 
                                                (theta - dt)/Math.PI);			
                            else
                                gbuf.texCoord2d(0.75 - (startDelta + endDelta)/(4*Math.PI), 
                                                1.0 - (theta - dt)/Math.PI);
			} else {
                            if (yUp)
                                gbuf.texCoord2d(0.75 - (startDelta + endDelta)/(4*Math.PI), 
                                                1.0 - (theta - dt)/Math.PI);
                            else
                                gbuf.texCoord2d(0.75 - (startDelta + endDelta)/(4*Math.PI), 
                                                (theta - dt)/Math.PI);			    
			}
                        gbuf.vertex3d( 0, starth*radius, 0);      

		    }
		    delta -= ds;
		}

		// Put the last vertex in that row,
		// for numerical accuracy we don't use delta
		// compute from above. 
		delta = startDelta;
		vx = r*Math.cos(delta);
		vz = r*Math.sin(delta);
		gbuf.normal3d( vx*sign, h*sign, vz*sign );
		gbuf.texCoord2d(0.75 - delta/(2*Math.PI), tcOffset - t);
		gbuf.vertex3d( vx*radius, h*radius, vz*radius );	    

	    } 

	    gbuf.end();

	    if (i < nstep) {
		theta += dt;
	    } else { // take care of numerical imprecision
		theta = Math.PI/2;
	    }
	}

    }
}
