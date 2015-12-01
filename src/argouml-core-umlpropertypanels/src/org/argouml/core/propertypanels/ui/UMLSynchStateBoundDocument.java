/* $Id: UMLSynchStateBoundDocument.java 19907 2012-12-30 13:06:01Z closettop_nightlybuild $
 *****************************************************************************
 * Copyright (c) 2009-2012 Contributors - see below
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    bobtarling
 *****************************************************************************
 *
 * Some portions of this file was previously release using the BSD License:
 */

// Copyright (c) 1996-2006 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.argouml.core.propertypanels.ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import org.argouml.model.Model;

/**
 * The Document/model for the bound of a synch state.
 *
 * @author pepargouml@yahoo.es
 */
class UMLSynchStateBoundDocument extends UMLPlainTextDocument {

    /**
     * The serial version.
     */
    private static final long serialVersionUID = -1391739151659430935L;

    /**
     * Constructor for UMLSynchStateBoundDocument.
     */
    public UMLSynchStateBoundDocument(
            final String propertyName, 
            final Object target) {
        super(propertyName, target);
    }

    /*
     * @see org.argouml.uml.ui.UMLPlainTextDocument#setProperty(java.lang.String)
     */
    protected void setProperty(String text) {
        if (text.equals("")) {
            Model.getStateMachinesHelper().setBound(getTarget(), 0);
        } else {
            Model.getStateMachinesHelper()
                    .setBound(getTarget(), Integer.valueOf(text).intValue());
        }
    }

    /*
     * @see org.argouml.uml.ui.UMLPlainTextDocument#getProperty()
     */
    protected String getProperty() {
        int bound = Model.getFacade().getBound(getTarget());
        if (bound <= 0) {
            return "*";
        } else {
            return String.valueOf(bound);
        }
    }

    /*
     * @see javax.swing.text.Document#insertString(int, java.lang.String,
     *      javax.swing.text.AttributeSet)
     */
    public void insertString(int offset, String str, AttributeSet a)
        throws BadLocationException {
        try {
            // Make sure it's parseable as an number
            Integer.parseInt(str);
            super.insertString(offset, str, a);
        } catch (NumberFormatException e) {
            // ignored - we just skipped inserting it in our document
        }

    }
}
