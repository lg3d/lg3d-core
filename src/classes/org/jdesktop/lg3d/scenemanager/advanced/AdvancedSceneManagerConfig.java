/**
 * Project Looking Glass
 *
 * $RCSfile: AdvancedSceneManagerConfig.java,v $
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
 * $Date: 2006-05-01 22:03:01 $
 * $State: Exp $
 */

package org.jdesktop.lg3d.scenemanager.advanced;

import org.jdesktop.lg3d.scenemanager.SceneManager;
import org.jdesktop.lg3d.scenemanager.config.SceneManagerConfig;

/**
 *
 * @author  Amir Bukhari
 */
public class AdvancedSceneManagerConfig extends SceneManagerConfig {
    
    /** Creates a new instance of AdvancedSceneManagerConfig */
    public AdvancedSceneManagerConfig() {
    }

    public SceneManager createSceneManager() {
        return new AdvancedSceneManager();
    }
    
}
