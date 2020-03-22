/**
 * Project Looking Glass
 *
 * $RCSfile: CursorModule.java,v $
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
 * $Date: 2004-06-23 18:50:49 $
 * $State: Exp $
 */
package org.jdesktop.lg3d.sg.internal.rmi.rmiclient;

/**
 * Interface for the DisplayServer cursor module.
 * 
 */
public interface CursorModule {

    /**
     * Add the cursor to the display
     */
    public void addCursor( BranchGroup cursor );
    
}
