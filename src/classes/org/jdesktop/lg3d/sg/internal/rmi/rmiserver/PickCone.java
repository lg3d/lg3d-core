/**
 * Project Looking Glass
 *
 * $RCSfile: PickCone.java,v $
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
 * $Date: 2004-06-23 18:51:18 $
 * $State: Exp $
 */
package org.jdesktop.lg3d.sg.internal.rmi.rmiserver;

import javax.vecmath.*;

/**
 * PickCone is the abstract base class of all cone pick shapes.
 *
 * @since Java 3D 1.2
 */
public abstract class PickCone extends PickShape {

    /**
     * Constructs an empty PickCone.
     * The origin and direction of the cone are
     * initialized to (0,0,0).  The spread angle is initialized
     * to <code>PI/64</code>.
     * @deprecated Not in WSG
     */
    PickCone() {
    }

    /**
     * Gets the origin of this PickCone.
     * @param origin the Point3d object into which the origin will be copied.
     */
    public void getOrigin(Point3f origin) {
	((org.jdesktop.lg3d.sg.PickCone)wrapped).getOrigin( origin );
    }
    
    /**
     * Gets the direction of this PickCone.
     * @param direction the Vector3d object into which the direction
     * will be copied.
     */
    public void getDirection(Vector3f direction) {
	((org.jdesktop.lg3d.sg.PickCone)wrapped).getDirection( direction );
    }

    /**
     * Gets the spread angle of this PickCone.
     * @return the spread angle.
     */
    public float getSpreadAngle() {
	return (float)((org.jdesktop.lg3d.sg.PickCone)wrapped).getSpreadAngle();
    }

    /*
     * Return true if shape intersect with bounds.
     * The point of intersection is stored in pickPos.
     */
    abstract boolean intersect(Bounds bounds, Point4d pickPos);
    }
