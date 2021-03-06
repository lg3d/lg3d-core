/**
 * Project Looking Glass
 *
 * $RCSfile: Component3DVisualAppearanceEvent.java,v $
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
 * $Date: 2005-04-14 23:05:07 $
 * $State: Exp $
 */
package org.jdesktop.lg3d.wg.event;


/**
 *
 */
public class Component3DVisualAppearanceEvent extends Component3DEvent {
    public enum VisualAppearanceType {NORMAL, HIGHLIGHT, LOWLIGHT, VISUALBELL};
    
    private VisualAppearanceType appearance;
    
    public Component3DVisualAppearanceEvent(VisualAppearanceType appearance) {
        this.appearance = appearance;
    }
    
    public VisualAppearanceType getVisualAppearance() {
        return appearance;
    }
}
