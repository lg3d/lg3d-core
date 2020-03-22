/**
 * Project Looking Glass
 *
 * $RCSfile: RotationInterpolator.java,v $
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
 * $Date: 2004-06-23 18:50:29 $
 * $State: Exp $
 */
package org.jdesktop.lg3d.sg;

import org.jdesktop.lg3d.sg.internal.wrapper.*;

/**
 * RotationInterpolator; A rotation behavior node.
 *
 * @version     1.46, 02/04/01 14:58:57
 */

/**
 * Rotation interpolator behavior.  This class defines a behavior
 * that modifies the rotational component of its target TransformGroup
 * by linearly interpolating between a pair of specified angles
 * (using the value generated by the specified Alpha object).
 * The interpolated angle is used to generate a rotation transform
 * about the local Y-axis of this interpolator.
 *
 * @deprecated this is just for testing and will be removed from the api
 */
public class RotationInterpolator extends Leaf {

    float minimumAngle;
    float maximumAngle;
    private Transform3D rotation = new Transform3D();

    
    // non-public, default constructor used by cloneNode
    RotationInterpolator() {
    }

    /**
      * Constructs a trivial rotation interpolator with a specified target,
      * an default axisOfTranform set to identity, a minimum angle of 0.0f, and
      * a maximum angle of 2*pi radians.
      * @param alpha The alpha object for this Interpolator
      * @param target The target for this rotation Interpolator
      */
    public RotationInterpolator(Alpha alpha, TransformGroup target) {
        wrapped = instantiate( SceneGraphSetup.getWrapperPackage()+"RotationInterpolator", 
                               new Class[] {AlphaWrapper.class, TransformGroupWrapper.class}, 
                               new Object[] { alpha.wrapped, target.wrapped } );
    }


    /**
     * Constructs a new rotation interpolator that varies the target
     * transform node's rotational component.
     * @param alpha the alpha generator to use in the rotation computation
     * @param target the TransformGroup node affected by this interpolator
     * @param axisOfTransform the transform that defines the local coordinate
     * system in which this interpolator operates.  The rotation is done
     * about the Y-axis of this local coordinate system.
     * @param minimumAngle the starting angle in radians
     * @param maximumAngle the ending angle in radians
     */
    public RotationInterpolator(Alpha alpha,
				TransformGroup target,
				Transform3D axisOfTransform,
				float minimumAngle,
				float maximumAngle) {
        wrapped = instantiate( SceneGraphSetup.getWrapperPackage()+"RotationInterpolator", 
                               new Class[] {AlphaWrapper.class, TransformGroupWrapper.class, Transform3DWrapper.class, Float.TYPE, Float.TYPE}, 
                               new Object[] { alpha.wrapped, target.wrapped, axisOfTransform.wrapped, new Float(minimumAngle), new Float(maximumAngle) } );
    }
    
    protected void createWrapped() {
    }

    /**
      * This method sets the minimumAngle for this interpolator, in
      * radians.
      * @param angle the new minimal angle
      */
    public void setMinimumAngle(float angle) {
	((RotationInterpolatorWrapper)wrapped).setMinimumAngle( angle );
    }

    /**
      * This method retrieves this interpolator's minimumAngle, in 
      * radians.
      * @return the interpolator's minimal angle value
      */
    public float getMinimumAngle() {
	return ((RotationInterpolatorWrapper)wrapped).getMinimumAngle();
    }

    /**
      * This method sets the maximumAngle for this interpolator, in
      * radians.
      * @param angle the new maximal angle value
      */
    public void setMaximumAngle(float angle) {
	((RotationInterpolatorWrapper)wrapped).setMaximumAngle( angle );
    }

    /**
      * This method retrieves this interpolator's maximumAngle, in 
      * radians.
      * @return the interpolator's maximal angle value
      */
    public float getMaximumAngle() {
	return ((RotationInterpolatorWrapper)wrapped).getMaximumAngle();
    }
    
    public void computeTransform(float alphaValue, Transform3D transform) {
    }
    
    /**
     * Computes the new transform for this interpolator for a given
     * alpha value.
     *
     * @param alphaValue alpha value between 0.0 and 1.0
     * @param transform object that receives the computed transform for
     * the specified alpha value
     *
     * @since Java 3D 1.3
     */
//    public void computeTransform(float alphaValue, Transform3D transform) {
//	double val = (1.0-alphaValue)*minimumAngle + alphaValue*maximumAngle;
//
//	/* construct a Transform3D from:  axis * rotation * axisInverse  */
//	rotation.rotY(val);
//	transform.mul(axis, rotation);
//	transform.mul(transform, axisInverse);
//    }


}
