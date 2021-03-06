/**
 * Project Looking Glass
 *
 * $RCSfile: OrderedGroupWrapper.java,v $
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
 * $Revision: 1.1 $
 * $Date: 2004-06-23 20:55:37 $
 * $State: Exp $
 */
package org.jdesktop.lg3d.sg.internal.wrapper;

public interface OrderedGroupWrapper extends GroupWrapper {

    public void setChildIndexOrder(int[] childIndexOrder);

    public int[] getChildIndexOrder();

    public void addChild(NodeWrapper child, int[] childIndexOrder);
}
