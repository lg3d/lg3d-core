/*
 * $RCSfile: java_awt_event_InputEvent.h,v $
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
 * $Date: 2004-06-23 03:58:48 $
 * $State: Exp $
 */

/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class java_awt_event_InputEvent */

#ifndef _Included_java_awt_event_InputEvent
#define _Included_java_awt_event_InputEvent
#ifdef __cplusplus
extern "C" {
#endif
#undef java_awt_event_InputEvent_serialVersionUID
#define java_awt_event_InputEvent_serialVersionUID 5516075349620653480LL
#undef java_awt_event_InputEvent_COMPONENT_EVENT_MASK
#define java_awt_event_InputEvent_COMPONENT_EVENT_MASK 1LL
#undef java_awt_event_InputEvent_CONTAINER_EVENT_MASK
#define java_awt_event_InputEvent_CONTAINER_EVENT_MASK 2LL
#undef java_awt_event_InputEvent_FOCUS_EVENT_MASK
#define java_awt_event_InputEvent_FOCUS_EVENT_MASK 4LL
#undef java_awt_event_InputEvent_KEY_EVENT_MASK
#define java_awt_event_InputEvent_KEY_EVENT_MASK 8LL
#undef java_awt_event_InputEvent_MOUSE_EVENT_MASK
#define java_awt_event_InputEvent_MOUSE_EVENT_MASK 16LL
#undef java_awt_event_InputEvent_MOUSE_MOTION_EVENT_MASK
#define java_awt_event_InputEvent_MOUSE_MOTION_EVENT_MASK 32LL
#undef java_awt_event_InputEvent_WINDOW_EVENT_MASK
#define java_awt_event_InputEvent_WINDOW_EVENT_MASK 64LL
#undef java_awt_event_InputEvent_ACTION_EVENT_MASK
#define java_awt_event_InputEvent_ACTION_EVENT_MASK 128LL
#undef java_awt_event_InputEvent_ADJUSTMENT_EVENT_MASK
#define java_awt_event_InputEvent_ADJUSTMENT_EVENT_MASK 256LL
#undef java_awt_event_InputEvent_ITEM_EVENT_MASK
#define java_awt_event_InputEvent_ITEM_EVENT_MASK 512LL
#undef java_awt_event_InputEvent_TEXT_EVENT_MASK
#define java_awt_event_InputEvent_TEXT_EVENT_MASK 1024LL
#undef java_awt_event_InputEvent_INPUT_METHOD_EVENT_MASK
#define java_awt_event_InputEvent_INPUT_METHOD_EVENT_MASK 2048LL
#undef java_awt_event_InputEvent_INPUT_METHODS_ENABLED_MASK
#define java_awt_event_InputEvent_INPUT_METHODS_ENABLED_MASK 4096LL
#undef java_awt_event_InputEvent_PAINT_EVENT_MASK
#define java_awt_event_InputEvent_PAINT_EVENT_MASK 8192LL
#undef java_awt_event_InputEvent_INVOCATION_EVENT_MASK
#define java_awt_event_InputEvent_INVOCATION_EVENT_MASK 16384LL
#undef java_awt_event_InputEvent_HIERARCHY_EVENT_MASK
#define java_awt_event_InputEvent_HIERARCHY_EVENT_MASK 32768LL
#undef java_awt_event_InputEvent_HIERARCHY_BOUNDS_EVENT_MASK
#define java_awt_event_InputEvent_HIERARCHY_BOUNDS_EVENT_MASK 65536LL
#undef java_awt_event_InputEvent_MOUSE_WHEEL_EVENT_MASK
#define java_awt_event_InputEvent_MOUSE_WHEEL_EVENT_MASK 131072LL
#undef java_awt_event_InputEvent_WINDOW_STATE_EVENT_MASK
#define java_awt_event_InputEvent_WINDOW_STATE_EVENT_MASK 262144LL
#undef java_awt_event_InputEvent_WINDOW_FOCUS_EVENT_MASK
#define java_awt_event_InputEvent_WINDOW_FOCUS_EVENT_MASK 524288LL
#undef java_awt_event_InputEvent_RESERVED_ID_MAX
#define java_awt_event_InputEvent_RESERVED_ID_MAX 1999L
#undef java_awt_event_InputEvent_serialVersionUID
#define java_awt_event_InputEvent_serialVersionUID -1825314779160409405LL
#undef java_awt_event_InputEvent_COMPONENT_FIRST
#define java_awt_event_InputEvent_COMPONENT_FIRST 100L
#undef java_awt_event_InputEvent_COMPONENT_LAST
#define java_awt_event_InputEvent_COMPONENT_LAST 103L
#undef java_awt_event_InputEvent_COMPONENT_MOVED
#define java_awt_event_InputEvent_COMPONENT_MOVED 100L
#undef java_awt_event_InputEvent_COMPONENT_RESIZED
#define java_awt_event_InputEvent_COMPONENT_RESIZED 101L
#undef java_awt_event_InputEvent_COMPONENT_SHOWN
#define java_awt_event_InputEvent_COMPONENT_SHOWN 102L
#undef java_awt_event_InputEvent_COMPONENT_HIDDEN
#define java_awt_event_InputEvent_COMPONENT_HIDDEN 103L
#undef java_awt_event_InputEvent_serialVersionUID
#define java_awt_event_InputEvent_serialVersionUID 8101406823902992965LL
#undef java_awt_event_InputEvent_SHIFT_MASK
#define java_awt_event_InputEvent_SHIFT_MASK 1L
#undef java_awt_event_InputEvent_CTRL_MASK
#define java_awt_event_InputEvent_CTRL_MASK 2L
#undef java_awt_event_InputEvent_META_MASK
#define java_awt_event_InputEvent_META_MASK 4L
#undef java_awt_event_InputEvent_ALT_MASK
#define java_awt_event_InputEvent_ALT_MASK 8L
#undef java_awt_event_InputEvent_ALT_GRAPH_MASK
#define java_awt_event_InputEvent_ALT_GRAPH_MASK 32L
#undef java_awt_event_InputEvent_BUTTON1_MASK
#define java_awt_event_InputEvent_BUTTON1_MASK 16L
#undef java_awt_event_InputEvent_BUTTON2_MASK
#define java_awt_event_InputEvent_BUTTON2_MASK 8L
#undef java_awt_event_InputEvent_BUTTON3_MASK
#define java_awt_event_InputEvent_BUTTON3_MASK 4L
#undef java_awt_event_InputEvent_SHIFT_DOWN_MASK
#define java_awt_event_InputEvent_SHIFT_DOWN_MASK 64L
#undef java_awt_event_InputEvent_CTRL_DOWN_MASK
#define java_awt_event_InputEvent_CTRL_DOWN_MASK 128L
#undef java_awt_event_InputEvent_META_DOWN_MASK
#define java_awt_event_InputEvent_META_DOWN_MASK 256L
#undef java_awt_event_InputEvent_ALT_DOWN_MASK
#define java_awt_event_InputEvent_ALT_DOWN_MASK 512L
#undef java_awt_event_InputEvent_BUTTON1_DOWN_MASK
#define java_awt_event_InputEvent_BUTTON1_DOWN_MASK 1024L
#undef java_awt_event_InputEvent_BUTTON2_DOWN_MASK
#define java_awt_event_InputEvent_BUTTON2_DOWN_MASK 2048L
#undef java_awt_event_InputEvent_BUTTON3_DOWN_MASK
#define java_awt_event_InputEvent_BUTTON3_DOWN_MASK 4096L
#undef java_awt_event_InputEvent_ALT_GRAPH_DOWN_MASK
#define java_awt_event_InputEvent_ALT_GRAPH_DOWN_MASK 8192L
#undef java_awt_event_InputEvent_JDK_1_3_MODIFIERS
#define java_awt_event_InputEvent_JDK_1_3_MODIFIERS 63L
#undef java_awt_event_InputEvent_serialVersionUID
#define java_awt_event_InputEvent_serialVersionUID -2482525981698309786LL
/*
 * Class:     java_awt_event_InputEvent
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_java_awt_event_InputEvent_initIDs
  (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif
