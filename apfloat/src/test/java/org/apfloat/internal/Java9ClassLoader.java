/*
 * Apfloat arbitrary precision arithmetic library
 * Copyright (C) 2002-2017  Mikko Tommila
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.apfloat.internal;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class loader to load Java 9 specific multi-version classes.
 *
 * @version 1.9.0
 * @author Mikko Tommila
 */

class Java9ClassLoader extends ClassLoader
{
    public Java9ClassLoader(ClassLoader parent)
    {
        super(parent);
    }

    public Class<?> loadJava9Class(String name)
        throws IOException, ClassNotFoundException
    {
        String path = "META-INF/versions/9/" + name.replace('.', '/') + ".class";
        byte[] bytes;
        try (InputStream in = getResourceAsStream(path))
        {
            if (in == null) {
                throw new ClassNotFoundException(name);
            }
            bytes = in.readAllBytes();
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
